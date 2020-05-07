package com.fkhwl.scf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  </p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dongshijie@fkhwl.com"
 * @date 2020.01.10 17:32
 */
@Getter
@AllArgsConstructor
public enum TemplateCodes {
    ;

    /** 返回码 */
    private final int code;
    /** 返回消息 */
    private final String message;

}
