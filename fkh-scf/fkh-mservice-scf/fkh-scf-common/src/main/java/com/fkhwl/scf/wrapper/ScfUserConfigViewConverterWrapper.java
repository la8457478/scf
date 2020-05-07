package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;
import com.fkhwl.scf.entity.form.ScfUserConfigForm;
import com.fkhwl.scf.entity.vo.ScfUserConfigVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户配置 转换器, 默认提供 4 种转换, 根据业务需求重写转换逻辑或新增转换接口 </p>
 *
 * @author sj
 * @email sj@fkhwl.com
 * @since 2020-02-19
 */
@Mapper
public interface ScfUserConfigViewConverterWrapper extends ViewConverterWrapper<ScfUserConfigVO, ScfUserConfigDTO> {
    /**
     * vo -> dto: ScfConfigViewConverterWrapper.INSTANCE.to(vo);
     * dto -> vo: ScfConfigViewConverterWrapper.INSTANCE.from(dto);
     */
    ScfUserConfigViewConverterWrapper INSTANCE = Mappers.getMapper(ScfUserConfigViewConverterWrapper.class);
    /**
     * form转dto
     *
     * @param userConfigForm form
     * @return dto
     */
    ScfUserConfigDTO dto(ScfUserConfigForm userConfigForm);
}
