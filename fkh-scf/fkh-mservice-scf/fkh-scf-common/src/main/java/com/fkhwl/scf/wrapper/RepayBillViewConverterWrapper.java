package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.entity.form.RepayBillForm;
import com.fkhwl.scf.entity.vo.RepayBillVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 发起的还款订单-发起待出纳确认 视图层转换器, 提供 vo 和 dto 互转 </p>
 * form 和 dto 互转需要在此接口中自己添加.
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.22 15:48
 */
@Mapper
public interface RepayBillViewConverterWrapper extends ViewConverterWrapper<RepayBillVO, RepayBillDTO> {

    RepayBillViewConverterWrapper INSTANCE = Mappers.getMapper(RepayBillViewConverterWrapper.class);
    /**
     * form转dto
     *
     * @param repayBillForm form
     * @return dto
     */
    RepayBillDTO dto(RepayBillForm repayBillForm);
}


