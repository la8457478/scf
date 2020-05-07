package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.FlowNodeDTO;
import com.fkhwl.scf.entity.po.FlowNode;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 流程节点 服务层转换器, 提供 po 和 dto 互转 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.21 15:51
 */
@Mapper
public interface FlowNodeServiceConverterWrapper extends ServiceConverterWrapper<FlowNodeDTO, FlowNode> {

    FlowNodeServiceConverterWrapper INSTANCE = Mappers.getMapper(FlowNodeServiceConverterWrapper.class);
}
