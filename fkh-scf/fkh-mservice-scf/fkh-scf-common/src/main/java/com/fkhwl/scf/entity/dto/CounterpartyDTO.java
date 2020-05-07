package com.fkhwl.scf.entity.dto;

import com.fkhwl.starter.common.base.BaseDTO;

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
 * <p>Description:  数据传输实体 (根据业务需求添加字段) </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.26 11:21
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CounterpartyDTO extends BaseDTO<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户合同ID")
    private Long companyContractId;
    @ApiModelProperty(value = "资方企业ID")
    private Long companyCapitalId;
    @ApiModelProperty(value = "所属借款公司id")
    private Long companyBorrowerId;

    @ApiModelProperty(value = "分项限额")
    private BigDecimal subitemLimitBalance;
    @ApiModelProperty(value = "分项已使用额")
    private BigDecimal subitemUsedBalance;
    @ApiModelProperty(value = "分项剩余限额：分项额度的可用余额")
    private BigDecimal subitemRemainBalance;
    @ApiModelProperty(value = "交易对手名称")
    private String counterpartyName;
    @ApiModelProperty(value = "规定比例：融资比例")
    private BigDecimal ruleRatio;
    @ApiModelProperty(value = "项目Id")
    private Long companyId;
    @ApiModelProperty(value = "回款核销额：计算得出")
    private BigDecimal returnedBalance;
    @ApiModelProperty(value = "已转应收帐款额：计算得出")
    private BigDecimal hadReceivableBalance;
    @ApiModelProperty(value = "累计放款额：计算得出")
    private BigDecimal totalLendingBalance;
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
    @ApiModelProperty(value = " 状态：0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）")
    private Integer status;
    @ApiModelProperty(value = "合同文件链接地址")
    private String contractLink;
    /** 回款核销应收账款额：计算得出 */
    private BigDecimal returnedTransferBalance;

    @ApiModelProperty(value = "保理合同号")
    private String factorContractNo;
    @ApiModelProperty(value = "保理额度核准明细表编号")
    private String factorLimitCheckListNo;
    @ApiModelProperty(value = "保理服务协议编号")
    private String factorServiceAgreementNo;
    @ApiModelProperty(value = "应收账款转让合同编号")
    private String transferBalanceContractNo;
    @ApiModelProperty(value = "基础合同名称")
    private String baseContractName;
    @ApiModelProperty(value = "基础合同编号")
    private String baseContractNo;
}
