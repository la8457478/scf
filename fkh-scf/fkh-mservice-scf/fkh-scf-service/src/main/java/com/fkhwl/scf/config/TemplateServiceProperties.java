package com.fkhwl.scf.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"
 * @date 2020.01.27 18:30
 */
@Data
@ConfigurationProperties(prefix = TemplateServiceProperties.PREFIX)
public class TemplateServiceProperties {

    /** Prefix */
    static final String PREFIX = "fkh.template.service";

}
