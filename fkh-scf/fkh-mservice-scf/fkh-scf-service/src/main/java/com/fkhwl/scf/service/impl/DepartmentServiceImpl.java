package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.DepartmentDTO;
import com.fkhwl.scf.entity.po.Department;
import com.fkhwl.scf.service.DepartmentRepositoryService;
import com.fkhwl.scf.service.DepartmentService;
import com.fkhwl.scf.service.ScfUserRepositoryService;
import com.fkhwl.scf.utils.Const;
import com.fkhwl.scf.utils.Tools;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.DepartmentServiceConverterWrapper;
import com.fkhwl.starter.common.enums.DeleteEnum;
import com.fkhwl.starter.core.support.AssertUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 部门表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class DepartmentServiceImpl  implements DepartmentService {

    private final DepartmentRepositoryService departmentRepositoryService;

    private final ScfUserRepositoryService scfUserRepositoryService;

    @Override
    public IPage<DepartmentDTO> listPage(IPage<DepartmentDTO> page, Map<String, Object> params) {
        return departmentRepositoryService.listPage(page, params);
    }

    @Override
    public DepartmentDTO getDetail(Long id) {
        return DepartmentServiceConverterWrapper.INSTANCE.dto(departmentRepositoryService.getById(id));
    }

    @Override
    public void saveOrUpdate(DepartmentDTO departmentDTO) {
        Date currentDate = new Date();
        Department department = DepartmentServiceConverterWrapper.INSTANCE.po(departmentDTO);
        department.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(departmentDTO.getId())) {
            department.setDeleted(DeleteEnum.N);
            department.setCreateTime(currentDate);
        }
        departmentRepositoryService.saveOrUpdate(department);
    }

    @Transactional
    @Override
    public void delete(String deptIds) {
        List<Long> deptIdList = Tools.removeReplaceLong(deptIds, Const.COMMA_CHAR);
        AssertUtils.notEmpty(deptIdList, "参数错误");
        //逻辑删除部门
        departmentRepositoryService.deleteByIds(deptIdList);

        //并修改对应的人员
        Map<String, Object> params = new HashMap<>();
        params.put("deptId", 0);
        params.put("deptIds", deptIdList);
        scfUserRepositoryService.updateByParams(params);
    }
}
