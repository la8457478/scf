package com.fkhwl.scf.dao;

import com.fkhwl.scf.entity.po.AuthRoleFunc;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 角色权限中间表 Dao 接口  </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Mapper
public interface AuthRoleFuncDao extends BaseDao<AuthRoleFunc> {
    /**
     * 根据角色id查询对应的权限id
     *
     * @return 权限id列表
     */
    List<AuthRoleFunc> listByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据map修改
     *
     */
    void updateDeletedByParams(@Param("map") Map<String, Object> params);
}
