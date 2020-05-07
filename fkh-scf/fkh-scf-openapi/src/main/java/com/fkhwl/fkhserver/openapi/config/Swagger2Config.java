//package com.fkhwl.fkhserver.openapi.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * <p>Title: com.fkhwl.domain.rest.resp</p>
// * <p>Company: 成都返空汇网络技术有限公司</p>
// * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
// * <p>Description: </p>
// * author: liaohong
// * emali: liaohong@fkhwl.com
// * version: 1.0
// * date: 2018-9-10 19:05
// * updatetime:
// * reason:
// */
//@EnableSwagger2
//@ComponentScan(basePackages={"com.fkhwl.fkhserver.openapi.controller"})
//@Configuration
//public class Swagger2Config {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(apiInfo())
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.fkhwl.fkhserver.openapi"))
//            .paths(PathSelectors.any())
//            .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//            .title("springboot利用swagger构建api文档")
//            .description("简单优雅的restfun风格")
//            .termsOfServiceUrl("")
//            .version("v1.0")
//            .build();
//    }
//}
