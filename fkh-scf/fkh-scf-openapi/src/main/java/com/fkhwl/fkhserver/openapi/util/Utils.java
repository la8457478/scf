package com.fkhwl.fkhserver.openapi.util;

import javax.servlet.http.HttpServletRequest;
/**
* <p>Title: com.fkhwl.fkhserver.component.configure.enums</p>
* <p>Company: 成都返空汇网络技术有限公司</p>handleCancelPayWaybillCar
* <p>Copyright © 2014 返空汇 All Rights Reserved</p>
 *  工具类
 * @author AndyLiu
 * @date 2019/3/4 16:00
 * @param
 * @return
 */

public class Utils {
    public static String getIpAddress(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getRemoteAddr();
    }
    if(ip != null && ip.contains(",")){
        ip = ip.split(",")[0];
    }
    return ip;
}
}
