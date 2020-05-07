package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  实体类  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.26 11:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Counterparty extends BasePO<Long, Counterparty> {

    public static final String SUBITEM_LIMIT = "subitem_limit";
    public static final String COUNTERPARTY_NAME = "counterparty_name";
    public static final String company_borrower_id = "company_borrower_id";
    private static final long serialVersionUID = 1L;

    /** 客户合同ID */
    private Long companyContractId;
    /** 资方企业ID */
    private Long companyCapitalId;
    /** 所属借款公司id */
    private Long companyBorrowerId;

    /** 分项限额:可配置 */
    private BigDecimal subitemLimitBalance;
    /** 融资款归还额：计算得出 */
    private BigDecimal returnedBalance;
    /** 已转应收帐款额：计算得出 */
    private BigDecimal hadReceivableBalance;
    /** 累计放款额：计算得出 */
    private BigDecimal totalLendingBalance;
    /** 规定比例：融资比例，用于计算可申请提用额度 */
    private BigDecimal ruleRatio;
    /** 分项已使用额 */
    private BigDecimal subitemUsedBalance;
    /** 分项剩余限额 */
    private BigDecimal subitemRemainBalance;
    /** 交易对手名称 */
    private String counterpartyName;
    /** 管理费率：用于计算管理费 */
    private BigDecimal manageRate;
    /** 融资利率：用于计算利息 */
    private BigDecimal interestRate;
    /** 账期：天数 */
    private int paymentDays;
    /** 宽限期：天数 */
    private int graceDays;
    /**  逾期利率：用于计算逾期天数的利息  */
    private BigDecimal overdueRate;
    /**  宽限期利率：用于计算宽限期天数的利息  */
    private BigDecimal graceRate;
    /** 状态：0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过） */
    private Integer status;
    /** 合同文件链接地址 */
    private String contractLink;
    /** 应收账款回款核销额：计算得出 */
    private BigDecimal returnedTransferBalance;
    /** 保理合同号 */
    private String factorContractNo;
    /** 保理额度核准明细表编号 */
    private String factorLimitCheckListNo;
    /** 保理服务协议编号 */
    private String factorServiceAgreementNo;
    /** 应收账款转让合同编号 */
    private String transferBalanceContractNo;
    /** 基础合同名称 */
    private String baseContractName;
    /** 基础合同编号 */
    private String baseContractNo;
}
