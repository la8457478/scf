package com.fkhwl.scf.service;

import com.fkhwl.scf.entity.dto.ChinaAreaDTO;

import java.util.List;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 行政区域表 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface ChinaAreaService {

    /**
     * 根据父级id查询行政区域
     *
     * @param parentId 父级id
     * @return 返回行政区域数据
     */
    List<ChinaAreaDTO> listParentId(Long parentId);
}

