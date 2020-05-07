package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfConfigDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: scf用户Dubbo Service</p>
 *
 * @author liuan
 * @version 1.0.1
 * @email "mailto:liuan@fkhwl.com"@fkhwl.com
 * @date 2020.02.20 10:20
 */
public interface ScfConfigProvider {

    ScfConfigDTO getByUsername(String username);

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

    /**
     * 通过id修改状态
     *
     * @param idAndConfigStatus id-configId-configStatus，多个之间使用英文逗号分隔
     */
    void updateByIdAndStatus(String idAndConfigStatus);
}
