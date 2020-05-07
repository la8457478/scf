package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;
import com.fkhwl.scf.entity.po.ScfUserConfig;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户配置关系表 Dao 接口  </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.02.29 16:22
 */
@Mapper
public interface ScfUserConfigDao extends BaseDao<ScfUserConfig> {

    /**
     * 分页查询用户配置列表
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 用户配置列表 page
     */
    IPage<ScfUserConfigDTO> findPage(@Param("page") IPage<ScfUserConfigDTO> page,
                                     @Param("map") Map<String, Object> params);

    /**
     * 获取用户自己的配置列表
     * @param configKey
     * @return
     */
    List<ScfUserConfigDTO> getUserConfigsByConfigIdList(@Param("configKey")String configKey,@Param("userId")Long userId);

    /**
     * 获取用户配置
     * @param configKey
     * @return
     */
    ScfUserConfigDTO getByConfigKey(@Param("configKey")String configKey,@Param("userId")Long userId);
}
