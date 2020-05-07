package com.fkhwl.scf.third.service;


import com.fkhwl.scf.third.resp.logink.BizContent;

/**
 * <p>Title: com.fkhwl.service.logink.service</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: dong4j
 * emali: dongshijie@fkhwl.com
 * version: 1.0
 * date: 2018年04月15日 15点09分
 * updatetime:
 * reason:
 */
public interface LoginkService {


    /**
     * 返回车辆是否入网正常
     * @param licensePlateNo
     * @return
     */
    boolean checkPlatformNetInIsOk(String licensePlateNo);

    /**
     * 获取无车承运平台的车辆信息
     * @param licensePlateNo 需要查询的车牌号
     * @return
     */
    BizContent getPlatformCarInfo(String licensePlateNo);
}
