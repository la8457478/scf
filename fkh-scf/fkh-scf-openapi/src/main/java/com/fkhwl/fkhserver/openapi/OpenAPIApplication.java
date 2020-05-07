package com.fkhwl.fkhserver.openapi;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.fkhwl.starter.launcher.FkhStarter;

import org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthContributorAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>Title: com.fkhwl.domain.rest.resp</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: liaohong
 * emali: liaohong@fkhwl.com
 * version: 1.0
 * date: 2018-9-11 15:47
 * updatetime:
 * reason:
 */
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, DataSourceHealthContributorAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class OpenAPIApplication extends FkhStarter {

}
