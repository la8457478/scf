package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.ProjectListDTO;
import com.fkhwl.scf.entity.po.Project;
import com.fkhwl.scf.dao.ProjectDao;
import com.fkhwl.scf.service.ProjectRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 项目 服务接口实现类 </p>
 *
 * @author hezhiming
 * @email hezhiming@fkhwl.com
 * @since 2020-02-20
 */
@Slf4j
@Service
public class ProjectRepositoryServiceImpl extends BaseServiceImpl<ProjectDao, Project> implements ProjectRepositoryService {

    @Override
    public IPage<ProjectListDTO> listPage(IPage<ProjectListDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page,params);
    }

    @Override
    public void updateSubitemClaimsOrderCount(Long projectId, Integer count) {
         baseMapper.updateSubitemClaimsOrderCount(projectId,count);
    }
}
