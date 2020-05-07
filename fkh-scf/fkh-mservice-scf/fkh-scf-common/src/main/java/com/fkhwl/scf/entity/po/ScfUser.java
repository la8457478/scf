package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 管理用户表 实体类  </p>
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
public class ScfUser extends BasePO<Long, ScfUser> {

    public static final String OWNER_ID = "owner_id";
    public static final String USER_TYPE = "user_type";
    public static final String ROLE_ID = "role_id";
    public static final String USERNAME = "username";
    public static final String USER_PASSWORD = "user_password";
    public static final String NICK_NAME = "nick_name";
    public static final String COMPANY_ID = "company_id";
    public static final String COMPANY_NAME = "company_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_MOBILE_NO = "user_mobile_no";
    public static final String USER_AVATAR = "user_avatar";
    public static final String LAST_LOGIN_TIME = "last_login_time";
    private static final long serialVersionUID = 1L;

    /** 主账号id */
    private Long ownerId;
    /** 用户类型 1.系统超级管理员，2.资金方主账户，3.借款方账户， 21.资金方子账户 */
    private Integer userType;
    /** 角色Id */
    private Long roleId;
    /** 部门Id */
    private Long deptId;
    /** 用户名 */
    private String username;
    /** 用户密码 */
    private String userPassword;
    /** 姓名 */
    private String nickName;
    /** 用户邮箱 */
    private String userEmail;
    /** 联系电话 */
    private String userMobileNo;
    /** 用户头像 */
    private String userAvatar;
    /** 上次登录时间 */
    private Date lastLoginTime;

}
