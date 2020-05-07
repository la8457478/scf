package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AccountBillDTO;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.entity.po.AccountBill;
import com.fkhwl.scf.entity.po.AccountNotifySmsLog;
import com.fkhwl.scf.entity.po.CompanyContract;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.scf.entity.po.CreditApply;
import com.fkhwl.scf.entity.po.Flow;
import com.fkhwl.scf.entity.po.FlowNode;
import com.fkhwl.scf.entity.po.RepayBill;
import com.fkhwl.scf.entity.vo.AccountBillListVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.enums.RepayStatus;
import com.fkhwl.scf.redis.RedisCachedBaseService;
import com.fkhwl.scf.service.AccountBillRepositoryService;
import com.fkhwl.scf.service.AccountBillService;
import com.fkhwl.scf.service.AccountNotifySmsLogRepositoryService;
import com.fkhwl.scf.service.CompanyContractRepositoryService;
import com.fkhwl.scf.service.CompanyService;
import com.fkhwl.scf.service.CounterpartyRepositoryService;
import com.fkhwl.scf.service.CreditApplyRepositoryService;
import com.fkhwl.scf.service.FlowNodeRepositoryService;
import com.fkhwl.scf.service.FlowRepositoryService;
import com.fkhwl.scf.service.RepayBillRepositoryService;
import com.fkhwl.scf.service.YiMeiSmsService;
import com.fkhwl.scf.utils.CacheKeyScf;
import com.fkhwl.scf.utils.Const;
import com.fkhwl.scf.utils.ToolsHelper;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.AccountBillServiceConverterWrapper;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.core.util.DateUtils;
import com.fkhwl.starter.sms.model.YiMeiNotifyContent;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 账单  </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
@Service
@AllArgsConstructor
@Slf4j
public class AccountBillServiceImpl implements AccountBillService {

    private final AccountBillRepositoryService accountBillRepositoryService;
    private CounterpartyRepositoryService counterpartyRepositoryService;
    private CompanyContractRepositoryService companyContractRepositoryService;
    private CreditApplyRepositoryService creditApplyRepositoryService;
    private RepayBillRepositoryService repayBillRepositoryService;
    private FlowRepositoryService flowRepositoryService;
    private final YiMeiSmsService yiMeiSmsService;
    private FlowNodeRepositoryService flowNodeRepositoryService;
    private final RedisCachedBaseService redisCachedBaseService;
    private CompanyService companyService;
    private AccountNotifySmsLogRepositoryService accountNotifySmsLogRepositoryService;


    @Override
    public IPage<AccountBillDTO> listPage(IPage<AccountBillDTO> page, Map<String, Object> params) {
        return accountBillRepositoryService.listPage(page, params);
    }

    @Override
    public IPage<AccountBillDTO> listPageForRepay(IPage<AccountBillDTO> page, Map<String, Object> params) {
        return accountBillRepositoryService.listPageForRepay(page, params);
    }
    @Override
    public AccountBillDTO getDetail(Long id) {
        return AccountBillServiceConverterWrapper.INSTANCE.dto(accountBillRepositoryService.getById(id));
    }

