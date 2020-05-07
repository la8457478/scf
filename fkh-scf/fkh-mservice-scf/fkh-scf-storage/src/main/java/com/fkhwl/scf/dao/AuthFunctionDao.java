package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AuthFunctionDTO;
import com.fkhwl.scf.entity.po.AuthFunction;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限系统资源表 Dao 接口  </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Mapper
public interface AuthFunctionDao extends BaseDao<AuthFunction> {

    /**
     * 分页查询权限列表
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 权限列表 page
     */
    IPage<AuthFunctionDTO> listPage(@Param("page") IPage<AuthFunctionDTO> page,
                                    @Param("map") Map<String, Object> params);

    /**
     * 查询权限名称
     *
     * @param roleId 角色id
     * @return 返回权限名称数据 page
     */
    List<String> listFuncKey(@Param("roleId") Long roleId);
}
