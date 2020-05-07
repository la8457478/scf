package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.PushWaybillDTO;
import com.fkhwl.scf.entity.dto.WaybillDTO;
import com.fkhwl.scf.entity.form.PushWaybillForm;
import com.fkhwl.scf.entity.form.WaybillForm;
import com.fkhwl.scf.entity.vo.WaybillVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 系统表 转换器, 默认提供 1 种转换, 根据业务需求重写转换逻辑或新增转换接口 </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"
 * @date 2019.12.23 15:52
 */
@Mapper
public interface WaybillViewConverterWrapper extends ViewConverterWrapper<WaybillVO, WaybillDTO> {

    /**
     * The constant INSTANCE.
     */
    WaybillViewConverterWrapper INSTANCE = Mappers.getMapper(WaybillViewConverterWrapper.class);

    /**
     * form转dto
     *
     * @param waybillForm form
     * @return dto
     */
    WaybillDTO dto(WaybillForm waybillForm);
    /**
     * PushWaybillForm
     *
     * @param pushWaybillDTO form
     * @return dto
     */
    WaybillDTO dto(PushWaybillDTO pushWaybillDTO);
}
