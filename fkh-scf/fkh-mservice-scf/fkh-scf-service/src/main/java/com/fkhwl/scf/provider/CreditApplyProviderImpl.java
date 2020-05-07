package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.CreditApplyProvider;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.CreditApplyReviewDTO;
import com.fkhwl.scf.service.CreditApplyService;
import com.fkhwl.starter.mybatis.support.Condition;

import org.apache.dubbo.config.annotation.Service;

import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  用款申请 </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
@Service
@AllArgsConstructor
public class CreditApplyProviderImpl implements CreditApplyProvider {

    private final CreditApplyService creditApplyService;

    /**
     *
     * @param params
     * @return
     */
    public IPage<CreditApplyDTO> listPage(Map<String, Object> params) {
        return creditApplyService.listPage(Condition.getPage(params), params);
    }
    /**
     *
     * @param params
     * @return
     */
    @Override
    public IPage<CreditApplyListDTO> listCreditApplyPage(Map<String, Object> params) {
        return creditApplyService.listCreditApplyPage(Condition.getPage(params), params);
    }

    @Override
    public IPage<CreditApplyListDTO> listCreditApplyPage(IPage<CreditApplyListDTO> page,Map<String, Object> params) {
        return creditApplyService.listCreditApplyPage(page, params);
    }
    @Override
    public CreditApplyDTO getDetail(Long id) {
        return creditApplyService.getDetail(id);
    }

    @Override
    public void saveOrUpdate(CreditApplyDTO subjectClaimsOrderDTO) {
         creditApplyService.saveOrUpdate(subjectClaimsOrderDTO);
    }

    @Override
    public void createCreditApply(CreditApplyDTO creditApplyDTO, String subjectClaimsOrderIds) {
        creditApplyService.createCreditApply(creditApplyDTO,subjectClaimsOrderIds);
    }

    @Override
    public void delete(Long id) {
         creditApplyService.delete(id);
    }

    @Override
    public Map<String, Object> review(Long creditApplyId, Boolean passStatus, String reviewReason, Long userId, Boolean isValid, Long ownerId, Long companyId, String reviewBillUrl) {
      return  creditApplyService.review(creditApplyId,passStatus,reviewReason,userId,isValid,ownerId,companyId,reviewBillUrl);

    }

    @Override
    public CreditApplyReviewDTO getReviewDetail(Long creditApplyId, Long companyCapitalId) {
         return    creditApplyService.getReviewDetail(creditApplyId,companyCapitalId);
    }

    @Override
    public void reCreateCreditApply(CreditApplyDTO creditApplyDTO) {
        creditApplyService.reCreateCreditApply(creditApplyDTO);
    }
}
