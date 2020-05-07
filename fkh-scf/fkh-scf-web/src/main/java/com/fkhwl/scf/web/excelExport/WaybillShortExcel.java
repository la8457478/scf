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
public class WaybillShortExcel implements Serializable {
    //    发货时间	收货时间	项目名称	计划名称	运单号	货源出发地	货源目的地	驾驶员姓名	车牌号	联系电话	运费总额

    @Excel(name = "发货时间")
    private Date loadingTime;
    @Excel(name = "收货时间")
    private Date receiveTime;
    @Excel(name = "项目名称")
    private String projectName;
    @Excel(name = "计划名称")
    private String programName;
    @Excel(name = "运单号")
    private String waybillNo;
    @Excel(name = "货源出发地")
    private String departureCity;
    @Excel(name = "货源目的地")
    private String arrivalCity;
    @Excel(name = "驾驶员姓名")
    private String driverName;
    @Excel(name = "车牌号")
    private String licensePlateNo;
    @Excel(name = "联系电话")
    private String driverMobileNo;
    @Excel(name = "运费总额（元）")
    private BigDecimal totalPrice;


//    @Excel(name = "第三方运单id")
//    private Long thirdId;
//    @Excel(name = "货物类型")
//    private String cargoType;
//    @Excel(name = "货物数量")
//    private String cargoNum;
//    @Excel(name = "项目Id")
//    private Long projectId;
//    @Excel(name = "计划Id")
//    private Long programId;
//    @Excel(name = "车厢类型")
//    private String carType;
//    @Excel(name = "车轴数量")
//    private String axleNum;
//    @Excel(name = "车厢长度")
//    private String carLength;
//    @Excel(name = "核载吨位")
//    private String cargoTonnage;
//    @Excel(name = "燃料类型: 1=燃油 2=LNG 3=新能源")
//    private Integer fuelType;
//    @Excel(name = "备注说明")
//    private String remark;
//    @Excel(name = "货物单位")
//    private Integer unit;
//    @Excel(name = "发货单据图片")
//    private String uploadSendInvoice;
//    @Excel(name = "收货单据图片")
//    private String uploadReceiveInvoice;
//    @Excel(name = "发货方填写的发货毛重")
//    private BigDecimal grossWeightBySend;
//    @Excel(name = "发货方填写的发货皮重")
//    private BigDecimal tareWeightBySend;
//    @Excel(name = "发货方填写的发货净重")
//    private BigDecimal netWeightBySend;
//    @Excel(name = "收货方填写的发货毛重")
//    private BigDecimal sendGrossWeight;
//    @Excel(name = "收货方填写的发货皮重")
//    private BigDecimal sendTareWeight;
//    @Excel(name = "收货方填写的发货净重")
//    private BigDecimal sendNetWeight;
//    @Excel(name = "收货方填写的收货毛重")
//    private BigDecimal receiveGrossWeight;
//    @Excel(name = "收货方填写的收货皮重")
//    private BigDecimal receiveTareWeight;
//    @Excel(name = "收货方填写的收货净重")
//    private BigDecimal receiveNetWeight;
//    @Excel(name = "允许磅差类型")
//    private Integer allowDifference;
//    @Excel(name = "允许的磅差比例值")
//    private Integer allowDifferenceVal;
//    @Excel(name = "允许的磅差数")
//    private String allowDifferenceAmount;
//    @Excel(name = "运费单价")
//    private BigDecimal unitPrice;
//    @Excel(name = "货值单价")
//    private BigDecimal valuePrice;
//    @Excel(name = "收入单价")
//    private BigDecimal incomePrice;
//    @Excel(name = "ETC支付金额")
//    private BigDecimal etcCardAmount;
//    @Excel(name = "现金支付金额")
//    private BigDecimal cashPayAmount;
//    @Excel(name = "押金金额")
//    private BigDecimal depositAmount;
//    @Excel(name = "油卡支付金额")
//    private BigDecimal oilCardAmount;
//    @Excel(name = "气卡支付金额")
//    private BigDecimal gasCardAmount;
//    @Excel(name = "运单已使用授信")
//    private BigDecimal oilUsedCredit;
//    @Excel(name = "授信额度")
//    private BigDecimal oilCreditLimit;
//    @Excel(name = "操作类型：0=双方完善，1=发货方为准，2=收货方为准")
//    private Integer operatorType;
//    @Excel(name = "单据通过时间")
//    private Date billPassTime;
//    @Excel(name = "承运方支付运费时间")
//    private Date payFreightTime;
//    @Excel(name = "完善发货单时间")
//    private Date editSendTime;
//    @Excel(name = "完善收货单时间")
//    private Date editReceiveTime;
//    @Excel(name = "复核发货单时间")
//    private Date reviewSendTime;
//    @Excel(name = "复核收货单时间")
//    private Date reviewReceiveTime;
//    @Excel(name = "标的债权订单id")
//    private Long subjectClaimsOrderId;
//    @Excel(name = "运输合同地址")
//    private String pdfPath;
//    @Excel(name = "派车时间")
//    private Date createTime;
//    @Excel(name = "cfca存证文件地址")
//    private String cfcaPdfPath;
}
