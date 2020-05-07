package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.dto.ProjectContractDTO;
import com.fkhwl.scf.entity.po.Program;
import com.fkhwl.scf.entity.po.ProjectContract;
import com.fkhwl.scf.service.ProgramRepositoryService;
import com.fkhwl.scf.service.ProgramService;
import com.fkhwl.scf.service.ProjectContractRepositoryService;
import com.fkhwl.scf.service.ProjectContractService;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.ProgramServiceConverterWrapper;
import com.fkhwl.scf.wrapper.ProjectContractServiceConverterWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 计划 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class ProjectContractServiceImpl implements ProjectContractService {

    private final ProjectContractRepositoryService projectContractRepositoryService;

    @Override
    public IPage<ProjectContractDTO> listPage(IPage<ProjectContractDTO> page, Map<String, Object> params) {
        return projectContractRepositoryService.listPage(page, params);
    }

    @Override
    public Optional<ProjectContractDTO> getDetail(Long id) {
        return Optional.ofNullable(ProjectContractServiceConverterWrapper.INSTANCE.dto(projectContractRepositoryService.getById(id)));
    }

    @Override
    public void saveOrUpdate(ProjectContractDTO projectContractDTO) {
        Date currentDate = new Date();
        ProjectContract program = ProjectContractServiceConverterWrapper.INSTANCE.po(projectContractDTO);
        program.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(projectContractDTO.getId())) {
            program.setCreateTime(currentDate);
        }
        projectContractRepositoryService.saveOrUpdate(program);
    }

    @Override
    public void delete(Long id) {
        //逻辑删除运单
        projectContractRepositoryService.removeById(id);
    }
}
