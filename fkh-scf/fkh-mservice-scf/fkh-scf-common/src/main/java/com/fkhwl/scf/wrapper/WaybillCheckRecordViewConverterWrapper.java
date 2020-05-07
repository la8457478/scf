package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.WaybillCheckRecordDTO;
import com.fkhwl.scf.entity.form.WaybillCheckRecordForm;
import com.fkhwl.scf.entity.vo.WaybillCheckRecordVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单查阅记录表 转换器, 默认提供 1 种转换, 根据业务需求重写转换逻辑或新增转换接口 </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"
 * @date 2019.12.23 15:52
 */
@Mapper
public interface WaybillCheckRecordViewConverterWrapper extends ViewConverterWrapper<WaybillCheckRecordVO, WaybillCheckRecordDTO> {

    /**
     * The constant INSTANCE.
     */
    WaybillCheckRecordViewConverterWrapper INSTANCE = Mappers.getMapper(WaybillCheckRecordViewConverterWrapper.class);

    /**
     * form转dto
     *
     * @param waybillCheckRecordForm form
     * @return dto
     */
    WaybillCheckRecordDTO dto(WaybillCheckRecordForm waybillCheckRecordForm);

}
