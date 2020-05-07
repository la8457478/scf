package com.fkhwl.scf.config;


import com.fkhwl.scf.third.anxinsign.constant.Request;
import com.fkhwl.scf.third.utils.HttpConnectorCfcaAxQian;
import com.fkhwl.starter.common.util.ConfigKit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 安心签配置
 * @author wudq
 * @date 2019/11/28
 */
@Component
@Data
@Slf4j
public class AnxinSignConfig implements Serializable, InitializingBean {


    /** 待盖章合同的pdf模版中驾驶员盖章位置的域名称 */
    @Value("${anxinsign.pdf.template.driver}")
    private  String pdfTemplateDriver;

    /** 待盖章合同的pdf模版中签约主体盖章位置的域名称 */
    @Value("${anxinsign.pdf.template.company}")
    private  String pdfTemplateCompany;

    @Value("${anxinsign.fkhwl.platId}")
    private String platId;

    @Value("${anxinsign.url}")
    private String url;
    /**
     * 通讯证书路径
     */
    @Value("${anxinsign.jks.path}")
    private String jksPath;

    /**
     * 通讯证书密码
     */
    @Value("${anxinsign.jks.password}")
    private String jksPwd="fkhwl2019";

    @Value("${anxinsign.path.contract}")
    private  String contractPathFileName;
    public  String contractPathFilePath;//完整地址
    public  String getContractPathFilePath(){
        if(contractPathFilePath==null){
            String urlStr= ConfigKit.getConfigPath()+contractPathFileName;
            contractPathFilePath=urlStr.contains(":")?urlStr.replaceFirst("\\\\",""):urlStr;
        }
        return contractPathFilePath;
    }
    //ttc格式，后面必须带",1",,其他格式未验证
    @Value("${anxinsign.path.font}")
    private  String fontPathFileName;
    public  String fontPathFilePath;//完整地址
    public  String getFontPathFilePath(){
        if(fontPathFilePath==null){
            String urlStr= ConfigKit.getConfigPath()+fontPathFileName;
            fontPathFilePath=(urlStr.contains(":")?urlStr.replaceFirst("\\\\",""):urlStr) ;
        }
        return fontPathFilePath;
    }
    public  String jksPathFilePath;//完整地址
    public  String getJksPathFilePath(){
        if(jksPathFilePath==null){
            String urlStr= ConfigKit.getConfigPath()+jksPath;
            jksPathFilePath=(urlStr.contains(":")?urlStr.replaceFirst("\\\\",""):urlStr) ;
        }
        return jksPathFilePath;
    }
    /**
     * 初始化安心签sdk 环境变量
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        HttpConnectorCfcaAxQian.JKS_PATH = getJksPathFilePath();
        HttpConnectorCfcaAxQian.JKS_PWD = jksPwd;
        HttpConnectorCfcaAxQian.url = url;
        Request.PLAT_ID = platId;
        log.error("anxinsign init success");
    }
}
