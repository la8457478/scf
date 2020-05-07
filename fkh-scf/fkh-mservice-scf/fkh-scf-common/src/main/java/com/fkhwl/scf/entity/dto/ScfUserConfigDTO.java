package com.fkhwl.scf.entity.dto;

import com.fkhwl.starter.common.base.BaseDTO;

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
 * <p>Description: 用户配置关系表 数据传输实体 (根据业务需求添加字段) </p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com"
 * @date 2020.02.29 17:03
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ScfUserConfigDTO extends BaseDTO<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属用户id")
    private Long ownerId;
    @ApiModelProperty(value = "配置表id")
    private Long configId;
    @ApiModelProperty(value = "配置key")
    private String configKey;
    @ApiModelProperty(value = "配置值")
    private String configValue;
    @ApiModelProperty(value = "常量描述")
    private String configDesc;
    @ApiModelProperty(value = "配置是否生效")
    private Integer configStatus;

    @ApiModelProperty(value = "id-configId-configStatus，多个之间使用英文逗号分隔")
    private String idAndConfigIdAndConfigStatus;
}
