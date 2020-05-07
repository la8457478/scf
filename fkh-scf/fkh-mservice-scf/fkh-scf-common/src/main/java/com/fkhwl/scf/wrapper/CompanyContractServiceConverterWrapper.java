package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.po.CompanyContract;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 主体合同：资方与借款方签订的合同 服务层转换器, 提供 po 和 dto 互转 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.03 15:12
 */
@Mapper
public interface CompanyContractServiceConverterWrapper extends ServiceConverterWrapper<CompanyContractDTO, CompanyContract> {

    CompanyContractServiceConverterWrapper INSTANCE = Mappers.getMapper(CompanyContractServiceConverterWrapper.class);
}
