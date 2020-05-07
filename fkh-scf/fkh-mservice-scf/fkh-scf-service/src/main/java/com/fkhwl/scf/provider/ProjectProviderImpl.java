package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ProjectProvider;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.ProjectListDTO;
import com.fkhwl.scf.service.ProjectService;

import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectProviderImpl implements ProjectProvider {
    private  final ProjectService projectService;
    @Override
    public IPage<ProjectListDTO> listPage(IPage<ProjectListDTO> page, Map<String, Object> params) {
        return projectService.listPage(page, params);
    }
    @Override
    public ProjectDTO getDetail(Long id) {
        return projectService.getDetail(id).orElse(null);
    }

    @Override
    public void saveOrUpdate(ProjectDTO projectDTO) {
        projectService.saveOrUpdate(projectDTO);
    }

    @Override
    public void delete(Long id) {
        projectService.delete(id);
    }

    @Override
    public void delete(List<Long> ids) {
        projectService.delete(ids);
    }
}
