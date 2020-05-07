package com.fkhwl.scf.entity.dto;

import com.fkhwl.scf.enums.GenderType;
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
 * <p>Description: 用户表 数据传输实体  </p>
 * 根据业务需求添加字段
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"@fkhwl.com
 * @date 2020.01.27 18:26
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TemplateUserDTO extends BaseDTO<Long> {
    @ApiModelProperty(value = "serialVersionUID")
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "性别")
    private GenderType gender;
    @ApiModelProperty(value = "年龄")
    private Integer years;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "联系电话")
    private String telephone;
}
