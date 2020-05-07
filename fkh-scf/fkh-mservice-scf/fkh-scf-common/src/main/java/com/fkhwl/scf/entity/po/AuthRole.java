package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限系统角色表 实体类  </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AuthRole extends BasePO<Long, AuthRole> {

    public static final String OWNER_ID = "owner_id";
    public static final String ROLE_NAME = "role_name";
    public static final String ROLE_DESC = "role_desc";
    public static final String ROLE_TYPE = "role_type";
    private static final long serialVersionUID = 1L;

    /** 所属企业主账号id或者0(系统) */
    private Long ownerId;
    /** 角色名 */
    private String roleName;
    /** 角色描述 */
    private String roleDesc;
    /** 用户类型：1.特殊类角色(平台超管，资金方企业主账号，借款方企业主账号)，2.普通类角色 */
    private Integer roleType;

}
