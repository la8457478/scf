package com.fkhwl.scf.web.excelExport;

import com.fkhwl.scf.utils.Excel;

import java.io.*;
import java.math.BigDecimal;

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
public class SubjectClaimsOrderExcel implements Serializable {
    @Excel(name = "应收账款转让订单号")
    private String subjectClaimsOrderNo;
    @Excel(name = "交易对手")
    private String counterpartyName;
    @Excel(name = "本次可提用融资额度（元）")
    private BigDecimal canApplyBalance;
    @Excel(name = "该订单应收账款转让金额（元）")
    private BigDecimal transferBalance;
    @Excel(name = "运单个数")
    private Integer waybillCount;
//    @Excel(name = "金融产品名称")
//    private String financialProductName;
//
//    //交易对手Id
//    @Excel(name = "交易对手Id")
//    private Long counterpartyId;
//    //金融产品id
//    @Excel(name = "金融产品id")
//    private Long financialProductId;
}
