package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.entity.po.AuthRoleFunc;
import com.fkhwl.scf.dao.AuthRoleFuncDao;
import com.fkhwl.scf.service.AuthRoleFuncRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 角色权限中间表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
public class AuthRoleFuncRepositoryServiceImpl extends BaseServiceImpl<AuthRoleFuncDao, AuthRoleFunc> implements AuthRoleFuncRepositoryService {

    @Override
    public List<AuthRoleFunc> listByRoleId(Long roleId) {
        return baseMapper.listByRoleId(roleId);
    }

    @Override
    public void updateDeletedByParams(Map<String, Object> params) {
        baseMapper.updateDeletedByParams(params);
    }
}
