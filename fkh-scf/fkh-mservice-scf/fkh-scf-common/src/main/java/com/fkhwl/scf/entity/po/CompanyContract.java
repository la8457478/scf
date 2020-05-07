package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 主体合同：资方与借款方签订的合同 实体类  </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.03 15:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CompanyContract extends BasePO<Long, CompanyContract> {

    public static final String COMPANY_CAPITAL_ID = "company_capital_id";
    public static final String COMPANY_BORROWER_ID = "company_borrower_id";
    public static final String CONTRACT_NUMBER = "contract_number";
    public static final String START_TIME = "start_time";
    public static final String END_TIME = "end_time";
    public static final String MANAGE_RATE = "manage_rate";
    public static final String INTEREST_RATE = "interest_rate";
    public static final String TOTAL_BALANCE = "total_balance";
    public static final String CONTRACT_LINK = "contract_link";
    private static final long serialVersionUID = 1L;

    /** 资方企业ID */
    private Long companyCapitalId;
    /** 借款方企业ID：合同主体ID */
    private Long companyBorrowerId;
    /** 借款方企业ID：合同主体名称 */
    private String companyBorrowerName;
    /** 合同编号 */
    private String contractNumber;
    /** 合同开始时间 */
    private Date startTime;
    /** 合同结束时间 */
    private Date endTime;
    /** 管理费率 */
    private BigDecimal manageRate;
    /** 利率 */
    private BigDecimal interestRate;
    /** 总额度 */
    private BigDecimal totalBalance;
    /** 合同文件链接地址 */
    private String contractLink;

    /** 企业所有已审核放款成功的总金额：累计 */
    private BigDecimal loanSuccessBalance;
    /** 可用余额 */
    private BigDecimal remainingBalance;
    /** 企业已归还金额：累计 */
    private BigDecimal returnedBalance;
    /** 未还,在途,还需要归还的金额：累计 */
    private BigDecimal needReturnBalance;

    /** 可分配的分项额度：总额度-已分配的分享额度总和 */
    private BigDecimal remainingSubitemBalance;

    /** 已分配的分享额度总和：交易对手的分享额度总和 */
    private BigDecimal hadSubitemBalance;
    /** 状态：0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过） */
    private Integer status;

    /** 客户开户银行 */
    private String bankName;
    /** 开户银行户主名称 */
    private String bankAccountName;
    /** 开户银行卡号 */
    private String bankAccountNo;
    /** 一级项目经理 */
    private String projectMgName;
    /** 二级项目经理 */
    private String projectSecondMgName;
    /** 部门经理 */
    private String departmentManager;
    /** 经办部门 */
    private String departmentName;
//客户支行
    private String branchBankName;

}
