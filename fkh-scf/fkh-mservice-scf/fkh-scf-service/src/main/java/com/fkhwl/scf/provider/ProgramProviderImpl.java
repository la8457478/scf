package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ProgramProvider;
import com.fkhwl.scf.ProjectProvider;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.vo.ProgramVO;
import com.fkhwl.scf.service.ProgramService;
import com.fkhwl.scf.service.ProjectService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 * 根据业务需求添加字段
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/24
 */
@Service
@AllArgsConstructor
public class ProgramProviderImpl implements ProgramProvider {
    private final ProgramService programService;
    @Override
    public IPage<ProgramDTO> listPage(IPage<ProgramDTO> page, Map<String, Object> params) {
        return programService.listPage(page, params);
    }

    @Override
    public ProgramDTO getDetail(Long id) {
        return programService.getDetail(id).orElse(null);
    }

    @Override
    public void saveOrUpdate(ProgramDTO projectDTO) {
        programService.saveOrUpdate(projectDTO);
    }

    @Override
    public void delete(Long id) {
        programService.delete(id);
    }
}
