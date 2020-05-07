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
 * <p>Description: 权限系统角色表 视图实体  </p>
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
public class AuthRoleForm extends BaseForm<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "所属企业主账号id或者0(系统)")
    private Long ownerId;
    @ApiModelProperty(value = "角色描述")
    private String roleDesc;
    @ApiModelProperty(value = "用户类型：1.特殊类角色(平台超管，资金方企业主账号，借款方企业主账号)，2.普通类角色")
    private Integer roleType;

    @ApiModelProperty(value = "权限id,多个之间使用英文逗号分隔")
    private String funcIds;
}
