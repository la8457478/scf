package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.WaybillOperationHistoryDTO;
import com.fkhwl.scf.entity.po.WaybillOperationHistory;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:运单操作历史表 转换器, 默认提供 4 种转换, 根据业务需求重写转换逻辑或新增转换接口 </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-03-21
 */
@Mapper
public interface WaybillOperationHistoryServiceConverterWrapper extends ServiceConverterWrapper<WaybillOperationHistoryDTO, WaybillOperationHistory> {

    WaybillOperationHistoryServiceConverterWrapper INSTANCE = Mappers.getMapper(WaybillOperationHistoryServiceConverterWrapper.class);
}
