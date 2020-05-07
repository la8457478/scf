package com.fkhwl.scf.enums;

import com.fkhwl.starter.core.assertion.BaseExceptionAssert;

/**
 * <p>Title: com.fkhwl.scf.enums</p>
 * <p>Company: 成都返空汇网络技术有限公</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: 响应code信息</p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date: 2020年03月12日 15点23分
 * updatetime:
 * reason:
 */
public enum  ScfBaseCodes implements BaseExceptionAssert {

    COMPANY_BORROWER_EXIST(2000, "借款方合同已经存在");

    private final int code;
    private final String message;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    ScfBaseCodes(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}
