package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.ReviewRecordDTO;
import com.fkhwl.scf.entity.vo.ReviewRecordVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  视图层转换器, 提供 vo 和 dto 互转 </p>
 * form 和 dto 互转需要在此接口中自己添加.
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.03.02 12:11
 */
@Mapper
public interface ReviewRecordViewConverterWrapper extends ViewConverterWrapper<ReviewRecordVO, ReviewRecordDTO> {

    ReviewRecordViewConverterWrapper INSTANCE = Mappers.getMapper(ReviewRecordViewConverterWrapper.class);
}


