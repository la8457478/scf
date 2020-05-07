package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfUserDTO;
import com.fkhwl.scf.entity.po.ScfUser;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 管理用户表 Dao 接口  </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Mapper
public interface ScfUserDao extends BaseDao<ScfUser> {

    /**
     * 分页查询用户列表
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 用户列表 page
     */
    IPage<ScfUserDTO> listPage(@Param("page") IPage<ScfUserDTO> page,
                               @Param("map") Map<String, Object> params);

    /**
     * 分页查询客户列表
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 客户列表 page
     */
    IPage<ScfUserDTO> listPageCustomer(@Param("page") IPage<ScfUserDTO> page,
                               @Param("map") Map<String, Object> params);

    /**
     * 根据参数更新用户信息
     */
    void updateByParams(@Param("map")Map<String, Object> params);
}
