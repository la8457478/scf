package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfConfigDTO;
import com.fkhwl.scf.entity.po.ScfConfig;
import com.fkhwl.scf.service.ScfConfigRepositoryService;
import com.fkhwl.scf.service.ScfConfigService;
import com.fkhwl.scf.wrapper.ScfConfigServiceConverterWrapper;
import com.fkhwl.starter.common.enums.DeleteEnum;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class ScfConfigServiceImpl implements ScfConfigService {

    /** User repository service */
    private final ScfConfigRepositoryService scfConfigRepositoryService;

    /**
     * Find info optional
     *
     * @param userId user id
     * @return the optional
     */
    @Override
    public Optional<ScfConfigDTO> findInfo(Long userId) {
        return Optional.ofNullable(ScfConfigServiceConverterWrapper.INSTANCE.dto(scfConfigRepositoryService.getById(userId)));
    }

    /**
     * Find list list
     *
     * @return the list
     */
    @Override
    public List<ScfConfigDTO> findList() {
        List<ScfConfig> list = scfConfigRepositoryService.list();
        return list.stream().map(ScfConfigServiceConverterWrapper.INSTANCE::dto).collect(Collectors.toList());
    }
    /**
     * 获取所有的父类
     * @return
     */
    @Override
    public List<ScfConfigDTO> findAllParentList() {
        List<ScfConfig> list = scfConfigRepositoryService.findAllParentList();
        return list.stream().map(ScfConfigServiceConverterWrapper.INSTANCE::dto).collect(Collectors.toList());
    }
    /**
     * Find page page
     *
     * @param page   page
     * @param params params
     * @return the page
     */
    @Override
    public IPage<ScfConfigDTO> findPage(IPage<ScfConfigDTO> page, Map<String, Object> params) {
        return scfConfigRepositoryService.findPage(page, params);
    }

    /**
     * Save or update *
     *
     * @param scfConfigDTO scfConfig dto
     */
    @Override
    public void saveOrUpdate(ScfConfigDTO scfConfigDTO) {

        ScfConfig scfConfig = ScfConfigServiceConverterWrapper.INSTANCE.po(scfConfigDTO);
        if (scfConfig.getId() == null) {
            scfConfig.setDeleted(DeleteEnum.N);
        }
//        String configValue = scfConfig.getConfigValue();
//        if(BaseValidate.errorLong(scfConfig.getId())){
//            //新增系统配置
//            Date nowDateTime = ToolsHelper.getCurrentDate();
//            scfConfig.setCreateTime(nowDateTime);
//            scfConfig.setUpdateTime(nowDateTime);
//            this.scfConfigRepositoryService.save(scfConfig);
//        }else {
//            //更新配置
//            ScfConfig dbConfig = scfConfigRepositoryService.getById(scfConfig.getId());
//            if (BaseValidate.errorLong(scfConfig.getParentId())) {
//                scfConfig.setParentId(dbConfig.getParentId());
//            }
//
//            Long parentAppconfigId = scfConfig.getParentId();
//            if (parentAppconfigId != 0) {
//                AppConfig parentAppconfig = appConfigService.get(parentAppconfigId);
//            }
//
//            super.appConfigService.updateBaseInfo(scfConfig);
//            if (scfConfig.getConfigKey().equals(AppConfigEnum.SAME_CITY.getCacheKey())) {
//                super.appConfigService.updateSameCityChildrenInvalid(scfConfig.getId(), scfConfig.getConfigValue());
//            }
//        }
        scfConfigRepositoryService.saveOrUpdate(scfConfig);
    }

    /**
     * Delete *
     *
     * @param id id
     */
    @Override
    public void delete(Long id) {
        scfConfigRepositoryService.removeById(id);
    }

    @Override
    public ScfConfigDTO getConfigByConfigKey(String configKey) {
        return ScfConfigServiceConverterWrapper.INSTANCE.dto(scfConfigRepositoryService.getConfigByConfigKey(configKey));
    }

    @Override
    public List<ScfConfigDTO> getConfigsByParentConfigKey(String configKey) {
        List<ScfConfig> list= scfConfigRepositoryService.getConfigsByParentConfigKey(configKey);

        return list.stream().map(ScfConfigServiceConverterWrapper.INSTANCE::dto).collect(Collectors.toList());
    }

    @Override
    public List<ScfConfigDTO> getConfigByParentId(Long parentId) {
        List<ScfConfig> list= scfConfigRepositoryService.getConfigByParentId(parentId);
        return list.stream().map(ScfConfigServiceConverterWrapper.INSTANCE::dto).collect(Collectors.toList());
    }
}
