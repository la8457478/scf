package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.starter.mybatis.service.BaseService;
import com.fkhwl.scf.entity.dto.TemplateUserDTO;
import com.fkhwl.scf.entity.po.TemplateUser;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户表 服务接口 </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"@fkhwl.com
 * @date 2020.01.27 18:29
 */
public interface TemplateUserRepositoryService extends BaseService<TemplateUser> {

    /**
     * 分页查询用户列表
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 用户列表 page
     */
    IPage<TemplateUserDTO> findPage(IPage<TemplateUserDTO> page, Map<String, Object> params);
}

