package com.fkhwl.scf.exception;

import com.fkhwl.starter.core.api.BaseCodes;
import com.fkhwl.starter.core.api.IResultCode;
import com.fkhwl.starter.core.exception.BaseException;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 自定义异常</p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"
 * @date 2020.01.27 18:30
 */
public class TemplateException extends BaseException {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Base exception.
     */
    public TemplateException() {
        super();
        resultCode = BaseCodes.FAILURE;
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param msg the msg
     */
    public TemplateException(String msg) {
        super(msg);
        resultCode = BaseCodes.FAILURE;
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param resultCode the result code
     */
    public TemplateException(IResultCode resultCode) {
        super(resultCode);
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param resultCode the response enum
     * @param args       the args
     * @param message    the message
     */
    public TemplateException(IResultCode resultCode, Object[] args, String message) {
        super(resultCode, args, message);
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param resultCode the response enum
     * @param args       the args
     * @param message    the message
     * @param cause      the cause
     */
    public TemplateException(IResultCode resultCode, Object[] args, String message, Throwable cause) {
        super(resultCode, args, message, cause);
    }
}
