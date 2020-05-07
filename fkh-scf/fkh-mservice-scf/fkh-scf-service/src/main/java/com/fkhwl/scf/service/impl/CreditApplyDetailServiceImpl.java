package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyDetailDTO;
import com.fkhwl.scf.entity.dto.ReviewPageDTO;
import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.po.CreditApplyDetail;
import com.fkhwl.scf.entity.po.SubjectClaimsOrder;
import com.fkhwl.scf.entity.vo.ReviewPageVo;
import com.fkhwl.scf.service.CreditApplyDetailRepositoryService;
import com.fkhwl.scf.service.CreditApplyDetailService;
import com.fkhwl.scf.service.SubjectClaimsOrderRepositoryService;
import com.fkhwl.scf.service.SubjectClaimsOrderService;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.CreditApplyDetailServiceConverterWrapper;
import com.fkhwl.scf.wrapper.SubjectClaimsOrderServiceConverterWrapper;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 * 根据业务需求添加字段
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
@Service
@AllArgsConstructor
public class CreditApplyDetailServiceImpl implements CreditApplyDetailService {
    private final CreditApplyDetailRepositoryService creditApplyDetailRepositoryService;

    @Override
    public IPage<CreditApplyDetailDTO> listPage(IPage<CreditApplyDetailDTO> page, Map<String, Object> params) {
        return creditApplyDetailRepositoryService.listPage(page, params);
    }

    @Override
    public CreditApplyDetailDTO getDetail(Long id) {
        return CreditApplyDetailServiceConverterWrapper.INSTANCE.dto(creditApplyDetailRepositoryService.getById(id));
    }

    @Override
    public void saveOrUpdate(CreditApplyDetailDTO creditApplyDetailDTO) {
        Date currentDate = new Date();
        CreditApplyDetail creditApplyDetail = CreditApplyDetailServiceConverterWrapper.INSTANCE.po(creditApplyDetailDTO);
        creditApplyDetail.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(creditApplyDetailDTO.getId())) {
            creditApplyDetail.setCreateTime(currentDate);
        }
        creditApplyDetailRepositoryService.saveOrUpdate(creditApplyDetail);
    }

    @Override
    public void delete(Long id) {
        //逻辑删除部门
        creditApplyDetailRepositoryService.removeById(id);
        //todo add-by chenli 是否逻辑删除对应的“用户-部门”中间表
    }

    @Override
    public List<ReviewPageDTO> reviewPage(Map<String, Object> params) {
       return creditApplyDetailRepositoryService.reviewPage(params);
    }
}
