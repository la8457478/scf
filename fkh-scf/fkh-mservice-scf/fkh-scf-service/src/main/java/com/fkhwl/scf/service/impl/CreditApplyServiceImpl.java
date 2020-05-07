package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ScfUserConfigProvider;
import com.fkhwl.scf.config.ReviewBillConfig;
import com.fkhwl.scf.entity.dto.AuthRoleDTO;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyDetailDTO;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.CreditApplyReviewDTO;
import com.fkhwl.scf.entity.dto.ReviewRecordDTO;
import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;
import com.fkhwl.scf.entity.dto.WaybillDTO;
import com.fkhwl.scf.entity.po.AccountBill;
import com.fkhwl.scf.entity.po.Company;
import com.fkhwl.scf.entity.po.CompanyContract;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.scf.entity.po.CreditApply;
import com.fkhwl.scf.entity.po.CreditApplyDetail;
import com.fkhwl.scf.entity.po.Flow;
import com.fkhwl.scf.entity.po.FlowNode;
import com.fkhwl.scf.entity.po.ReviewRecord;
import com.fkhwl.scf.entity.po.ScfUser;
import com.fkhwl.scf.entity.po.SubjectClaimsOrder;
import com.fkhwl.scf.entity.po.Waybill;
import com.fkhwl.scf.enums.AuthRoleTypeEnum;
import com.fkhwl.scf.enums.CreditApplyStatus;
import com.fkhwl.scf.enums.DueStatus;
import com.fkhwl.scf.enums.RepayStatus;
import com.fkhwl.scf.enums.ScfConfigEnum;
import com.fkhwl.scf.redis.RedisCachedBaseService;
import com.fkhwl.scf.service.AccountBillRepositoryService;
import com.fkhwl.scf.service.AuthRoleService;
import com.fkhwl.scf.service.CompanyContractRepositoryService;
import com.fkhwl.scf.service.CompanyRepositoryService;
import com.fkhwl.scf.service.CounterpartyRepositoryService;
import com.fkhwl.scf.service.CreditApplyDetailRepositoryService;
import com.fkhwl.scf.service.CreditApplyRepositoryService;
import com.fkhwl.scf.service.CreditApplyService;
import com.fkhwl.scf.service.FinancialProductRepositoryService;
import com.fkhwl.scf.service.FlowNodeRepositoryService;
import com.fkhwl.scf.service.FlowRepositoryService;
import com.fkhwl.scf.service.ProjectRepositoryService;
import com.fkhwl.scf.service.ProjectService;
import com.fkhwl.scf.service.ReviewRecordRepositoryService;
import com.fkhwl.scf.service.ScfUserConfigRepositoryService;
import com.fkhwl.scf.service.ScfUserRepositoryService;
import com.fkhwl.scf.service.SubjectClaimsOrderRepositoryService;
import com.fkhwl.scf.service.WaybillRepositoryService;
import com.fkhwl.scf.third.service.AnxinSignService;
import com.fkhwl.scf.utils.CacheKeyScf;
import com.fkhwl.scf.utils.FileUploadType;
import com.fkhwl.scf.utils.ToolsHelper;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.CreditApplyServiceConverterWrapper;
import com.fkhwl.starter.common.enums.DeleteEnum;
import com.fkhwl.starter.core.enums.RandomType;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.core.util.DateUtils;
import com.fkhwl.starter.core.util.Tools;
import com.fkhwl.starter.mybatis.support.Condition;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 * 根据业务需求添加字段
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
@Service
@AllArgsConstructor
@Slf4j
public class CreditApplyServiceImpl implements CreditApplyService {

    private final CreditApplyRepositoryService creditApplyRepositoryService;

    private final SubjectClaimsOrderRepositoryService subjectClaimsOrderRepositoryService;

    private final CreditApplyDetailRepositoryService creditApplyDetailRepositoryService;

    private final FinancialProductRepositoryService financialProductRepositoryService;

    private final CounterpartyRepositoryService counterpartyRepositoryService;
    private final ReviewRecordRepositoryService reviewRecordRepositoryService;

    private final AccountBillRepositoryService accountBillRepositoryService;
    private final CompanyContractRepositoryService companyContractRepositoryService;
    private final ScfUserRepositoryService scfUserRepositoryService;
    private final FlowNodeRepositoryService flowNodeRepositoryService;
    private final FlowRepositoryService flowRepositoryService;
    private final CompanyRepositoryService companyRepositoryService;
    private WaybillRepositoryService waybillRepositoryService;
    private final ScfUserConfigRepositoryService scfUserConfigRepositoryService;
    private final ProjectRepositoryService projectRepositoryService;
    private final AnxinSignService anxinSignService;
    private final ProjectService projectService;
    private final AuthRoleService roleService;
    private final RedisCachedBaseService redisCachedBaseService;
    private final ScfUserConfigProvider scfUserConfigProvider;

    @Override
    public IPage<CreditApplyDTO> listPage(IPage<CreditApplyDTO> page, Map<String, Object> params) {
        return creditApplyRepositoryService.listPage(page, params);
    }

    @Override
    public IPage<CreditApplyListDTO> listCreditApplyPage(IPage<CreditApplyListDTO> page, Map<String, Object> params) {

        if (params.get("roleId") != null) {
            AuthRoleDTO roleDTO = roleService.getDetail(Long.valueOf(params.get("roleId").toString()));
            if (roleDTO != null && roleDTO.getRoleType() >= AuthRoleTypeEnum.FUNDING_DEFAULT.getValue()) {
                //如果不是主账号或特殊账号
                //                if (params.get("isReviewRecord") != null && Boolean.valueOf(params.get
                //                ("isReviewRecord").toString())) {
                //如果是审核记录
                Flow flow = flowRepositoryService.getOne(new LambdaQueryWrapper<Flow>().eq(Flow::getFlowClass,
                    CreditApply.class.getSimpleName()));
                AssertUtils.isTrue(flow != null, "没有可用审批流程");
                //获取该角色可审核的节点
                FlowNode flowNode =
                    flowNodeRepositoryService.getOne(new LambdaQueryWrapper<FlowNode>().eq(FlowNode::getFlowId,
                        flow.getId()).eq(FlowNode::getRoleId, params.get("roleId")));
                if (flowNode != null && !CreditApplyStatus.ALREADY_LOAN.getValue().equals(params.get("status"))) {
                    //放款管理
                    params.put("flowNodeId", flowNode.getId());
                }
                //                } else {
                //如果是运营方审核或客户方
                //                    params.put("flowNodeId", flowNode.getId());

                //                }
            }

        }
        return creditApplyRepositoryService.listCreditApplyPage(page, params);
    }

    @Override
    public CreditApplyDTO getDetail(Long id) {
        return CreditApplyServiceConverterWrapper.INSTANCE.dto(creditApplyRepositoryService.getById(id));
    }

