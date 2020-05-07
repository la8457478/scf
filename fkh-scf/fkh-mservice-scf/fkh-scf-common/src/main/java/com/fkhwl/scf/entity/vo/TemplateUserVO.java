package com.fkhwl.scf.entity.vo;

import com.fkhwl.starter.common.base.BaseVO;
import com.fkhwl.scf.enums.GenderType;

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
 * <p>Description: 用户表 视图实体  </p>
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
@ApiModel("用户实体")
public class TemplateUserVO extends BaseVO<Long> {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    private String username;
    /** 密码 */
    @ApiModelProperty(value = "密码")
    private String password;
    /** 性别 */
    @ApiModelProperty(value = "性别")
    private GenderType gender;
    /** 年龄 */
    @ApiModelProperty(value = "年龄")
    private Integer years;
    /** 地址 */
    @ApiModelProperty(value = "地址")
    private String address;
    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /** 联系电话 */
    @ApiModelProperty(value = "联系电话")
    private String telephone;
}
