package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AuthFunctionDTO;
import com.fkhwl.scf.entity.po.AuthFunction;
import com.fkhwl.starter.mybatis.service.BaseService;

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
public interface AuthFunctionRepositoryService extends BaseService<AuthFunction> {

    /**
     * 分页查询权限
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页权限数据 page
     */
    IPage<AuthFunctionDTO> listPage(IPage<AuthFunctionDTO> page, Map<String, Object> params);

    /**
     * 查询权限名称
     *
     * @param roleId 角色id
     * @return 返回权限名称数据
     */
    List<String> listFuncKey(Long roleId);
}

