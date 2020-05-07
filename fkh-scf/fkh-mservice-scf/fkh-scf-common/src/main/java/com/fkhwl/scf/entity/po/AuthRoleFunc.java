package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 角色权限中间表 实体类  </p>
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
public class AuthRoleFunc extends BasePO<Long, AuthRoleFunc> {

    public static final String AUTH_ROLE_ID = "auth_role_id";
    public static final String AUTH_FUNC_ID = "auth_func_id";
    private static final long serialVersionUID = 1L;

    /** 角色Id */
    private Long authRoleId;
    /** 功能Id */
    private Long authFuncId;

}
