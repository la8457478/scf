package com.fkhwl.scf.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fkhwl.scf.enums.GenderType;
import com.fkhwl.starter.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户表 实体类  </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"@fkhwl.com
 * @date 2020.01.27 18:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("template_user")
public class TemplateUser extends BasePO<Long, TemplateUser> {

    /** USERNAME */
    public static final String USERNAME = "username";
    /** PASSWORD */
    public static final String PASSWORD = "password";
    /** GENDER */
    public static final String GENDER = "gender";
    /** YEARS */
    public static final String YEARS = "years";
    /** ADDRESS */
    public static final String ADDRESS = "address";
    /** EMAIL */
    public static final String EMAIL = "email";
    /** TELEPHONE */
    public static final String TELEPHONE = "telephone";
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 性别 */
    private GenderType gender;
    /** 年龄 */
    private Integer years;
    /** 地址 */
    private String address;
    /** 邮箱 */
    private String email;
    /** 联系电话 */
    private String telephone;

}
