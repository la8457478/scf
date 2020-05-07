package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AuthRoleDTO;
import com.fkhwl.scf.entity.po.AuthRole;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限系统角色表 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface AuthRoleRepositoryService extends BaseService<AuthRole> {
    /**
     * 分页查询角色
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页角色数据 page
     */
    IPage<AuthRoleDTO> listPage(IPage<AuthRoleDTO> page, Map<String, Object> params);

    /**
     * 根据ownerId和roleType查询角色
     *
     * @param ownerId 主账号id
     * @param roleType 角色类型：1.平台超级管理员,2资金方企业主账号,3.借款方企业主账号,4.默认角色,5.普通类角色
     * @return 返回角色信息
     */
    AuthRoleDTO getByOwnerIdAndRoleType(Long ownerId, Integer roleType);

}

