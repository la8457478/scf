package com.fkhwl.scf.entity.dto;

import java.math.BigDecimal;

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
 * <p>Description:  用款申请 (根据业务需求添加字段) </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.27 10:54
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreditApplyReviewDTO extends CreditApplyListDTO {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "总额度")
    private BigDecimal totalBalance;
    @ApiModelProperty(value = "客户剩余可用")
    private BigDecimal remainingBalance;
    @ApiModelProperty(value = "分项限额")
    private BigDecimal subitemLimitBalance;
    @ApiModelProperty(value = "规定比例：融资比例")
    private BigDecimal ruleRatio;
    @ApiModelProperty(value = "累计放款额：计算得出")
    private BigDecimal totalLendingBalance;
    @ApiModelProperty(value = "回款核销额：计算得出")
    private BigDecimal returnedBalance;
    @ApiModelProperty(value = "是否有过逾期")
    private Boolean hasDue;
    @ApiModelProperty(value = "可审核角色id")
    private Long roleId;
    @ApiModelProperty(value = "应该需要审核的运单数量")
    private Integer needCheckWaybillCount;
    @ApiModelProperty(value = "已查阅运单条数")
    private Integer checkedWaybillCount;
    @ApiModelProperty(value = "融资利率")
    private BigDecimal interestRate;
    /** 开户银行户主名称 */
    private String bankName;
    /** 开户银行卡号 */
    private String bankAccountNo;

    private BigDecimal hadReceivableBalance;

}
