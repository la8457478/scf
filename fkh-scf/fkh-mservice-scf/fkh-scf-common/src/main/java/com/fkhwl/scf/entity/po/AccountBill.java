package com.fkhwl.scf.entity.po;

import com.fkhwl.scf.entity.base.ScfBasePO;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 账单 实体类  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.28 13:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccountBill extends ScfBasePO<Long, AccountBill> {

    public static final String REPAY_STATUS = "repay_status";
    public static final String DUE_STATUS = "due_status";
    public static final String BILL_NO = "bill_no";
    public static final String BILL_BALANCE = "bill_balance";
    public static final String REMAIN_REPAY_BALANCE = "remain_repay_balance";
    public static final String WAYBILL_COUNT = "waybill_count";
    public static final String CREDIT_APPLY_ID = "credit_apply_id";
    public static final String SUBJECT_CLAIMS_ORDER_ID = "subject_claims_order_id";
    public static final String REPAY_DATE = "repay_date";
    private static final long serialVersionUID = 1L;

    /** 还款状态 */
    private Integer repayStatus;
    /** 到期状态 */
    private Integer dueStatus;
    /** 账单标号 */
    private String billNo;
    /** 账单金额 */
    private BigDecimal billBalance;
    /** 还款金额 */
    private BigDecimal repayBalance;
    /** 剩余待还 */
    private BigDecimal remainRepayBalance;
    /** 运单个数 */
    private Integer waybillCount;
    /** 用款申请Id */
    private Long creditApplyId;
    /** 关联标的债权订单id */
    private Long subjectClaimsOrderId;
    /** 还款期限 */
    private Date repayDate;
    /** 账单日期 */
    private Date billDate;

    /** 管理费率：用于计算管理费 */
    private BigDecimal manageRate;
    /** 融资利率：用于计算利息 */
    private BigDecimal interestRate;
    /** 账期：天数 */
    private Integer paymentDays;
    /** 宽限期：天数 */
    private Integer graceDays;
    /**  逾期利率：用于计算逾期天数的利息  */
    private BigDecimal overdueRate;
    /**  宽限期利率：用于计算宽限期天数的利息  */
    private BigDecimal graceRate;
    //交易对手Id
    private Long counterpartyId;

    private BigDecimal interestRateBalance;
    private BigDecimal graceRateBalance;
    private BigDecimal overdueRateBalance;
    //"实际还款日")
    private Date factRepayDate;
    //宽限日
    private Date graceDate;
}
