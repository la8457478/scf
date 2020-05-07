package com.fkhwl.scf.service.impl;

import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.config.SystemConfig;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.WaybillDTO;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.scf.entity.po.CreditApplyDetail;
import com.fkhwl.scf.entity.po.Project;
import com.fkhwl.scf.entity.po.SubjectClaimsOrder;
import com.fkhwl.scf.entity.po.Waybill;
import com.fkhwl.scf.enums.CreditApplyStatus;
import com.fkhwl.scf.enums.ScfConfigEnum;
import com.fkhwl.scf.redis.RedisCachedBaseService;
import com.fkhwl.scf.service.CounterpartyRepositoryService;
import com.fkhwl.scf.service.CounterpartyService;
import com.fkhwl.scf.service.CreditApplyDetailRepositoryService;
import com.fkhwl.scf.service.CreditApplyService;
import com.fkhwl.scf.service.ProjectRepositoryService;
import com.fkhwl.scf.service.ScfConfigService;
import com.fkhwl.scf.service.SubjectClaimsOrderService;
import com.fkhwl.scf.service.WaybillRepositoryService;
import com.fkhwl.scf.service.WaybillService;
import com.fkhwl.scf.utils.CacheKeyScf;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.WaybillServiceConverterWrapper;
import com.fkhwl.starter.common.enums.DeleteEnum;
import com.fkhwl.starter.core.enums.RandomType;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.core.util.DateUtils;
import com.fkhwl.starter.core.util.Tools;
import com.fkhwl.starter.mybatis.support.Condition;

