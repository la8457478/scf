package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfConfigDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户Service</p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"
 * @date 2020.01.10 17:27
 */
public interface ScfConfigService {
    /**
     * 获取用户详情
     *
     * @param userId 用户ID
     * @return 用户详情DTO optional
     */
    Optional<ScfConfigDTO> findInfo(Long userId);

    /**
     * 获取用户列表
     *
     * @return 用户列表 list
     */
    List<ScfConfigDTO> findList();

    /**
     * 用户分页查询
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页用户数据 page
     */
    IPage<ScfConfigDTO> findPage(IPage<ScfConfigDTO> page, Map<String, Object> params);

    /**
     * 用户新增和更新
     *
     * @param userDTO 用户实体
     */
    void saveOrUpdate(ScfConfigDTO userDTO);

    /**
     * 删除系统配置
     * @param id id
     */
    void delete(Long id);
    /**
     * 获取所有的父类
     * @return
     */
    List<ScfConfigDTO> findAllParentList();


    /**
     * 获取配置
     * @param configKey
     * @return
     */
    ScfConfigDTO getConfigByConfigKey(String configKey);

    /**
     * 获取配置列表
     * @param configKey
     * @return
     */
    List<ScfConfigDTO> getConfigsByParentConfigKey(String configKey);

    /**
     * 获取子级配置列表
     * @return List<ScfConfig>
     */
    List<ScfConfigDTO> getConfigByParentId(Long parentId);
}
