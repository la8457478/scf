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
 * <p>Description: 货主运单表 实体类  </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Waybill extends BasePO<Long, Waybill> {

    public static final String THIRD_ID = "third_id";
    public static final String WAYBILL_NO = "waybill_no";
    public static final String DEPARTURE_CITY = "departure_city";
    public static final String ARRIVAL_CITY = "arrival_city";
    public static final String CARGO_TYPE = "cargo_type";
    public static final String CARGO_NUM = "cargo_num";
    public static final String PROJECT_ID = "project_id";
    public static final String PROJECT_NAME = "project_name";
    public static final String PROGRAM_ID = "program_id";
    public static final String PROGRAM_NAME = "program_name";
    public static final String DRIVER_NAME = "driver_name";
    public static final String DRIVER_MOBILE_NO = "driver_mobile_no";
    public static final String LICENSE_PLATE_NO = "license_plate_no";
    public static final String CAR_TYPE = "car_type";
    public static final String AXLE_NUM = "axle_num";
    public static final String CAR_LENGTH = "car_length";
    public static final String CARGO_TONNAGE = "cargo_tonnage";
    public static final String FUEL_TYPE = "fuel_type";
    public static final String REMARK = "remark";
    public static final String UNIT = "unit";
    public static final String UPLOAD_SEND_INVOICE = "upload_send_invoice";
    public static final String UPLOAD_RECEIVE_INVOICE = "upload_receive_invoice";
    public static final String GROSS_WEIGHT_BY_SEND = "gross_weight_by_send";
    public static final String TARE_WEIGHT_BY_SEND = "tare_weight_by_send";
    public static final String NET_WEIGHT_BY_SEND = "net_weight_by_send";
    public static final String SEND_GROSS_WEIGHT = "send_gross_weight";
    public static final String SEND_TARE_WEIGHT = "send_tare_weight";
    public static final String SEND_NET_WEIGHT = "send_net_weight";
    public static final String RECEIVE_GROSS_WEIGHT = "receive_gross_weight";
    public static final String RECEIVE_TARE_WEIGHT = "receive_tare_weight";
    public static final String RECEIVE_NET_WEIGHT = "receive_net_weight";
    public static final String ALLOW_DIFFERENCE = "allow_difference";
    public static final String ALLOW_DIFFERENCE_VAL = "allow_difference_val";
    public static final String ALLOW_DIFFERENCE_AMOUNT = "allow_difference_amount";
    public static final String UNIT_PRICE = "unit_price";
    public static final String VALUE_PRICE = "value_price";
    public static final String TOTAL_PRICE = "total_price";
    public static final String INCOME_PRICE = "income_price";
    public static final String ETC_CARD_AMOUNT = "etc_card_amount";
    public static final String CASH_PAY_AMOUNT = "cash_pay_amount";
    public static final String DEPOSIT_AMOUNT = "deposit_amount";
    public static final String OIL_CARD_AMOUNT = "oil_card_amount";
    public static final String GAS_CARD_AMOUNT = "gas_card_amount";
    public static final String OIL_USED_CREDIT = "oil_used_credit";
    public static final String OIL_CREDIT_LIMIT = "oil_credit_limit";
    public static final String OPERATOR_TYPE = "operator_type";
    public static final String LOADING_TIME = "loading_time";
    public static final String RECEIVE_TIME = "receive_time";
    public static final String BILL_PASS_TIME = "bill_pass_time";
    public static final String PAY_FREIGHT_TIME = "pay_freight_time";
    public static final String EDIT_SEND_TIME = "edit_send_time";
    public static final String EDIT_RECEIVE_TIME = "edit_receive_time";
    public static final String REVIEW_SEND_TIME = "review_send_time";
    public static final String REVIEW_RECEIVE_TIME = "review_receive_time";
    public static final String SUBJECT_CLAIMS_ORDER_ID = "subject_claims_order_id";
    public static final String PDF_PATH = "pdf_path";
    public static final String WAYBILL_STATUS = "waybill_status";
    private static final long serialVersionUID = 1L;

    /** 第三方运单id */
    private Long thirdId;
    /** 运单唯一编号 */
    private String waybillNo;
    /** 出发地 */
    private String departureCity;
    /** 目的地 */
    private String arrivalCity;
    /** 货物类型 */
    private String cargoType;
    /** 货物数量 */
    private String cargoNum;
    /** 项目Id */
    private Long projectId;
    /** 项目名称 */
    private String projectName;
    /** 计划Id */
    private Long programId;
    /** 计划名称 */
    private String programName;
    /** 驾驶员姓名 */
    private String driverName;
    /** 驾驶员手机号 */
    private String driverMobileNo;
    /** 车牌号 */
    private String licensePlateNo;
    /** 车厢类型 */
    private String carType;
    /** 车轴数量 */
    private String axleNum;
    /** 车厢长度 */
    private String carLength;
    /** 核载吨位 */
    private String cargoTonnage;
    /** 燃料类型: 1=燃油 2=LNG 3=新能源 */
    private Integer fuelType;
    /** 备注说明 */
    private String remark;
    /** 货物单位 */
    private Integer unit;
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
    /** 收入单价 ，结算单价*/
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
    private Integer waybillStatus;

    /** 结算金额*/
    private BigDecimal invoiceMoney;
    /** 轨迹点个数*/
    private Long gpsCount;
    /** 派车时间*/
    private Date waybillCreateTime;

    /** cfca存证单号 */
    private String cfcaNo;

    /** 轨迹点类型 */
    private Integer gpsType;

}