import org.apache.poi.ss.formula.functions.Count;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class WaybillServiceImpl implements WaybillService {

    private final WaybillRepositoryService waybillRepositoryService;
    private final CreditApplyService creditApplyService;

    private final CounterpartyService counterpartyService;

    private final SubjectClaimsOrderService subjectClaimsOrderService;

    private final CounterpartyRepositoryService counterpartyRepositoryService;

    private final ProjectRepositoryService projectService;
    private final RedisCachedBaseService redisCachedBaseService;
    private final CreditApplyDetailRepositoryService creditApplyDetailService;
    private final ScfConfigService scfConfigService;
    @Override
    public IPage<WaybillDTO> listPage(IPage<WaybillDTO> page, Map<String, Object> params) {
        return waybillRepositoryService.listPage(page, params);
    }

    @Override
    public Optional<WaybillDTO> getDetail(Long id) {
        return Optional.ofNullable(WaybillServiceConverterWrapper.INSTANCE.dto(waybillRepositoryService.getById(id)));
    }

    @Override
    public Long saveOrUpdate(WaybillDTO waybillDTO) {
        Date currentDate = new Date();
        Waybill waybill = WaybillServiceConverterWrapper.INSTANCE.po(waybillDTO);
        waybill.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(waybillDTO.getId())) {
            waybill.setCreateTime(currentDate);
        }
        Waybill old = waybillRepositoryService.getOne(new LambdaQueryWrapper<Waybill>().eq(Waybill::getThirdId, waybillDTO.getThirdId()));
        if (old == null) {
            waybillRepositoryService.saveOrUpdate(waybill);
        } else {
            waybill.setId(old.getId());
            waybillRepositoryService.updateById(waybill);
        }
        waybillDTO.setId(waybill.getId());
        return waybill.getId();
    }

    @Override
    public void delete(Long id) {
        //逻辑删除运单
        waybillRepositoryService.removeById(id);
    }

    @Transactional
    @Override
    public void generateSubjectClaimsOrder(Map<String, Object> params) {
        // todo chenli 2020-03-05 如果一次查询出来数据太多，可能会有性能问题，考虑分批次处理
        List<WaybillDTO> waybills = waybillRepositoryService.listSubjectClaimsOrderWaybill(params);
        AssertUtils.notEmpty(waybills, "暂无未生成应收账款转让的运单数据");
        List<String> lockList = new ArrayList<>();
        boolean isAllLocked = false;

        for (WaybillDTO waybillDTO : waybills) {
            String key = CacheKeyScf.getCreateSorderLockKey(waybillDTO.getWaybillNo());
            Long l1  =System.currentTimeMillis();
            log.error("start :{}",l1);
            isAllLocked = redisCachedBaseService.tryGetDistributedLock(key, waybillDTO.getWaybillNo(), CacheKeyScf.LOCK_TIME);
            log.error("end :{}",System.currentTimeMillis()-l1);
            log.error("generateSubjectClaimsOrderWaybill locked:{},{}", waybillDTO.getWaybillNo(), isAllLocked);
            AssertUtils.isTrue(isAllLocked, "运单处理中");
            lockList.add(key);
        }
        //锁项目Id
        Long projectId = Long.valueOf(params.get("projectId").toString());
        //        String key = CacheKeyScf.getCreateSorderLockKey(projectId);
        Project project = projectService.getById(projectId);
        CounterpartyDTO counterparty = counterpartyService.findInfo(project.getCounterpartyId());
        /** 规定比例 */
        BigDecimal ruleRatio = null == counterparty.getRuleRatio() ? BigDecimal.ZERO : (counterparty.getRuleRatio().divide(new BigDecimal("100")));
        try {
           Integer maxWaybillCount =scfConfigService.getConfigByConfigKey(ScfConfigEnum.MAX_WAYBILL_COUNT_IN_SUBJECT_CLAIMS_ORDER.getCacheKey())!=null? Integer.valueOf(scfConfigService.getConfigByConfigKey(ScfConfigEnum.MAX_WAYBILL_COUNT_IN_SUBJECT_CLAIMS_ORDER.getCacheKey()).getConfigValue()):9999;
            if (waybills.size() <=maxWaybillCount ) {
                //如果小于600个
                //生成
                saveSubitemClaimsOrder(params, waybills, projectId, project, counterparty, ruleRatio);
            } else {
                //如果大于600个 ，则分组处理
                List<List<WaybillDTO>> parts = Lists.partition(waybills, maxWaybillCount);
                parts.stream().forEach(list -> {
                    //合计应转换金额
                    /** 转让金额 */
                    saveSubitemClaimsOrder(params, list, projectId, project, counterparty, ruleRatio);
                });
            }
        } catch (Exception e) {
            log.error("Bad things", e);
            throw e;
        } finally {
            for (String key : lockList) {
                log.error("waybill start del  key:{}",key);
                redisCachedBaseService.del(key);
                log.error("waybill end del  key:{}",key);
            }
        }
    }

    private void saveSubitemClaimsOrder(Map<String, Object> params, List<WaybillDTO> waybills, Long projectId, Project project, CounterpartyDTO counterparty, BigDecimal ruleRatio) {
        BigDecimal transferBalance = waybills.stream() // 将user对象的age取出来map为Bigdecimal
            .map(WaybillDTO::getInvoiceMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        /** 运单ids */
        List<Long> waybillIds = waybills.stream() // 将WaybillDto对象的id取出来为list
            .map(WaybillDTO::getId).collect(Collectors.toList());
        SubjectClaimsOrder subjectClaimsOrder = initSubjectClaimsOrder(project.getCounterpartyId(), waybills, counterparty, transferBalance, ruleRatio);
        subjectClaimsOrder.setProjectId(projectId);
        subjectClaimsOrder.setOwnerId((Long) params.get("ownerId"));
        subjectClaimsOrder.setCreateUserId((Long) params.get("userId"));
        subjectClaimsOrderService.save(subjectClaimsOrder);
        Boolean isNewCreate =Boolean.valueOf(params.get("isNewCreate").toString());
        int result = waybillRepositoryService.updateSubjectClaimsOrderId(isNewCreate,subjectClaimsOrder.getId(), waybillIds, DateUtils.now());
        AssertUtils.isTrue(result>0,"暂无未生成应收账款转让的运单数据");
        //更新项目的应收账款转让单数 +1
        projectService.updateSubitemClaimsOrderCount(projectId, 1);
    }

    /**
     * 初始化标的债券
     * @param counterpartyId
     * @param waybillList
     * @param counterparty
     * @param transferBalance
     * @param ruleRatio
     * @return
     */
    @NotNull
    private SubjectClaimsOrder initSubjectClaimsOrder(Long counterpartyId, List<WaybillDTO> waybillList, CounterpartyDTO counterparty, BigDecimal transferBalance, BigDecimal ruleRatio) {
        SubjectClaimsOrder item = new SubjectClaimsOrder();
        item.setSubjectClaimsOrderNo(DateUtils.format(new Date(), DateUtils.PATTERN_DATE_NO_SEPARATOR) + Tools.random(5, RandomType.INT));
        item.setCounterpartyId(counterpartyId);
        item.setCounterpartyName(counterparty.getCounterpartyName());
        item.setFinancialProductId(1l);
        item.setWaybillCount(waybillList.size());
        item.setTransferBalance(transferBalance);
        item.setCanApplyBalance(transferBalance.multiply(ruleRatio).setScale(BigDecimal.ROUND_HALF_UP, 2));
        item.setDeleted(DeleteEnum.N);
        item.setReviewStatus(0);
        return item;
    }

    @Override
    public void updateByParams(Map<String, Object> params) {
        //判断异常情况
        Long waybillId = Long.valueOf(params.get("waybillId").toString());
        Waybill waybill = waybillRepositoryService.getById(waybillId);
        AssertUtils.isTrue(waybill != null, "该运单不存在");
        AssertUtils.isTrue(waybill.getSubjectClaimsOrderId() != null, "运单未绑定应收账款");
        CreditApplyDetail creditApplyDetail = creditApplyDetailService.getOne(new LambdaQueryWrapper<CreditApplyDetail>().eq(CreditApplyDetail::getSubjectClaimsOrderId,
            waybill.getSubjectClaimsOrderId()));
        AssertUtils.isTrue(waybill.getSubjectClaimsOrderId() != null, "运单未绑定用款申请");
        CreditApplyDTO creditApplyDTO = creditApplyService.getDetail(creditApplyDetail.getCreditApplyId());
        AssertUtils.isTrue(creditApplyDTO.getStatus().equals(CreditApplyStatus.UNDER_OPERATION_REVIEW.getValue()), "该状态不允许标记异常!");

        boolean isLocked = false;
        boolean isCreditApplyLock = false;
        String key = CacheKeyScf.getCreateSorderLockKey(params.get("waybillId"));
        String creditOrderLockKey = CacheKeyScf.getCreditOrderLockKey(creditApplyDetail.getCreditApplyId());
        try {
            isLocked = redisCachedBaseService.tryGetDistributedLock(key, String.valueOf(params.get("waybillId")), CacheKeyScf.LOCK_TIME);
            AssertUtils.isTrue(isLocked, "正在处理中");
            //审核时 锁住用款申请id操作。不允许标记异常
            isCreditApplyLock = redisCachedBaseService.tryGetDistributedLock(creditOrderLockKey, creditApplyDTO.getCreditApplyNo(), CacheKeyScf.LOCK_TIME);
            AssertUtils.isTrue(isCreditApplyLock, "正在处理中");
            waybillRepositoryService.updateByParams(params);
        } catch (Exception e) {
            log.error("Bad things", e);
            throw e;
        } finally {
            if (isLocked) {
                redisCachedBaseService.del(key);
            }
            if (isCreditApplyLock) {
                redisCachedBaseService.del(creditOrderLockKey);
            }
        }
    }

    @Override
    public void reCreateCreditApply(Map<String, Object> params) {
        params.put("isNewCreate",false);
        generateSubjectClaimsOrder(params);
    }

    @Override
    public Map<String, Object> getReStartCreditApplyData(Long creditApplyId, Long counterpartyId) {
        Map<String, Object> result = new HashMap<>();
        result.put("limit", Integer.MAX_VALUE);
        result.put("creditApplyId", creditApplyId);
        //按照审核时间倒序排列
        result.put("searchTimeType", 4);
        result.put("waybillStatus", 0);
        IPage<WaybillDTO> waybillListPage = waybillRepositoryService.listPage(Condition.getPage(result), result);
        AssertUtils.notEmpty(waybillListPage.getRecords(), "暂无正常运单数据!");
        result.clear();

        Counterparty counterparty = counterpartyRepositoryService.getById(counterpartyId);
        AssertUtils.notNull(counterparty, "交易对手不存在");

        // 转让金额
        BigDecimal transferBalance = BigDecimal.ZERO;
        for (WaybillDTO waybill : waybillListPage.getRecords()) {
            transferBalance = transferBalance.add(waybill.getInvoiceMoney());
        }
        result.put("transferBalance", transferBalance);
        // 本次可提
        result.put("canApplyBalance", transferBalance.multiply(counterparty.getRuleRatio()).divide(new BigDecimal("100"), BigDecimal.ROUND_HALF_UP).setScale( 2,BigDecimal.ROUND_HALF_UP));
        result.put("dueDate", DateUtils.plusDays(waybillListPage.getRecords().get(waybillListPage.getRecords().size() - 1)
            .getBillPassTime(), counterparty.getPaymentDays()).getTime());

        return result;
    }

    @Override
    public List<Long> listIdsByParams(Map<String, Object> params) {
        return waybillRepositoryService.listIdsByParams(params);
    }
}
