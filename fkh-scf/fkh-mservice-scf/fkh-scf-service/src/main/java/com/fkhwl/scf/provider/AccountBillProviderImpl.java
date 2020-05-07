package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.AccountBillProvider;
import com.fkhwl.scf.entity.dto.AccountBillDTO;
import com.fkhwl.scf.entity.dto.AccountBillRateDTO;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.entity.po.AccountBill;
import com.fkhwl.scf.entity.po.AccountNotifySmsLog;
import com.fkhwl.scf.entity.vo.AccountBillListVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.service.AccountBillService;
import com.fkhwl.scf.service.AccountNotifySmsLogRepositoryService;
import com.fkhwl.scf.service.CompanyService;
import com.fkhwl.scf.service.CounterpartyService;
import com.fkhwl.scf.service.CreditApplyService;
import com.fkhwl.scf.service.RepayBillService;
import com.fkhwl.scf.service.SubjectClaimsOrderService;
import com.fkhwl.scf.utils.ToolsHelper;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.mybatis.support.Condition;
import com.fkhwl.starter.mybatis.support.Query;
import com.fkhwl.starter.sms.model.AliyunNotifyContent;
import com.fkhwl.starter.sms.service.AliyunSmsService;

import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 * 根据业务需求添加字段
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/29
 */
@Service
@AllArgsConstructor
@Slf4j
public class AccountBillProviderImpl implements AccountBillProvider {

    private final AccountBillService accountBillService;
    private final RepayBillService repayBillService;

    @Override
    public IPage<AccountBillDTO> listPage(Map<String, Object> params) {
        return accountBillService.listPage(Condition.getPage(params), params);
    }

    @Override
    public AccountBillDTO getDetail(Long id) {
        return accountBillService.getDetail(id);
    }

    @Override
    public void saveOrUpdate(AccountBillDTO accountBillDTO) {
        accountBillService.saveOrUpdate(accountBillDTO);
    }

    @Override
    public void delete(Long id) {
        accountBillService.delete(id);
    }

    @Override
    public void repayBill(RepayBillDTO accountBillRateDTO) {
        if(repayBillService.checkHadRepay(accountBillRateDTO.getCounterpartyId())){
            AssertUtils.isTrue(false,"该交易对手已发起还款申请！");
            return;
        }
        accountBillService.repayBill(accountBillRateDTO);
    }

    @Override
    public IPage<Map<String, Object>> listRepayPage(Map<String, Object> params) {
        return accountBillService.listRepayPage(Condition.getPage(params), params);
    }

    @Override
    public String notify(Long accountBillId, ScfUserVO scfUserVO) {

        return accountBillService.notify(accountBillId,scfUserVO);

    }

