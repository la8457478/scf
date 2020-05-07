package com.fkhwl.scf.dao;

import com.fkhwl.scf.entity.dto.ChinaAreaDTO;
import com.fkhwl.scf.entity.po.ChinaArea;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 行政区域表 Dao 接口  </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.12 19:08
 */
@Mapper
public interface ChinaAreaDao extends BaseDao<ChinaArea> {

    /**
     * 根据父级id查询行政区域
     *
     * @param parentId 父级id
     * @return 返回行政区域数据
     */
    List<ChinaAreaDTO> listParentId(@Param("parentId") Long parentId);
}
