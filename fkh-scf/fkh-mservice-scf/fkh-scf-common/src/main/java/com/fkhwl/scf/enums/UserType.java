package com.fkhwl.scf.enums;

import com.fkhwl.starter.common.enums.EntityEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"@fkhwl.com
 * @date 2020.01.27 18:26
 */
@Getter
@AllArgsConstructor
public enum  UserType implements EntityEnum<Integer> {
    /** 用户类型 1.系统超级管理员，2.资金方主账户，3.借款方账户， 21.资金方子账户， 21.借金方子账户 */

    ADMIN(1, "系统超级管理员"),
    FUNDING(2, "资金方主账户"),
    BORROWER(3, "借款方主账户"),
    FUNDING_SUB(21, "资金方子账户"),
    BORROWER_SUB(31, "借款方子账户");
    /** Value */
    private final Integer value;
    /** Desc */
    private final String desc;
}
