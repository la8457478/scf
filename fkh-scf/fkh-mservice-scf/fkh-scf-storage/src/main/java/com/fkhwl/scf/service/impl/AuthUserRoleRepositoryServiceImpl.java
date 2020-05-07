package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.entity.po.AuthUserRole;
import com.fkhwl.scf.dao.AuthUserRoleDao;
import com.fkhwl.scf.service.AuthUserRoleRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户角色中间表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
public class AuthUserRoleRepositoryServiceImpl extends BaseServiceImpl<AuthUserRoleDao, AuthUserRole> implements AuthUserRoleRepositoryService {

}
