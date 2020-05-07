package com.fkhwl.scf.entity.form;

import com.fkhwl.scf.enums.GenderType;
import com.fkhwl.starter.common.base.BaseForm;
import com.fkhwl.starter.validation.constraints.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
 * <p>Description: </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"@fkhwl.com
 * @date 2020.01.27 18:29
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel("新增/修改用户入参实体")
public class TemplateUserForm extends BaseForm<Long> {

    /** serialVersionUID */
    private static final long serialVersionUID = -7002718979123279245L;
    /** 用户名 */
    @NotBlank(message = "用户名不能为空", groups = CreateGroup.class)
    @ApiModelProperty(value = "用户名", dataType = "String")
    private String username;
    /** 密码 */
    @NotBlank(message = "密码不能为空", groups = CreateGroup.class)
    @ApiModelProperty(value = "密码", dataType = "String")
    private String password;
    /** 性别 */
    @NotNull(groups = CreateGroup.class)
    @ApiModelProperty(value = "性别")
    private GenderType gender;
    /** 年龄 */
    @Min(value = 0, groups = {CreateGroup.class, UpdateGroup.class})
    @Max(value = 120, groups = {CreateGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "年龄", dataType = "Integer")
    private Integer years;
    /** 地址 */
    @NotBlank(groups = CreateGroup.class)
    @ApiModelProperty(value = "联系地址", dataType = "String")
    private String address;
    /** 邮箱 */
    @Email(groups = {CreateGroup.class, UpdateGroup.class})
    @NotBlank(groups = CreateGroup.class)
    @ApiModelProperty(value = "邮箱地址", dataType = "String")
    private String email;
    /** 联系电话 */
    @Phone(groups = {CreateGroup.class, UpdateGroup.class})
    @NotBlank(groups = CreateGroup.class)
    @ApiModelProperty(value = "联系电话", dataType = "String")
    private String telephone;

    /**
     * <p>Company: 成都返空汇网络技术有限公司 </p>
     * <p>Description: </p>
     *
     * @author dong4j
     * @version 1.0.0
     * @email "mailto:dongshijie@fkhwl.com"
     * @date 2020.01.27 18:29
     */
    public interface UpdateGroup {

    }

    /**
     * <p>Company: 成都返空汇网络技术有限公司 </p>
     * <p>Description: </p>
     *
     * @author dong4j
     * @version 1.0.0
     * @email "mailto:dongshijie@fkhwl.com"
     * @date 2020.01.27 18:29
     */
    public interface CreateGroup {

    }

}
