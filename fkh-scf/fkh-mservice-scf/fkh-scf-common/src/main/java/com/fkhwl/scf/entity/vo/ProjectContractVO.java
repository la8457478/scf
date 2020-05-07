package com.fkhwl.scf.entity.vo;

import com.fkhwl.starter.common.base.BaseVO;

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
 * <p>Description: 项目合同 视图实体 (根据业务需求添加字段) </p>
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
public class ProjectContractVO extends BaseVO<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "第三方合同id")
    private Long thirdId;
    @ApiModelProperty(value = "项目Id")
    private Long projectId;
    @ApiModelProperty(value = "合同")
    private String contract;
    @ApiModelProperty(value = "托运人")
    private String consignName;
    @ApiModelProperty(value = "承运人")
    private String transportName;
    @ApiModelProperty(value = "合同乙方对应公司id(即项目中的签约主体id)")
    private Long transportCompanyId;
    @ApiModelProperty(value = "托运货物名称")
    private String goodName;
    @ApiModelProperty(value = "货物价值")
    private Double goodPrice;
    @ApiModelProperty(value = "税号")
    private String taxNumber;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "电话")
    private String phoneNumber;
    @ApiModelProperty(value = "开户行名称")
    private String bankName;
    @ApiModelProperty(value = "开户行账号")
    private String bankNccountNumber;
    @ApiModelProperty(value = "合同编号")
    private String contractNumber;
    @ApiModelProperty(value = "开票方式")
    private Integer calculationType;
    @ApiModelProperty(value = "运费抹零")
    private Integer pricehandleType;
    @ApiModelProperty(value = "执行周期开始时间")
    private Date executionStartTime;
    @ApiModelProperty(value = "执行周期结束时间")
    private Date executionEndTime;
    @ApiModelProperty(value = "补充协议")
    private String supplementaryAgreement;
    @ApiModelProperty(value = "运费差")
    private BigDecimal taxRate;
    @ApiModelProperty(value = "结算时间类型 （0=以派车时间计算运费，1=以发货时间计算运费，2=以收货时间计算运费）")
    private Integer calcTimeType;
    @ApiModelProperty(value = "预设信息")
    private String presetRecord;

}
