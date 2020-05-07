package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyDetailDTO;
import com.fkhwl.scf.entity.dto.ReviewPageDTO;
import com.fkhwl.scf.entity.po.CreditApplyDetail;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用款申请详情 Dao 接口  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.03.02 12:11
 */
@Mapper
public interface CreditApplyDetailDao extends BaseDao<CreditApplyDetail> {

    IPage<CreditApplyDetailDTO> listPage(
        @Param("page") IPage<CreditApplyDetailDTO> page, @Param("map") Map<String, Object> params);

    List<ReviewPageDTO> reviewPage(@Param("map") Map<String, Object> params);

    List<CreditApplyDetailDTO> getBySubjectClaimsOrderId(Long subjectClaimsOrderId);
}
