package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.ReviewRecordDTO;
import com.fkhwl.scf.entity.po.ReviewRecord;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  服务层转换器, 提供 po 和 dto 互转 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.03.02 12:11
 */
@Mapper
public interface ReviewRecordServiceConverterWrapper extends ServiceConverterWrapper<ReviewRecordDTO, ReviewRecord> {

    ReviewRecordServiceConverterWrapper INSTANCE = Mappers.getMapper(ReviewRecordServiceConverterWrapper.class);
}
