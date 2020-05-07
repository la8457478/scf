package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.form.SubjectClaimsOrderForm;
import com.fkhwl.scf.entity.vo.SubjectClaimsOrderVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 标的债权订单 视图层转换器, 提供 vo 和 dto 互转 </p>
 * form 和 dto 互转需要在此接口中自己添加.
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.25 11:34
 */
@Mapper
public interface SubjectClaimsOrderViewConverterWrapper extends ViewConverterWrapper<SubjectClaimsOrderVO, SubjectClaimsOrderDTO> {

    SubjectClaimsOrderViewConverterWrapper INSTANCE = Mappers.getMapper(SubjectClaimsOrderViewConverterWrapper.class);

    /**
     * form转dto
     *
     * @param subjectClaimsOrderForm form
     * @return dto
     */
    SubjectClaimsOrderDTO dto(SubjectClaimsOrderForm subjectClaimsOrderForm);
}


