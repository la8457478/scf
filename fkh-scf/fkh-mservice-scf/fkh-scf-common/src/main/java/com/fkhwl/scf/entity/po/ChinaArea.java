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
 * <p>Description: 行政区域表 实体类  </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.12 19:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ChinaArea extends BasePO<Long, ChinaArea> {

    public static final String CODE = "code";
    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String AREA = "area";
    public static final String PARENT_ID = "parent_id";
    private static final long serialVersionUID = 1L;

    /** 地区编码 */
    private String code;
    /** 省 */
    private String province;
    /** 市 */
    private String city;
    /** 区、县 */
    private String area;
    /** 父级id */
    private Long parentId;

}
