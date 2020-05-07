package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.vo.ProgramVO;

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
public interface ProgramService {
    /**
     * 分页查询计划
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页计划数据 page
     */
    IPage<ProgramDTO> listPage(IPage<ProgramDTO> page, Map<String, Object> params);

    /**
     * 获取计划详情
     *
     * @param id  计划id
     * @return 计划详情DTO optional
     */
    Optional<ProgramDTO> getDetail(Long id);

    /**
     * 新增和更新计划
     *
     * @param programDTO 计划实体
     */
    void saveOrUpdate(ProgramDTO programDTO);

    /**
     * 删除计划
     *
     * @param id 主键id
     */
    void delete(Long id);
}