    @Override
    public List<AccountBillRateDTO> calculateRate(BigDecimal repayBalance,String repayDateStr, Long counterpartyId, Long userId, Long ownerId) {
        if(repayBillService.checkHadRepay(counterpartyId)){
            AssertUtils.isTrue(false,"该交易对手已发起还款申请！");
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("repayBalance", repayBalance);
        params.put("counterpartyId", counterpartyId);
        params.put("userId", userId);

        List<Long> ownerIds=new ArrayList<>();
        //如果是借款方用户，则根据创建人的负责人账号查询
        ownerIds.add(ownerId);
        params.put("ownerIds",ownerIds);
        //查询需要还款的账单。
        // 同一交易对手同时只能有一笔还款申请
        IPage<AccountBillDTO> accountBillDTOIPage=accountBillService.listPageForRepay(Condition.getPage(new Query(1, Integer.MAX_VALUE)), params);
        List<AccountBillDTO> accountBillDTOList= accountBillDTOIPage.getRecords();
        AssertUtils.isTrue(!accountBillDTOList.isEmpty(),"该交易对手暂无待还款账单");
        //依次还款，并计算利息
        //本次，已还款金额总和
        BigDecimal repayBalanceTotal=BigDecimal.ZERO;
        List<AccountBillRateDTO> accountBillRateDTOList=new ArrayList<>();
        for (AccountBillDTO accountBillDTO : accountBillDTOList) {
            BigDecimal curryRepayBalance=null;
            boolean isAll=true;
            //本金
            if(repayBalanceTotal.add(accountBillDTO.getRemainRepayBalance()).compareTo(repayBalance)<=0){
                //归还全部
                curryRepayBalance=accountBillDTO.getRemainRepayBalance();
                repayBalanceTotal=repayBalanceTotal.add(accountBillDTO.getRemainRepayBalance());
            }else{
                //归还部分
                curryRepayBalance=repayBalance.subtract(repayBalanceTotal);
                isAll=false;
                repayBalanceTotal=repayBalance;
            }

            //计算本金的利息
            // 本金使用了多少天 ")
            int interestRateDay=0;
            // 宽限期使用了多少天 ")
            int graceRateDay=0;
            // 宽限期后，又使用了多少天 ")
            int overdueRateDay=0;
            //相差多少天
            // TODO-SJ: 2020.03.26 金融监管平台：检查临界值，1号到15号，是否为14天。
            //3天，35天，50天，，账期：31天数 宽限期：10天数
            // 归还部分 只计算剩余
           Date factRepayDate =ToolsHelper.formatStr2Date(repayDateStr);
           //普通利息日， 应收账款到期日-放款日    如放款日1号   到期日为3号，则普通利息为2天; 不能按账期日算。
            int normalDays = ToolsHelper.getDayDiff(accountBillDTO.getBillDate(),accountBillDTO.getRepayDate());
            int diff= ToolsHelper.getDayDiff(accountBillDTO.getBillDate(),factRepayDate);
            //计算逾期利息
            if(diff>normalDays+accountBillDTO.getGraceDays()){//归还账期已到，宽限期已到，又使用了多少天
                //如果是使用50天，有3种利息
                interestRateDay=accountBillDTO.getPaymentDays();
                graceRateDay=accountBillDTO.getGraceDays();
                overdueRateDay=diff-interestRateDay-graceRateDay;
                accountBillDTO.setDueStatus(2);

            }else
            //计算宽限期的利息
            if(diff>normalDays){//归还账期已到，使用了多少天宽限期
                //如果是使用35天，有2种利息
                interestRateDay=accountBillDTO.getPaymentDays();
                graceRateDay=diff-interestRateDay;
                accountBillDTO.setDueStatus(1);

            }else if(diff>0){//归还账期是否已到，使用了多少天
                //如果是使用3天，有1种利息
                interestRateDay=diff;
                accountBillDTO.setDueStatus(0);

            }else{
                accountBillDTO.setDueStatus(0);
            }
            // 融资利率：用于计算利息 ")
            BigDecimal interestRateBalance=interestRateDay<=0?BigDecimal.ZERO:curryRepayBalance.multiply(accountBillDTO.getInterestRate()).multiply(BigDecimal.valueOf(interestRateDay)).divide(new BigDecimal("360"),4,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP);
            // 宽限期利率：用于计算宽限期天数的利息 ")
            BigDecimal graceRateBalance=graceRateDay<=0?BigDecimal.ZERO:curryRepayBalance.multiply(accountBillDTO.getGraceRate()).multiply(BigDecimal.valueOf(graceRateDay)).divide(new BigDecimal("360"),4,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP);
            // 逾期利率：用于计算逾期天数的利息 ")
            BigDecimal overdueRateBalance=overdueRateDay<=0?BigDecimal.ZERO:curryRepayBalance.multiply(accountBillDTO.getOverdueRate()).multiply(BigDecimal.valueOf(overdueRateDay)).divide(new BigDecimal("360"),4,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP);
            AccountBillRateDTO accountBillRateDTO=new AccountBillRateDTO();
            accountBillRateDTO.setAccountBillId(accountBillDTO.getId());
            accountBillRateDTO.setCreditApplyId(accountBillDTO.getCreditApplyId());
            accountBillRateDTO.setRepayBalance(curryRepayBalance);
            accountBillRateDTO.setInterestRateBalance(interestRateBalance);
            accountBillRateDTO.setOverdueRateBalance(overdueRateBalance);
            accountBillRateDTO.setGraceRateBalance(graceRateBalance);
            accountBillRateDTOList.add(accountBillRateDTO);

            AccountBill accountBill = new AccountBill();
            accountBill.setId(accountBillDTO.getId());
            accountBill.setDueStatus(accountBillDTO.getDueStatus());
            accountBill.setOwnerId(accountBillDTO.getOwnerId());
            accountBill.setCreateUserId(accountBillDTO.getCreateUserId());
            accountBill.setDueStatus(accountBillDTO.getDueStatus());
            accountBillService.updateById(accountBill);
            if(!isAll){//只归还了部分，说明本次的本金已经抵扣账单，抵扣完毕了。
            }
            if(repayBalanceTotal.compareTo(repayBalance)>=0){//本金已经抵扣账单，抵扣完毕了。
                break;
            }
        }
        return accountBillRateDTOList;
    }

    @Override
    public AccountBillDTO getByCreditApplyId(Long creditApplyId) {
       return  accountBillService.getByCreditApplyId(creditApplyId);
    }

    @Override
    public IPage<AccountBillListVO> listAccountBillListPage(IPage<AccountBillListVO> page,Map<String, Object> params) {
        return  accountBillService.listAccountBillListPage(page,params);
    }
}
