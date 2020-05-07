package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.DepartmentProvider;
import com.fkhwl.scf.entity.dto.DepartmentDTO;
import com.fkhwl.scf.service.DepartmentService;
import com.fkhwl.starter.mybatis.support.Condition;

import org.apache.dubbo.config.annotation.Service;

import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 部门Dubbo接口</p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com" @fkhwl.com
 * @date 2020.01.10 19:24
 */
@Service
@AllArgsConstructor
public class DepartmentProviderImpl implements DepartmentProvider {

    private final DepartmentService departmentService;

    @Override
    public IPage<DepartmentDTO> listPage(Map<String, Object> params) {
        return departmentService.listPage(Condition.getPage(params), params);
    }

    @Override
    public DepartmentDTO getDetail(Long id) {
        return departmentService.getDetail(id);
    }

    @Override
    public void saveOrUpdate(DepartmentDTO departmentDTO) {
        departmentService.saveOrUpdate(departmentDTO);
    }

    @Override
    public void delete(String deptIds) {
        departmentService.delete(deptIds);
    }
}
