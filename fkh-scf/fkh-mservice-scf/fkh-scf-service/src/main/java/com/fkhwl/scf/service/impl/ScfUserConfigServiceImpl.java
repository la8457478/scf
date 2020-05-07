package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;
import com.fkhwl.scf.entity.po.ScfUserConfig;
import com.fkhwl.scf.service.ScfUserConfigRepositoryService;
import com.fkhwl.scf.service.ScfUserConfigService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"
 * @date 2020.01.10 17:27
 */
@Service
@AllArgsConstructor
public class ScfUserConfigServiceImpl implements ScfUserConfigService {

    private final ScfUserConfigRepositoryService scfUserConfigRepositoryService;

    @Override
    public IPage<ScfUserConfigDTO> findPage(IPage<ScfUserConfigDTO> page, Map<String, Object> params) {
        return scfUserConfigRepositoryService.findPage(page, params);
    }

    @Override
    public void saveOrUpdate(ScfUserConfigDTO scfUserConfigDTO) {
        String[] idAndConfigStatusArray = scfUserConfigDTO.getIdAndConfigIdAndConfigStatus().split(",");

        for (String item : idAndConfigStatusArray) {
            String[] idAndConfigStatusItem = item.split("-");
            ScfUserConfig scfUserConfig = scfUserConfigRepositoryService.getById(Long.valueOf(idAndConfigStatusItem[0]));
            if (scfUserConfig == null) {
                scfUserConfig = new ScfUserConfig();
                scfUserConfig.setOwnerId(scfUserConfigDTO.getOwnerId());
                scfUserConfig.setConfigId(Long.valueOf(idAndConfigStatusItem[1]));
            }
            scfUserConfig.setConfigStatus(Integer.valueOf(idAndConfigStatusItem[2]));
            scfUserConfig.setConfigValue(idAndConfigStatusItem[3]);
            scfUserConfigRepositoryService.saveOrUpdate(scfUserConfig);
        }
    }
    /**
     * 获取用户自己的配置列表
     * @param configKey
     * @return
     */
    @Override
    public List<ScfUserConfigDTO> getUserConfigsByConfigIdList(String configKey,Long userId){
        return scfUserConfigRepositoryService.getUserConfigsByConfigIdList( configKey, userId);
    }

    @Override
    public ScfUserConfigDTO getByConfigKey(String configKey, Long userId) {
        return scfUserConfigRepositoryService.getByConfigKey(configKey, userId);
    }
}
