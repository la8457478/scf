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
 * <p>Description: 权限系统资源表 视图实体  </p>
 * 根据业务需求添加字段
 *
 * @author ASpiralMoon
 * @email ASpiralMoon#fkhwl.com
 * @since 2020-02-18
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("新增、更新权限系统资源实体")
public class AuthFunctionForm extends BaseForm<Long> {
    private static final long serialVersionUID = 1L;

    /** 父级Id */
    @ApiModelProperty(value = "父级资源")
    private Long parentId;
    /** 功能定位key */
    @ApiModelProperty(value = "功能定位key")
    private String funcKey;
    /** 功能名称 */
    @ApiModelProperty(value = "功能名称")
    private String funcName;
    /** 功能描述 */
    @ApiModelProperty(value = "功能描述")
    private String funcDesc;
    /** 功能地址 */
    @ApiModelProperty(value = "功能地址")
    private String funcUrl;
    @ApiModelProperty(value = "功能类型（1=菜单，2=权限）")
    /** 功能类型（1=菜单） */
    private Integer funcType;
    @ApiModelProperty(value = "功能排序")
    /** 功能排序 */
    private double funcSort;
}
