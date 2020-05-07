package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.ProjectListDTO;
import com.fkhwl.scf.entity.po.Project;
import com.fkhwl.scf.service.ProjectRepositoryService;
import com.fkhwl.scf.service.ProjectService;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.ProjectServiceConverterWrapper;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 项目 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepositoryService projectRepositoryService;

    @Override
    public IPage<ProjectListDTO> listPage(IPage<ProjectListDTO> page, Map<String, Object> params) {
        return projectRepositoryService.listPage(page, params);
    }

    @Override
    public Optional<ProjectDTO> getDetail(Long id) {
        return Optional.ofNullable(ProjectServiceConverterWrapper.INSTANCE.dto(projectRepositoryService.getById(id)));
    }

    @Override
    public void saveOrUpdate(ProjectDTO projectDTO) {
        Date currentDate = new Date();
        Project project = ProjectServiceConverterWrapper.INSTANCE.po(projectDTO);
        project.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(projectDTO.getId())) {
            project.setCreateTime(currentDate);
        }
        Project old = projectRepositoryService.getOne(new LambdaQueryWrapper<Project>().eq(Project::getThirdId, projectDTO.getThirdId()));
        if (old == null) {
            projectRepositoryService.saveOrUpdate(project);
        } else {
            project.setId(old.getId());
            projectRepositoryService.updateById(project);
        }
        projectDTO.setId(project.getId());

    }

    @Override
    public void delete(Long id) {
        //逻辑删除运单
        projectRepositoryService.removeById(id);
    }
    @Override
    public void delete(List<Long> ids) {
        projectRepositoryService.removeByIds(ids);
    }

    @Override
    public Project getByThirdId(Long thirdId) {
       return projectRepositoryService.getOne(new LambdaQueryWrapper<Project>().eq(Project::getThirdId,thirdId));
    }
}
