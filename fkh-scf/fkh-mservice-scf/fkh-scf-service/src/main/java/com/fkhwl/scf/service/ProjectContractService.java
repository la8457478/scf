package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.dto.ProjectContractDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.vo.ProjectContractVO;

import java.util.Map;
import java.util.Optional;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单 服务接口 </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-18
 */
public interface ProjectContractService {
    /**
     * 分页查询项目合同
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页项目合同数据 page
     */
    IPage<ProjectContractDTO> listPage(IPage<ProjectContractDTO> page, Map<String, Object> params);

    /**
     * 获取项目合同详情
     *
     * @param id  项目合同id
     * @return 项目合同详情DTO optional
     */
    Optional<ProjectContractDTO> getDetail(Long id);

    /**
     * 新增和更新项目合同
     *
     * @param projectContractDTO 项目合同实体
     */
    void saveOrUpdate(ProjectContractDTO projectContractDTO);

    /**
     * 删除项目合同
     *
     * @param id 主键id
     */
    void delete(Long id);
}

