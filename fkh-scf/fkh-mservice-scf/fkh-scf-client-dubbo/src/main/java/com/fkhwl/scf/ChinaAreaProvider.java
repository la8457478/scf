package com.fkhwl.scf;


import com.fkhwl.scf.entity.dto.ChinaAreaDTO;

import java.util.List;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: scf权限 Dubbo Service</p>
 *
 * @author chenli
 * @version 1.0.1
 * @email "mailto:chenli@fkhwl.com"@fkhwl.com
 * @date 2020.02.20 10:20
 */
public interface ChinaAreaProvider {

    /**
     * 根据父级id查询行政区域
     *
     * @param parentId 父级id
     * @return 返回行政区域数据
     */
    List<ChinaAreaDTO> listParentId(Long parentId);
}
