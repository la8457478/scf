package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.entity.po.RepayBill;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 发起的还款订单-发起待出纳确认 服务层转换器, 提供 po 和 dto 互转 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.22 15:48
 */
@Mapper
public interface RepayBillServiceConverterWrapper extends ServiceConverterWrapper<RepayBillDTO, RepayBill> {

    RepayBillServiceConverterWrapper INSTANCE = Mappers.getMapper(RepayBillServiceConverterWrapper.class);
}