    @Override
    public void saveOrUpdate(CreditApplyDTO creditApplyDTO) {
        Date currentDate = new Date();
        CreditApply creditApply = CreditApplyServiceConverterWrapper.INSTANCE.po(creditApplyDTO);
        creditApply.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(creditApplyDTO.getId())) {
            creditApply.setCreateTime(currentDate);
        }
        creditApplyRepositoryService.saveOrUpdate(creditApply);
        creditApplyDTO.setId(creditApply.getId());
    }

    @Override
    public void delete(Long id) {
        //逻辑删除部门
        creditApplyRepositoryService.removeById(id);
    }

    /**
     * 生成用款申请
     * @param creditApplyDTO        申请转让金额
     * @param subjectClaimsOrderIds 标的债权ids
     */
    @Override
    @Transactional
    public void createCreditApply(CreditApplyDTO creditApplyDTO, String subjectClaimsOrderIds) {

        List<SubjectClaimsOrder> subjectClaimsOrders =
            subjectClaimsOrderRepositoryService.list(new QueryWrapper<SubjectClaimsOrder>().in("id",
                subjectClaimsOrderIds.split(",")));
        AssertUtils.isTrue(!subjectClaimsOrders.isEmpty(), "无可用应收账款转让订单!");

        List<String> lockList = new ArrayList<>();
        boolean isAllLocked = false;
        for (SubjectClaimsOrder waybillDTO : subjectClaimsOrders) {
            String key = CacheKeyScf.getCreateCreditApplyLockKey(waybillDTO.getId());
            isAllLocked = redisCachedBaseService.tryGetDistributedLock(key, waybillDTO.getId().toString(),
                CacheKeyScf.LOCK_TIME);
            log.error("createCreditApply locked:{},{}", waybillDTO.getId(), isAllLocked);
            AssertUtils.isTrue(isAllLocked, "应收账款转让订单处理中");
            lockList.add(key);
        }

        try {
            List<CreditApplyDetailDTO> list =
                creditApplyDetailRepositoryService.getBySubjectClaimsOrderId(subjectClaimsOrders.get(0).getId());
            AssertUtils.isTrue(list == null || list.size() <= 0, "应收账款转让订单已经处理");
            //运单数合计
            Integer waybillCount =
                subjectClaimsOrders.stream().collect(Collectors.summingInt(SubjectClaimsOrder::getWaybillCount));
            //合计应转换金额
            BigDecimal canApplyBalance = subjectClaimsOrders.stream() // 将user对象的age取出来map为Bigdecimal
                .map(SubjectClaimsOrder::getCanApplyBalance).reduce(BigDecimal.ZERO, BigDecimal::add);
            //合计应转换金额
            BigDecimal transferBalance = subjectClaimsOrders.stream() // 将user对象的age取出来map为Bigdecimal
                .map(SubjectClaimsOrder::getTransferBalance).reduce(BigDecimal.ZERO, BigDecimal::add);
            AssertUtils.isTrue(canApplyBalance.compareTo(BigDecimal.ZERO) >= 0, "无可用金额");
            //用款申请编号生成
            String creditApplyNo = DateUtils.format(new Date(), DateUtils.PATTERN_DATE_NO_SEPARATOR) + Tools.random(5
                , RandomType.INT);
            //用款申请数据
            creditApplyDTO.setCreditApplyNo(creditApplyNo);
            creditApplyDTO.setStatus(CreditApplyStatus.UNDER_OPERATION_REVIEW.getValue());
            creditApplyDTO.setWaybillCount(waybillCount);
            SubjectClaimsOrder subjectClaimsOrder1 = subjectClaimsOrders.get(0);
            creditApplyDTO.setCounterpartyId(subjectClaimsOrder1.getCounterpartyId());
            creditApplyDTO.setPassLastFlowNodeId(1D);
            //交易对手
            Counterparty counterparty = counterpartyRepositoryService.getById(creditApplyDTO.getCounterpartyId());
            //获取流程
            Flow flow = flowRepositoryService.getOne(new LambdaQueryWrapper<Flow>().eq(Flow::getCompanyCapitalId,
                counterparty.getCompanyCapitalId()).eq(Flow::getFlowClass,
                CreditApply.class.getSimpleName()));
            AssertUtils.isTrue(flow != null, "没有可用审批流程");
            creditApplyDTO.setFlowId(flow.getId());
            //获取初始节点
            FlowNode flowNode =
                flowNodeRepositoryService.getOne(new LambdaQueryWrapper<FlowNode>().eq(FlowNode::getFlowId,
                    flow.getId()).eq(FlowNode::getStatus, creditApplyDTO.getStatus()));
            AssertUtils.isTrue(flowNode != null, "没有可用审批流程节点");
            creditApplyDTO.setFlowNodeId(flowNode.getId());
            //宽限日和计算
            Integer graceDays = counterparty.getGraceDays();
            //到期日
            Date dueDate = creditApplyDTO.getDueDate();
            AssertUtils.isTrue(DateUtils.toDate(LocalDate.now()).compareTo(dueDate) <= 0, "已经超过应收账款到期日，无法发起申请！");
            //宽限日
            Date graceDate = DateUtils.plusDays(dueDate, graceDays);
            //本次提用后金额
            BigDecimal factRatio = calculateCreditApplyBalance(creditApplyDTO, counterparty, transferBalance);
            creditApplyDTO.setGraceDate(graceDate);
            creditApplyDTO.setCheckedWaybillCount(0);

            CompanyDTO companyDTO = companyRepositoryService.getByOwnerId(creditApplyDTO.getOwnerId());
            AssertUtils.notNull(companyDTO, "借款方公司不存在!");
            ScfUserConfigDTO loanReviewConfig = scfUserConfigProvider.getByConfigKey(ScfConfigEnum.LOAN_REVIEW_CONFIG_CHECK.getCacheKey(),
                companyDTO.getCreateOwnerId());
            if (loanReviewConfig == null || loanReviewConfig.getConfigStatus() == 0) {
                creditApplyDTO.setNeedCheckWaybillCount(0);
            } else {
                creditApplyDTO.setNeedCheckWaybillCount((int) Math.ceil((double)(creditApplyDTO.getWaybillCount() * Integer.valueOf(loanReviewConfig.getConfigValue())) / 100));
            }

            List<Waybill> waybillList = waybillRepositoryService.list(new QueryWrapper<Waybill>().in("subject_claims_order_id",
                    subjectClaimsOrderIds.split(",")));
            AssertUtils.isTrue(!waybillList.isEmpty(), "运单不存在!");

            //初始化合同中所需要的标签内容
            Map<String, String> txtFields = initTextFields(creditApplyDTO, subjectClaimsOrder1, waybillList);

            //生成未签章的合同
            try {
                String pdfPath = anxinSignService.createFileFromTemplate(txtFields, waybillList);
                creditApplyDTO.setPdfPath(pdfPath);
            } catch (Exception e) {
                log.error("Bad things", e);
            }
            this.saveOrUpdate(creditApplyDTO);
            //创建每条债权订单关联的用款申请里的详情数据。
            List<CreditApplyDetail> creditApplyDetails = new ArrayList<>();


            for (SubjectClaimsOrder subjectClaimsOrder :
                subjectClaimsOrders) {
                subjectClaimsOrder.setReviewStatus(CreditApplyStatus.UNDER_OPERATION_REVIEW.getValue());
                BigDecimal dipositBalance = BigDecimal.ZERO;
                Boolean inRuleRatio = Boolean.TRUE;

                CreditApplyDetail creditApplyDetail = new CreditApplyDetail();
                creditApplyDetail.setCreditApplyId(creditApplyDTO.getId());
                creditApplyDetail.setApplyBalance(creditApplyDTO.getApplyBalance());
                creditApplyDetail.setCounterpartyId(subjectClaimsOrder
                    .getCounterpartyId());
                creditApplyDetail.setSubjectClaimsOrderId(subjectClaimsOrder.getId());
                creditApplyDetail.setDipositBalance(dipositBalance);
                creditApplyDetail.setFactRatio(factRatio);
                creditApplyDetail.setInRuleRatio(inRuleRatio);
                creditApplyDetails.add(creditApplyDetail);
                //修改债券金额信息
                //            subjectClaimsOrder.setTransferBalance(subjectClaimsOrder.getTransferBalance().subtract
                //            (transferBalance));
                //本次可提用不变
                //            subjectClaimsOrder.setCanApplyBalance(subjectClaimsOrder.getCanApplyBalance().subtract
                //            (creditApplyDTO.getApplyBalance()));
                //已转余额，本次转之后标的债权剩余
                creditApplyDetail.setRemainTransferBalance(subjectClaimsOrder.getTransferBalance());
            }
            if (null != subjectClaimsOrder1) {
                //项目的未转让运单数应该-1
                Long projectId = subjectClaimsOrder1.getProjectId();
                projectRepositoryService.updateSubitemClaimsOrderCount(projectId,
                    -subjectClaimsOrderIds.split(",").length);
            }
            //更新标的债权
            subjectClaimsOrderRepositoryService.updateBatchById(subjectClaimsOrders);
            //保存用款申请详情数据
            creditApplyDetailRepositoryService.saveBatch(creditApplyDetails);

        } catch (Exception e) {
            log.error("Bad things", e);
            throw e;
        } finally {
            for (String key : lockList) {
                redisCachedBaseService.del(key);
            }
        }
    }

    /**
     * 初始化合同中所需要的标签内容
     * @param creditApplyDTO
     * @param subjectClaimsOrder1
     * @return
     */
    private Map<String, String> initTextFields(CreditApplyDTO creditApplyDTO, SubjectClaimsOrder subjectClaimsOrder1, List<Waybill> waybillList) {
        Map<String, String> txtFields = new HashMap<>();
        Counterparty counterparty = counterpartyRepositoryService.getById(creditApplyDTO.getCounterpartyId());
        Company company = companyRepositoryService.getById(counterparty.getCompanyCapitalId());
        Company company2 = companyRepositoryService.getById(counterparty.getCompanyBorrowerId());
        txtFields.put("credit_apply_no", creditApplyDTO.getCreditApplyNo());
        txtFields.put("credit_apply_no2", creditApplyDTO.getCreditApplyNo());
        txtFields.put("factor_contract_no", counterparty.getFactorContractNo());
        txtFields.put("transfer_balance_contract_no", counterparty.getTransferBalanceContractNo());
        txtFields.put("counterparty_name", counterparty.getCounterpartyName());
        txtFields.put("subitem_limit_balance", counterparty.getSubitemLimitBalance().toString());
        txtFields.put("company_capital_name", company.getCompanyName());
        txtFields.put("company_borrower_name", company2.getCompanyName());
        Calendar validityTime = Calendar.getInstance();
        txtFields.put("curry_year", validityTime.get(Calendar.YEAR) + "");
        txtFields.put("curry_month", (validityTime.get(Calendar.MONTH) + 1) + "");
        txtFields.put("curry_day", validityTime.get(Calendar.DAY_OF_MONTH) + "");
        txtFields.put("apply_balance", creditApplyDTO.getApplyBalance().toString());
        txtFields.put("subitem_used_balance", counterparty.getSubitemUsedBalance().toString());
        txtFields.put("after_this_balance", creditApplyDTO.getAfterThisBalance().toString());
        txtFields.put("base_contract_name", counterparty.getBaseContractName());
        txtFields.put("base_contract_no", counterparty.getBaseContractNo());
        txtFields.put("subject_claims_order_no", subjectClaimsOrder1.getSubjectClaimsOrderNo());
        txtFields.put("waybill_count", String.valueOf(waybillList.size()));
        txtFields.put("due_date", ToolsHelper.formatDate2Str(creditApplyDTO.getDueDate()));
        txtFields.put("grace_date", ToolsHelper.formatDate2Str(creditApplyDTO.getGraceDate()));
        //转让金额
        BigDecimal transferBalance = waybillList.stream().map(Waybill::getInvoiceMoney).reduce(BigDecimal.ZERO, BigDecimal::add);

        txtFields.put("total_invoice_money", transferBalance.toString());
        txtFields.put("total_invoice_money2", transferBalance.toString());

        //账期天数：附件运单明细表中计算使用
        txtFields.put("paymentDays", String.valueOf(counterparty.getPaymentDays()));
//        txtFields.put("ruleRatio", String.valueOf(counterparty.getRuleRatio()));
        return txtFields;
    }

    @Override
    public Map<String, Object> review(Long creditApplyId, Boolean passStatus, String reviewReason, Long userId,
                                      Boolean hasCharge, Long ownerId, Long companyId, String reviewBillUrl) {
        CreditApply creditApply = creditApplyRepositoryService.getById(creditApplyId);

        boolean isLocked = false;
        boolean isCounterpartyLocked = false;
        String key = CacheKeyScf.getCreditOrderLockKey(creditApply.getId());
        String counterpartyKey = CacheKeyScf.getOperatorReviewLockKey(creditApply.getCounterpartyId());
        Map<String, Object> map = null;
        try {
            isLocked = redisCachedBaseService.tryGetDistributedLock(key, creditApply.getCreditApplyNo(),
                CacheKeyScf.LOCK_TIME);

            log.error("creditApplyReview locked:{},{}", creditApply.getId(), isLocked);
            AssertUtils.isTrue(isLocked, "正在处理中");
            Long currentFlowNodeId = creditApply.getFlowNodeId();
            FlowNode currentNode = flowNodeRepositoryService.getById(creditApply.getFlowNodeId());
            AssertUtils.isTrue(currentNode!=null,"审核已被处理或暂无审核权限。");
            String currentflowNodeName = currentNode.getFlowNodeName();

            //        if(true){
            //            Counterparty counterparty=counterpartyRepositoryService.getById(creditApply
            //            .getCounterpartyId());
            //            Company company=companyRepositoryService.getById(counterparty.getCompanyCapitalId());
            //            Company company2=companyRepositoryService.getById(counterparty.getCompanyBorrowerId());
            //            CompanyContract companyContract=companyContractRepositoryService.getById(counterparty
            //            .getCompanyContractId());
            //            //设置合同表单填充值
            //            Map<String, Object> txtFields = getTxtFields(1L, userId, company.getCompanyName(), null);
            //            //批量签署两次
            //            Map<String, Object> resultMap = anxinSignService.multiSign(creditApply.getPdfPath(),
            //            company.getSignAccountId(), company.getSignSealData(), company2.getSignAccountId(),
            //            company2.getSignSealData(), txtFields);
            //
            //            System.out.println(resultMap.size());
            //          /*qVBNM}
            ScfUser scfUser = scfUserRepositoryService.getById(userId);
            //后台判断审核权限
            //        if(nextFlowNode.getRoleId().equals(scfUser.getRoleId())){
            //作废和放款状态和拒绝，无需审核
            AssertUtils.isTrue(!(currentNode.getStatus().equals(CreditApplyStatus.ALREADY_LOAN.getValue()) || creditApply.getStatus().equals(CreditApplyStatus.DESTROYED.getValue()) || creditApply.getStatus().equals(CreditApplyStatus.OPERATION_REVIEW_NO_PASS.getValue())), "审核已被处理或暂无审核权限。");
            AssertUtils.isTrue(currentNode.getRoleId().equals(scfUser.getRoleId()), "审核已被处理或暂无审核权限。");

            //        }
            //是否支付服务费
            if (hasCharge != null) {
                //            if (CreditApplyStatus.UNDER_FINANCIAL_REVIEW.getValue().equals(creditApply.getStatus())) {
                //                //财务审核不通过，支付服务费改为未支付
                //                creditApply.setHasCharge(Boolean.FALSE);
                //            }else{
                creditApply.setHasCharge(hasCharge);
                //            }
            }
            //交易对手
            Counterparty counterparty = counterpartyRepositoryService.getById(creditApply.getCounterpartyId());
            //客户公司合同
            CompanyContract companyContract =
                companyContractRepositoryService.getOne(new LambdaQueryWrapper<CompanyContract>().eq(CompanyContract::getCompanyCapitalId, companyId).eq(CompanyContract::getCompanyBorrowerId,
                    counterparty.getCompanyBorrowerId()));
            AssertUtils.isTrue(companyContract != null, "客户公司不存在!");
            if (passStatus) {
                AssertUtils.isTrue(DateUtils.toDate(LocalDate.now()).compareTo(creditApply.getDueDate()) <= 0,
                    "已经超过应收账款到期日，无法审核。");
                //查询有无异常运单
                Map<String, Object> params = new HashMap<>();
                params.put("creditApplyId", creditApplyId);
                params.put("waybillStatus", -1);
                List<WaybillDTO> waybills = waybillRepositoryService.listSubjectClaimsOrderWaybill(params);
                //异常运单
                //            List<WaybillDTO> errorWaybills = waybills.stream().filter(waybillDTO -> waybillDTO
                //            .getWaybillStatus()==-1).collect(Collectors.toList());
                AssertUtils.isTrue(waybills.isEmpty(), "有异常运单，无法审核通过!");
                //审核运单
                //            List<WaybillDTO> normalWaybills = waybills.stream().filter(waybillDTO -> waybillDTO
                //            .getWaybillStatus()==1).collect(Collectors.toList());
                //            Project project=   projectRepositoryService.getById(normalWaybills.get(0).getProjectId());
                //获取当前节点
                //审核通过
                FlowNode nextFlowNode = flowNodeRepositoryService.getNextNode(currentFlowNodeId);
                AssertUtils.isTrue(nextFlowNode!=null,"审核已被处理或暂无审核权限。");
                creditApply.setStatus(nextFlowNode.getStatus());
                creditApply.setFlowNodeId(nextFlowNode.getId());
                if (creditApply.getPassLastFlowNodeId() == null || nextFlowNode.getId() > creditApply.getPassLastFlowNodeId()) {
                    creditApply.setPassLastFlowNodeId(Double.valueOf(nextFlowNode.getId()));
                }

                if (CreditApplyStatus.UNDER_CASHIER_LOAN.getValue().equals(creditApply.getStatus())) {
                        map = generateReviewBill(creditApply, counterparty, companyContract, reviewReason, scfUser);
                        creditApply.setReviewBillUrl(map.get("fileName").toString());

                } else if (CreditApplyStatus.ALREADY_LOAN.getValue().equals(creditApply.getStatus())) {
                    validBalance(creditApply, counterparty, companyContract);

                    //放款审核 需要设置生效和放款金额
                    creditApply.setLoanBalance(creditApply.getApplyBalance());
                    creditApply.setLoanTime(DateUtils.now());
                    //如果生效，则生成账单
                    saveAccountBill(creditApply, counterparty);
                    try {
                        map = generateReviewBill(creditApply, counterparty, companyContract, reviewReason, scfUser);
                        creditApply.setReviewBillUrl(map.get("fileName").toString());
                    } catch (IOException e) {
                        log.error("not find reviewBiLL path", e);
                    }
                    // 出纳审核通过后，需要对合同进行盖章
                    Company company = companyRepositoryService.getById(counterparty.getCompanyCapitalId());
                    Company company2 = companyRepositoryService.getById(counterparty.getCompanyBorrowerId());
                    //设置合同表单填充值
                    Map<String, Object> txtFields = getTxtFields(1L, userId, company.getCompanyName(), null);
                    //批量签署两次
                    Map<String, Object> resultMap = anxinSignService.multiSign(creditApply.getPdfPath(),
                        company.getSignAccountId(), company.getSignSealData(), company2.getSignAccountId(),
                        company2.getSignSealData(), txtFields);
                    if (resultMap != null && resultMap.get("filePath") != null) {
                        String signPdfPath = (String) resultMap.get("filePath");
                        creditApply.setSignPdfPath(signPdfPath);
                    } else {
                    }

                    //已使用分项额度 增加
                    counterparty.setSubitemUsedBalance(counterparty.getSubitemUsedBalance().add(creditApply.getApplyBalance()));
                    //累计放款 增加
                    counterparty.setTotalLendingBalance(counterparty.getTotalLendingBalance().add(creditApply.getApplyBalance()));
                    //剩余分项额度 减少
                    counterparty.setSubitemRemainBalance(counterparty.getSubitemRemainBalance().subtract(creditApply.getApplyBalance()));
                    //已转应收账款 增加
                    counterparty.setHadReceivableBalance(counterparty.getHadReceivableBalance().add(creditApply.getTransferBalance()));

                    counterparty.setUpdateTime(DateUtils.now());

                    //已放款累计
                    companyContract.setLoanSuccessBalance(companyContract.getLoanSuccessBalance().add(creditApply.getApplyBalance()));
                    //可用额度
                    companyContract.setRemainingBalance(companyContract.getRemainingBalance().subtract(creditApply.getApplyBalance()));
                    //已提用融资
                    companyContract.setNeedReturnBalance(companyContract.getNeedReturnBalance().add(creditApply.getApplyBalance()));
                    int counterpartyCount =   counterpartyRepositoryService.updateLoanBalance(counterparty.getId(),
                        creditApply.getApplyBalance(), creditApply.getTransferBalance());
                    int companyContractCount = companyContractRepositoryService.updateLoanBalance(companyContract.getId(),
                        creditApply.getApplyBalance());
                    AssertUtils.isTrue(counterpartyCount>0,"交易对手可用余额不够，无法通过，请联系相关人员!");
                    AssertUtils.isTrue(companyContractCount>0,"客户可用余额不够，无法通过，请联系相关人员!");

                } else if (CreditApplyStatus.UNDER_RISK_CONTROL_REVIEW.getValue().equals(creditApply.getStatus()) && currentNode.getStatus().equals(CreditApplyStatus.UNDER_OPERATION_REVIEW.getValue())) {
                    //运营审核时要加锁
                    isCounterpartyLocked = redisCachedBaseService.tryGetDistributedLock(counterpartyKey, creditApply.getCounterpartyId().toString(),
                        CacheKeyScf.LOCK_TIME);
                    log.error("creditApplyReview locked:{},{}", creditApply.getId(), isLocked);
                    AssertUtils.isTrue(isCounterpartyLocked, "正在处理中");

                    //如果是运营审核通过-》风险一审核。 1.需检验交易对手剩余金额是否足够 2.运营审核通过后，需要对合同进行盖章
                    //                1.需检验交易对手剩余金额是否足够
                    validBalance(creditApply, counterparty, companyContract);
                    Map<String, Object> queryP = new HashMap<>();
                    queryP.put("counterpartyId", creditApply.getCounterpartyId());
                    queryP.put("status", ">1");
                    Map<String, Object> result = creditApplyRepositoryService.sumBalance(queryP);
                    BigDecimal sumApplyBalance = result != null && result.get("sumApplyBalance") != null ?
                        new BigDecimal(result.get("sumApplyBalance").toString()) : BigDecimal.ZERO;
                    AssertUtils.isTrue(counterparty.getSubitemRemainBalance().subtract(sumApplyBalance).compareTo(creditApply.getApplyBalance()) >= 0, "交易对手可用余额不够，无法通过，请联系相关人员!");
                    AssertUtils.isTrue(companyContract.getRemainingBalance().subtract(sumApplyBalance).compareTo(creditApply.getApplyBalance()) >= 0, "客户可用余额不够，无法通过，请联系相关人员!");

                }
            } else {
                //拒绝
                if (CreditApplyStatus.UNDER_OPERATION_REVIEW.getValue().equals(creditApply.getStatus())) {
                    //运营审核不通过，会还原成应收账款。意思就是不通过的用款申请无法使用了，但是需要保留申请和审核记录。如果还需要用款得重新再次发起用款申请。
                    creditApply.setStatus(CreditApplyStatus.OPERATION_REVIEW_NO_PASS.getValue());
                    handleOperationReviewNoPass(creditApply);
                } //拒绝
                else {
                    ///拒绝
                    //获取前一个节点
                    FlowNode nextPrevNode = flowNodeRepositoryService.getPrevNode(currentFlowNodeId);
                    AssertUtils.isTrue(nextPrevNode!=null,"审核已被处理或暂无审核权限。");
                    //设置status
                    creditApply.setStatus(nextPrevNode.getStatus());
                    creditApply.setFlowNodeId(nextPrevNode.getId());
                }
                if (!creditApply.getPassLastFlowNodeId().toString().contains(".5")) {
                    //如果已经拒绝过，则不再修改数据。
                    creditApply.setPassLastFlowNodeId(new BigDecimal(currentNode.getId().toString()).add(new BigDecimal("0.5")).doubleValue());

                }
            }
            //审核前状态:具体流程  1.运营审核 2风控 3管理层 4财务
            //        Integer process = creditApply.getStatus();
            //审核记录存储
            ReviewRecord reviewRecord = new ReviewRecord();
            reviewRecord.setOwnerId(ownerId);
            reviewRecord.setCreateUserId(userId);
            reviewRecord.setReviewReason(reviewReason);
            reviewRecord.setBusinessId(creditApplyId);
            reviewRecord.setReviewResult(creditApply.getStatus());
            reviewRecord.setPassStatus(passStatus);
            //        reviewRecord.setReviewProcess(process);
            reviewRecord.setFlowNodeName(currentflowNodeName);
            //审批流程公用的字段设置
            reviewRecord.setBusinessId(creditApplyId);
            reviewRecord.setFlowId(creditApply.getFlowId());
            reviewRecord.setFlowNodeId(creditApply.getFlowNodeId());
            reviewRecordRepositoryService.saveOrUpdate(reviewRecord);
            creditApply.setUpdateTime(DateUtils.now());
            creditApplyRepositoryService.updateById(creditApply);

            return map;
        } catch (IOException e) {
            log.error("not find reviewBiLL path", e);
        } catch (Exception e) {
            log.error("Bad things", e);
            throw e;
        } finally {
            if (isLocked) {
                redisCachedBaseService.del(key);
            }
            if (isCounterpartyLocked) {
                redisCachedBaseService.del(counterpartyKey);
            }
        }
        return map;
    }

    private void validBalance(CreditApply creditApply, Counterparty counterparty, CompanyContract companyContract) {
        BigDecimal subitemRemainBalance = counterparty.getSubitemRemainBalance();
        BigDecimal remainingBalance = companyContract.getRemainingBalance();
        AssertUtils.isTrue(subitemRemainBalance.compareTo(creditApply.getApplyBalance()) >= 0,
            "交易对手可用余额不够，无法通过，请联系相关人员!");
        AssertUtils.isTrue(remainingBalance.compareTo(creditApply.getApplyBalance()) >= 0, "客户可用余额不够，无法通过，请联系相关人员!");
    }

    private Map<String, Object> getTxtFields(Long projectId, Long userId, String companyName, Date signTime) {
        Map<String, Object> txtFields = new HashMap<>();
        txtFields.put("projectId", projectId);
        txtFields.put("userId", userId);
        //再次写入签约主体公司名称，以防派车时因没有签约主体导致此栏为空
        txtFields.put("companyName", companyName);
        Calendar validityTime = Calendar.getInstance();
        //可能是后来补的签章，时间需要与驾驶员确认时间一致
        //这时已经有SignTime时间了。
        if (signTime != null) {
            validityTime.setTime(signTime);
        }
        txtFields.put("year", validityTime.get(Calendar.YEAR));
        txtFields.put("month", (validityTime.get(Calendar.MONTH) + 1));
        txtFields.put("day", validityTime.get(Calendar.DAY_OF_MONTH));
        return txtFields;
    }

    public Map<String, Object> generateReviewBill(CreditApply creditApply, Counterparty counterparty,
                                                  CompanyContract companyContract, String reviewReason,
                                                  ScfUser scfUser) throws IOException {
        Workbook workbook;
        String sourceFilePath = ReviewBillConfig.getReviewBillFilePath();
        log.error("reviewBillPath:{}", sourceFilePath);
        if (sourceFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(new FileInputStream(sourceFilePath));
        } else {
            workbook = new XSSFWorkbook(new FileInputStream(sourceFilePath));
        }
        //风控部和管理层副总 审核意见
        Map<String, Object> params = new HashMap<>();
        params.put("creditApplyId", creditApply.getId());
        params.put("passStatus", true);
        List<ReviewRecordDTO> reviewRecordDTOS = reviewRecordRepositoryService.listReviewHistory(params);

        Sheet sheet = workbook.getSheetAt(0);
        //放款申请编号一行
        Row row3 = sheet.getRow(3);
        Cell creditApplyNoCell = row3.getCell(0);
        String creditApplyNo = creditApply.getCreditApplyNo();
        creditApplyNoCell.setCellValue("放款申请编号：" + creditApplyNo);
        //日期
        Cell dateCell = row3.getCell(3);
        //风控一审核时间
        List<ReviewRecordDTO> riskRecord =
            reviewRecordDTOS.stream().filter(reviewRecordDTO -> reviewRecordDTO.getFlowNodeName().equals("风控经理审核")).collect(Collectors.toList());
        LocalDateTime date = DateUtils.fromDate(riskRecord.get(0).getCreateTime());
        dateCell.setCellValue("日期：" + date.getYear() + "年" + date.getMonthValue() + "月" + date.getDayOfMonth() + "日");
        //单位 固定的
        //项目名称一行  固定叫保理
        Row row4 = sheet.getRow(4);
        //        String projectName = project.getProjectName();
        //        Cell projectNameCell = row4.getCell(1);
        //        projectNameCell.setCellValue(projectName);
        //收款单位
        Cell companyBorrowerCell = row4.getCell(5);
        String companyBorrowerName = companyContract.getCompanyBorrowerName();
        companyBorrowerCell.setCellValue(companyBorrowerName);
        //经办部门一行
        Row row5 = sheet.getRow(5);
        Cell deptmentCell = row5.getCell(1);
        String departmentName = companyContract.getDepartmentName();
        deptmentCell.setCellValue(departmentName);
        //开户银行
        Cell bankNameCell = row5.getCell(5);
        String bankName = companyContract.getBankName();
        bankNameCell.setCellValue(bankName);
        //经办人
        Row row6 = sheet.getRow(6);
        Cell managerCell = row6.getCell(1);
        String projectMgName = companyContract.getProjectMgName();
        managerCell.setCellValue(projectMgName);
        Cell bankNoCell = row6.getCell(5);

        bankNoCell.setCellValue(companyContract.getBankAccountNo());
        //支付方式
        //已付金额
        //申请金额
        Row row9 = sheet.getRow(9);
        Cell applyBalanceCellUp = row9.getCell(1);
        Cell applyBalanceCell = row9.getCell(7);
        BigDecimal applyBalance = creditApply.getApplyBalance();
        String applyBalanceUp = com.fkhwl.scf.utils.Tools.digitCapital(applyBalance.doubleValue());
        applyBalanceCellUp.setCellValue(applyBalanceUp);
        applyBalanceCell.setCellValue(creditApply.getApplyBalance().toString() + "元");
        //付款事由
        String reason = "按照成都聚源商业保理有限责任公司评审决议书规定（{contractNumber}），{companyBorrowerName" +
            "}公司与成都聚源商业保理有限责任公司签订的《国内保理业务合同》(合同号：{factorContractNo})" +
            "已达到放款条件，现申请于{year}年{month}月{day}日通过{bankName}支行向{companyBorrowerName}公司发放该本合同项下基本收购款￥{applyBalance" +
            "}元（大写：人民币{applyBalanceUp}）。";
        reason = reason.replaceAll("\\{contractNumber}", companyContract.getContractNumber() == null ? "" :
            companyContract.getContractNumber());
        reason = reason.replaceAll("\\{companyBorrowerName}", companyBorrowerName);
        reason = reason.replaceAll("\\{factorContractNo}", counterparty.getFactorContractNo() == null ? "" :
            counterparty.getFactorContractNo());
        reason = reason.replaceAll("\\{year}", "");
        reason = reason.replaceAll("\\{month}", "");
        reason = reason.replaceAll("\\{day}", "");
        reason = reason.replaceAll("\\{bankName}", companyContract.getBranchBankName() == null ? "" :
            companyContract.getBranchBankName());
        reason = reason.replaceAll("\\{applyBalance}", applyBalance.toString());
        reason = reason.replaceAll("\\{applyBalanceUp}", applyBalanceUp);
        Row row11 = sheet.getRow(10);
        Cell reasonCell = row11.getCell(1);
        reasonCell.setCellValue(reason);
        //经办人意见
        Row row15 = sheet.getRow(15);
        Cell manageOpinionCell = row15.getCell(1);
        manageOpinionCell.setCellValue(projectMgName);
        //证明人
        String projectSecondMgName = companyContract.getProjectSecondMgName();
        Cell projectSecondMgNameCell = row15.getCell(7);
        projectSecondMgNameCell.setCellValue(projectSecondMgName);
        //部门负责人意见行
        Row row16 = sheet.getRow(16);
        Cell departmentOpinion = row16.getCell(1);
        String departmentManager = companyContract.getDepartmentManager();
        departmentOpinion.setCellValue(departmentManager);

        //FIXME: 2020/3/31 写死用flow_node_name判断流程，不严谨。
        List<ReviewRecordDTO> riskManagerRecord =
            reviewRecordDTOS.stream().filter(reviewRecordDTO -> reviewRecordDTO.getFlowNodeName().equals("风控部长审核")).collect(Collectors.toList());
        List<ReviewRecordDTO> finacialRecord =
            reviewRecordDTOS.stream().filter(reviewRecordDTO -> reviewRecordDTO.getFlowNodeName().equals("财务经理审核")).collect(Collectors.toList());
        List<ReviewRecordDTO> finacialManagerRecord =
            reviewRecordDTOS.stream().filter(reviewRecordDTO -> reviewRecordDTO.getFlowNodeName().equals("财务部长审核")).collect(Collectors.toList());
        List<ReviewRecordDTO> managerOneRecord =
            reviewRecordDTOS.stream().filter(reviewRecordDTO -> reviewRecordDTO.getFlowNodeName().equals("管理层副总经理审核")).collect(Collectors.toList());
        List<ReviewRecordDTO> managerTwoRecord =
            reviewRecordDTOS.stream().filter(reviewRecordDTO -> reviewRecordDTO.getFlowNodeName().equals("管理层总经理审核")).collect(Collectors.toList());
        List<ReviewRecordDTO> managerThreeRecord =
            reviewRecordDTOS.stream().filter(reviewRecordDTO -> reviewRecordDTO.getFlowNodeName().equals("管理层执行董事审核")).collect(Collectors.toList());
        //        List<ReviewRecordDTO>  cashierRecord= reviewRecordDTOS.stream().filter(reviewRecordDTO ->
        //        reviewRecordDTO.getFlowNodeName().equals("出纳")).collect(Collectors.toList());
        Cell riskAndMgmtOneCell = row16.getCell(5);
        //风控部
        String riskName = riskRecord.isEmpty() ? "     " : riskRecord.get(0).getCreateUserName();
        //管理层副总
        String managerOneName = managerOneRecord.isEmpty() ? "     " : managerOneRecord.get(0).getCreateUserName();
        riskAndMgmtOneCell.setCellValue("风控部:" + riskName + "、" + riskManagerRecord.get(0).getCreateUserName() + "\n " +
            "  管理层副总:" + managerOneName);
        //主办会计审验
        Row row18 = sheet.getRow(18);
        Cell finacialCell = row18.getCell(1);
        String finaicalOpinion = finacialRecord.isEmpty() ? "**" : finacialRecord.get(0).getReviewReason();
        String finacialName = finacialRecord.isEmpty() ? "**" : finacialRecord.get(0).getCreateUserName();
        finacialCell.setCellValue("符合支付标准   会计:" + finacialName + "，" + finaicalOpinion);
        //财务部负责人
        Row row19 = sheet.getRow(19);
        Cell finacialManagerOpinionCell = row19.getCell(1);
        String finacialManagerOpinion = finacialManagerRecord.isEmpty() ? "**" :
            finacialManagerRecord.get(0).getReviewReason();
        String finacialManagerName = finacialManagerRecord.isEmpty() ? "**" :
            finacialManagerRecord.get(0).getCreateUserName();
        finacialManagerOpinionCell.setCellValue("建议支付");
        Cell finacialManagerNameCell = row19.getCell(2);
        finacialManagerNameCell.setCellValue("财务经理 \n" + finacialManagerName);
        //财务总监
        Cell finacialLastCell = row19.getCell(6);
        finacialLastCell.setCellValue("建议支付   财务总监:" + finacialManagerName);
        //经营责任人审批
        Row row20 = sheet.getRow(20);
        Cell managerTwoCell = row20.getCell(1);
        String managerTwoOpinion = managerTwoRecord.isEmpty() ? "    " : managerTwoRecord.get(0).getReviewReason();
        String managerTwoName = managerTwoRecord.isEmpty() ? "    " : managerTwoRecord.get(0).getCreateUserName();
        managerTwoCell.setCellValue("金额超过授权，提请董事长审批 \n   管理层总经理:" + managerTwoName);
        //董事长审批
        Row row21 = sheet.getRow(21);
        Cell managerThreeCell = row21.getCell(1);
        //如果是执行董事审核，生成审批单是当前处理人的名字
        managerThreeCell.setCellValue("管理层执行董事:" + scfUser.getNickName() + "," + reviewReason + "\n 集团董事长：      ");
        if (CreditApplyStatus.ALREADY_LOAN.getValue().equals(creditApply.getStatus())) {
        //如果是出纳审核，则取 管理层执行董事名字
            managerThreeCell.setCellValue("管理层执行董事:" + managerThreeRecord.get(0).getCreateUserName() + "," + managerThreeRecord.get(0).getReviewReason() + "\n 集团董事长：      ");
            //出纳办结情况
            Row row22 = sheet.getRow(22);
            Cell cashierCell = row22.getCell(1);
            cashierCell.setCellValue(reviewReason);
            LocalDateTime cnTime = DateUtils.fromDate(creditApply.getUpdateTime());
            reason = reason.replaceAll("\\{year}", String.valueOf(cnTime.getYear()));
            reason = reason.replaceAll("\\{month}", String.valueOf(cnTime.getMonthValue()));
            reason = reason.replaceAll("\\{day}", String.valueOf(cnTime.getDayOfMonth()));
            //事由
            reasonCell.setCellValue(reason);
        }
        //说明里面需要的数据
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        byte[] excelBytes = bos.toByteArray();
        Map<String, Object> map = new HashMap<>();
        map.put("excelBytes", excelBytes);
        map.put("fileName", System.currentTimeMillis() + "_" + creditApplyNo + "_" + FileUploadType.REVIEW_BILL +
            "_reviewBill.xlsx");
        return map;
    }

    /**
     * 生效生成账单
     * @param creditApply
     * @param counterparty
     * @return: void
     * @Author: liuan
     * @Date: 2020/3/13 18:32
     */
    private void saveAccountBill(CreditApply creditApply, Counterparty counterparty) {
        //获取当前节点
        //用款申请下的运单数量
        Integer waybillCount = creditApply.getWaybillCount();
        //生成账单
        String billNo = DateUtils.format(new Date(), DateUtils.PATTERN_DATE_NO_SEPARATOR) + Tools.random(5,
            RandomType.INT);
        AccountBill accountBill = new AccountBill();
        accountBill.setOwnerId(creditApply.getOwnerId());
        accountBill.setCreateUserId(creditApply.getCreateUserId());
        accountBill.setBillNo(billNo);
        accountBill.setCreditApplyId(creditApply.getId());
        accountBill.setBillBalance(creditApply.getApplyBalance());
        //还款金额
        accountBill.setRepayBalance(BigDecimal.ZERO);
        accountBill.setRepayStatus(RepayStatus.NOT_REPAY.getValue());
        if (DateUtils.toDate(LocalDate.now()).compareTo(creditApply.getDueDate()) > 0) {
            //如果放款时已过了到期日
            accountBill.setDueStatus(DueStatus.ALREADY_DUE.getValue());
        } else if (DateUtils.toDate(LocalDate.now()).compareTo(creditApply.getDueDate()) > 0) {
            //如果放款时已过了宽限期
            accountBill.setDueStatus(DueStatus.ALREADY_OVERDUE.getValue());
        } else {
            accountBill.setDueStatus(DueStatus.NOT_DUE.getValue());
        }
        accountBill.setRemainRepayBalance(creditApply.getApplyBalance().add(creditApply.getInterestBalance() == null
            ? BigDecimal.ZERO : creditApply.getInterestBalance()));
        accountBill.setRepayDate(creditApply.getDueDate());
        accountBill.setBillDate(DateUtils.now());
        accountBill.setWaybillCount(waybillCount);
        accountBill.setGraceDate(creditApply.getGraceDate());
        accountBill.setGraceRate(counterparty.getGraceRate());
        accountBill.setOverdueRate(counterparty.getOverdueRate());
        accountBill.setInterestRate(counterparty.getInterestRate());
        accountBill.setManageRate(counterparty.getManageRate());
        accountBill.setPaymentDays(counterparty.getPaymentDays());
        accountBill.setGraceDays(counterparty.getGraceDays());
        accountBill.setManageRate(counterparty.getManageRate());
        accountBill.setCounterpartyId(counterparty.getId());
        //保存账单信息
        accountBillRepositoryService.save(accountBill);
    }

    @Override
    public CreditApplyReviewDTO getReviewDetail(Long creditApplyId, Long companyCapitalId) {
        CreditApplyReviewDTO creditApplyReviewDTO = creditApplyRepositoryService.getReviewDetail(creditApplyId,
            companyCapitalId);
        FlowNode flowNode = flowNodeRepositoryService.getById(creditApplyReviewDTO.getFlowNodeId());
        creditApplyReviewDTO.setRoleId(flowNode.getRoleId());
        //是否有逾期的账单 1.交易对手下 有逾期且未还款的账单
        int dueCount =
            accountBillRepositoryService.count(new LambdaQueryWrapper<AccountBill>().eq(AccountBill::getCounterpartyId, creditApplyReviewDTO.getCounterpartyId()).ne(AccountBill::getRepayStatus, RepayStatus.ALREADY_REPAY.getValue()).lt(AccountBill::getGraceDate,
            DateUtils.toDate(LocalDate.now())));
        creditApplyReviewDTO.setHasDue(dueCount > 0);
        //实际融资比例 =本次提用后融资额度/（已转让金额-已还转让金额+本次转让金额）
        Map<String, Object> params = new HashMap<>();
        params.put("counterpartyId", creditApplyReviewDTO.getId());
        params.put("status", CreditApplyStatus.ALREADY_LOAN.getValue());

        //        Map<String, Object> map = creditApplyRepositoryService.sumBalance(params);
        //        BigDecimal sumTransferBalance = map == null || map.get("sumTransferBalance") == null ? BigDecimal
        //        .ZERO : new BigDecimal(map.get("sumTransferBalance").toString());
        //本次提用后金额
        //        BigDecimal afterThisBalance = creditApplyReviewDTO.getTotalLendingBalance().subtract
        //        (creditApplyReviewDTO.getReturnedBalance()).add(creditApplyReviewDTO.getApplyBalance());
        //实际融资比例
        //        BigDecimal factRatio = afterThisBalance.divide(sumTransferBalance.add(creditApplyReviewDTO
        //        .getTransferBalance()), 4, BigDecimal.ROUND_HALF_UP);
        //        creditApplyReviewDTO.setAfterThisBalance(afterThisBalance);
        //        creditApplyReviewDTO.setFactRatio(factRatio);
        return creditApplyReviewDTO;
    }

    /**
     * 处理运营审核不通过：会还原成应收账款。意思就是不通过的用款申请无法使用了，但是需要保留申请和审核记录。如果还需要用款得重新再次发起用款申请。
     * @param creditApply 用款申请信息
     */
    private void handleOperationReviewNoPass(CreditApply creditApply) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", Integer.MAX_VALUE);
        params.put("creditApplyId", creditApply.getId());
        IPage<CreditApplyDetailDTO> creditApplyDetailPage =
            creditApplyDetailRepositoryService.listPage(Condition.getPage(params), params);
        List<CreditApplyDetailDTO> creditApplyDetails = creditApplyDetailPage.getRecords();
        AssertUtils.notEmpty(creditApplyDetails, "放款申请数据不存在!");

        //应收款转让
        List<SubjectClaimsOrder> subjectClaimsOrders = new ArrayList<>(creditApplyDetails.size());
        Date updateTime = DateUtils.now();
        for (CreditApplyDetailDTO creditApplyDetail : creditApplyDetails) {
            SubjectClaimsOrder subjectClaimsOrder =
                subjectClaimsOrderRepositoryService.getById(creditApplyDetail.getSubjectClaimsOrderId());
            AssertUtils.notNull(subjectClaimsOrder, "应收转让数据不存在!");
            subjectClaimsOrder.setCanApplyBalance(subjectClaimsOrder.getCanApplyBalance().add(creditApply.getApplyBalance()));
            subjectClaimsOrder.setUpdateTime(updateTime);
            // todo chenli 2020-03-22 需要考虑是否为应收款转让订单的第一次发起审核
            subjectClaimsOrder.setReviewStatus(CreditApplyStatus.OPERATION_REVIEW_NO_PASS.getValue());
            subjectClaimsOrders.add(subjectClaimsOrder);
        }

        //更新应收款转让为：审核不通过
        subjectClaimsOrderRepositoryService.updateBatchById(subjectClaimsOrders);
    }

    @Transactional
    @Override
    public void reCreateCreditApply(CreditApplyDTO creditApplyDTO) {
        //修改“已拒绝”的用款申请为“已作废”
        CreditApply refusedCreditApply = creditApplyRepositoryService.getById(creditApplyDTO.getId());
        AssertUtils.notNull(refusedCreditApply, "用款申请数据不存在");

        boolean isLocked = false;
        String key = CacheKeyScf.getCreditOrderLockKey(refusedCreditApply.getId());
        try {
            isLocked = redisCachedBaseService.tryGetDistributedLock(key, refusedCreditApply.getCreditApplyNo(),
                CacheKeyScf.LOCK_TIME);
            log.error("reCreateCreditApply locked:{},{}", refusedCreditApply.getId(), isLocked);
            AssertUtils.isTrue(isLocked, "正在处理中");
            refusedCreditApply.setStatus(CreditApplyStatus.DESTROYED.getValue());
            creditApplyRepositoryService.updateById(refusedCreditApply);

            //#1.生成应收款转让
            Map<String, Object> params = new HashMap<>();
            params.put("limit", Integer.MAX_VALUE);
            params.put("creditApplyId", creditApplyDTO.getId());
            //按照审核时间倒序排列
            params.put("searchTimeType", 4);
            params.put("waybillStatus", 0);
            List<WaybillDTO> waybills = waybillRepositoryService.listSubjectClaimsOrderWaybill(params);
            AssertUtils.notEmpty(waybills, "暂无正常运单数据");

            //获取交易对手信息
            Counterparty counterparty = counterpartyRepositoryService.getById(creditApplyDTO.getCounterpartyId());
            AssertUtils.notNull(counterparty, "交易对手不存在!");

            List<Long> waybillIds = new ArrayList<>();
            /** 转让金额 */
            BigDecimal transferBalance = BigDecimal.ZERO;
            /** 规定比例 */
            BigDecimal ruleRatio = null == counterparty.getRuleRatio() ? BigDecimal.ZERO :
                (counterparty.getRuleRatio().divide(new BigDecimal("100")));
            for (WaybillDTO item : waybills) {
                waybillIds.add(item.getId());
                transferBalance = transferBalance.add(item.getInvoiceMoney());
            }
            SubjectClaimsOrder subjectClaimsOrder = initSubjectClaimsOrder(creditApplyDTO.getCounterpartyId(),
                waybills.size(), counterparty
                .getCounterpartyName(), transferBalance, ruleRatio);
            subjectClaimsOrder.setOwnerId(creditApplyDTO.getOwnerId());
            subjectClaimsOrder.setCreateUserId(creditApplyDTO.getCreateUserId());
            subjectClaimsOrderRepositoryService.save(subjectClaimsOrder);

            Date currentDate = new Date();
            //回填标的债券id到运单表中
            waybillRepositoryService.updateSubjectClaimsOrderId(false,subjectClaimsOrder.getId(), waybillIds, currentDate);

            //#2.生成用款申请
            //合计应转换金额
            BigDecimal canApplyBalance = subjectClaimsOrder.getCanApplyBalance();
            AssertUtils.isTrue(canApplyBalance.compareTo(BigDecimal.ZERO) >= 0, "无可用金额");
            //用款申请编号生成
            String creditApplyNo = DateUtils.format(new Date(), DateUtils.PATTERN_DATE_NO_SEPARATOR) + Tools.random(5
                , RandomType.INT);
            //用款申请数据
            creditApplyDTO.setCreditApplyNo(creditApplyNo);
            creditApplyDTO.setStatus(CreditApplyStatus.UNDER_OPERATION_REVIEW.getValue());
            creditApplyDTO.setWaybillCount(subjectClaimsOrder.getWaybillCount());
            creditApplyDTO.setCounterpartyId(subjectClaimsOrder.getCounterpartyId());
            BigDecimal factRatio = calculateCreditApplyBalance(creditApplyDTO, counterparty, transferBalance);

            //获取流程
            Flow flow = flowRepositoryService.getOne(new LambdaQueryWrapper<Flow>().eq(Flow::getCompanyCapitalId,
                counterparty.getCompanyCapitalId()).eq(Flow::getFlowClass,
                CreditApply.class.getSimpleName()));
            AssertUtils.isTrue(flow != null, "没有可用审批流程");
            creditApplyDTO.setFlowId(flow.getId());
            //获取初始节点
            FlowNode flowNode =
                flowNodeRepositoryService.getOne(new LambdaQueryWrapper<FlowNode>().eq(FlowNode::getFlowId,
                    flow.getId()).eq(FlowNode::getStatus, creditApplyDTO.getStatus()));
            AssertUtils.isTrue(flowNode != null, "没有可用审批流程节点");
            creditApplyDTO.setFlowNodeId(flowNode.getId());
            //宽限日和计算
            Integer graceDays = counterparty.getGraceDays();
            //到期日
            Date dueDate = creditApplyDTO.getDueDate();
            AssertUtils.isTrue(DateUtils.toDate(LocalDate.now()).compareTo(dueDate) <= 0, "已经超过应收账款到期日，无法发起申请。");
            //宽限日
            Date graceDate = DateUtils.plusDays(dueDate, graceDays);
            creditApplyDTO.setDueDate(dueDate);
            creditApplyDTO.setGraceDate(graceDate);
            creditApplyDTO.setCheckedWaybillCount(0);
            CompanyDTO companyDTO = companyRepositoryService.getByOwnerId(creditApplyDTO.getOwnerId());
            AssertUtils.notNull(companyDTO, "借款方公司不存在!");
            ScfUserConfigDTO loanReviewConfig = scfUserConfigProvider.getByConfigKey(ScfConfigEnum.LOAN_REVIEW_CONFIG_CHECK.getCacheKey(),
                companyDTO.getCreateOwnerId());
            if (loanReviewConfig == null || loanReviewConfig.getConfigStatus() == 0) {
                creditApplyDTO.setNeedCheckWaybillCount(0);
            } else {
                creditApplyDTO.setNeedCheckWaybillCount((int) Math.ceil((double)(creditApplyDTO.getWaybillCount() * Integer.valueOf(loanReviewConfig.getConfigValue())) / 100));
            }
            //清空原来的id
            creditApplyDTO.setId(null);
            this.saveOrUpdate(creditApplyDTO);
            //创建每条债权订单关联的用款申请里的详情数据。
            BigDecimal dipositBalance = BigDecimal.ZERO;
            Boolean inRuleRatio = Boolean.TRUE;
            transferBalance = creditApplyDTO.getApplyBalance().multiply(new BigDecimal(10)).divide(new BigDecimal(9),
                2, BigDecimal.ROUND_HALF_UP);

            CreditApplyDetail creditApplyDetail = new CreditApplyDetail();
            creditApplyDetail.setCreditApplyId(creditApplyDTO.getId());

            creditApplyDetail.setApplyBalance(creditApplyDTO.getApplyBalance());
            creditApplyDetail.setTransferBalance(transferBalance);

            creditApplyDetail.setCounterpartyId(subjectClaimsOrder
                .getCounterpartyId());
            creditApplyDetail.setSubjectClaimsOrderId(subjectClaimsOrder.getId());
            creditApplyDetail.setDipositBalance(dipositBalance);
            creditApplyDetail.setFactRatio(factRatio);
            creditApplyDetail.setInRuleRatio(inRuleRatio);
            //已转余额，本次转之后标的债权剩余  //TODO-LA: 2020/3/4 可能需要修改
            creditApplyDetail.setRemainTransferBalance(subjectClaimsOrder.getTransferBalance());
            //保存用款申请详情数据
            creditApplyDetailRepositoryService.save(creditApplyDetail);

        } catch (Exception e) {
            log.error("Bad things", e);
            throw e;
        } finally {
            if (isLocked) {
                redisCachedBaseService.del(key);
            }
        }
    }

    /**
     * 计算本次提用后额度和实际比例 用款申请和重新申请都要计算。
     * @param creditApplyDTO
     * @param counterparty
     * @param transferBalance
     * @return: java.math.BigDecimal
     * @Author: liuan
     * @Date: 2020/4/8 22:39
     */
    private BigDecimal calculateCreditApplyBalance(CreditApplyDTO creditApplyDTO, Counterparty counterparty,
                                                   BigDecimal transferBalance) {
        //本次提用后金额
        BigDecimal afterThisBalance = counterparty.getSubitemUsedBalance().add(creditApplyDTO.getApplyBalance());
        //应收账款转让总额
        BigDecimal sumTransferBalance = counterparty.getHadReceivableBalance().subtract(counterparty.getReturnedTransferBalance()).add(transferBalance);
        //实际融资比例
        BigDecimal factRatio = afterThisBalance.divide(sumTransferBalance, 4, BigDecimal.ROUND_HALF_UP);
        creditApplyDTO.setFactRatio(factRatio);
        creditApplyDTO.setAfterThisBalance(afterThisBalance);
        return factRatio;
    }


    /**
     * 初始化运营审核中的应收款转让订单
     * @param counterpartyId
     * @param waybillCount
     * @param counterpartyName
     * @param transferBalance
     * @param ruleRatio
     * @return
     */
    @NotNull
    private SubjectClaimsOrder initSubjectClaimsOrder(Long counterpartyId, Integer waybillCount, String counterpartyName, BigDecimal
        transferBalance, BigDecimal ruleRatio) {
        SubjectClaimsOrder item = new SubjectClaimsOrder();
        item.setSubjectClaimsOrderNo(DateUtils.format(new Date(), DateUtils.PATTERN_DATE_NO_SEPARATOR) + Tools.random(5, RandomType.INT));
        item.setCounterpartyId(counterpartyId);
        item.setCounterpartyName(counterpartyName);
        //todo chenli 2020-03-05 金融产品暂时未知 默认使用id为1 金融产品 1期 0.01利率
        item.setFinancialProductId(1l);
        item.setWaybillCount(waybillCount);
        item.setTransferBalance(transferBalance);
        item.setCanApplyBalance(transferBalance.multiply(ruleRatio).setScale(BigDecimal.ROUND_HALF_UP, 2));
        item.setDeleted(DeleteEnum.N);
        item.setReviewStatus(1);
        return item;
    }
}
