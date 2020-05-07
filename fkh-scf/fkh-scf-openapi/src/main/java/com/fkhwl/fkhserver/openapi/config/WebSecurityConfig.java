//package com.fkhwl.fkhserver.openapi.config;
//
//import com.fkhwl.fkhserver.openapi.interceptor.AuthorizeInterceptor;
//
//import org.springframework.boot.web.servlet.MultipartConfigFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.format.datetime.DateFormatter;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.resource.ContentVersionStrategy;
//import org.springframework.web.servlet.resource.VersionResourceResolver;
//
//import java.util.List;
//
//import javax.servlet.MultipartConfigElement;
//
///**
// * <p>Title: com.fkhwl.domain.rest.resp</p>
// * <p>Company: 成都返空汇网络技术有限公司</p>
// * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
// * <p>Description: </p>
// * author: liaohong
// * emali: liaohong@fkhwl.com
// * version: 1.0
// * date: 2018-9-11 16:51
// * updatetime:
// * reason:
// */
//@Configuration
//public class WebSecurityConfig extends WebMvcConfigurerAdapter {
//
//    @Bean
//    public AuthorizeInterceptor getAuthorizeInterceptor() {
//        return new AuthorizeInterceptor();
//    }
//    /**
//     * 配置静态访问资源
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/image/").addResourceLocations("classpath:/agreements/").addResourceLocations("classpath:/resources/").addResourceLocations("classpath:/statics/").addResourceLocations("classpath:/META-INF/resources/");
//        super.addResourceHandlers(registry);
//    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getAuthorizeInterceptor()).excludePathPatterns("/login/**").excludePathPatterns("/statics/**").excludePathPatterns("/resources/**").excludePathPatterns("/**/swagger-resources/**").excludePathPatterns("/dingtalk/**").excludePathPatterns("/**");
//        super.addInterceptors(registry);
//    }
//
//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        super.configureHandlerExceptionResolvers(exceptionResolvers);
//    }
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
//    }
//
//}
