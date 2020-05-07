package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.PushWaybillDTO;
import com.fkhwl.scf.entity.dto.WaybillOperationHistoryDTO;
import com.fkhwl.scf.entity.form.WaybillOperationHistoryForm;
import com.fkhwl.scf.entity.vo.WaybillOperationHistoryVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单操作历史表 转换器, 默认提供 1 种转换, 根据业务需求重写转换逻辑或新增转换接口 </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"
 * @date 2019.12.23 15:52
 */
@Mapper
public interface WaybillOperationHistoryViewConverterWrapper extends ViewConverterWrapper<WaybillOperationHistoryVO, WaybillOperationHistoryDTO> {

    /**
     * The constant INSTANCE.
     */
    WaybillOperationHistoryViewConverterWrapper INSTANCE = Mappers.getMapper(WaybillOperationHistoryViewConverterWrapper.class);

    /**
     * form转dto
     *
     * @param waybillOperationHistoryForm form
     * @return dto
     */
    WaybillOperationHistoryDTO dto(WaybillOperationHistoryForm waybillOperationHistoryForm);
    /**
     * PushWaybillForm
     *
     * @param pushWaybillDTO form
     * @return dto
     */
    WaybillOperationHistoryDTO dto(PushWaybillDTO pushWaybillDTO);
}
