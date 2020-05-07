package com.fkhwl.scf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProjectContractDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.vo.ProjectContractVO;

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
public interface ProjectContractProvider {
    /**
     * 分页查询运单
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页运单数据 page
     */
    IPage<ProjectContractDTO> listPage(IPage<ProjectContractDTO> page, Map<String, Object> params);

    /**
     * 获取项目合同详情
     *
     * @param id  项目合同id
     * @return 项目合同详情DTO optional
     */
    ProjectContractDTO getDetail(Long id);

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
