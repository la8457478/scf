package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.AuthFunctionProvider;
import com.fkhwl.scf.entity.dto.AuthFunctionDTO;
import com.fkhwl.scf.service.AuthFunctionService;
import com.fkhwl.starter.mybatis.support.Condition;

import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限Dubbo接口</p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com" @fkhwl.com
 * @date 2020.01.10 19:24
 */
@Service
@AllArgsConstructor
public class AuthFunctionProviderImpl implements AuthFunctionProvider {

    private final AuthFunctionService authFunctionService;

    @Override
    public IPage<AuthFunctionDTO> listPage(Map<String, Object> params) {
        return authFunctionService.listPage(Condition.getPage(params), params);
    }

    @Override
    public AuthFunctionDTO getDetail(Long id) {
        return authFunctionService.getDetail(id);
    }

    @Override
    public void saveOrUpdate(AuthFunctionDTO authFunctionDTO) {
        authFunctionService.saveOrUpdate(authFunctionDTO);
    }

    @Override
    public void delete(Long id) {
        authFunctionService.delete(id);
    }

    @Override
    public List<String> listFuncKey(Long roleId) {
        return authFunctionService.listFuncKey(roleId);
    }
}
