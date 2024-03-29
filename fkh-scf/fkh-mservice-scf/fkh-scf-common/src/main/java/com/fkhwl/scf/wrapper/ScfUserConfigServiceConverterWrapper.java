package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;
import com.fkhwl.scf.entity.po.ScfUserConfig;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户配置 转换器, 默认提供 4 种转换, 根据业务需求重写转换逻辑或新增转换接口 </p>
 *
 * @author sj
 * @email sj@fkhwl.com
 * @since 2020-02-19
 */
@Mapper
public interface ScfUserConfigServiceConverterWrapper extends ServiceConverterWrapper<ScfUserConfigDTO,ScfUserConfig> {
    /**
     * po -> dto: ScfConfigServiceConverterWrapper.INSTANCE.to(po);
     * dto -> po: ScfConfigServiceConverterWrapper.INSTANCE.from(dto);
     */
    ScfUserConfigServiceConverterWrapper INSTANCE = Mappers.getMapper(ScfUserConfigServiceConverterWrapper.class);
}
