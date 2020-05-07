package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 发起的还款订单-发起待出纳确认 实体类  </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.22 15:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RepayBill extends BasePO<Long, RepayBill> {

    public static final String REPAY_BALANCE = "repay_balance";
    public static final String STATUS = "status";
    public static final String FLOW_ID = "flow_id";
    public static final String FLOW_NODE_ID = "flow_node_id";
    public static final String REPAY_DATE = "repay_date";
    public static final String OWNER_ID = "owner_id";
    public static final String CREATE_USER_ID = "create_user_id";
    private static final long serialVersionUID = 1L;

    private BigDecimal repayBalance;
    /** 审核状态1.待审核 2.已通过 3已拒绝 */
    private Integer status;
    /** 审核流程id */
    private Long flowId;
    /** 流程节点id */
    private Long flowNodeId;
    /** 还款时间 */
    private Date repayDate;
    /** 创建人所属主账号 */
    private Long ownerId;
    /** 创建人id */
    private Long createUserId;
    /** 账单id：多个，以英文逗号隔开 */
    private String accountBillIds;

    /**  融资利息 */
    private BigDecimal interestRateBalance;
    /**  逾期利息 */
    private BigDecimal overdueRateBalance;
    /**  宽限期利息 */
    private BigDecimal graceRateBalance;
    /** 交易对手Id*/
    private Long counterpartyId;
    /** 交易对手名称*/
    private String counterpartyName;
    /** 借款方公司Id*/
    private Long companyBorrowerId;
    /** 借款方公司名称*/
    private String companyBorrowerName;
    /** 每个账单的还款情况：id,本金，利息，宽限利息，逾期利息；id2,本金，利息，宽限利息，逾期利息*/
    private String accountBillBalanceInfo;
    //处理意见
    private String reviewReason;

}
