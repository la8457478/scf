package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ProjectContractProvider;
import com.fkhwl.scf.ProjectProvider;
import com.fkhwl.scf.entity.dto.ProjectContractDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.vo.ProjectContractVO;
import com.fkhwl.scf.service.ProjectContractService;
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
public class ProjectContractProviderImpl implements ProjectContractProvider {
    private final ProjectContractService projectContractService;
    @Override
    public IPage<ProjectContractDTO> listPage(IPage<ProjectContractDTO> page, Map<String, Object> params) {
        return projectContractService.listPage(page, params);
    }

    @Override
    public ProjectContractDTO getDetail(Long id) {
        return projectContractService.getDetail(id).orElse(null);
    }

    @Override
    public void saveOrUpdate(ProjectContractDTO projectContractDTO) {
        projectContractService.saveOrUpdate(projectContractDTO);
    }

    @Override
    public void delete(Long id) {
        projectContractService.delete(id);
    }
}
