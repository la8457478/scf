package com.fkhwl.scf.enums;

import com.fkhwl.starter.common.enums.EntityEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限资源表功能类型枚举  </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-18
 */
@Getter
@AllArgsConstructor
public enum AuthFuncType implements EntityEnum<Integer> {

    MENU(1, "菜单"),
    AUTH(2, "权限");

    /** Value */
    private final Integer value;
    /** Desc */
    private final String desc;
}
