package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.po.Program;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 计划 服务接口 </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-20
 */
public interface ProgramRepositoryService extends BaseService<Program> {

    IPage<ProgramDTO> listPage(IPage<ProgramDTO> page, Map<String, Object> params);
}

