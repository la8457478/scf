package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.ChinaAreaDTO;
import com.fkhwl.scf.entity.po.ChinaArea;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 行政区域 服务层转换器, 提供 po 和 dto 互转 </p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com"
 * @date 2020.03.12
 */
@Mapper
public interface ChinaAreaServiceConverterWrapper extends ServiceConverterWrapper<ChinaAreaDTO, ChinaArea> {

    ChinaAreaServiceConverterWrapper INSTANCE = Mappers.getMapper(ChinaAreaServiceConverterWrapper.class);
}
