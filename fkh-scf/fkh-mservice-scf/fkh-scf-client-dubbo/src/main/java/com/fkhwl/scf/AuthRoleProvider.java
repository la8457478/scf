package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AuthRoleDTO;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: scf角色 Dubbo Service</p>
 *
 * @author chenli
 * @version 1.0.1
 * @email "mailto:chenli@fkhwl.com"@fkhwl.com
 * @date 2020.02.20 10:20
 */
public interface AuthRoleProvider {

    /**
     * 分页查询角色
     *
     * @param params 查询参数
     * @return 返回分页用户数据 page
     */
    IPage<AuthRoleDTO> listPage(Map<String, Object> params);

    /**
     * 获取角色详情
     *
     * @param id  角色id
     * @return 角色详情DTO optional
     */
    AuthRoleDTO getDetail(Long id);

    /**
     * 新增和更新角色
     *
     * @param authRoleDTO 角色实体
     */
    void saveOrUpdate(AuthRoleDTO authRoleDTO);

    /**
     * 删除角色
     *
     * @param id 角色id
     */
    void delete(Long id);

    /**
     * 根据ownerId和roleType查询角色
     *
     * @param ownerId 主账号id
     * @param roleType 角色类型：1.平台超级管理员,2资金方企业主账号,3.借款方企业主账号,4.默认角色,5.普通类角色
     * @return 返回角色信息
     */
    AuthRoleDTO getByOwnerIdAndRoleType(Long ownerId, Integer roleType);
}
