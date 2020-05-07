package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfConfigDTO;
import com.fkhwl.scf.entity.po.ScfConfig;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 系统配置 服务接口 </p>
 *
 * @author sj
 * @email sj@fkhwl.com
 * @since 2020-02-19
 */
public interface ScfConfigRepositoryService extends BaseService<ScfConfig> {

    /**
     * 分页查询企业
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页部门数据 page
     */
    IPage<ScfConfigDTO> findPage(IPage<ScfConfigDTO> page, Map<String, Object> params);
    /**
     * 获取所有的父类
     * @return
     */
    List<ScfConfig>  findAllParentList();

    /**
     * 获取配置
     * @param configKey
     * @return
     */
    ScfConfig getConfigByConfigKey(String configKey);

    /**
     * 获取配置列表
     * @param configKey
     * @return
     */
    List<ScfConfig> getConfigsByParentConfigKey(String configKey);

    /**
     * 获取子级配置列表
     * @return List<ScfConfig>
     */
    List<ScfConfig> getConfigByParentId(Long parentId);
}

