package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.entity.po.AccountNotifySmsLog;
import com.fkhwl.scf.dao.AccountNotifySmsLogDao;
import com.fkhwl.scf.service.AccountNotifySmsLogRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 短信提醒记录 服务接口实现类 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.04.08 15:59
 */
@Slf4j
@Service
public class AccountNotifySmsLogRepositoryServiceImpl extends BaseServiceImpl<AccountNotifySmsLogDao, AccountNotifySmsLog> implements AccountNotifySmsLogRepositoryService {

}
