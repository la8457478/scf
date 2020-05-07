package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.FlowNodeTypeDTO;
import com.fkhwl.scf.entity.vo.FlowNodeTypeVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 节点类型 视图层转换器, 提供 vo 和 dto 互转 </p>
 * form 和 dto 互转需要在此接口中自己添加.
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.21 15:51
 */
@Mapper
public interface FlowNodeTypeViewConverterWrapper extends ViewConverterWrapper<FlowNodeTypeVO, FlowNodeTypeDTO> {

    FlowNodeTypeViewConverterWrapper INSTANCE = Mappers.getMapper(FlowNodeTypeViewConverterWrapper.class);
}


