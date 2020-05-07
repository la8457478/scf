package com.fkhwl.fkhserver.openapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

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
 * date: 2018-9-11 16:02
 * updatetime:
 * reason:
 */
@Slf4j
@Component
public class ExceptionHandlerFilter implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        String path = request.getServletPath();
        log.error("Request exception, path: "+ path, ex.getMessage());

        response.setStatus(HttpStatus.OK.value()); //设置状态码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
//
//        Result responseBody;
//        if (ex instanceof DataValidateException) {
//            DataValidateException e = (DataValidateException)ex;
//            responseBody =  Result.failed(e.getCode(), e.getMessage());
//        }else if(ex instanceof NotFoundException){
//            responseBody =  Result.failed();
//        }else if(ex instanceof NoAuthException){
//            responseBody =  Result.failed();
//        }else if(ex instanceof RpcException){
//            log.error("Request exception, path: "+ path, ex);
//            responseBody = Result.failed();
//            if(ex.getCause().toString().contains("SentinelRpcException")){
//                responseBody.setMessage("系统保护，限流访问");
//            }else{
//                responseBody.setMessage("");
//            }
//        }else if(ex instanceof MultipartException || ex instanceof IllegalStateException){
//            responseBody = Result.failed();
//            responseBody.setMessage("上传的文件太大!不能超过10MB.");
//        }else{
//            log.error("Request exception, path: "+ path, ex);
//            responseBody = Result.failed();
//        }
//
//        try {
//            response.getWriter().write(JSON.toJSONString(responseBody));
//        } catch (IOException e) {
//            log.error("与客户端通讯异常:"+ e.getMessage(), e);
//        }

        return new ModelAndView();
    }

}
