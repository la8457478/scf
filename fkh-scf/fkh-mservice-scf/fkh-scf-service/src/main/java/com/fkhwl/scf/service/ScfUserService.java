package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfUserDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 管理用户表 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface ScfUserService  {
    /**
     * 获取用户列表
     *
     * @return 用户列表 list
     */
    List<ScfUserDTO> listScfUser();

    /**
     * 获取用户详情
     *
     * @param userId 用户ID
     * @return 用户详情DTO optional
     */
    Optional<ScfUserDTO> getDetail(Long userId);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    Optional<ScfUserDTO> getByUserName(String username);

    /**
     * 根据用户名，密码 获取用户
     * @param username
     * @param password
     * @return
     */
    Optional<ScfUserDTO> getByUserNameAndPassword(String username,String password);

    /**
     * 修改登录面
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    void modifyLoginPwd(Long id, String oldPassword, String newPassword);

    /**
     * 分页查询用户系统资源
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页用户数据 page
     */
    IPage<ScfUserDTO> listPage(IPage<ScfUserDTO> page, Map<String, Object> params);

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
}

