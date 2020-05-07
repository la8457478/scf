package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户配置关系表Service</p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com"
 * @date 2020.01.10 17:27
 */
public interface ScfUserConfigService {

    /**
     * 用户配置分页查询
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页用户数据 page
     */
    IPage<ScfUserConfigDTO> findPage(IPage<ScfUserConfigDTO> page, Map<String, Object> params);

    /**
     * 用户配置新增和更新
     *
     * @param scfUserConfigDTO 用户配置实体
     */
    void saveOrUpdate(ScfUserConfigDTO scfUserConfigDTO);

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
