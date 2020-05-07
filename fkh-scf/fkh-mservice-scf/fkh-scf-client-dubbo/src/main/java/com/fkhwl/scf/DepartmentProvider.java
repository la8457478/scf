package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.DepartmentDTO;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: scf部门 Dubbo Service</p>
 *
 * @author chenli
 * @version 1.0.1
 * @email "mailto:chenli@fkhwl.com"@fkhwl.com
 * @date 2020.02.20 10:20
 */
public interface DepartmentProvider {

    /**
     * 分页查询部门
     *
     * @param params 查询参数
     * @return 返回分页部门数据 page
     */
    IPage<DepartmentDTO> listPage(Map<String, Object> params);

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
