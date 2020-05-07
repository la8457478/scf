package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.TemplateUserDTO;
import com.fkhwl.scf.entity.po.TemplateUser;
import com.fkhwl.starter.common.mapstruct.ServiceConverterWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: </p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dongshijie@fkhwl.com"
 * @date 2019.12.22 21:52
 */
@Mapper
public interface TemplateUserServiceConverterWrapper extends ServiceConverterWrapper<TemplateUserDTO, TemplateUser> {

    /**
     * The constant INSTANCE.
     */
    TemplateUserServiceConverterWrapper INSTANCE = Mappers.getMapper(TemplateUserServiceConverterWrapper.class);

}
