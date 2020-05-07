package com.fkhwl.scf.config;

import com.fkhwl.starter.common.util.ConfigKit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

import lombok.Data;

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
@Data
@Component
public class CfcaConfig  implements Serializable {

    @Value("${cfca.url}")
    private  String url;
    @Value("${cfca.password}")
    private  String password;
    @Value("${cfca.certAlias}")
    private  String certAlias;
    @Value("${cfca.txCode1101}")
    private  String txCode1101;
    @Value("${cfca.txCode1103}")
    private  String txCode1103;
    @Value("${cfca.branchCode}")
    private  String branchCode;
    @Value("${cfca.applicationCode}")
    private  String applicationCode;
    @Value("${cfca.templateCode}")
    private  String templateCode;
    @Value("${cfca.connectTimeout}")
    private  int connectTimeout;
    @Value("${cfca.readTimeout}")
    private  int readTimeout;
    @Value("${cfca.fileDirPath}")
    private  String fileDirPath ;
    @Value("${cfca.certPathFileName}")
    private  String certPathFileName;
    private  String certPathFilePath;//完整地址
    public  String getCertPathFilePath(){
        if(certPathFilePath==null){
            String urlStr=ConfigKit.getConfigPath()+certPathFileName;
            //去掉\C:\的第一个\
            certPathFilePath=urlStr.contains(":")?urlStr.replaceFirst("\\\\",""):urlStr;
        }
        return certPathFilePath;
    }
}
