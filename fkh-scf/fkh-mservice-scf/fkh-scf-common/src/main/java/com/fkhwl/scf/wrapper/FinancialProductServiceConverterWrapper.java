package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.FinancialProductDTO;
import com.fkhwl.scf.entity.po.FinancialProduct;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 金融产品:暂定 服务层转换器, 提供 po 和 dto 互转 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.06 15:45
 */
@Mapper
public interface FinancialProductServiceConverterWrapper extends ServiceConverterWrapper<FinancialProductDTO, FinancialProduct> {

    FinancialProductServiceConverterWrapper INSTANCE = Mappers.getMapper(FinancialProductServiceConverterWrapper.class);
}
