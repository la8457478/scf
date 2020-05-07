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
public class AccountBillExcel implements Serializable {

    @Excel(name = "客户名称")
    private String companyName;
    @Excel(name = "交易对手名称")
    private String counterpartyName;
    @Excel(name = "放款申请订单号")
    private String creditApplyNo;

    @Excel(name = "账单号")
    private String billNo;
    @Excel(name = "融资放款金额（元）")
    private BigDecimal billBalance;
    @Excel(name = "申请转让日")
    private Date applyTransferDate;
    @Excel(name = "应收账款到期日")
    private Date repayDate;
    @Excel(name = "管理费（元）")
    private BigDecimal manageBalance;
    @Excel(name = "利息（元）")
    private BigDecimal interestRateBalance=BigDecimal.ZERO;
    @Excel(name = "宽限利息（元）")
    private BigDecimal graceRateBalance=BigDecimal.ZERO;
    @Excel(name = "逾期利息（元）")
    private BigDecimal overdueRateBalance=BigDecimal.ZERO;
    @Excel(name = "待还款（元）")
    private BigDecimal remainRepayBalance;
    @Excel(name = "已还款（元）")
    private BigDecimal repayBalance;

    private Integer repayStatus;
    private Integer dueStatus;
    private Integer graceStatus;

    @Excel(name = "是否结清")
    private String repayStatusStr;
    @Excel(name = "是否到期")
    private String dueStatusStr;
    @Excel(name = "是否超宽限期")
    private String graceStatusStr;

    public String getRepayStatusToExcel() {
        if (repayStatus == 3) {
            return "是";
        } else if (repayStatus < 3) {
            return "否";
        } else {
            return "";
        }
    }
    public String getDueStatusToExcel() {
        if (dueStatus >0) {
            return "是";
        } else if (dueStatus == 0) {
            return "否";
        } else {
            return "";
        }
    }
    public String getGraceStatusExcel() {
        if (dueStatus == 2) {
            return "是";
        } else if (dueStatus < 2) {
            return "否";
        } else {
            return "";
        }
    }
}
