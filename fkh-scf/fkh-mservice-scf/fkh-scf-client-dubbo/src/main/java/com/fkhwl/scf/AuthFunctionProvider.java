package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AuthFunctionDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: scf权限 Dubbo Service</p>
 *
 * @author chenli
 * @version 1.0.1
 * @email "mailto:chenli@fkhwl.com"@fkhwl.com
 * @date 2020.02.20 10:20
 */
public interface AuthFunctionProvider {

    /**
     * 分页查询权限系统资源
     *
     * @param params 查询参数
     * @return 返回分页用户数据 page
     */
    IPage<AuthFunctionDTO> listPage(Map<String, Object> params);

    /**
     * 获取权限详情
     *
     * @param id  角色id
     * @return 角色详情DTO optional
     */
    AuthFunctionDTO getDetail(Long id);

    /**
     * 新增和更新权限系统资源
     *
     * @param authFunctionDTO 权限实体
     */
    void saveOrUpdate(AuthFunctionDTO authFunctionDTO);

    /**
     * 删除权限系统资源表
     *
     * @param id 权限d
     */
    void delete(Long id);

    /**
     * 查询权限名称
     *
     * @param roleId 角色id
     * @return 返回权限名称数据
     */
    List<String> listFuncKey(Long roleId);
}
