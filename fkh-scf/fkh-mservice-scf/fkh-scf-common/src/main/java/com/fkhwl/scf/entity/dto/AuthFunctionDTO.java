package com.fkhwl.scf.entity.dto;

import com.fkhwl.starter.common.base.BaseDTO;

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
 * <p>Description: 权限系统资源表 数据传输实体  </p>
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
public class AuthFunctionDTO extends BaseDTO<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父级Id")
    private Long parentId;
    @ApiModelProperty(value = "功能定位key")
    private String funcKey;
    @ApiModelProperty(value = "功能名称")
    private String funcName;
    @ApiModelProperty(value = "功能描述")
    private String funcDesc;
    @ApiModelProperty(value = "功能地址")
    private String funcUrl;
    @ApiModelProperty(value = "功能类型（1=菜单）")
    private Integer funcType;
    @ApiModelProperty(value = "功能排序")
    private double funcSort;
}
