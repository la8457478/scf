package com.fkhwl.scf.enums;

import com.fkhwl.starter.common.enums.EntityEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 到期状态  </p>
 *
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/27
 */
@Getter
@AllArgsConstructor
public enum DueStatus implements EntityEnum<Integer> {
    NOT_DUE(0, "未到期"),
    ALREADY_DUE(1, "到期"),
    ALREADY_OVERDUE(2, "超宽限期");

    /** Value */
    private final Integer value;
    /** Desc */
    private final String desc;

}
