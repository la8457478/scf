package com.fkhwl.scf.provider;

import com.fkhwl.scf.ChinaAreaProvider;
import com.fkhwl.scf.entity.dto.ChinaAreaDTO;
import com.fkhwl.scf.service.ChinaAreaService;

import org.apache.dubbo.config.annotation.Service;

import java.util.List;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限Dubbo接口</p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com" @fkhwl.com
 * @date 2020.01.10 19:24
 */
@Service
@AllArgsConstructor
public class ChinaAreaProviderImpl implements ChinaAreaProvider {

    private final ChinaAreaService chinaAreaService;

    @Override
    public List<ChinaAreaDTO> listParentId(Long parentId) {
        return chinaAreaService.listParentId(parentId);
    }
}
