package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.DepartmentDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.ProjectListDTO;
import com.fkhwl.scf.entity.po.Project;
import com.fkhwl.starter.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 项目 Dao 接口  </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-20
 */
@Mapper
public interface ProjectDao extends BaseDao<Project> {

    IPage<ProjectListDTO> listPage(@Param("page") IPage<ProjectListDTO> page,
                               @Param("map") Map<String, Object> params);

    void updateSubitemClaimsOrderCount(Long projectId, Integer count);
}
