package com.fkhwl.scf.entity.vo;

import com.fkhwl.starter.common.base.BaseVO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
 * <p>Description: 管理用户表 视图实体  </p>
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
public class ScfUserVO extends BaseVO<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主账号id")
    private Long ownerId;
    @ApiModelProperty(value = "用户类型 1.  系统超级管理员，2.企业主账号，3.运营管理用户，4.风控用户，5.财务用户，6.企业管理层")
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
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;
    @ApiModelProperty(value = "联系电话")
    private String userMobileNo;
    @ApiModelProperty(value = "用户头像")
    private String userAvatar;
    @ApiModelProperty(value = "上次登录时间")
    private Date lastLoginTime;
    @ApiModelProperty(value = "公司id")
    private Long companyId;
    @ApiModelProperty(value = "公司类型")
    //企业类型：1.资方，2.借款方
    private Integer companyType;
    @ApiModelProperty(value = "注册时间")
    private Date createTime;
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 获取主账号id
     * @return
     */
    public Long getParentId() {
        return ownerId == 0 ? getId() : ownerId;
    }

    /**
     * 用户权限map
     * @return
     */
    Map<String, Boolean> authMap = new HashMap<>();
}
