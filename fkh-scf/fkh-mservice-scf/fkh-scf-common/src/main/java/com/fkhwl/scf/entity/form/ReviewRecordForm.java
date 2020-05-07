package com.fkhwl.scf.entity.form;

import com.fkhwl.starter.common.base.BaseForm;

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
 * <p>Description:  视图实体 (根据业务需求添加字段) </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.03.02 12:11
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ReviewRecordForm extends BaseForm<Long> {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "审核结果")
    private Integer reviewResult;
    @ApiModelProperty(value = "审核意见")
    private String reviewReason;
    @ApiModelProperty(value = "用款申请id")
    private Long creditApplyId;
    @ApiModelProperty(value = "审核人id")
    private Long reviewUserId;
}
