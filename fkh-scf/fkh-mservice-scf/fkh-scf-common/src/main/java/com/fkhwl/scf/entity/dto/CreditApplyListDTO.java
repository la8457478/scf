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
public class CreditApplyListDTO extends CreditApplyDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户名称")
    private String companyBorrowerName;

    @ApiModelProperty(value = "客户id")
    private String companyBorrowerId;

    @ApiModelProperty(value = "交易对手名称")
    private String counterpartyName;
    @ApiModelProperty(value = "分项剩余限额：分项额度的可用余额")
    private BigDecimal subitemRemainBalance;

}
