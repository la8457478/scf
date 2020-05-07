package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.FlowDTO;
import com.fkhwl.scf.entity.po.Flow;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 流程表 服务层转换器, 提供 po 和 dto 互转 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.21 15:51
 */
@Mapper
public interface FlowServiceConverterWrapper extends ServiceConverterWrapper<FlowDTO, Flow> {

    FlowServiceConverterWrapper INSTANCE = Mappers.getMapper(FlowServiceConverterWrapper.class);
}
