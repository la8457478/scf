package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AuthRoleDTO;
import com.fkhwl.scf.entity.po.AuthRole;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限系统角色表 Dao 接口  </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Mapper
public interface AuthRoleDao extends BaseDao<AuthRole> {
    /**
     * 分页查询角色列表
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 角色列表 page
     */
    IPage<AuthRoleDTO> listPage(@Param("page") IPage<AuthRoleDTO> page,
                                @Param("map") Map<String, Object> params);

    /**
     * 根据ownerId和roleType查询角色
     *
     * @param ownerId 主账号id
     * @param roleType 角色类型：1.特殊类角色(平台超管，资金方企业主账号，借款方企业主账号)，2.普通类角色
     * @return 返回角色信息
     */
    AuthRoleDTO getByOwnerIdAndRoleType(@Param("ownerId")Long ownerId, @Param("roleType")Integer roleType);
}
