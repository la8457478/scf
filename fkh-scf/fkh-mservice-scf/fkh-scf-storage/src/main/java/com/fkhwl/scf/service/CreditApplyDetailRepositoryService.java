package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyDetailDTO;
import com.fkhwl.scf.entity.dto.ReviewPageDTO;
import com.fkhwl.scf.entity.po.CreditApplyDetail;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用款申请详情 服务接口 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.03.02 12:11
 */
public interface CreditApplyDetailRepositoryService extends BaseService<CreditApplyDetail> {

    IPage<CreditApplyDetailDTO> listPage(IPage<CreditApplyDetailDTO> page, Map<String, Object> params);

    List<ReviewPageDTO> reviewPage(Map<String, Object> params);
    List<CreditApplyDetailDTO> getBySubjectClaimsOrderId(Long subjectClaimsOrderId);
}

