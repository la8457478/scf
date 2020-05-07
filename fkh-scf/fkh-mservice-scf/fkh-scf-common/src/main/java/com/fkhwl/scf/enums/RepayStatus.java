package com.fkhwl.scf.enums;

import com.fkhwl.starter.common.enums.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 账单还款状态  </p>
 *
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/27
 */
@Getter
@AllArgsConstructor
public enum RepayStatus implements EntityEnum<Integer> {
    NOT_REPAY(1, "未还款"),
    UNDER_REPAY(2, "还款中"),
    ALREADY_REPAY(3, "已还款"),
    ALREADY_REJECT(-1, "已拒绝");
    /** Value */
    private final Integer value;
    /** Desc */
    private final String desc;

}
