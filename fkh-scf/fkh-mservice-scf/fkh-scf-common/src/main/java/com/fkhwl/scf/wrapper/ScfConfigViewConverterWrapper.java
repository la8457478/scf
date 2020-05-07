package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.ScfConfigDTO;
import com.fkhwl.scf.entity.form.ScfConfigForm;
import com.fkhwl.scf.entity.vo.ScfConfigVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 系统配置 转换器, 默认提供 4 种转换, 根据业务需求重写转换逻辑或新增转换接口 </p>
 *
 * @author sj
 * @email sj@fkhwl.com
 * @since 2020-02-19
 */
@Mapper
public interface ScfConfigViewConverterWrapper extends ViewConverterWrapper<ScfConfigVO, ScfConfigDTO> {
    /**
     * vo -> dto: ScfConfigViewConverterWrapper.INSTANCE.to(vo);
     * dto -> vo: ScfConfigViewConverterWrapper.INSTANCE.from(dto);
     */
    ScfConfigViewConverterWrapper INSTANCE = Mappers.getMapper(ScfConfigViewConverterWrapper.class);
    /**
     * form转dto
     *
     * @param userForm form
     * @return dto
     */
    ScfConfigDTO dto(ScfConfigForm userForm);

    // TODO-SJ: 2020.03.12 金融监管平台：其他的转换如何写？
//    ScfConfigDTO dto(ScfUserConfigForm userForm);
}
