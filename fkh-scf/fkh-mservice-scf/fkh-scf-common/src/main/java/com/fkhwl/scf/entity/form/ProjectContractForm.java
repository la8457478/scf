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
public class ProjectContractForm extends BaseForm<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "todo: [自动生成的字段, 避免此实体没有字段导致启动失败的问题, 可删除]")
    private String autoField;
}
