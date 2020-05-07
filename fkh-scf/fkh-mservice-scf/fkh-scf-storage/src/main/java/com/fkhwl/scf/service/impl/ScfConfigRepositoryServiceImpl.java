package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.ScfConfigDao;
import com.fkhwl.scf.entity.dto.ScfConfigDTO;
import com.fkhwl.scf.entity.po.ScfConfig;
import com.fkhwl.scf.service.ScfConfigRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 系统配置 服务接口实现类 </p>
 *
 * @author sj
 * @email sj@fkhwl.com
 * @since 2020-02-19
 */
@Slf4j
@Service
public class ScfConfigRepositoryServiceImpl extends BaseServiceImpl<ScfConfigDao, ScfConfig> implements ScfConfigRepositoryService {
    @Override
    public IPage<ScfConfigDTO> findPage(IPage<ScfConfigDTO> page, Map<String, Object> params) {
        return baseMapper.findPage(page, params);
    }
    /**
     * 获取所有的父类
     * @return
     */
    @Override
    public List<ScfConfig>  findAllParentList() {
        return baseMapper.findAllParentList();
    }

    @Override
    public ScfConfig getConfigByConfigKey(String configKey) {
        return baseMapper.getConfigByConfigKey(configKey);
    }

    @Override
    public List<ScfConfig> getConfigsByParentConfigKey(String configKey) {
        return baseMapper.getConfigsByParentConfigKey(configKey);
    }

    @Override
    public List<ScfConfig> getConfigByParentId(Long parentId) {
        return baseMapper.getConfigByParentId(parentId);
    }
}
