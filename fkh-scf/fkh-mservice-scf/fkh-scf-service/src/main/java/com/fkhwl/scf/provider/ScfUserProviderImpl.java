package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ScfUserProvider;
import com.fkhwl.scf.entity.dto.ScfUserDTO;
import com.fkhwl.scf.service.ScfUserService;
import com.fkhwl.starter.mybatis.support.Condition;

import org.apache.dubbo.config.annotation.Service;

import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户相关Dubbo接口</p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com" @fkhwl.com
 * @date 2020.01.10 19:24
 */
@Service
@AllArgsConstructor
public class ScfUserProviderImpl implements ScfUserProvider {

    /** User service */
    private final ScfUserService userService;

    /**
     * Find info template user dto
     *
     * @param username username
     * @return the template user dto
     */
    @Override
    public ScfUserDTO getByUserName(String username) {
        return userService.getByUserName(username).orElse(null);
    }
    /**
     * Find info template user dto
     *
     * @param username username
     * @return the template user dto
     */
    @Override
    public ScfUserDTO getByUserNameAndPassword(String username,String password) {
        return userService.getByUserNameAndPassword(username,password).orElse(null);
    }

    @Override
    public void modifyLoginPwd(Long id, String oldPassword, String newPassword) {
         userService.modifyLoginPwd(id,oldPassword,newPassword);
    }

    @Override
    public IPage<ScfUserDTO> listPage(Map<String, Object> params) {
        return userService.listPage(Condition.getPage(params), params);
    }

    @Override
    public void saveOrUpdate(ScfUserDTO scfUserDTO) {
        userService.saveOrUpdate(scfUserDTO);
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public ScfUserDTO getDetail(Long userId) {
        return userService.getDetail(userId).orElse(null);
    }
}
