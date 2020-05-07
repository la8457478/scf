package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.DepartmentDTO;
import com.fkhwl.scf.entity.po.Department;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 部门表 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface DepartmentRepositoryService extends BaseService<Department> {

    /**
     * 分页查询部门
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页部门数据 page
     */
    IPage<DepartmentDTO> listPage(IPage<DepartmentDTO> page, Map<String, Object> params);

    /**
     * 根据id删除部门
     *
     */
    int deleteByIds(List<Long> ids);
}

