package com.fkhwl.scf.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fkhwl.starter.common.base.BasePO;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 系统配置 实体类  </p>
 *
 * @author sj
 * @email sj@fkhwl.com
 * @since 2020-02-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ScfConfig extends BasePO<Long, ScfConfig> {

    public static final String PARENT_ID = "parent_id";
    public static final String CONFIG_KEY = "config_key";
    public static final String CONFIG_VALUE = "config_value";
    public static final String CONFIG_DESC = "config_desc";
    public static final String CONFIG_STATUS = "config_status";
    //定时任务时间KEY
    public static final String SCHEDULE_EXECUTE_HOUR_CONFIG_KEY = "SCHEDULE_EXECUTE_HOUR";
    private static final long serialVersionUID = 1L;

    /** 父级Id */
    private Long parentId;
    /** 配置键 */
    private String configKey;
    /** 配置值 */
    private String configValue;
    /** 常量描述 */
    private String configDesc;
    /** 配置是否生效 */
    private Integer configStatus;

}
