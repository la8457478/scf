package com.fkhwl.scf.enums;

import com.fkhwl.starter.common.enums.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"@fkhwl.com
 * @date 2020.01.27 18:26
 */
@Getter
@AllArgsConstructor
public enum GenderType implements EntityEnum<Integer> {

    /** 性别：男 */
    MAN(1, "男"),
    /** 性别：女 */
    WOMAN(2, "女");

    /** Value */
    private final Integer value;
    /** Desc */
    private final String desc;
}
