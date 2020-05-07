package com.fkhwl.scf.entity.dto;

import com.fkhwl.starter.common.base.BaseDTO;

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
 * <p>Description: 管理用户表 数据传输实体  </p>
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
public class ScfUserDTO extends BaseDTO<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主账号id")
    private Long ownerId;
    @ApiModelProperty(value = "用户类型 1.系统超级管理员，2.资金方主账户，3.借款方账户， 21.资金方子账户")
    private Integer userType;
    @ApiModelProperty(value = "角色Id")
    private Long roleId;
    @ApiModelProperty(value = "部门Id")
    private Long deptId;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String userPassword;
    @ApiModelProperty(value = "姓名")
    private String nickName;
    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;
    @ApiModelProperty(value = "联系电话")
    private String userMobileNo;
    @ApiModelProperty(value = "用户头像")
    private String userAvatar;
    @ApiModelProperty(value = "上次登录时间")
    private Date lastLoginTime;
    @ApiModelProperty(value = "注册时间")
    private Date createTime;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
