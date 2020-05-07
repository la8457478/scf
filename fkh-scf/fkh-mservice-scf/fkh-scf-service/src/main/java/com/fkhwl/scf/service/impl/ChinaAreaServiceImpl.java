package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.entity.dto.ChinaAreaDTO;
import com.fkhwl.scf.service.ChinaAreaRepositoryService;
import com.fkhwl.scf.service.ChinaAreaService;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 行政区域表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class ChinaAreaServiceImpl implements ChinaAreaService {

    private final ChinaAreaRepositoryService chinaAreaRepositoryService;

    @Override
    public List<ChinaAreaDTO> listParentId(Long parentId) {
        return chinaAreaRepositoryService.listParentId(parentId);
    }
}
