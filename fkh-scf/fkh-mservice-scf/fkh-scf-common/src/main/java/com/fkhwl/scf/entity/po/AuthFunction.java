package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限系统资源表 实体类  </p>
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
public class AuthFunction extends BasePO<Long, AuthFunction> {

    public static final String PARENT_ID = "parent_id";
    public static final String FUNC_KEY = "func_key";
    public static final String FUNC_NAME = "func_name";
    public static final String FUNC_DESC = "func_desc";
    public static final String FUNC_URL = "func_url";
    public static final String FUNC_TYPE = "func_type";
    public static final String FUNC_SORT = "func_sort";
    private static final long serialVersionUID = 1L;

    /** 父级Id */
    private Long parentId;
    /** 功能定位key */
    private String funcKey;
    /** 功能名称 */
    private String funcName;
    /** 功能描述 */
    private String funcDesc;
    /** 功能地址 */
    private String funcUrl;
    /** 功能类型（1=菜单） */
    private Integer funcType;
    /** 功能排序 */
    private double funcSort;

}
