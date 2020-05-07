package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AuthFunctionDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限系统资源表 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface AuthFunctionService  {
    /**
     * 分页查询权限系统资源
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页用户数据 page
     */
    IPage<AuthFunctionDTO> listPage(IPage<AuthFunctionDTO> page, Map<String, Object> params);

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

