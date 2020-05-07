package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.entity.dto.ChinaAreaDTO;
import com.fkhwl.scf.entity.po.ChinaArea;
import com.fkhwl.scf.dao.ChinaAreaDao;
import com.fkhwl.scf.service.ChinaAreaRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 行政区域表 服务接口实现类 </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.12 19:08
 */
@Slf4j
@Service
public class ChinaAreaRepositoryServiceImpl extends BaseServiceImpl<ChinaAreaDao, ChinaArea> implements ChinaAreaRepositoryService {

    @Override
    public List<ChinaAreaDTO> listParentId(Long parentId) {
        return baseMapper.listParentId(parentId);
    }
}
