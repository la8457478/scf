package com.fkhwl.scf.entity.form;

import com.fkhwl.starter.common.base.BaseForm;

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
 * <p>Description: 用款申请详情 视图实体 (根据业务需求添加字段) </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.03.02 12:11
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreditApplyDetailForm extends BaseForm<Long> {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "转让额度")
    private BigDecimal transferBalance;
    @ApiModelProperty(value = "本次提用额")
    private BigDecimal applyBalance;
    @ApiModelProperty(value = "质保金")
    private BigDecimal dipositBalance;
    @ApiModelProperty(value = "实际比例")
    private BigDecimal factRatio;
    @ApiModelProperty(value = "是否在比例内")
    private Boolean inRuleRatio;
    @ApiModelProperty(value = "用款申请id")
    private Long creditApplyId;
    @ApiModelProperty(value = "债权用信id")
    private Long subjectClaimsOrderId;
    @ApiModelProperty(value = "交易对手id")
    private Long counterpartyId;
}
