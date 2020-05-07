package com.fkhwl.scf.entity.vo;

import com.fkhwl.starter.common.base.BaseVO;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 发起的还款订单-发起待出纳确认 视图实体 (根据业务需求添加字段) </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.22 15:48
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RepayBillVO extends BaseVO<Long> {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "还款金额")
    private BigDecimal repayBalance;
    @ApiModelProperty(value = "审核状态1.待审核 2.已通过 3已拒绝")
    private Integer status;
    @ApiModelProperty(value = "审核流程id")
    private Long flowId;
    @ApiModelProperty(value = "流程节点id")
    private Long flowNodeId;
    @ApiModelProperty(value = "还款时间")
    private Date repayDate;
    @ApiModelProperty(value = "创建人所属主账号")
    private Long ownerId;
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;
    @ApiModelProperty(value = "账单id：多个，以英文逗号隔开")
    private String accountBillIds;

    @ApiModelProperty(value = " 融资利息 ")
    private BigDecimal interestRateBalance;
    @ApiModelProperty(value = " 逾期利息 ")
    private BigDecimal overdueRateBalance;
    @ApiModelProperty(value = " 宽限期利息 ")
    private BigDecimal graceRateBalance;
    @ApiModelProperty(value = "交易对手Id")
    private Long counterpartyId;
    @ApiModelProperty(value = "交易对手名称")
    private String counterpartyName;
    @ApiModelProperty(value = "借款方公司Id")
    private Long companyBorrowerId;
    @ApiModelProperty(value = "借款方公司名称")
    private String companyBorrowerName;

    @ApiModelProperty(value = "手动设置的还款日期")
    private String repayDateStr;
    @ApiModelProperty(value = "每个账单的还款情况：id,本金，利息，宽限利息，逾期利息；id2,本金，利息，宽限利息，逾期利息")
    private String accountBillBalanceInfo;
    private Date updateTime;
    private String reviewReason;
}
