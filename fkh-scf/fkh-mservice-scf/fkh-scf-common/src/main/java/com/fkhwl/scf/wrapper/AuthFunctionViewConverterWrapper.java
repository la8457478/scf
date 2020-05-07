package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.AuthFunctionDTO;
import com.fkhwl.scf.entity.form.AuthFunctionForm;
import com.fkhwl.scf.entity.vo.AuthFunctionVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:角色表 转换器, 默认提供 4 种转换, 根据业务需求重写转换逻辑或新增转换接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Mapper
public interface AuthFunctionViewConverterWrapper extends ViewConverterWrapper<AuthFunctionVO, AuthFunctionDTO> {

    AuthFunctionViewConverterWrapper INSTANCE = Mappers.getMapper(AuthFunctionViewConverterWrapper.class);

    /**
     * form转dto
     *
     * @param authFunctionForm form
     * @return dto
     */
    AuthFunctionDTO dto(AuthFunctionForm authFunctionForm);
}
