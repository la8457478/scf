package com.fkhwl.scf.config;

import com.fkhwl.starter.common.util.ConfigKit;

import java.io.*;

/**
 * <p>Title: com.fkhwl.scf.config</p>
 * <p>Company: 成都返空汇网络技术有限公</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date: 2020年03月05日 17点20分
 * updatetime:
 * reason:
 */
public class ReviewBillConfig {

    private static String reviewBillFileName="reviewBill"+File.separator+"reviewBill.xlsx";
    public static String reviewBillFilePath;//完整地址
    public static String getReviewBillFilePath(){
        if(reviewBillFilePath==null){
            //            System.out.println(ConfigKit.getConfigPath());
            //            \C:\fkhgit\supply-chain-finance\fkh-scf\fkh-mservice-scf\fkh-scf-service\target\test-classes\cfca
            //            System.out.println(ConfigKit.getConfigPath()+certPathFileName);
            //            URL url= ClassUtils.getDefaultClassLoader().getResource(certPathFileName);
            //            certPathFilePath=url!=null?(url.getPath().contains(":")?url.getPath().replaceFirst("/",""):url.getPath()):null;
            String urlStr=ConfigKit.getConfigPath()+reviewBillFileName;
            reviewBillFilePath=urlStr.contains(":")?urlStr.replaceFirst("\\\\",""):urlStr;
        }
        return reviewBillFilePath;
    }
}
