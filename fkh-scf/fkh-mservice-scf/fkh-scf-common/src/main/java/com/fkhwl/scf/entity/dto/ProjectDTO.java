package com.fkhwl.scf.entity.dto;

import com.fkhwl.starter.common.base.BaseDTO;
import com.fkhwl.starter.common.enums.DeleteEnum;

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
 * <p>Description: 项目 数据传输实体 (根据业务需求添加字段) </p>
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
public class ProjectDTO extends BaseDTO<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "第三方项目id")
    private Long thirdId;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "创建者的主账号名称")
    private String createOwnerName;
    @ApiModelProperty(value = "创建者用户ID")
    private Long createUserId;
    @ApiModelProperty(value = "创建者用户名称")
    private String createUserName;
    @ApiModelProperty(value = "发货方Id")
    private Long sendUserId;
    @ApiModelProperty(value = "发货方公司名称")
    private String sendCompanyName;
    @ApiModelProperty(value = "运输方Id")
    private Long transportUserId;
    @ApiModelProperty(value = "运输方公司名称")
    private String transportCompanyName;
    @ApiModelProperty(value = "收货方Id")
    private Long consigneeUserId;
    @ApiModelProperty(value = "收货方公司名称")
    private String consigneeCompanyName;
    @ApiModelProperty(value = "支付方 id")
    private Long paymentUserId;
    @ApiModelProperty(value = "项目状态：-1=删除,1=新建，2=启动,3=结束")
    private Integer projectStatus;
    @ApiModelProperty(value = "0=对外货主,1=无车承运平台货主")
    private Boolean isSelf;
    @ApiModelProperty(value = "是否只完善净重信息 0：否；1：是")
    private Boolean netWeightOnly;
    @ApiModelProperty(value = "物资类型：0普通物资，1汽车,2土石方")
    private Integer materialType;
    @ApiModelProperty(value = "签约主体id")
    private Long signingCompanyid;
    @ApiModelProperty(value = "签约主体名称")
    private String signingCompanyName;

    @ApiModelProperty(value = "监管服务的企业id")
    private Long companyId;
    @ApiModelProperty(value = "项目所属借款方企业的某个交易对手的ID")
    private Long counterpartyId;
    @ApiModelProperty(value = "项目所属借款方企业的某个交易对手的名称")
    private String counterpartyName;
    @ApiModelProperty(value = "项目创建时间")
    private Date projectCreateTime;

    private DeleteEnum deleted;
    private Date createTime;
    private Date updateTime;
    private Integer subjectClaimsOrderCount;

    private String sendDutyUserName;
    private String transportDutyUserName;
    private String consigneeDutyUserName;

}
