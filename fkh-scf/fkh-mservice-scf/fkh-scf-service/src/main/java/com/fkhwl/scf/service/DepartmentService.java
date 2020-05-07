package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.DepartmentDTO;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限系统资源表 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface DepartmentService  {
    /**
     * 分页查询部门
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页部门数据 page
     */
    IPage<DepartmentDTO> listPage(IPage<DepartmentDTO> page, Map<String, Object> params);

    /**
     * 获取部门详情
     *
     * @param id  部门id
     * @return 角色详情DTO optional
     */
    DepartmentDTO getDetail(Long id);

    /**
     * 新增和更新部门
     *
     * @param departmentDTO 部门实体
     */
    void saveOrUpdate(DepartmentDTO departmentDTO);

    /**
     * 删除部门
     *
     * @param deptIds 部门ids
     */
    void delete(String deptIds);
}

