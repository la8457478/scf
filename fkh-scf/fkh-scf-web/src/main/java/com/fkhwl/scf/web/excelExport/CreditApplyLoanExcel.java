package com.fkhwl.scf.web.excelExport;

import com.fkhwl.scf.utils.Excel;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>Title: com.fkhwl.scf.web.excelExport</p>
 * <p>Company: 成都返空汇网络技术有限公</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date: 2020年03月18日 17点31分
 * updatetime:
 * reason:
 */
@Data
public class CreditApplyLoanExcel implements Serializable {
    @Excel(name = "用款申请编号")
    private String creditApplyNo;
    @Excel(name = "申请融资额度（元）")
    private BigDecimal applyBalance;

    @Excel(name = "可提用融资额度（元）")
    private BigDecimal transferBalance;

    @Excel(name = "运单个数")
    private Integer waybillCount;
    @Excel(name = "申请转让时间")
    private Date createTime;

    @Excel(name = "融资款发放金额（元）")
    private BigDecimal loanBalance;

    @Excel(name = "融资款发放时间")
    private Date loanTime;
//    @Excel(name = "附件路径（暂定，可能需要修改）")
//    private String attachment;
//    @Excel(name = "应收账款id")
//    private Long counterpartyId;
//    @Excel(name = "到期日")
//    private Date dueDate;
//    @Excel(name = "利息金额")
//    private BigDecimal interestBalance;
//    @Excel(name = "有效转让金额")
//    private BigDecimal transferBalance;
//    @Excel(name = "放款金额")
//    private BigDecimal loanBalance;
//    @Excel(name = "是否生效")
//    private Boolean isValid;
//    @Excel(name = "实际比例")
//    private BigDecimal factRatio;
//    @Excel(name = "管理费用")
//    private BigDecimal manageBalance;
//
//    @Excel(name = "分项剩余限额：分项额度的可用余额")
//    private BigDecimal subitemRemainBalance;
}
