package com.fkhwl.scf.enums;

import com.fkhwl.starter.common.enums.EntityEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 角色类型枚举  </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-03-10
 */
@Getter
@AllArgsConstructor
public enum AuthRoleTypeEnum implements EntityEnum<Integer> {

    ADMIN(1, "平台超级管理员"),
    FUNDING(2, "资金方企业主账号"),
    BORROWER(3, "借款方企业主账号"),
    FUNDING_DEFAULT(4, "资金方默认角色"),
    BORROWER_DEFAULT(5, "借款方默认角色"),
    NORMAL(6, "普通类角色"),
    BORROWER_ALL_FUNC(7, "借款方特殊角色：拥有借款方所有权限，但是该角色被隐藏，角色列表上看不到");

    /** Value */
    private final Integer value;
    /** Desc */
    private final String desc;
}
