package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.po.Program;
import com.fkhwl.scf.entity.po.Project;
import com.fkhwl.scf.service.ProgramRepositoryService;
import com.fkhwl.scf.service.ProgramService;
import com.fkhwl.scf.service.ProjectRepositoryService;
import com.fkhwl.scf.service.ProjectService;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.ProgramServiceConverterWrapper;
import com.fkhwl.scf.wrapper.ProjectServiceConverterWrapper;
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
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepositoryService programRepositoryService;

    @Override
    public IPage<ProgramDTO> listPage(IPage<ProgramDTO> page, Map<String, Object> params) {
        return programRepositoryService.listPage(page, params);
    }

    @Override
    public Optional<ProgramDTO> getDetail(Long id) {
        return Optional.ofNullable(ProgramServiceConverterWrapper.INSTANCE.dto(programRepositoryService.getById(id)));
    }

    @Override
    public void saveOrUpdate(ProgramDTO programDTO) {
        Date currentDate = new Date();
        Program program = ProgramServiceConverterWrapper.INSTANCE.po(programDTO);
        program.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(programDTO.getId())) {
            program.setCreateTime(currentDate);
        }
        Program old = programRepositoryService.getOne(new LambdaQueryWrapper<Program>().eq(Program::getThirdId, programDTO.getThirdId()));
        if (old == null) {
            programRepositoryService.saveOrUpdate(program);
        } else {
            program.setId(old.getId());
            programRepositoryService.updateById(program);
        }
        programDTO.setId(program.getId());

    }

    @Override
    public void delete(Long id) {
        //逻辑删除运单
        programRepositoryService.removeById(id);
    }
}
