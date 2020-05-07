package com.fkhwl.scf.wrapper;

import com.fkhwl.scf.entity.dto.AccountBillRateDTO;
import com.fkhwl.scf.entity.form.AccountBillRateForm;
import com.fkhwl.scf.entity.vo.AccountBillRateVO;
import com.fkhwl.starter.common.mapstruct.ViewConverterWrapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 账单 视图层转换器, 提供 vo 和 dto 互转 </p>
 * form 和 dto 互转需要在此接口中自己添加.
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.28 13:57
 */
@Mapper
public interface AccountBillRateViewConverterWrapper extends ViewConverterWrapper<AccountBillRateVO, AccountBillRateDTO> {

    AccountBillRateViewConverterWrapper INSTANCE = Mappers.getMapper(AccountBillRateViewConverterWrapper.class);
    /**
     * form转dto
     *
     * @param accountBillRateForm form
     * @return dto
     */
    AccountBillRateDTO dto(AccountBillRateForm accountBillRateForm);
}


