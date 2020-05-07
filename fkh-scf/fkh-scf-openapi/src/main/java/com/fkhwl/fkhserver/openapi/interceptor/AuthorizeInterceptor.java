package com.fkhwl.fkhserver.openapi.interceptor;

import com.fkhwl.starter.core.api.BaseCodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: com.fkhwl.domain.rest.resp</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: liaohong
 * emali: liaohong@fkhwl.com
 * version: 1.0
 * date: 2018-9-11 16:40
 * updatetime:
 * reason:
 */
@Component
@Slf4j
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(validateRequestSign(request)){
            return super.preHandle(request, response, handler);
        }else{
            throw new com.fkhwl.starter.core.exception.BaseException(BaseCodes.FAILURE);
        }
    }

    private boolean validateRequestSign(HttpServletRequest request){
        String path = request.getServletPath();
        String ipAddress =getIpAddress(request);
        log.error("ip: {}, path: {}", ipAddress, path);
        if(path.startsWith("/wx/") || path.startsWith("/weighbridge/") || path.startsWith("/lb/")
                || path.startsWith("/jingye/") || path.startsWith("/agreements/") || path.startsWith("/contract/") || path.startsWith("/lbs/")){
            return true;
        }
        /**
         *
        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()){
            String name = headers.nextElement();
            System.out.println("header: "+name+" = \t"+request.getHeader(name));
        }

        Enumeration<String> params = request.getParameterNames();
        while(params.hasMoreElements()){
            String name = params.nextElement();
            System.out.println("param: "+name+" = \t"+request.getParameter(name));
        }
         */

        String headFkh=request.getHeader("fkhOilKey");
        if(!"78dd8670d8d175561f95390c08acfeae".equals(headFkh)
            && !(path+"/").contains("/oil/qrcode/") &&  !(path+"/").contains("/oil/list/")&&  !(path+"/").contains("/oil/encode/")){
            log.error("fkhOilKey error============================");
            return false;
        }
        return true;
    }
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        } else {
            ip = request.getHeader("X-Forwarded-For");
            if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                int index = ip.indexOf(44);
                return index != -1 ? ip.substring(0, index) : ip;
            } else {
                return request.getRemoteAddr();
            }
        }
    }
}
