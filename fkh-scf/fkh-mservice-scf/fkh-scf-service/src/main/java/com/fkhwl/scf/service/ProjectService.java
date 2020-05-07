package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.ProjectListDTO;
import com.fkhwl.scf.entity.po.Project;

import java.util.List;
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
public interface ProjectService  {
    /**
     * 分页查询项目
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页项目数据 page
     */
    IPage<ProjectListDTO> listPage(IPage<ProjectListDTO> page, Map<String, Object> params);

    /**
     * 获取项目详情
     *
     * @param id  项目id
     * @return 项目详情DTO optional
     */
    Optional<ProjectDTO> getDetail(Long id);

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
    /**
    * 批量删除:
     * @param ids
    * @return: void
    * @Author: liuan
    * @Date: 2020/3/9 13:00
    */
    void delete(List<Long> ids);
/**
* 根据业务id查询
 * @param thirdId
* @return: void
* @Author: liuan
* @Date: 2020/3/9 13:00
*/
    Project getByThirdId(Long thirdId);
}

