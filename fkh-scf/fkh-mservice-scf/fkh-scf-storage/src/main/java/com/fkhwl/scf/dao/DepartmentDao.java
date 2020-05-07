package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.DepartmentDTO;
import com.fkhwl.scf.entity.po.Department;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 部门表 Dao 接口  </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Mapper
public interface DepartmentDao extends BaseDao<Department> {

    /**
     * 分页查询部门列表
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 部门列表 page
     */
    IPage<DepartmentDTO> listPage(@Param("page") IPage<DepartmentDTO> page,
                                  @Param("map") Map<String, Object> params);

    /**
     * 根据id删除部门
     *
     */
    int deleteByIds(@Param("ids") List<Long> ids);
}
