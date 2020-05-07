package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.DepartmentDao;
import com.fkhwl.scf.entity.dto.DepartmentDTO;
import com.fkhwl.scf.entity.po.Department;
import com.fkhwl.scf.service.DepartmentRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
public class DepartmentRepositoryServiceImpl extends BaseServiceImpl<DepartmentDao, Department> implements DepartmentRepositoryService {
    @Override
    public IPage<DepartmentDTO> listPage(IPage<DepartmentDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page, params);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        return baseMapper.deleteByIds(ids);
    }
}
