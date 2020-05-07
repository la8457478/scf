package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ScfConfigProvider;
import com.fkhwl.scf.entity.dto.ScfConfigDTO;
import com.fkhwl.scf.service.ScfConfigService;

import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
public class ScfConfigProviderImpl implements ScfConfigProvider {

    /** User service */
    private final ScfConfigService scfConfigService;

    /**
     * Find info template user dto
     *
     * @param username username
     * @return the template user dto
     */
    @Override
    public ScfConfigDTO getByUsername(String username) {
//        return scfConfigService.getByUsername(username).orElse(null);
        return null;
    }

    @Override
    public Optional<ScfConfigDTO> findInfo(Long userId) {
        return scfConfigService.findInfo(userId);
    }

    @Override
    public List<ScfConfigDTO> findList() {
        return scfConfigService.findList();
    }

    @Override
    public IPage<ScfConfigDTO> findPage(IPage<ScfConfigDTO> page, Map<String, Object> params) {
        return scfConfigService.findPage(page, params);
    }

    @Override
    public void saveOrUpdate(ScfConfigDTO userDTO) {
        scfConfigService.saveOrUpdate(userDTO);
    }

    @Override
    public void delete(Long id) {
        scfConfigService.delete(id);
    }
    /**
     * 获取所有的父类
     * @return
     */
    @Override
    public List<ScfConfigDTO> findAllParentList(){
        return scfConfigService.findAllParentList();
    }

    /**
     * 获取配置
     * @param configKey
     * @return
     */
    @Override
    public ScfConfigDTO getConfigByConfigKey(String configKey){
        return scfConfigService.getConfigByConfigKey(configKey);
    }

    /**
     * 获取配置列表
     * @param configKey
     * @return
     */
    @Override
    public List<ScfConfigDTO> getConfigsByParentConfigKey(String configKey){
        return scfConfigService.getConfigsByParentConfigKey(configKey);
    }

    /**
     * 获取子级配置列表
     * @return List<ScfConfig>
     */
    @Override
    public List<ScfConfigDTO> getConfigByParentId(Long parentId){
        return scfConfigService.getConfigByParentId( parentId);
    }

    @Override
    public void updateByIdAndStatus(String idAndConfigStatus){
        String[] idAndConfigStatusArray = idAndConfigStatus.split(",");
        for (String item : idAndConfigStatusArray) {
            String[] idAndConfigStatusItem = item.split("-");
            Optional<ScfConfigDTO> scfConfig = scfConfigService.findInfo(Long.valueOf(idAndConfigStatusItem[0]));
            ScfConfigDTO scfConfigDTO =scfConfig.orElse(null);
            if (scfConfigDTO!=null) {
                scfConfigDTO.setConfigStatus(Integer.valueOf(idAndConfigStatusItem[2]));
                scfConfigService.saveOrUpdate(scfConfigDTO);
            }
        }
    }
}
