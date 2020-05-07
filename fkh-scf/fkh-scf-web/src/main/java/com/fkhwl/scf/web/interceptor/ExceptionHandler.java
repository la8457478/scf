package com.fkhwl.scf.web.interceptor;


import com.alibaba.fastjson.JSON;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.api.BaseCodes;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	private Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
										 Exception ex) {
//		log.error("["+request.getServletPath()+"] "+handler.toString(),ex);
		ModelAndView mv = new ModelAndView();
		response.setStatus(HttpStatus.OK.value()); //设置状态码
		response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
		response.setCharacterEncoding("UTF-8"); //避免乱码
		response.setHeader("Cache-Control", "no-cache, must-revalidate");

        log.error("request uri:{} error: {} exception:{}", request.getRequestURI(),ex.getMessage(),ex.getClass().getName());
        if(StringUtils.isBlank(ex.getMessage())){
            Result result= StandardResult.failed(BaseCodes.SERVER_INNER_ERROR.getCode(),BaseCodes.SERVER_INNER_ERROR.getMessage());
            try {
                response.getWriter().write(JSON.toJSONString(result));
            } catch (IOException e) {
                log.error("与客户端通讯异常:"+ e.getMessage(), e);
            }
        }else {
            try {
                response.getWriter().write(JSON.toJSONString(StandardResult.failed(ex.getMessage())));
            } catch (IOException e) {
                log.error("与客户端通讯异常:" + e.getMessage(), e);
            }
        }

		return mv;
	}
}
