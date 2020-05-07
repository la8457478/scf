package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.form.CompanyForm;
import com.fkhwl.scf.entity.vo.CompanyVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:企业表 转换器, 默认提供 4 种转换, 根据业务需求重写转换逻辑或新增转换接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Mapper
public interface CompanyViewConverterWrapper extends ViewConverterWrapper<CompanyVO, CompanyDTO> {

    CompanyViewConverterWrapper INSTANCE = Mappers.getMapper(CompanyViewConverterWrapper.class);

    /**
     * form转dto
     *
     * @param companyForm form
     * @return dto
     */
    CompanyDTO dto(CompanyForm companyForm);
}
