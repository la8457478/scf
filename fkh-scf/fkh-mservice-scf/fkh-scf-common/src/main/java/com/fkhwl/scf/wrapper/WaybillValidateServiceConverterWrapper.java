package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.WaybillValidateDTO;
import com.fkhwl.scf.entity.po.WaybillValidate;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单表 服务层转换器, 提供 po 和 dto 互转 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.29 11:43
 */
@Mapper
public interface WaybillValidateServiceConverterWrapper extends ServiceConverterWrapper<WaybillValidateDTO, WaybillValidate> {

    WaybillValidateServiceConverterWrapper INSTANCE = Mappers.getMapper(WaybillValidateServiceConverterWrapper.class);
}
