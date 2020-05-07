package com.fkhwl.scf.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.ReviewRecordDTO;
import com.fkhwl.scf.entity.po.ReviewRecord;
import com.fkhwl.starter.common.base.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  Dao 接口  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.03.02 12:11
 */
@Mapper
public interface ReviewRecordDao extends BaseDao<ReviewRecord> {

    List<ReviewRecordDTO> listReviewHistory(@Param(value="map") Map<String, Object> params);
}
