package com.fkhwl.scf.enums;

import com.fkhwl.starter.common.enums.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用信订单 审核状态 </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/27
 */
@Getter
@AllArgsConstructor
public enum CreditApplyStatus implements EntityEnum<Integer> {
    DESTROYED(-2,"已作废"),
    OPERATION_REVIEW_NO_PASS(-1,"运营审核不通过"),
    UNDER_OPERATION_REVIEW(1,"运营审核中"),
    UNDER_RISK_CONTROL_REVIEW(2,"风控审核中"),
    UNDER_FINANCIAL_REVIEW(3, "财务审核中"),
    UNDER_MANAGEMENT_REVIEW(4, "管理层审核中"),
    UNDER_CASHIER_LOAN(5, "出纳审核"),
    ALREADY_LOAN(6, "已放款"),
    NOT_REPAY(7, "未还款"),
    ALREADY_REPAY(8, "已还款");

    /** Value */
    private final Integer value;
    /** Desc */
    private final String desc;

}
