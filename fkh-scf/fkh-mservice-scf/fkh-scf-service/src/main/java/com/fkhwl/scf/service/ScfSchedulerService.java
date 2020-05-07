package com.fkhwl.scf.service;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/4/10
 */
public interface ScfSchedulerService {
    /**
    * 定时生成应收账款订单:
     * @param
    * @return: void
    * @Author: liuan
    * @Date: 2020/4/10 19:22
    */
    void generateSubitemClaimsOrder();
}
