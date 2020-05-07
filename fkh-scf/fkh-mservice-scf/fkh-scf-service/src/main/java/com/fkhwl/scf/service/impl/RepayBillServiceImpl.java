package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.entity.po.AccountBill;
import com.fkhwl.scf.entity.po.CompanyContract;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.scf.entity.po.CreditApply;
import com.fkhwl.scf.entity.po.FlowNode;
import com.fkhwl.scf.entity.po.RepayBill;
import com.fkhwl.scf.entity.po.ReviewRecord;
import com.fkhwl.scf.enums.CreditApplyStatus;
import com.fkhwl.scf.enums.DueStatus;
import com.fkhwl.scf.enums.RepayStatus;
import com.fkhwl.scf.redis.RedisCachedBaseService;
import com.fkhwl.scf.service.AccountBillRepositoryService;
import com.fkhwl.scf.service.CompanyContractRepositoryService;
import com.fkhwl.scf.service.CounterpartyRepositoryService;
import com.fkhwl.scf.service.CreditApplyRepositoryService;
import com.fkhwl.scf.service.FlowNodeRepositoryService;
import com.fkhwl.scf.service.RepayBillRepositoryService;
import com.fkhwl.scf.service.RepayBillService;
import com.fkhwl.scf.service.ReviewRecordRepositoryService;
import com.fkhwl.scf.utils.CacheKeyScf;
import com.fkhwl.scf.wrapper.RepayBillServiceConverterWrapper;
import com.fkhwl.starter.core.support.AssertUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 账单  </p>
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
@Service
@AllArgsConstructor
@Slf4j
public class RepayBillServiceImpl implements RepayBillService {

    private final RepayBillRepositoryService repayBillRepositoryService;

    private final AccountBillRepositoryService accountBillService;
    private CreditApplyRepositoryService creditApplyRepositoryService;
    private CounterpartyRepositoryService counterpartyRepositoryService;
    private CompanyContractRepositoryService companyContractRepositoryService;
    private FlowNodeRepositoryService flowNodeRepositoryService;
    private ReviewRecordRepositoryService reviewRecordRepositoryService;
    private final RedisCachedBaseService redisCachedBaseService;

    @Override
    public IPage<RepayBillDTO> listPage(IPage<RepayBillDTO> page, Map<String, Object> params) {
        return repayBillRepositoryService.listPage(page, params);
    }

    @Override
    public RepayBillDTO getDetail(Long id) {
        return RepayBillServiceConverterWrapper.INSTANCE.dto(repayBillRepositoryService.getById(id));
    }

