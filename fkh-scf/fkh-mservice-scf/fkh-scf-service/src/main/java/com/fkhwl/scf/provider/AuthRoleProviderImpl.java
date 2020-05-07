package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.AuthRoleProvider;
import com.fkhwl.scf.entity.dto.AuthRoleDTO;
import com.fkhwl.scf.service.AuthRoleService;
import com.fkhwl.starter.mybatis.support.Condition;

import org.apache.dubbo.config.annotation.Service;

import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:角色Dubbo接口</p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com" @fkhwl.com
 * @date 2020.01.10 19:24
 */
@Service
@AllArgsConstructor
public class AuthRoleProviderImpl implements AuthRoleProvider {

    private final AuthRoleService authRoleService;

    @Override
    public IPage<AuthRoleDTO> listPage( Map<String, Object> params) {
        return authRoleService.listPage(Condition.getPage(params), params);
    }

    @Override
    public AuthRoleDTO getDetail(Long id) {
        return authRoleService.getDetail(id);
    }

    @Override
    public void saveOrUpdate(AuthRoleDTO authRoleDTO) {
        authRoleService.saveOrUpdate(authRoleDTO);
    }

    @Override
    public void delete(Long id) {
        authRoleService.delete(id);
    }

    @Override
    public AuthRoleDTO getByOwnerIdAndRoleType(Long ownerId, Integer roleType) {
        return authRoleService.getByOwnerIdAndRoleType(ownerId, roleType);
    }
}
