package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.form.ProgramForm;
import com.fkhwl.scf.entity.vo.ProgramVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 计划 视图层转换器, 提供 vo 和 dto 互转 </p>
 * form 和 dto 互转需要在此接口中自己添加.
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.24 14:19
 */
@Mapper
public interface ProgramViewConverterWrapper extends ViewConverterWrapper<ProgramVO, ProgramDTO> {

    ProgramViewConverterWrapper INSTANCE = Mappers.getMapper(ProgramViewConverterWrapper.class);
    /**
     * form转dto
     *
     * @param projectForm form
     * @return dto
     */
    ProgramDTO dto(ProgramForm projectForm);
}


