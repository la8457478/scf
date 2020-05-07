package com.fkhwl.scf.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableWebMvc
@Configuration
public class SwaggerConfig  extends WebMvcConfigurationSupport {
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.fkhwl.scf.web.controller";

    @Bean
    public Docket customDocket() {
        //
        return new Docket(DocumentationType.SWAGGER_2).groupName("scf").select().apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE)).build()
            .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("监管平台-国际版API")
            .description("http不对外开放接口")
            .version("1.0.0")
            .license("假装这里有license")
            .licenseUrl("http://xxx.xxx.com")
            .build();
    }

}
