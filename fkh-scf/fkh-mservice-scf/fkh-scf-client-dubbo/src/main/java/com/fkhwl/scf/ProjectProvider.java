package com.fkhwl.scf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.ProjectListDTO;

import java.util.List;
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
public interface ProjectProvider {
    /**
     * 分页查询运单
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页运单数据 page
     */
    IPage<ProjectListDTO> listPage(IPage<ProjectListDTO> page, Map<String, Object> params);

    /**
     * 获取项目详情
     *
     * @param id  项目id
     * @return 项目详情DTO optional
     */
    ProjectDTO getDetail(Long id);

    /**
     * 新增和更新项目
     *
     * @param projectDTO 项目实体
     */
    void saveOrUpdate(ProjectDTO projectDTO);

    /**
     * 删除项目
     *
     * @param id 主键id
     */
    void delete(Long id);
    void delete(List<Long> ids);
}
