package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ScfUserConfigProvider;
import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;
import com.fkhwl.scf.service.ScfUserConfigService;

import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Title: 系统配置dobbu服务实现</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date:
 * updatetime:
 * reason:
 */
@Service
@AllArgsConstructor
public class ScfUserConfigProviderImpl implements ScfUserConfigProvider {

    /** User service */
    private final ScfUserConfigService userConfigService;

    @Override
    public IPage<ScfUserConfigDTO> findPage(IPage<ScfUserConfigDTO> page, Map<String, Object> params) {
        return userConfigService.findPage(page, params);
    }

    @Override
    public void saveOrUpdate(ScfUserConfigDTO scfUserConfigDTO) {
        userConfigService.saveOrUpdate(scfUserConfigDTO);
    }
    /**
     * 获取用户自己的配置列表
     * @param configKey
     * @return
     */
    @Override
    public List<ScfUserConfigDTO> getUserConfigsByConfigIdList(String configKey, Long userId) {
        return userConfigService.getUserConfigsByConfigIdList(configKey,  userId);
    }

    @Override
    public ScfUserConfigDTO getByConfigKey(String configKey, Long userId) {
        return userConfigService.getByConfigKey(configKey,  userId);
    }
}
