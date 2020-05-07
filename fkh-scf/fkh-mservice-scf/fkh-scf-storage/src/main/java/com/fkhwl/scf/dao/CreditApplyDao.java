package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import org.apache.ibatis.annotations.Mapper;

import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.CreditApplyReviewDTO;
import com.fkhwl.scf.entity.po.CreditApply;
import com.fkhwl.starter.common.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  Dao 接口  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.27 10:54
 */
@Mapper
public interface CreditApplyDao extends BaseDao<CreditApply> {
    IPage<CreditApplyDTO> listPage(@Param("page") IPage<CreditApplyDTO> page,@Param("map") Map<String, Object> params);
    IPage<CreditApplyListDTO> listCreditApplyPage(@Param("page") IPage<CreditApplyListDTO> page, @Param("map") Map<String, Object> params);

    CreditApplyReviewDTO getReviewDetail(Long creditApplyId, Long companyCapitalId);

    Map<String, Object> sumBalance(@Param("params") Map<String, Object> params);
}


