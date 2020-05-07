package com.fkhwl.scf.entity.form;

import com.fkhwl.starter.common.base.BaseForm;

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
 * <p>Description: 项目 视图实体 (根据业务需求添加字段) </p>
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
public class ProjectForm extends BaseForm<Long> {
    private static final long serialVersionUID = 1L;

    /* project 数据*/
    @ApiModelProperty(value = "第三方项目id")
    private Long thirdId;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "创建者用户名称")
    private String createUserName;
    @ApiModelProperty(value = "发货方公司名称")
    private String sendCompanyName;
    @ApiModelProperty(value = "运输方公司名称")
    private String transportCompanyName;
    @ApiModelProperty(value = "收货方公司名称")
    private String consigneeCompanyName;
    @ApiModelProperty(value = "发货方负责人名称")
    private String sendDutyUserName;
    @ApiModelProperty(value = "运输方负责人名称")
    private String transportDutyUserName;
    @ApiModelProperty(value = "收货方负责人名称")
    private String consigneeDutyUserName;
    @ApiModelProperty(value = "物资类型：0普通物资，1汽车,2土石方")
    private Integer materialType;
    @ApiModelProperty(value = "签约主体名称")
    private String signingCompanyName;
    @ApiModelProperty(value = "项目创建时间")
    private Date projectCreateTime;
    @ApiModelProperty(value = "创建人主账号名称")
    private String createOwnerName;

    // 需要再监管平台绑定的id
    //    @ApiModelProperty(value = "绑定的企业用户id")
    //    private Long userId;
    //    @ApiModelProperty(value = "监管系统绑定的企业id")
    //    private Long companyId;
    //    @ApiModelProperty(value = "项目所属借款方企业的某个交易对手的ID")
        private String counterpartyName;

}