    @Override
    public boolean checkHadRepay(Long counterpartyId) {
        List<RepayBillDTO> repayBillDTO = repayBillRepositoryService.getCheckingByCounterpartyId(counterpartyId);
        if (repayBillDTO != null && !repayBillDTO.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> checkHadRepayAndRemainBalance(Long counterpartyId) {
        Map<String, Object> map = accountBillService.getSumRemainRepayBalance(counterpartyId);
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("hasRepayBill", checkHadRepay(counterpartyId));
        return map;
    }

    @Override
    public void delete(Long id) {
        //逻辑删除部门
        repayBillRepositoryService.removeById(id);
    }

    @Override
    @Transactional
    public void reviewRepayBill(Long repayBillId, Boolean passStatus, Long companyId, String reviewReason) {
        // 1、修改审核流程状态
        //2.修改账单的还款金额，以及还款状态、实际还款时间
        //3、修改客户合同的相关金额、已使用金额
        //4、修改交易对手的相关金额
        //5、修改用款申请的状态
        //6、创建审核记录
        RepayBill repayBill =
            repayBillRepositoryService.getOne(new LambdaQueryWrapper<RepayBill>().eq(RepayBill::getId, repayBillId).eq(RepayBill::getStatus, RepayStatus.UNDER_REPAY));

        AssertUtils.isTrue(repayBill != null, "该还款记录已经在处理，请刷新后再操作!");

        boolean isLocked = false;
        String key = CacheKeyScf.getReviewRepayBillLockKey(repayBill.getId());
        try {
            isLocked = redisCachedBaseService.tryGetDistributedLock(key, repayBill.getId().toString(),
                CacheKeyScf.LOCK_TIME);
            log.error("reviewRepayBill locked:{},{}", repayBill.getId(), isLocked);
            AssertUtils.isTrue(isLocked, "正在处理中");

            //得到多个账单
            String[] accountBills = repayBill.getAccountBillBalanceInfo().split(";");
            //获取当前节点
            Long currentFlowNodeId = repayBill.getFlowNodeId();
            FlowNode currentFlowNode = flowNodeRepositoryService.getById(currentFlowNodeId);
            AssertUtils.isTrue(currentFlowNode!=null,"已经处理，请刷新后重试");
            for (String accountBillInfo : accountBills) {
                //每个账单的还款信息:
                String[] accountBillFields = accountBillInfo.split(",");
                AccountBill accountBill = accountBillService.getById(Long.valueOf(accountBillFields[0]));
                if (passStatus) {
                    //审核同意:1.还款审核单为同意状态，节点为已还款节点。2.账单还款金额和待还款修改。3.用款申请为还款状态。4.交易对手回款核销额添加，分项剩余金额添加。5公司合同分项审核金额添加
                    //获取当前节点
                    //当前账单还款金额
                    BigDecimal repayBalance = new BigDecimal(accountBillFields[1]);
                    BigDecimal interestRateBalance = new BigDecimal(accountBillFields[2]);
                    BigDecimal graceRateBalance = new BigDecimal(accountBillFields[3]);
                    BigDecimal overdueRateBalance = new BigDecimal(accountBillFields[4]);
                    //账单修改
                    accountBill.setRemainRepayBalance(accountBill.getRemainRepayBalance().subtract(repayBalance));
                    accountBill.setRepayBalance(accountBill.getRepayBalance().add(repayBalance));
                    accountBill.setFactRepayDate(repayBill.getRepayDate());
                    //设置本次还款利息金额
                    accountBill.setInterestRateBalance(accountBill.getInterestRateBalance().add(interestRateBalance));
                    accountBill.setGraceRateBalance(accountBill.getGraceRateBalance().add(graceRateBalance));
                    accountBill.setOverdueRateBalance(accountBill.getOverdueRateBalance().add(overdueRateBalance));
                    accountBill.setRepayStatus(accountBill.getRemainRepayBalance().compareTo(BigDecimal.ZERO) <= 0 ?
                        RepayStatus.ALREADY_REPAY.getValue() : RepayStatus.UNDER_REPAY.getValue());
//                    Integer dueStatus =DueStatus.NOT_DUE.getValue();
//                    if (overdueRateBalance.doubleValue() > 0) {
//                        //有超期利息
//                        accountBill.setDueStatus(DueStatus.ALREADY_OVERDUE.getValue());
//                    } else if (graceRateBalance.doubleValue() > 0) {
//                        //有到期利息
//                        accountBill.setDueStatus(DueStatus.ALREADY_DUE.getValue());
//                    }
                    accountBillService.updateRepayBalance(accountBill.getId(), repayBalance, interestRateBalance,
                        graceRateBalance, overdueRateBalance, repayBill.getRepayDate());

                    //还完，标记已还款；还了部分，标记还款中
                    CreditApply creditApply = creditApplyRepositoryService.getById(accountBill.getCreditApplyId());
                    //公司和交易对手金额处理
                    Counterparty counterparty = counterpartyRepositoryService.getById(creditApply.getCounterpartyId());
                    //分项剩余金额增加
                    counterparty.setSubitemRemainBalance(counterparty.getSubitemRemainBalance().add(repayBalance));
                    //已使用额度减少
                    counterparty.setSubitemUsedBalance(counterparty.getSubitemUsedBalance().subtract(repayBalance));
                    //还款额度增加
                    counterparty.setReturnedBalance(counterparty.getReturnedBalance().add(repayBalance));
                    //回款核销
                    //应收账款核销 增加
                    BigDecimal repayTransferBalance =
                        repayBalance.multiply(creditApply.getTransferBalance()).divide(accountBill.getBillBalance(),
                            4, BigDecimal.ROUND_HALF_UP);
                    //回款核销：应收账款核销
                    counterparty.setReturnedTransferBalance(counterparty.getReturnedTransferBalance().add(repayTransferBalance));
                    counterpartyRepositoryService.updateRepayBalance(creditApply.getCounterpartyId(), repayBalance,
                        repayTransferBalance);

                    CompanyContract companyContract =
                        companyContractRepositoryService.getOne(new LambdaQueryWrapper<CompanyContract>().eq(CompanyContract::getCompanyCapitalId, companyId).eq(CompanyContract::getCompanyBorrowerId,
                            counterparty.getCompanyBorrowerId()));
                    //可用额度增加
                    companyContract.setRemainingBalance(companyContract.getRemainingBalance().add(repayBalance));
                    //已归还金额增加
                    companyContract.setReturnedBalance(companyContract.getReturnedBalance().add(repayBalance));
                    //
                    companyContract.setNeedReturnBalance(companyContract.getNeedReturnBalance().subtract(repayBalance));
                    companyContractRepositoryService.updateRepayBalance(companyContract.getId(), repayBalance);
                    if (accountBill.getRemainRepayBalance().compareTo(BigDecimal.ZERO) <= 0) {
                        //如果账单金额为还完，则修改用款申请状态
                        creditApply.setStatus(CreditApplyStatus.ALREADY_REPAY.getValue());
                        creditApplyRepositoryService.updateById(creditApply);
                    }

                    //                companyContractRepositoryService.updateById(companyContract);
                    //                counterpartyRepositoryService.updateById(counterparty);
                    //                accountBillService.updateById(accountBill);
                } else {
                    //审核拒绝:1.修改还款审核单为拒绝节点，状态为拒绝状态。2.账单为拒绝梳妆台
                    //                //获取当前节点
                    //                //前一个节点
                    FlowNode prevNode = flowNodeRepositoryService.getPrevNode(currentFlowNodeId);
                    accountBill.setRepayStatus(prevNode.getStatus());
                    accountBillService.updateById(accountBill);
                }
            }
            if (passStatus) {
                //审核同意:1.还款审核单为同意状态，节点为已还款节点。2.账单还款金额和待还款修改。3.用款申请为还款状态。4.交易对手回款核销额添加，分项剩余金额添加。5公司合同分项审核金额添加
                //获取当前节点
                FlowNode nextFlowNode = flowNodeRepositoryService.getNextNode(currentFlowNodeId);
                repayBill.setStatus(nextFlowNode.getStatus());
                repayBill.setFlowNodeId(nextFlowNode.getId());
            } else {
                //审核拒绝:1.修改还款审核单为拒绝节点，状态为拒绝状态。2.账单为拒绝梳妆台
                //前一个节点
                FlowNode prevNode = flowNodeRepositoryService.getPrevNode(currentFlowNodeId);
                repayBill.setStatus(prevNode.getStatus());
                repayBill.setFlowNodeId(prevNode.getId());
            }
            repayBill.setReviewReason(reviewReason);
            repayBillRepositoryService.updateById(repayBill);
            //审核记录存储
            ReviewRecord reviewRecord = new ReviewRecord();
            reviewRecord.setReviewReason(reviewReason);
            // todo chenli 2020-03-13 reviewResult的值是否和creditApply中的status保持一致
            reviewRecord.setReviewResult(repayBill.getStatus());
            reviewRecord.setPassStatus(passStatus);
            //        reviewRecord.setReviewProcess(process);
            reviewRecord.setFlowNodeName(currentFlowNode.getFlowNodeName());
            //审批流程公用的字段设置
            reviewRecord.setBusinessId(repayBill.getId());
            reviewRecord.setFlowId(2L);
            reviewRecord.setFlowNodeId(currentFlowNodeId);
            reviewRecordRepositoryService.saveOrUpdate(reviewRecord);

        } catch (Exception e) {
            log.error("Bad things", e);
            throw e;
        } finally {
            if (isLocked) {
                redisCachedBaseService.del(key);
            }
        }
    }


}
