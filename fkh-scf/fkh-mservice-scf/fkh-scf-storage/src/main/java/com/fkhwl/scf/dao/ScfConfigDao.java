package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfConfigDTO;
import com.fkhwl.scf.entity.po.ScfConfig;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 系统配置 Dao 接口  </p>
 *
 * @author sj
 * @email sj@fkhwl.com
 * @since 2020-02-19
 */
@Mapper
public interface ScfConfigDao extends BaseDao<ScfConfig> {
    /**
     * 分页查询角色列表
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 角色列表 page
     */
    IPage<ScfConfigDTO> findPage(@Param("page") IPage<ScfConfigDTO> page,
                                @Param("map") Map<String, Object> params);
    /**
     * 获取所有的父类
     * @return
     */
    List<ScfConfig> findAllParentList();

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
     * @return List<AppConfig>
     */
    List<ScfConfig> getConfigByParentId(Long parentId);
}
