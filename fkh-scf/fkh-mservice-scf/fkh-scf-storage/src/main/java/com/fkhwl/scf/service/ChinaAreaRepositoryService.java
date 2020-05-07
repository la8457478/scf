package com.fkhwl.scf.service;

import com.fkhwl.scf.entity.dto.ChinaAreaDTO;
import com.fkhwl.scf.entity.po.ChinaArea;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.List;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 行政区域表 服务接口 </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.12 19:08
 */
public interface ChinaAreaRepositoryService extends BaseService<ChinaArea> {

    /**
     * 根据父级id查询行政区域
     *
     * @param parentId 父级id
     * @return 返回行政区域数据
     */
    List<ChinaAreaDTO> listParentId(Long parentId);
}

