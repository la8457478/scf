package com.fkhwl.scf.entity.form;

import com.fkhwl.starter.common.base.BaseForm;

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
 * <p>Description: 计划 视图实体 (根据业务需求添加字段) </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.24 14:19
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProgramForm extends BaseForm<Long> {
    private static final long serialVersionUID = 1L;

    //    Program 计划数据
    /** 第三方计划id */
    @ApiModelProperty(value = "计划id",example = "11")
    private Long thirdId;
    @ApiModelProperty(value = "项目 id")
    private Long projectId;
    @ApiModelProperty(value = "计划创建者名称")
    private String createUserName;
    @ApiModelProperty(value = "计划名称")
    private String programName;
    @ApiModelProperty(value = "出发地")
    private String departureCity;
    @ApiModelProperty(value = "目的地")
    private String arrivalCity;
    @ApiModelProperty(value = "装货地")
    private String loadAddress;
    @ApiModelProperty(value = "卸货工地(工地名称)")
    private String arrivalAddress;
    @ApiModelProperty(value = "型号(标号) -- 货物品类")
    private String modelType;
    @ApiModelProperty(value = "包装形式")
    private String packagedForm;
    @ApiModelProperty(value = "货值单价")
    private BigDecimal cargoPrice;
    @ApiModelProperty(value = "计划数")
    private BigDecimal programNo;
    @ApiModelProperty(value = "计划单位(如:吨,个,件)")
    private String units;
    @ApiModelProperty(value = "计划开始日期")
    private Date programStartDate;

    @ApiModelProperty(value = "计划结束日期")
    private Date programEndDate;
    @ApiModelProperty(value = "里程数")
    private String mileage;
    @ApiModelProperty(value = "车厢长度")
    private String carLength;
    @ApiModelProperty(value = "车厢类型")
    private String carType;
    @ApiModelProperty(value = "运费单价")
    private BigDecimal unitPrice;
    @ApiModelProperty(value = "货物描述")
    private String cargoDesc;
    @ApiModelProperty(value = "磅差选择类型")
    private Integer poundKey;
    @ApiModelProperty(value = "磅差类型对应的值:" +
        "poundKey = 1(扣磅差比)  poundValue=xx (千分比)" +
        "poundKey = 2(扣磅差值) poundValue=xxx(公斤)" +
        "poundKey = 3(不扣磅差) poundValue=-1(已发货方过磅信息为准)/-2(已收货方过磅信息为准)" +
        "poundKey = 4(不过磅) poundValue=-3/poundKey=5(扣磅差阶梯值)poundValue格式含义见文档")
    private String poundValue;
    @ApiModelProperty(value = "物资类型：0普通物资，1汽车,2.土石方")
   private Integer materialType;
    @ApiModelProperty(value = "司机上传单据时必须完善发货或收货信息 0：否；1：是")
    private Integer driverFill;
    @ApiModelProperty(value = "运费收入单价")
    private BigDecimal incomePrice;
    @ApiModelProperty(value = "油卡运费单价")
    private BigDecimal oilOnitPrice;
    @ApiModelProperty(value = "计划创建时间")
    private Date programCreateTime;
    @ApiModelProperty(value = "关联线路名称")
    private String lineName;
    @ApiModelProperty(value = "派车运量")
    private Integer freightVolume;
}
