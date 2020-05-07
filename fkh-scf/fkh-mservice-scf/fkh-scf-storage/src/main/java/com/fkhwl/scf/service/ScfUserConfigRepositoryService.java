package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;
import com.fkhwl.scf.entity.po.ScfUserConfig;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户配置关系表 服务接口 </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.02.29 16:36
 */
public interface ScfUserConfigRepositoryService extends BaseService<ScfUserConfig> {

    /**
     * 分页查询用户配置
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页用户配置数据 page
     */
    IPage<ScfUserConfigDTO> findPage(IPage<ScfUserConfigDTO> page, Map<String, Object> params);

    /**
     * 获取用户自己的配置列表
     * @param configKey
     * @return
     */
    List<ScfUserConfigDTO> getUserConfigsByConfigIdList(String configKey,Long userId);

    /**
     * 获取用户配置
     * @param configKey
     * @return
     */
    ScfUserConfigDTO getByConfigKey(String configKey, Long userId);
}

