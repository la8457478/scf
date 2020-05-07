package com.fkhwl.scf.entity.form;

import com.fkhwl.starter.common.base.BaseForm;

import io.swagger.annotations.ApiModel;
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
 * <p>Description: 用户配置关系表 视图实体 (根据业务需求添加字段) </p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com"
 * @date 2020.02.29 17:03
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户")
public class ScfUserConfigForm extends BaseForm<Long> {
    private static final long serialVersionUID = 1L;

    /** id-configId-configStatus，多个之间使用英文逗号分隔 */
    @ApiModelProperty(value = "id-configId-configStatus-configValue，多个之间使用英文逗号分隔 ",example = "1-1-1,2-1-2")
    private String idAndConfigIdAndConfigStatus;
}
