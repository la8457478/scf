package com.fkhwl.scf.entity.vo;

import com.fkhwl.starter.common.base.BaseVO;

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
 * <p>Description: 标的债权订单 视图实体 (根据业务需求添加字段) </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.25 11:34
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SubjectClaimsOrderVO extends BaseVO<Long> {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "标的债权订单号")
    private String subjectClaimsOrderNo;
    @ApiModelProperty(value = "交易对手")
    private String counterpartyName;
    @ApiModelProperty(value = "转让金额")
    private BigDecimal transferBalance;
    @ApiModelProperty(value = "运单金额")
    private BigDecimal canApplyBalance;
    @ApiModelProperty(value = "运单个数")
    private Integer waybillCount;
    @ApiModelProperty(value = "金融产品名称")
    private String financialProductName;

    //交易对手Id
    @ApiModelProperty(value = "交易对手Id")
    private Long counterpartyId;
    //金融产品id
    @ApiModelProperty(value = "金融产品id")
    private Long financialProductId;
    /** 审核状态：-1.运营审核不通过，0.初始化，1.运营审核通过 */
    @ApiModelProperty(value = "审核状态")
    private Integer reviewStatus;
}
