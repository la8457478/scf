package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.AccountBillDTO;
import com.fkhwl.scf.entity.po.AccountBill;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 账单 服务层转换器, 提供 po 和 dto 互转 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.28 13:57
 */
@Mapper
public interface AccountBillServiceConverterWrapper extends ServiceConverterWrapper<AccountBillDTO, AccountBill> {

    AccountBillServiceConverterWrapper INSTANCE = Mappers.getMapper(AccountBillServiceConverterWrapper.class);
}