    @Override
    public void saveOrUpdate(AccountBillDTO accountBillDTO) {
        Date currentDate = new Date();
        AccountBill accountBill = AccountBillServiceConverterWrapper.INSTANCE.po(accountBillDTO);
        accountBill.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(accountBillDTO.getId())) {
            accountBill.setCreateTime(currentDate);
        }
        accountBillRepositoryService.saveOrUpdate(accountBill);
    }

    @Override
    public void delete(Long id) {
        //逻辑删除部门
        accountBillRepositoryService.removeById(id);
    }

    @Override
    @Transactional
    public void repayBill(RepayBillDTO accountBillRateDTO) {
        //账单可能已经提交还款申请，不可再次申请
        String accountBillIds=accountBillRateDTO.getAccountBillIds() ;
        String repayDateStr=accountBillRateDTO.getRepayDateStr();
        BigDecimal repayBalance=accountBillRateDTO.getRepayBalance();
//        Long companyId=accountBillRateDTO.getCompanyId();
        Long userId=accountBillRateDTO.getCreateUserId();
        Long ownerId=accountBillRateDTO.getOwnerId();
        //交易对手Id
         Long counterpartyId=accountBillRateDTO.getCounterpartyId();

//        List<String> lockList=new ArrayList<>();

//        for (String accountBillId : accountBillIds.split(",")) {
//            String key= CacheKeyScf.getCreateAccountBillLockKey(accountBillId);
//            isAllLocked= redisCachedBaseService.tryGetDistributedLock(key,accountBillId,CacheKeyScf.LOCK_TIME);
//            log.error("repayBill locked:{},{}",accountBillId,isAllLocked);
//            AssertUtils.isTrue(isAllLocked,"还款申请处理中");
//            lockList.add(key);
//        }
        //给交易对手加锁
        boolean isAllLocked=false;
        String key  = CacheKeyScf.getCreateAccountBillLockKey(counterpartyId);
        try {
            isAllLocked = redisCachedBaseService.tryGetDistributedLock(key, counterpartyId.toString(), CacheKeyScf.LOCK_TIME);
            log.error("repayBill locked:{},{}", counterpartyId, isAllLocked);

            AssertUtils.isTrue(isAllLocked, "该交易对手已发起还款申请");
            for (String accountBillId : accountBillIds.split(",")) {
                AccountBill accountBill = accountBillRepositoryService.getById(Long.valueOf(accountBillId));

                AssertUtils.isTrue(accountBill.getRemainRepayBalance().compareTo(BigDecimal.ZERO) > 0, "无需还款");

                //        if(repayBalance.compareTo(accountBill.getRemainRepayBalance())>=0){
                //如果还款金额大于待还
                accountBill.setRepayStatus(RepayStatus.UNDER_REPAY.getValue());
                //        }else{
                //
                //        }
                accountBillRepositoryService.updateById(accountBill);
            }
            Counterparty counterparty = counterpartyRepositoryService.getById(counterpartyId);
            //        Long companyCapitalId,Long companyBorrowerId
            CompanyContract companyContract = companyContractRepositoryService.findInfoByCompanyId(counterparty.getCompanyCapitalId(), counterparty.getCompanyBorrowerId());
            Flow flow = flowRepositoryService.getOne(new LambdaQueryWrapper<Flow>().eq(Flow::getCompanyCapitalId, counterparty.getCompanyCapitalId()).eq(Flow::getFlowClass,
                RepayBill.class.getSimpleName()));
            AssertUtils.isTrue(flow != null, "没有可用审批流程");

            RepayBill repayBill = new RepayBill();

            repayBill.setInterestRateBalance(accountBillRateDTO.getInterestRateBalance());
            repayBill.setOverdueRateBalance(accountBillRateDTO.getOverdueRateBalance());
            repayBill.setGraceRateBalance(accountBillRateDTO.getGraceRateBalance());
            repayBill.setAccountBillBalanceInfo(accountBillRateDTO.getAccountBillBalanceInfo());
            repayBill.setCounterpartyId(counterpartyId);
            repayBill.setCounterpartyName(counterparty.getCounterpartyName());
            repayBill.setCompanyBorrowerId(counterparty.getCompanyBorrowerId());
            repayBill.setCompanyBorrowerName(companyContract.getCompanyBorrowerName());
            repayBill.setFlowId(flow.getId());
            //还款中=出纳确认审核
            repayBill.setStatus(RepayStatus.UNDER_REPAY.getValue());
            //获取初始节点
            FlowNode flowNode = flowNodeRepositoryService.getOne(new LambdaQueryWrapper<FlowNode>().eq(FlowNode::getFlowId, flow.getId()).eq(FlowNode::getStatus, RepayStatus.UNDER_REPAY.getValue()));
            repayBill.setOwnerId(ownerId);
            repayBill.setFlowNodeId(flowNode.getId());
            repayBill.setRepayDate(ToolsHelper.formatStr2Date(repayDateStr));


            repayBill.setAccountBillIds(accountBillIds);
            repayBill.setCreateUserId(userId);
            repayBill.setRepayBalance(repayBalance);
            AssertUtils.isTrue(flowNode != null, "没有可用审批流程节点");
            repayBillRepositoryService.save(repayBill);
            //        //公司和交易对手金额处理
            //        Counterparty counterparty = counterpartyRepositoryService.getById(creditApply.getCounterpartyId());
            //        //分项审阅金额
            //        counterparty.setSubitemRemainBalance(counterparty.getSubitemRemainBalance().add(repayBalance));
            //        //回款核销
            //        counterparty.setReturnedBalance(counterparty.getReturnedBalance().add(repayBalance));
            //        counterparty.setUpdateTime(DateUtils.now());
            //        //TODO-LA: 2020/3/16 修改公司合同剩余金额
            //        CompanyContract companyContract=   companyContractRepositoryService.getOne(new LambdaQueryWrapper<CompanyContract>().eq(CompanyContract::getCompanyCapitalId,companyId).eq
            //        (CompanyContract::getCompanyBorrowerId,counterparty.getCompanyBorrowerId()));
            //        companyContract.setRemainingBalance(companyContract.getRemainingBalance().add(creditApply.getApplyBalance()));
            //        creditApply.setStatus(CreditApplyStatus.ALREADY_REPAY.getValue());
            //        companyContractRepositoryService.updateById(companyContract);
            //        counterpartyRepositoryService.updateById(counterparty);

        } catch (Exception e) {
            log.error("Bad things", e);
            throw e;
        }finally {
//            for (String key : lockList) {
                redisCachedBaseService.del(key);
//            }
        }
    }

    @Override
    public IPage<Map<String, Object>> listRepayPage(IPage<Map<String, Object>> page, Map<String, Object> params) {
        return accountBillRepositoryService.listRepayPage(page,params);
    }

    @Override
    public AccountBillDTO getByCreditApplyId(Long creditApplyId) {
        return AccountBillServiceConverterWrapper.INSTANCE.dto( accountBillRepositoryService.getOne(new LambdaQueryWrapper<AccountBill>().eq(AccountBill::getCreditApplyId,creditApplyId)));
    }

    @Override
    public IPage<AccountBillListVO> listAccountBillListPage(IPage<AccountBillListVO> page,Map<String, Object> params) {
        IPage<AccountBillListVO> result =  accountBillRepositoryService.listAccountBillListPage(page,params);
        result.getRecords().stream().forEach(accountBillListVO -> {
            Date date =  DateUtils.now();
            if(accountBillListVO.getRepayStatus()==RepayStatus.ALREADY_REPAY.getValue()){
                date = accountBillListVO.getFactRepayDate();
            }
            if(!accountBillListVO.getRepayStatus().equals(RepayStatus.ALREADY_REPAY.getValue())){
                //如果未结清 则需要计算利息。
                //TODO-LA: 2020/4/2 计算利息需要写成方法
                calculateSumInterestBalance(accountBillListVO, date);
                AccountBill accountBill = new AccountBill();
                accountBill.setId(accountBillListVO.getId());
                accountBill.setDueStatus(accountBillListVO.getDueStatus());
                accountBill.setOwnerId(accountBillListVO.getOwnerId());
                accountBill.setCreateUserId(accountBillListVO.getCreateUserId());
                accountBill.setDueStatus(accountBillListVO.getDueStatus());
                accountBillRepositoryService.updateById(accountBill);
            }else{
                //如果已经还清，则利息为0 。
                accountBillListVO.setInterestRateBalance(BigDecimal.ZERO);
                accountBillListVO.setGraceRateBalance(BigDecimal.ZERO);
                accountBillListVO.setOverdueRateBalance(BigDecimal.ZERO);
            }

        });
        return result;
    }

    @Override
    public void updateById(AccountBill accountBill) {
        accountBillRepositoryService.updateById(accountBill);
    }

    @Override
    public String notify(Long accountBillId, ScfUserVO scfUserVO) {
        AccountBillDTO accountBill = this.getDetail(accountBillId);
        AssertUtils.notNull(accountBill, "账单不存在");
        CreditApply creditApply= creditApplyRepositoryService.getById(accountBill.getCreditApplyId());
        AssertUtils.notNull(creditApply, "用款申请不存在");
        Counterparty counterparty = counterpartyRepositoryService.getById(creditApply.getCounterpartyId());
        AssertUtils.notNull(accountBill, "交易对手不存在");
        CompanyDTO companyDTO = companyService.getDetail(counterparty.getCompanyBorrowerId());
        AssertUtils.notNull(companyDTO, "公司不存在");
        String accountMobileNos = companyDTO.getAccountMobileNos();
        //资方
       CompanyDTO capitalCompany =   companyService.getDetail(scfUserVO.getCompanyId());

        String key =CacheKeyScf.getAccountNotifyBillLockKey(accountBillId);
        try {
            Object notifyStatus = redisCachedBaseService.get(key);
            AssertUtils.isTrue(notifyStatus == null, "该账单已提醒，请明日再操作！");
            redisCachedBaseService.setIfAbsent(key, companyDTO.getCompanyTel(), Integer.valueOf(DateUtils.between(DateUtils.now(), DateUtils.toDate(LocalDate.now().plusDays(1))).getSeconds() + ""));
            String smsContent = Const.JY_NOTIFY_SMS_TEMPLATE.replaceAll("\\{companyCapitalName}", capitalCompany.getShortCompanyName() == null ? capitalCompany.getCompanyName() : capitalCompany.getShortCompanyName());
            smsContent = smsContent.replaceAll("\\{companyBorrowerName}", companyDTO.getShortCompanyName() == null ? companyDTO.getCompanyName(): companyDTO.getShortCompanyName());
            smsContent = smsContent.replaceAll("\\{creditApplyNo}", creditApply.getCreditApplyNo());
            smsContent = smsContent.replaceAll("\\{balance}", accountBill.getRemainRepayBalance().toString());
            smsContent = smsContent.replaceAll("\\{smsConsultPhoneNo}", capitalCompany.getSmsConsultPhoneNo() == null ? "" : capitalCompany.getSmsConsultPhoneNo());
            LocalDateTime localDate = DateUtils.fromDate(accountBill.getRepayDate());
            final String content = smsContent.replaceAll("\\{date}", localDate.getYear() + "年" + localDate.getMonthValue() + "月" + localDate.getDayOfMonth() + "日");
            if (StringUtils.isNotBlank(accountMobileNos)) {
                String[] mobiles = accountMobileNos.split("\n");
                List<AccountNotifySmsLog> notifySmsLogs = new ArrayList<>();
                Arrays.stream(mobiles).forEach(o -> {
                    Long timestamp = System.currentTimeMillis();
                    YiMeiNotifyContent yiMeiNotifyContent = new YiMeiNotifyContent(timestamp, o, content);
                    String result = yiMeiSmsService.sendMsg(yiMeiNotifyContent);
                    AccountNotifySmsLog accountNotifySmsLog = new AccountNotifySmsLog();
                    accountNotifySmsLog.setCompanyBorrowerId(counterparty.getCompanyBorrowerId());
                    accountNotifySmsLog.setCreateUserId(scfUserVO.getCompanyId());
                    accountNotifySmsLog.setCompanyCapitalId(scfUserVO.getCompanyId());
                    accountNotifySmsLog.setMobileNo(o);
                    if (result.equals("0")) {
                        accountNotifySmsLog.setSendStatus(1);
                        log.error("sendMsg success mobileNo :{},result:{}", o, result);
                    } else {
                        accountNotifySmsLog.setSendStatus(0);
                        log.error("sendMsg fail mobileNo :{},result:{}", o, result);
                    }
                    notifySmsLogs.add(accountNotifySmsLog);
                });
                accountNotifySmsLogRepositoryService.saveBatch(notifySmsLogs);
            }
        }catch (Exception e){
            log.error("Bad things", e);
            throw e;
        }
        return "true";
    }

    private BigDecimal calculateSumInterestBalance(AccountBillListVO accountBillListVO,Date date) {

        BigDecimal curryRepayBalance = accountBillListVO.getRemainRepayBalance();
        //计算本金的利息
        // 本金使用了多少天 ")
        int interestRateDay=0;
        // 宽限期使用了多少天 ")
        int graceRateDay=0;
        // 宽限期后，又使用了多少天 ")
        int overdueRateDay=0;
        //相差多少天
        // TODO-SJ: 2020.03.26 金融监管平台：检查临界值，1号到15号，是否为14天。
        int normalDays = ToolsHelper.getDayDiff(accountBillListVO.getBillDate(),accountBillListVO.getRepayDate());

        //3天，35天，50天，，账期：31天数 宽限期：10天数
        // TODO-SJ: 2020.03.26 金融监管平台：如果只是归还了部分，怎么计算
        int diff= ToolsHelper.getDayDiff(accountBillListVO.getBillDate(),date);
        //计算逾期利息
        if (diff > normalDays + accountBillListVO.getGraceDays()) {//归还账期已到，宽限期已到，又使用了多少天
            //如果是使用50天，有3种利息
            interestRateDay = accountBillListVO.getPaymentDays();
            graceRateDay = accountBillListVO.getGraceDays();
            overdueRateDay = diff - interestRateDay - graceRateDay;
            accountBillListVO.setDueStatus(2);

        } else
            //计算宽限期的利息
            if (diff > normalDays) {//归还账期已到，使用了多少天宽限期
                //如果是使用35天，有2种利息
                interestRateDay = accountBillListVO.getPaymentDays();
                graceRateDay = diff - interestRateDay;
                accountBillListVO.setDueStatus(1);
            } else if (diff > 0) {//归还账期是否已到，使用了多少天
                //如果是使用3天，有1种利息
                interestRateDay = diff;
                accountBillListVO.setDueStatus(0);

            }else{
                accountBillListVO.setDueStatus(0);
            }
        // 融资利率：用于计算利息 ")
        BigDecimal interestRateBalance = interestRateDay <= 0 ? BigDecimal.ZERO : curryRepayBalance.multiply(accountBillListVO.getInterestRate()).multiply(BigDecimal.valueOf(interestRateDay)).divide(new BigDecimal("360"),4,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP);
        // 宽限期利率：用于计算宽限期天数的利息 ")
        BigDecimal graceRateBalance = graceRateDay <= 0 ? BigDecimal.ZERO : curryRepayBalance.multiply(accountBillListVO.getGraceRate()).multiply(BigDecimal.valueOf(graceRateDay)).divide(new BigDecimal("360"),4,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP);
        // 逾期利率：用于计算逾期天数的利息 ")
        BigDecimal overdueRateBalance = overdueRateDay <= 0 ? BigDecimal.ZERO : curryRepayBalance.multiply(accountBillListVO.getOverdueRate()).multiply(BigDecimal.valueOf(overdueRateDay)).divide(new BigDecimal("360"),4,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP);
        accountBillListVO.setInterestRateBalance(interestRateBalance);
        accountBillListVO.setGraceRateBalance(graceRateBalance);
        accountBillListVO.setOverdueRateBalance(overdueRateBalance);
        return interestRateBalance.add(graceRateBalance).add(overdueRateBalance);
    }

}
