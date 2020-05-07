package com.fkhwl.scf.config;

import com.fkhwl.starter.common.start.FkhAutoConfiguration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"
 * @date 2020.01.27 18:30
 */
@Configuration
@EnableConfigurationProperties(TemplateServiceProperties.class)
public class TemplateServiceConfiguration implements FkhAutoConfiguration {

}
