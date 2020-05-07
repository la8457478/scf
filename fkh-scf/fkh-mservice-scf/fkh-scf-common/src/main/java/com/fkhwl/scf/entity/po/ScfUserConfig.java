package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户配置关系表 实体类  </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.02.29 17:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ScfUserConfig extends BasePO<Long, ScfUserConfig> {

    public static final String OWNER_ID = "owner_id";
    public static final String CONFIG_ID = "config_id";
    public static final String CONFIG_VALUE = "config_value";
    public static final String CONFIG_STATUS = "config_status";
    private static final long serialVersionUID = 1L;

    /** 所属用户id */
    private Long ownerId;
    /** 配置表id */
    private Long configId;
    /** 配置值 */
    private String configValue;
    /** 是否生效：0.失效，1.生效 */
    private Integer configStatus;

}
