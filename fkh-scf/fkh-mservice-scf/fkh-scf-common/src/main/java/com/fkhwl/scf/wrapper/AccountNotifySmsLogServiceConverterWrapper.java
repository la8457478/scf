package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.AccountNotifySmsLogDTO;
import com.fkhwl.scf.entity.po.AccountNotifySmsLog;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 短信提醒记录 服务层转换器, 提供 po 和 dto 互转 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.04.08 16:01
 */
@Mapper
public interface AccountNotifySmsLogServiceConverterWrapper extends ServiceConverterWrapper<AccountNotifySmsLogDTO, AccountNotifySmsLog> {

    AccountNotifySmsLogServiceConverterWrapper INSTANCE = Mappers.getMapper(AccountNotifySmsLogServiceConverterWrapper.class);
}
