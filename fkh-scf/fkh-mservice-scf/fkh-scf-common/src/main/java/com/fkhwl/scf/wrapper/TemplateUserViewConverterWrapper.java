package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.TemplateUserDTO;
import com.fkhwl.scf.entity.form.TemplateUserForm;
import com.fkhwl.scf.entity.vo.TemplateUserVO;
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
public interface TemplateUserViewConverterWrapper extends ViewConverterWrapper<TemplateUserVO, TemplateUserDTO> {

    /**
     * The constant INSTANCE.
     */
    TemplateUserViewConverterWrapper INSTANCE = Mappers.getMapper(TemplateUserViewConverterWrapper.class);

    /**
     * form转dto
     *
     * @param userForm form
     * @return dto
     */
    TemplateUserDTO dto(TemplateUserForm userForm);
}
