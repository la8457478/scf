package com.fkhwl.scf.entity.form;

import com.fkhwl.starter.common.base.BaseForm;
import com.fkhwl.starter.common.enums.DeleteEnum;

import java.util.Date;

import io.swagger.annotations.Api;
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
 * <p>Description: 系统配置 视图实体  </p>
 * 根据业务需求添加字段
 *
 * @author sj
 * @email sj#fkhwl.com
 * @since 2020-02-19
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统配置")
public class ScfConfigForm extends BaseForm<Long> {
    private static final long serialVersionUID = 1L;

    /** 父级Id */
    @ApiModelProperty(value = "父级id")
    private Long parentId;
    /** 配置键 */
    @ApiModelProperty(value = "配置键")
    private String configKey;
    /** 配置值 */
    @ApiModelProperty(value = "配置值")
    private String configValue;
    /** 常量描述 */
    @ApiModelProperty(value = "常量描述")
    private String configDesc;
    /** 配置是否生效 */
    @ApiModelProperty(value = "配置是否生效")
    private Integer configStatus;

    private DeleteEnum deleted;
    private Date createTime;
    private Date updateTime;
    public interface UpdateGroup {

    }
    public interface CreateGroup {

    }
}
