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
public class CreditApplyExcel implements Serializable {

    @Excel(name = "申请状态")
    private String statusStr;
    private Integer status;

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

    public String getStatusName(){
        switch (status){
            case -2:
                return "已作废";
            case -1:
                return "已拒绝";
            case 1:
                return "运营审核中";
            case 2:
                return "风控审核中";
            case 3:
                return "财务审核中";
            case 4:
                return "管理层审核中";
            case 5:
                return "出纳审核中";
            case 6:
                return "已放款";
            case 7:
                return "未还款";
            case 8:
                return "已还款";
        }
        return "";
    }

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
