package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.po.SubjectClaimsOrder;
import com.fkhwl.scf.entity.vo.ReviewPageVo;
import com.fkhwl.starter.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 标的债权订单 Dao 接口  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.25 11:34
 */
@Mapper
public interface SubjectClaimsOrderDao extends BaseDao<SubjectClaimsOrder> {

    IPage<SubjectClaimsOrderDTO> listPage(@Param("page") IPage<SubjectClaimsOrderDTO> page, @Param("map") Map<String, Object> params);

    List<ReviewPageVo> reviewPage(@Param("map") Map<String, Object> params);

    Long countWaybillCountByCreditApplyId(Long creditApplyId);
}
