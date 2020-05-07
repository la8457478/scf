package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.CreditApplyDetailProvider;
import com.fkhwl.scf.SubjectClaimsOrderProvider;
import com.fkhwl.scf.entity.dto.CreditApplyDetailDTO;
import com.fkhwl.scf.entity.dto.ReviewPageDTO;
import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.po.CreditApplyDetail;
import com.fkhwl.scf.entity.vo.ReviewPageVo;
import com.fkhwl.scf.service.CreditApplyDetailService;
import com.fkhwl.scf.service.SubjectClaimsOrderService;
import com.fkhwl.starter.mybatis.support.Condition;

import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
@Service
@AllArgsConstructor
public class CreditApplyDetailProviderImpl implements CreditApplyDetailProvider {

    private final CreditApplyDetailService creditApplyDetailService;

    /**
     *
     * @param params
     * @return
     */
    public IPage<CreditApplyDetailDTO> listPage(Map<String, Object> params) {
        return creditApplyDetailService.listPage(Condition.getPage(params), params);
    }

    @Override
    public CreditApplyDetailDTO getDetail(Long id) {
        return creditApplyDetailService.getDetail(id);
    }

    @Override
    public void saveOrUpdate(CreditApplyDetailDTO creditApplyDetailDTO) {
        creditApplyDetailService.saveOrUpdate(creditApplyDetailDTO);
    }

    @Override
    public void delete(Long id) {
        creditApplyDetailService.delete(id);
    }

    @Override
    public List<ReviewPageDTO> reviewPage(Map<String, Object> params) {
       return  creditApplyDetailService.reviewPage(params);
    }

}
