package com.fkhwl.scf.entity.dto;

import com.fkhwl.starter.common.base.BaseDTO;

import java.math.BigDecimal;
import java.util.Date;

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
 * <p>Description: 货主运单表 数据传输实体  </p>
 * 根据业务需求添加字段
 *
 * @author chenli
 * @email chenli#fkhwl.com
 * @since 2020-02-20
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WaybillDTO extends BaseDTO<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "第三方运单id")
    private Long thirdId;
    @ApiModelProperty(value = "运单唯一编号")
    private String waybillNo;
    @ApiModelProperty(value = "出发地")
    private String departureCity;
    @ApiModelProperty(value = "目的地")
    private String arrivalCity;
    @ApiModelProperty(value = "货物类型")
    private String cargoType;
    @ApiModelProperty(value = "货物数量")
    private String cargoNum;
    @ApiModelProperty(value = "项目Id")
    private Long projectId;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "计划Id")
    private Long programId;
    @ApiModelProperty(value = "计划名称")
    private String programName;
    @ApiModelProperty(value = "驾驶员姓名")
    private String driverName;
    @ApiModelProperty(value = "驾驶员手机号")
    private String driverMobileNo;
    @ApiModelProperty(value = "车牌号")
    private String licensePlateNo;
    @ApiModelProperty(value = "车厢类型")
    private String carType;
    @ApiModelProperty(value = "车轴数量")
    private String axleNum;
    @ApiModelProperty(value = "车厢长度")
    private String carLength;
    @ApiModelProperty(value = "核载吨位")
    private String cargoTonnage;
    @ApiModelProperty(value = "燃料类型: 1=燃油 2=LNG 3=新能源")
    private Integer fuelType;
    @ApiModelProperty(value = "备注说明")
    private String remark;
    @ApiModelProperty(value = "货物单位")
    private Integer unit;
    @ApiModelProperty(value = "发货单据图片")
    private String uploadSendInvoice;
    @ApiModelProperty(value = "收货单据图片")
    private String uploadReceiveInvoice;
    @ApiModelProperty(value = "发货方填写的发货毛重")
    private BigDecimal grossWeightBySend;
    @ApiModelProperty(value = "发货方填写的发货皮重")
    private BigDecimal tareWeightBySend;
    @ApiModelProperty(value = "发货方填写的发货净重")
    private BigDecimal netWeightBySend;
    @ApiModelProperty(value = "收货方填写的发货毛重")
    private BigDecimal sendGrossWeight;
    @ApiModelProperty(value = "收货方填写的发货皮重")
    private BigDecimal sendTareWeight;
    @ApiModelProperty(value = "收货方填写的发货净重")
    private BigDecimal sendNetWeight;
    @ApiModelProperty(value = "收货方填写的收货毛重")
    private BigDecimal receiveGrossWeight;
    @ApiModelProperty(value = "收货方填写的收货皮重")
    private BigDecimal receiveTareWeight;
    @ApiModelProperty(value = "收货方填写的收货净重")
    private BigDecimal receiveNetWeight;
    @ApiModelProperty(value = "允许磅差类型")
    private Integer allowDifference;
    @ApiModelProperty(value = "允许的磅差比例值")
    private Integer allowDifferenceVal;
    @ApiModelProperty(value = "允许的磅差数")
    private String allowDifferenceAmount;
    @ApiModelProperty(value = "运费单价")
    private BigDecimal unitPrice;
    @ApiModelProperty(value = "货值单价")
    private BigDecimal valuePrice;
    @ApiModelProperty(value = "总运费额（成本）")
    private BigDecimal totalPrice;
    @ApiModelProperty(value = "收入单价:结算单价")
    private BigDecimal incomePrice;
    @ApiModelProperty(value = "ETC支付金额")
    private BigDecimal etcCardAmount;
    @ApiModelProperty(value = "现金支付金额")
    private BigDecimal cashPayAmount;
    @ApiModelProperty(value = "押金金额")
    private BigDecimal depositAmount;
    @ApiModelProperty(value = "油卡支付金额")
    private BigDecimal oilCardAmount;
    @ApiModelProperty(value = "气卡支付金额")
    private BigDecimal gasCardAmount;
    @ApiModelProperty(value = "运单已使用授信")
    private BigDecimal oilUsedCredit;
    @ApiModelProperty(value = "授信额度")
    private BigDecimal oilCreditLimit;
    @ApiModelProperty(value = "操作类型：0=双方完善，1=发货方为准，2=收货方为准")
    private Integer operatorType;
    @ApiModelProperty(value = "装货时间")
    private Date loadingTime;
    @ApiModelProperty(value = "收货时间")
    private Date receiveTime;
    @ApiModelProperty(value = "单据通过时间")
    private Date billPassTime;
    @ApiModelProperty(value = "承运方支付运费时间")
    private Date payFreightTime;
    @ApiModelProperty(value = "完善发货单时间")
    private Date editSendTime;
    @ApiModelProperty(value = "完善收货单时间")
    private Date editReceiveTime;
    @ApiModelProperty(value = "复核发货单时间")
    private Date reviewSendTime;
    @ApiModelProperty(value = "复核收货单时间")
    private Date reviewReceiveTime;
    @ApiModelProperty(value = "标的债权订单id")
    private Long subjectClaimsOrderId;
    @ApiModelProperty(value = "运输合同地址")
    private String pdfPath;
    @ApiModelProperty(value = "派车时间")
    private Date waybillCreateTime;

    private Date createTime;

    @ApiModelProperty(value = "交易对手id")
    private Long counterpartyId;
    @ApiModelProperty(value = "合同路径")
    private String  consigneeName;

    @ApiModelProperty(value = "cfca存证文件地址")
    private String cfcaPdfPath;

    @ApiModelProperty(value = "运单状态：-1.异常 ,0.正常")
    private Integer waybillStatus;

    @ApiModelProperty(value = "发货方：身份证号码")
    private String idCardNoSend;
    @ApiModelProperty(value = "发货方：身份证照片")
    private String idCardPictureSend;
    @ApiModelProperty(value = "发货方：身份证照片背面")
    private String idCardPictureBackSend;
    @ApiModelProperty(value = "发货方：营业执照号码")
    private String businessLicenseNoSend;
    @ApiModelProperty(value = "发货方：法人代表")
    private String legalPersonSend;
    @ApiModelProperty(value = "发货方：营业执照照片")
    private String businessLicensePictureSend;
    @ApiModelProperty(value = "道路运输许可证号码")
    private String vehicleOperatingNo;
    @ApiModelProperty(value = "从业资格证图片")
    private String licenceoPicture;
    @ApiModelProperty(value = "装货地坐标")
    private String loadLocation;
    @ApiModelProperty(value = "卸货地坐标")
    private String arrivalLocation;
    @ApiModelProperty(value = "收货方：身份证号码")
    private String idCardNoConsignee;
    @ApiModelProperty(value = "收货方：身份证照片")
    private String idCardPictureConsignee;
    @ApiModelProperty(value = "收货方：身份证照片背面")
    private String idCardPictureBackConsignee;
    @ApiModelProperty(value = "收货方：营业执照号码")
    private String businessLicenseNoConsignee;
    @ApiModelProperty(value = "收货方：法人代表")
    private String legalPersonConsignee;
    @ApiModelProperty(value = "收货方：营业执照照片")
    private String businessLicensePictureConsignee;
    @ApiModelProperty(value = "结算金额")
    private BigDecimal invoiceMoney;
    @ApiModelProperty(value = "轨迹点个数")
    private Long gpsCount;
    @ApiModelProperty(value = "cfca存证单号")
    private String cfcaNo;
    @ApiModelProperty(value = "轨迹点类型")
    private Integer gpsType;
}
