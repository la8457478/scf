package com.fkhwl.scf.assertion;

import com.fkhwl.starter.core.api.IResultCode;
import com.fkhwl.starter.core.assertion.IAssert;
import com.fkhwl.starter.core.exception.BaseException;
import com.fkhwl.scf.exception.TemplateException;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: </p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dongshijie@fkhwl.com"
 * @date 2020.01.10 18:27
 */
public interface TemplateExceptionAssert extends IResultCode, IAssert {

    /**
     * New exceptions base exception.
     *
     * @param args the args
     * @return the base exception
     */
    @Override
    default BaseException newException(Object... args) {
        return new TemplateException(this, args, getMessage());
    }


    /**
     * New exceptions base exception.
     *
     * @param t    the t
     * @param args the args
     * @return the base exception
     */
    @Override
    default BaseException newException(Throwable t, Object... args) {
        return new TemplateException(this, args, getMessage(), t);
    }
}
