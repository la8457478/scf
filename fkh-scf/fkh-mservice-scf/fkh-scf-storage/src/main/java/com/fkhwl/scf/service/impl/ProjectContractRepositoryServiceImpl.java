package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProjectContractDTO;
import com.fkhwl.scf.entity.po.ProjectContract;
import com.fkhwl.scf.dao.ProjectContractDao;
import com.fkhwl.scf.service.ProjectContractRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 项目合同 服务接口实现类 </p>
 *
 * @author hezhiming
 * @email hezhiming@fkhwl.com
 * @since 2020-02-20
 */
@Slf4j
@Service
public class ProjectContractRepositoryServiceImpl extends BaseServiceImpl<ProjectContractDao, ProjectContract> implements ProjectContractRepositoryService {

    @Override
    public IPage<ProjectContractDTO> listPage(IPage<ProjectContractDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page,params);
    }
}
