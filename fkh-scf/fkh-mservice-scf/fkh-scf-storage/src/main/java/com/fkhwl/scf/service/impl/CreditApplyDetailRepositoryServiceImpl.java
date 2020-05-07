package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.CreditApplyDetailDao;
import com.fkhwl.scf.entity.dto.CreditApplyDetailDTO;
import com.fkhwl.scf.entity.dto.ReviewPageDTO;
import com.fkhwl.scf.entity.po.CreditApplyDetail;
import com.fkhwl.scf.service.CreditApplyDetailRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用款申请详情 服务接口实现类 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.03.02 12:11
 */
@Slf4j
@Service
public class CreditApplyDetailRepositoryServiceImpl extends BaseServiceImpl<CreditApplyDetailDao, CreditApplyDetail> implements CreditApplyDetailRepositoryService {

    @Override
    public IPage<CreditApplyDetailDTO> listPage(IPage<CreditApplyDetailDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page,params);
    }

    @Override
    public List<ReviewPageDTO> reviewPage(Map<String, Object> params) {
        return baseMapper.reviewPage(params);
    }

    @Override
    public List<CreditApplyDetailDTO> getBySubjectClaimsOrderId(Long subjectClaimsOrderId) {
        return baseMapper.getBySubjectClaimsOrderId(subjectClaimsOrderId);
    }
}
