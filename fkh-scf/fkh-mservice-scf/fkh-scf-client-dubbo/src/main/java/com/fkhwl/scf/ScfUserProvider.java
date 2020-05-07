package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfUserDTO;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: scf用户Dubbo Service</p>
 *
 * @author liuan
 * @version 1.0.1
 * @email "mailto:liuan@fkhwl.com"@fkhwl.com
 * @date 2020.02.20 10:20
 */
public interface ScfUserProvider {

    ScfUserDTO getByUserName(String username);

    /**
     * 登陆判断账号密码
     * @param username
     * @param password
     * @return
     */
    ScfUserDTO getByUserNameAndPassword(String username, String password);

    /**
     * 修改登录密码
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    void modifyLoginPwd(Long id, String oldPassword, String newPassword);

    /**
     * 分页查询用户
     *
     * @param params 查询参数
     * @return 返回分页用户数据 page
     */
    IPage<ScfUserDTO> listPage(Map<String, Object> params);

    /**
     * 新增和更新用户
     *
     * @param scfUserDTO 用户实体
     */
    void saveOrUpdate(ScfUserDTO scfUserDTO);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void delete(Long id);

    /**
     * 获取用户详情
     * @param userId 用户id
     * @return
     */
    ScfUserDTO getDetail(Long userId);
}
