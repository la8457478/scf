package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.ChinaAreaDTO;
import com.fkhwl.scf.entity.vo.ChinaAreaVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 行政区域 视图层转换器, 提供 vo 和 dto 互转 </p>
 * form 和 dto 互转需要在此接口中自己添加.
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com"
 * @date 2020.03.12
 */
@Mapper
public interface ChinaAreaViewConverterWrapper extends ViewConverterWrapper<ChinaAreaVO, ChinaAreaDTO> {

    ChinaAreaViewConverterWrapper INSTANCE = Mappers.getMapper(ChinaAreaViewConverterWrapper.class);
}


