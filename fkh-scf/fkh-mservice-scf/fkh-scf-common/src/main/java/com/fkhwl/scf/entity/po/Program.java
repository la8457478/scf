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
 * <p>Description: 计划 实体类  </p>
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
public class Program extends BasePO<Long, Program> {

    public static final String THIRD_ID = "third_id";
    public static final String PROJECT_ID = "project_id";
    public static final String CREATE_USER_ID = "create_user_id";
    public static final String CREATE_OWNER_ID = "create_owner_id";
    public static final String CREATE_USER_NAME = "create_user_name";
    public static final String PROGRAM_NAME = "program_name";
    public static final String DEPARTURE_CITY = "departure_city";
    public static final String ARRIVAL_CITY = "arrival_city";
    public static final String LOAD_ADDRESS = "load_address";
    public static final String ARRIVAL_ADDRESS = "arrival_address";
    public static final String ARRIVAL_ADDRESS_WARN = "arrival_address_warn";
    public static final String LOAD_ADDRESS_WARN = "load_address_warn";
    public static final String MODEL_TYPE = "model_type";
    public static final String PACKAGED_FORM = "packaged_form";
    public static final String CARGO_PRICE = "cargo_price";
    public static final String PROGRAM_NO = "program_no";
    public static final String UNITS = "units";
    public static final String PROGRAM_STATUS = "program_status";
    public static final String PROGRAM_START_DATE = "program_start_date";
    public static final String PROGRAM_END_DATE = "program_end_date";
    public static final String MILEAGE = "mileage";
    public static final String CAR_LENGTH = "car_length";
    public static final String CAR_TYPE = "car_type";
    public static final String UNIT_PRICE = "unit_price";
    public static final String CARGO_DESC = "cargo_desc";
    public static final String POUND_KEY = "pound_key";
    public static final String POUND_VALUE = "pound_value";
    public static final String PROGRAM_TYPE = "program_type";
    public static final String MATERIAL_TYPE = "material_type";
    public static final String DRIVER_FILL = "driver_fill";
    public static final String CARGO_OWNER_TYPE = "cargo_owner_type";
    public static final String INCOME_PRICE = "income_price";
    public static final String LINE_ID = "line_id";
    public static final String OIL_ONIT_PRICE = "oil_onit_price";
    public static final String CONTACTER = "contacter";
    public static final String EXCUTION_TIME = "excution_time";
    public static final String MESSAGE_SWITCH = "message_switch";
    public static final String FREIGHT_VOLUME = "freight_volume";
    public static final String POUND_LESSER = "pound_lesser";
    public static final String IS_OPEN_LOCATION_CHECK = "is_open_location_check";
    private static final long serialVersionUID = 1L;

    /** 第三方计划id */
    private Long thirdId;
    /** 项目 id */
    private Long projectId;
    /** 创建者Id */
    private Long createUserId;
    /** 创建者主账号Id */
    private Long createOwnerId;
    /** 创建者名称 */
    private String createUserName;
    /** 计划名称 */
    private String programName;
    /** 出发地 */
    private String departureCity;
    /** 目的地 */
    private String arrivalCity;
    /** 装货地 */
    private String loadAddress;
    /** 卸货工地(工地名称) */
    private String arrivalAddress;
    /** 开启卸货地围栏提醒：0未开启,1开启 */
    private Integer arrivalAddressWarn;
    /** 开始装货地围栏提醒：0未开启,1开启 */
    private Integer loadAddressWarn;
    /** 型号(标号) -- 货物品类 */
    private String modelType;
    /** 包装形式 */
    private String packagedForm;
    /** 货值单价 */
    private BigDecimal cargoPrice;
    /** 计划数 */
    private BigDecimal programNo;
    /** 计划单位(如:吨,个,件) */
    private String units;
    /** 计划状态：-1=删除,1=新建，2=启动,3=结束,4=已完成 */
    private Integer programStatus;
    /** 计划开始日期 */
    private Date programStartDate;
    /** 计划结束日期 */
    private Date programEndDate;
    /** 里程数 */
    private String mileage;
    /** 车厢长度 */
    private String carLength;
    /** 车厢类型 */
    private String carType;
    /** 运费单价 */
    private BigDecimal unitPrice;
    /** 货物描述 */
    private String cargoDesc;
    /** 磅差选择类型 */
    private Integer poundKey;
    /** 磅差类型对应的值:
poundKey = 1(扣磅差比)  poundValue=xx (千分比)
poundKey = 2(扣磅差值) poundValue=xxx(公斤)
poundKey = 3(不扣磅差) poundValue=-1(已发货方过磅信息为准)/-2(已收货方过磅信息为准)
poundKey = 4(不过磅) poundValue=-3/poundKey=5(扣磅差阶梯值)poundValue格式含义见文档 */
    private String poundValue;
    /** 计划发货类型：0.手动，1.自动 */
    private Integer programType;
    /** 物资类型：0普通物资，1汽车,2.土石方 */
    private Integer materialType;
    /** 司机上传单据时必须完善发货或收货信息 0：否；1：是 */
    private Integer driverFill;
    /** 运费担当(1.一票送货;2.两票送货) */
    private Integer cargoOwnerType;
    /** 运费收入单价 */
    private BigDecimal incomePrice;
    /** 关联线路 */
    private Long lineId;
    /** 关联线路名称*/
    private String lineName;
    /** 油卡运费单价 */
    private BigDecimal oilOnitPrice;
    /** 计划联系人 */
    private Long contacter;
    /** 预设执行时间 */
    private Date excutionTime;
    /** 短信配置开关：0未开启,1开启 */
    private Integer messageSwitch;
    /** 派车运量 */
    private Integer freightVolume;
    /** 0：以磅差选择方式为准，1：以较小过磅值为准 */
    private Integer poundLesser;
    /** 1:开启校验扫码领单在发货地，0:不开启 */
    private Integer isOpenLocationCheck;

}
