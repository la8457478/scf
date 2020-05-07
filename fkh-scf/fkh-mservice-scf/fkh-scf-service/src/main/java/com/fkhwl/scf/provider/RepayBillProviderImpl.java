package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.RepayBillProvider;
import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.service.AccountBillService;
import com.fkhwl.scf.service.RepayBillService;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:发起的还款   </p>
 * 根据业务需求添加字段
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/29
 */
@Service
@AllArgsConstructor
public class RepayBillProviderImpl implements RepayBillProvider {

@Autowired
private RepayBillService repayBillService;
@Autowired
private AccountBillService accountBillService;
    @Override
    public RepayBillDTO getDetail(Long id) {
        return repayBillService.getDetail(id);
    }
    @Override
    public boolean checkHadRepay(Long counterpartyId) {
        return repayBillService.checkHadRepay(counterpartyId);
    }
    @Override
    public Map<String,Object> checkHadRepayAndRemainBalance(Long counterpartyId) {
        return repayBillService.checkHadRepayAndRemainBalance(counterpartyId);
    }
    @Override
    public void saveOrUpdate(RepayBillDTO repayBillDTO) {

    }

    @Override
    public void delete(Long id) {
        repayBillService.delete(id);
    }

    @Override
    public void reviewRepayBill(Long repayBillId, Boolean passStatus, Long companyId,String reviewReason) {
        repayBillService.reviewRepayBill(repayBillId,passStatus,companyId,reviewReason);
    }

    @Override
    public IPage<RepayBillDTO> listPage(IPage<RepayBillDTO> page, Map<String, Object> params) {
        return repayBillService.listPage(page, params);
    }

}
