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
 * <p>Description: 行政区域表 视图实体 (根据业务需求添加字段) </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.12 19:07
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ChinaAreaForm extends BaseForm<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地区编码")
    private String code;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "区、县")
    private String area;
    @ApiModelProperty(value = "父级id")
    private Long parentId;
}
