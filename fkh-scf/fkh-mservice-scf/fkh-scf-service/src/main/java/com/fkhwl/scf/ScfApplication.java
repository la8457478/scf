package com.fkhwl.scf;

import com.fkhwl.starter.launcher.FkhStarter;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>Title: ScfApplication</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date: 2020-02-12
 * updatetime:
 * reason:
 */
@SpringBootApplication
@DubboComponentScan(basePackages = "com.fkhwl.scf.provider")
@EnableDiscoveryClient
@EnableScheduling
public class ScfApplication extends FkhStarter {
}
