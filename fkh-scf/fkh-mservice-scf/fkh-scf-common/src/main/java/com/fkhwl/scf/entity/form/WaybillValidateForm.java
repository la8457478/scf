package com.fkhwl.scf.entity.form;

import com.fkhwl.starter.common.base.BaseForm;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单表 视图实体 (根据业务需求添加字段) </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.29 11:43
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WaybillValidateForm extends BaseForm<Long> {
    private static final long serialVersionUID = 1L;


    /** 第三方运单id */
    private Long thirdId;
    /** 运单唯一编号 */
    private String waybillNo;
    /** 出发地 */
    private String departureCity;
    /** 目的地 */
    private String arrivalCity;
    /** 驾驶员姓名 */
    private String driverName;
    /** 驾驶员手机号 */
    private String driverMobileNo;
    /** 车牌号 */
    private String licensePlateNo;
    /** 发货单据图片 */
    private String uploadSendInvoice;
    /** 收货单据图片 */
    private String uploadReceiveInvoice;
    /** 发货方填写的发货毛重 */
    private BigDecimal grossWeightBySend;
    /** 发货方填写的发货皮重 */
    private BigDecimal tareWeightBySend;
    /** 发货方填写的发货净重 */
    private BigDecimal netWeightBySend;
    /** 收货方填写的发货毛重 */
    private BigDecimal sendGrossWeight;
    /** 收货方填写的发货皮重 */
    private BigDecimal sendTareWeight;
    /** 收货方填写的发货净重 */
    private BigDecimal sendNetWeight;
    /** 收货方填写的收货毛重 */
    private BigDecimal receiveGrossWeight;
    /** 收货方填写的收货皮重 */
    private BigDecimal receiveTareWeight;
    /** 收货方填写的收货净重 */
    private BigDecimal receiveNetWeight;
    /** 允许磅差类型 */
    private Integer allowDifference;
    /** 允许的磅差比例值 */
    private Integer allowDifferenceVal;
    /** 允许的磅差数 */
    private String allowDifferenceAmount;
    /** 运费单价 */
    private BigDecimal unitPrice;
    /** 货值单价 */
    private BigDecimal valuePrice;
    /** 总运费额（成本） */
    private BigDecimal totalPrice;
    /** 收入单价 */
    private BigDecimal incomePrice;
    /** ETC支付金额 */
    private BigDecimal etcCardAmount;
    /** 现金支付金额 */
    private BigDecimal cashPayAmount;
    /** 押金金额 */
    private BigDecimal depositAmount;
    /** 油卡支付金额 */
    private BigDecimal oilCardAmount;
    /** 气卡支付金额 */
    private BigDecimal gasCardAmount;
    /** 运单已使用授信 */
    private BigDecimal oilUsedCredit;
    /** 授信额度 */
    private BigDecimal oilCreditLimit;
    /** 操作类型：0=双方完善，1=发货方为准，2=收货方为准 */
    private Integer operatorType;
    /** 装货时间 */
    private Date loadingTime;
    /** 收货时间 */
    private Date receiveTime;
    /** 单据通过时间 */
    private Date billPassTime;
    /** 承运方支付运费时间 */
    private Date payFreightTime;
    /** 完善发货单时间 */
    private Date editSendTime;
    /** 完善收货单时间 */
    private Date editReceiveTime;
    /** 复核发货单时间 */
    private Date reviewSendTime;
    /** 复核收货单时间 */
    private Date reviewReceiveTime;
    /** 标的债权订单id */
    private Long subjectClaimsOrderId;
    /** 运输合同地址 */
    private String pdfPath;
    /** cfca存证文件地址 */
    private String cfcaPdfPath;
    /** 运单状态：-1.异常 ,0.正常 */
    private Boolean waybillStatus;
    /** 发货方：身份证号码 */
    private String idCardNoSend;
    /** 发货方：身份证照片 */
    private String idCardPictureSend;
    /** 发货方：身份证照片背面 */
    private String idCardPictureBackSend;
    /** 发货方：营业执照号码 */
    private String businessLicenseNoSend;
    /** 发货方：法人代表 */
    private String legalPersonSend;
    /** 发货方：营业执照照片 */
    private String businessLicensePictureSend;
    /** 道路运输许可证号码 */
    private String vehicleOperatingNo;
    /** 从业资格证图片 */
    private String licenceoPicture;
    /** 装货地坐标 */
    private String loadLocation;
    /** 卸货地坐标 */
    private String arrivalLocation;
    /** 收货方：身份证号码 */
    private String idCardNoConsignee;
    /** 收货方：身份证照片 */
    private String idCardPictureConsignee;
    /** 收货方：身份证照片背面 */
    private String idCardPictureBackConsignee;
    /** 收货方：营业执照号码 */
    private String businessLicenseNoConsignee;
    /** 收货方：法人代表 */
    private String legalPersonConsignee;
    /** 收货方：营业执照照片 */
    private String businessLicensePictureConsignee;

    /** 结算金额*/
    private BigDecimal invoiceMoney;
    /** 轨迹点个数*/
    private Long gpsCount;
    /** 派车时间*/
    private Date waybillCreateTime;
}
