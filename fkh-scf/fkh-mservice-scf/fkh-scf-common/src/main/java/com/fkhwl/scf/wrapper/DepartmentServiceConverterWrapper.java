package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.DepartmentDTO;
import com.fkhwl.scf.entity.po.Department;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:部门表 转换器, 默认提供 4 种转换, 根据业务需求重写转换逻辑或新增转换接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Mapper
public interface DepartmentServiceConverterWrapper extends ServiceConverterWrapper<DepartmentDTO, Department> {

    DepartmentServiceConverterWrapper INSTANCE = Mappers.getMapper(DepartmentServiceConverterWrapper.class);
}
