package com.fkhwl.scf.web.excelExport;

import com.fkhwl.scf.utils.Excel;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
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
public class WaybillExcel implements Serializable {

    @Excel(name = "运单号")
    private String waybillNo;
    @Excel(name = "出发地")
    private String departureCity;
    @Excel(name = "目的地")
    private String arrivalCity;
    @Excel(name = "货物类型")
    private String cargoType;
    @Excel(name = "货物数量")
    private String cargoNum;
    @Excel(name = "项目名称")
    private String projectName;
    @Excel(name = "计划名称")
    private String programName;
    @Excel(name = "驾驶员姓名")
    private String driverName;
    @Excel(name = "驾驶员手机号")
    private String driverMobileNo;
    @Excel(name = "车牌号")
    private String licensePlateNo;
    @Excel(name = "车厢类型")
    private String carType;
    @Excel(name = "车轴数量")
    private String axleNum;
    @Excel(name = "车厢长度")
    private String carLength;
    @Excel(name = "核载吨位")
    private String cargoTonnage;
    @Excel(name = "备注说明")
    private String remark;
    @Excel(name = "货物单位")
    private String unit;
    @Excel(name = "发货方填写的发货毛重")
    private BigDecimal grossWeightBySend;
    @Excel(name = "发货方填写的发货皮重")
    private BigDecimal tareWeightBySend;
    @Excel(name = "发货方填写的发货净重")
    private BigDecimal netWeightBySend;
    @Excel(name = "收货方填写的发货毛重")
    private BigDecimal sendGrossWeight;
    @Excel(name = "收货方填写的发货皮重")
    private BigDecimal sendTareWeight;
    @Excel(name = "收货方填写的发货净重")
    private BigDecimal sendNetWeight;
    @Excel(name = "收货方填写的收货毛重")
    private BigDecimal receiveGrossWeight;
    @Excel(name = "收货方填写的收货皮重")
    private BigDecimal receiveTareWeight;
    @Excel(name = "收货方填写的收货净重")
    private BigDecimal receiveNetWeight;
    @Excel(name = "结算金额")
    private BigDecimal invoiceMoney;
    @Excel(name = "结算单价")
    private BigDecimal unitPrice;
    @Excel(name = "装货时间")
    private Date loadingTime;
    @Excel(name = "收货时间")
    private Date receiveTime;
    @Excel(name = "单据通过时间")
    private Date billPassTime;
    @Excel(name = "完善发货单时间")
    private Date editSendTime;
    @Excel(name = "完善收货单时间")
    private Date editReceiveTime;
    @Excel(name = "复核发货单时间")
    private Date reviewSendTime;
    @Excel(name = "复核收货单时间")
    private Date reviewReceiveTime;
    @Excel(name = "运输合同地址")
    private String pdfPath;
    @Excel(name = "派车时间")
    private Date waybillCreateTime;
    @Excel(name = "CFCA存证文件地址")
    private String cfcaPdfPath;
    @Excel(name = "CFCA单号")
    private String cfcaNo;
}
