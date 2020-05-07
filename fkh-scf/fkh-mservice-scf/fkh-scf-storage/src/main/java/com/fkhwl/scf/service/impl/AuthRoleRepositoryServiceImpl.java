package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.AuthRoleDao;
import com.fkhwl.scf.entity.dto.AuthRoleDTO;
import com.fkhwl.scf.entity.po.AuthRole;
import com.fkhwl.scf.service.AuthRoleRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限系统角色表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
public class AuthRoleRepositoryServiceImpl extends BaseServiceImpl<AuthRoleDao, AuthRole> implements AuthRoleRepositoryService {

    @Override
    public IPage<AuthRoleDTO> listPage(IPage<AuthRoleDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page, params);
    }

    @Override
    public AuthRoleDTO getByOwnerIdAndRoleType(Long ownerId, Integer roleType) {
        return baseMapper.getByOwnerIdAndRoleType(ownerId, roleType);
    }
}
