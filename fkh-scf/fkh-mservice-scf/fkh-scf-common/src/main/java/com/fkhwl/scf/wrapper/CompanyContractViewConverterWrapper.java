package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.form.CompanyContractForm;
import com.fkhwl.scf.entity.vo.CompanyContractVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 主体合同：资方与借款方签订的合同 视图层转换器, 提供 vo 和 dto 互转 </p>
 * form 和 dto 互转需要在此接口中自己添加.
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.03 15:12
 */
@Mapper
public interface CompanyContractViewConverterWrapper extends ViewConverterWrapper<CompanyContractVO, CompanyContractDTO> {

    CompanyContractViewConverterWrapper INSTANCE = Mappers.getMapper(CompanyContractViewConverterWrapper.class);

    /**
     * form转dto
     *
     * @param companyContractForm form
     * @return dto
     */
    CompanyContractDTO dto(CompanyContractForm companyContractForm);
}


