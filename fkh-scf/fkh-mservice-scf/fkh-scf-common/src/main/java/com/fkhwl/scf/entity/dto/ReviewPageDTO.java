package com.fkhwl.scf.entity.dto;

import com.fkhwl.starter.common.base.BaseDTO;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 * 审核页面的VO
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/3/1
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ReviewPageDTO extends BaseDTO<Long> {

    /**
     * 分项限额
     */
    @ApiModelProperty(value = "分项限额")
    private BigDecimal subitemLimitBalance;
    /**
     * 交易对手名称
     */
    @ApiModelProperty(value = "交易对手名称")
    private String counterpartyName;
    /**
     * 已使用金额
     */
    @ApiModelProperty(value = "已使用金额")
    private BigDecimal subitemUsedBalance;

    /**
     * 规定比例
     */
    @ApiModelProperty(value = "规定比例")
    private BigDecimal ruleRatio;

    //债权订单的数据
    /**
     * 转让金额
     */
    @ApiModelProperty(value = "债权订单的转让金额")
    @Builder.Default
    private BigDecimal transferBalance=BigDecimal.ZERO;
    /**
     * 剩余余额
     */
    @ApiModelProperty(value = "债权订单的剩余余额")
    @Builder.Default
    private BigDecimal remainTransferBalance=BigDecimal.ZERO;
    /**
     * 本次提用额度
     */
    @ApiModelProperty(value = "本次提用额度")
    @Builder.Default
    private BigDecimal applyBalance=BigDecimal.ZERO;
    /**
     * 提用后可使用余额
     */
    @ApiModelProperty(value = "提用后可使用余额")
    @Builder.Default
    private BigDecimal subitemRemainBalance=BigDecimal.ZERO;
    /**
     * 质保金 T
     */
    @ApiModelProperty(value = "质保金")
    private BigDecimal dipositBalance;
    /**
     * 实际比例
     */
    @ApiModelProperty(value = "实际比例")
    private BigDecimal factRatio;
    /**
     * 是否在规定比例内
     */
    @ApiModelProperty(value = "是否在规定比例内")
    @Builder.Default
    private Boolean inRuleRatio=false;
}
