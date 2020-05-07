package com.fkhwl.scf.entity.dto;

import com.fkhwl.scf.entity.base.ScfBaseDTO;

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
 * <p>Description: 账单 数据传输实体 (根据业务需求添加字段) </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.28 13:57
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AccountBillDTO extends ScfBaseDTO<Long> {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "还款状态")
    private Integer repayStatus;
    @ApiModelProperty(value = "到期状态")
    private Integer dueStatus;
    @ApiModelProperty(value = "账单标号")
    private String billNo;
    @ApiModelProperty(value = "账单金额")
    private BigDecimal billBalance;
    @ApiModelProperty(value = "还款金额")
    private BigDecimal repayBalance;
    @ApiModelProperty(value = "剩余待还")
    private BigDecimal remainRepayBalance;
    @ApiModelProperty(value = "运单个数")
    private Integer waybillCount;
    @ApiModelProperty(value = "用款申请Id")
    private Long creditApplyId;
    @ApiModelProperty(value = "关联标的债权订单id")
    private Long subjectClaimsOrderId;
    @ApiModelProperty(value = "还款期限")
    private Date repayDate;
    @ApiModelProperty(value = "账单日期")
    private Date billDate;

    @ApiModelProperty(value = " 管理费率：用于计算管理费 ")
    private BigDecimal manageRate;
    @ApiModelProperty(value = " 融资利率：用于计算利息 ")
    private BigDecimal interestRate;
    @ApiModelProperty(value = "账期：天数")
    private int paymentDays;
    @ApiModelProperty(value = "宽限期：天数")
    private int graceDays;
    @ApiModelProperty(value = " 逾期利率：用于计算逾期天数的利息 ")
    private BigDecimal overdueRate;
    @ApiModelProperty(value = " 宽限期利率：用于计算宽限期天数的利息 ")
    private BigDecimal graceRate;
    @ApiModelProperty(value = "交易对手Id")
    private Long counterpartyId;
}
