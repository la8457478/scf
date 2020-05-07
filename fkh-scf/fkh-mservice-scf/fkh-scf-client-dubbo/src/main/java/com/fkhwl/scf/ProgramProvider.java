package com.fkhwl.scf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.vo.ProgramVO;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/24
 */
public interface ProgramProvider {
    /**
     * 分页查询运单
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页运单数据 page
     */
    IPage<ProgramDTO> listPage(IPage<ProgramDTO> page, Map<String, Object> params);

    /**
     * 获取项目详情
     *
     * @param id  项目id
     * @return 项目详情DTO optional
     */
    ProgramDTO getDetail(Long id);

    /**
     * 新增和更新项目
     *
     * @param programDTO 项目实体
     */
    void saveOrUpdate(ProgramDTO programDTO);

    /**
     * 删除项目
     *
     * @param id 主键id
     */
    void delete(Long id);
}
