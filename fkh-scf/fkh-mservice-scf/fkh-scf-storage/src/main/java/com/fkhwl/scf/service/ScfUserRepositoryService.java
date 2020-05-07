package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfUserDTO;
import com.fkhwl.scf.entity.po.ScfUser;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 管理用户表 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface ScfUserRepositoryService extends BaseService<ScfUser> {

    /**
     * 分页查询用户
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页用户数据 page
     */
    IPage<ScfUserDTO> listPage(IPage<ScfUserDTO> page, Map<String, Object> params);

    /**
     * 分页查询客户
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页客户数据 page
     */
    IPage<ScfUserDTO> listPageCustomer(IPage<ScfUserDTO> page, Map<String, Object> params);

    /**
     * 根据参数更新用户信息
     */
    void updateByParams(Map<String, Object> params);
}

