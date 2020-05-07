package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.ScfUserConfigDao;
import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;
import com.fkhwl.scf.entity.po.ScfUserConfig;
import com.fkhwl.scf.service.ScfUserConfigRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户配置关系表 服务接口实现类 </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.02.29 17:24
 */
@Slf4j
@Service
public class ScfUserConfigRepositoryServiceImpl extends BaseServiceImpl<ScfUserConfigDao, ScfUserConfig> implements ScfUserConfigRepositoryService {

    @Override
    public IPage<ScfUserConfigDTO> findPage(IPage<ScfUserConfigDTO> page, Map<String, Object> params) {
        return baseMapper.findPage(page, params);
    }


    /**
     * 获取用户自己的配置列表
     * @param configKey
     * @return
     */
    @Override
    public List<ScfUserConfigDTO> getUserConfigsByConfigIdList(String configKey,Long userId){
        return baseMapper.getUserConfigsByConfigIdList( configKey, userId);
    }

    @Override
    public ScfUserConfigDTO getByConfigKey(String configKey, Long userId) {
        return baseMapper.getByConfigKey(configKey, userId);
    }
}
