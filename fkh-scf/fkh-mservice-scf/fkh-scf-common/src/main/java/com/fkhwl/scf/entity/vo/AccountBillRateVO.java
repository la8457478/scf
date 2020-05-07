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
 * <p>Description: 账单 视图实体 (根据业务需求添加字段) </p>
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
public class AccountBillRateVO  extends BaseVO<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账单Id")
    private Long accountBillId;
    @ApiModelProperty(value = "用款申请Id")
    private Long creditApplyId;
    @ApiModelProperty(value = "还款本金金额")
    private BigDecimal repayBalance;
    @ApiModelProperty(value = "还款日期")
    private Date billDate;
    @ApiModelProperty(value = " 融资利息")
    private BigDecimal interestRateBalance;
    @ApiModelProperty(value = " 逾期利息")
    private BigDecimal overdueRateBalance;
    @ApiModelProperty(value = " 宽限期利息")
    private BigDecimal graceRateBalance;
}
