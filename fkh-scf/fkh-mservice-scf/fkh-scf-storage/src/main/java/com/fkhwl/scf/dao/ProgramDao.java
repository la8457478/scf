package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.po.Program;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 计划 Dao 接口  </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-20
 */
@Mapper
public interface ProgramDao extends BaseDao<Program> {

    IPage<ProgramDTO> listPage(@Param("page") IPage<ProgramDTO> page,@Param("map") Map<String, Object> params);
}
