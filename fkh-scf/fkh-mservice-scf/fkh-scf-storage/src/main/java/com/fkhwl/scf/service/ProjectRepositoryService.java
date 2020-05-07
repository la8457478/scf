package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.ProjectListDTO;
import com.fkhwl.scf.entity.po.Project;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 项目 服务接口 </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-20
 */
public interface ProjectRepositoryService extends BaseService<Project> {

    IPage<ProjectListDTO> listPage(IPage<ProjectListDTO> page, Map<String, Object> params);

    /**
    * 功能描述:
     * @param projectId
    * @return: void
    * @Author: liuan
    * @Date: 2020/3/29 20:07
    */
    void updateSubitemClaimsOrderCount(Long projectId,Integer count);
}

