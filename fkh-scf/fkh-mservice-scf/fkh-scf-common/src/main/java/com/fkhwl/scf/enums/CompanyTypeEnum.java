package com.fkhwl.scf.enums;

import com.fkhwl.starter.common.enums.EntityEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 企业类型枚举  </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-03-10
 */
@Getter
@AllArgsConstructor
public enum CompanyTypeEnum implements EntityEnum<Integer> {

    FUNDING(1, "资金方"),
    BORROWER(2, "借款方");

    /** Value */
    private final Integer value;
    /** Desc */
    private final String desc;
}
