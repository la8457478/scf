package com.fkhwl.scf.third.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fkhwl.scf.config.CfcaConfig;
import com.fkhwl.scf.config.SystemConfig;
import com.fkhwl.scf.entity.dto.WaybillValidateDTO;
import com.fkhwl.scf.third.service.CfcaService;
import com.fkhwl.scf.third.utils.HttpConnectorCfcaEvidence;
import com.fkhwl.scf.third.utils.IOUtil;
import com.fkhwl.scf.third.utils.ThirdHttpHelper;
import com.fkhwl.scf.utils.ToolsHelper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.util.Base64;
import cfca.sadk.util.CertUtil;
import cfca.sadk.util.HashUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: </p>
 *
 * @author zhubo
 * @version 1.0.0
 * @email "mailto:zhubo@fkhwl.com"
 * @date 2020.01.10 17:27
 */
@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class CfcaServiceImpl implements CfcaService {

    @Autowired
    private SystemConfig systemConfig;
    @Autowired
    private CfcaConfig cfcaConfig;
    /**
     * 存证服务
     */
    @Override
    public String[] server1101(WaybillValidateDTO waybillDTO){

        // TODO-SJ: 2020.03.05 需要添加配置，处理正式和测试环境的切换问题
        String certPath = cfcaConfig.getCertPathFilePath();
        String password = cfcaConfig.getPassword();
        String certAlias = cfcaConfig.getCertAlias();
        log.error("server1101 certPath:{}，{}，{}",certPath,password,certAlias);
        String[] result=null;
        try {
            // https连接,创建httpConnector后执行initSSL方法初始化SSL环境，之后多次调用可使用同一hpConnector对象
            HttpConnectorCfcaEvidence httpConnector = new HttpConnectorCfcaEvidence(cfcaConfig.getUrl(), cfcaConfig.getConnectTimeout(), cfcaConfig.getReadTimeout());  //一级保全
            httpConnector.initSSL(certPath, password.toCharArray(), certPath, password.toCharArray());

            // 模板中标签的值只能为String类型！ 模板中标签的值只能为String类型！ 模板中标签的值只能为String类型！
            Map<String, String> sourceMap = new HashMap<String, String>();
            sourceMap.put("txCode", cfcaConfig.getTxCode1101());
            sourceMap.put("branchCode", cfcaConfig.getBranchCode());  //机构编码
            sourceMap.put("applicationCode", cfcaConfig.getApplicationCode());  //应用编码  模拟生产0002
            sourceMap.put("templateCode", cfcaConfig.getTemplateCode());  //模板编码 模拟0002
            // TODO-SJ: 2020.04.14 金融监管平台：模版内容修改，上传相关图片文件，需要沟通是否可以使用byte[]上传，不用文件
            sourceMap.put("Text1", waybillDTO.getDriverName());
            sourceMap.put("Text2", "ios");
            sourceMap.put("Text3", "AppStore");
            sourceMap.put("Text4", ToolsHelper.formatDate2StrLong(waybillDTO.getBillPassTime()));//时间
            sourceMap.put("Text5", "2154");
            sourceMap.put("Text6", "实时有效");
            sourceMap.put("Text7", waybillDTO.getWaybillNo());


            //            -----------------------二级保全------------------
            //            sourceMap.put("branchCode", "0000");  //机构编码
            //            sourceMap.put("applicationCode", "0001");  //应用编码  模拟生产0002
            //            sourceMap.put("templateCode", "0000");  //模板编码 模拟0002
            //            sourceMap.put("test", "test");  //标签

            ObjectMapper objectMapper = new ObjectMapper();

            // 多文件上传
            // 文件不存磁盘，进行流转
            //下载文件
            //运单的合同文件地址
            // TODO-SJ: 2020.03.05 金融监管平台：运单的合同文件地址
            String url =waybillDTO.getPdfPath();
            url = StringUtils.isNotBlank(waybillDTO.getPdfPath()) && waybillDTO.getPdfPath().contains("http")
                ? waybillDTO.getPdfPath() : "https://dev.fkhwl.com/uploads/file/contract/20200224/1582534701090_0_1_.pdf";
            byte[] contractByte = ThirdHttpHelper.invokeGetFile(url);
            // TODO-SJ: 2020.03.06 金融监管平台：1.处理存盘问题
            String filePath=systemConfig.getCfcaContractEvidencePath()+ cfcaConfig.getFileDirPath()+url.substring(url.lastIndexOf("/")+1);
            //写入存证对应的项目文件夹，用于取证时，根据MD5查询
            IOUtil.writeFile(systemConfig.getCfcaContractEvidencePath()+ cfcaConfig.getFileDirPath(),filePath,contractByte);
            List<File> fileList=new ArrayList<>();
            fileList.add(new File(filePath));
//            Map<String,byte[]> fileList=new LinkedHashMap<>();
//            fileList.put(url.substring(url.lastIndexOf("/")+1),contractByte);
            Signature signKit = new Signature();
            byte[] signedData = null;
            JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
            Session session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);

            StringBuilder fileBase64Hash = new StringBuilder();
//            for (File file : fileList) {
                byte[] hashData = null;
                hashData = HashUtil.RSAHashData(contractByte, new Mechanism(Mechanism.SHA256), session, false);
                fileBase64Hash.append(new String(Base64.encode(hashData), "UTF-8"));
//            }

            sourceMap.put("fileBase64Hash", fileBase64Hash.toString());


            String request = objectMapper.writeValueAsString(sourceMap);
            System.out.println(request);

            PrivateKey privateKey = KeyUtil.getPrivateKeyFromJKS(certPath, password, certAlias);
            X509Cert x509Cert = CertUtil.getCertFromJKS(certPath, password, certAlias);
            signedData = signKit.p7SignMessageDetach(Mechanism.SHA256_RSA, request.getBytes("UTF-8"), privateKey, x509Cert, session);

//            List<String> queryCodeList = new ArrayList<>();
//            queryCodeList.add("testCode你好");
//            queryCodeList.add("testCode123");
//
//            String queryCode = objectMapper.writeValueAsString(queryCodeList);

            //            String response = httpConnector.post(request, new String(signedData, "UTF-8"), null, null);

            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("source", request);
            paramMap.put("signature", new String(signedData, "UTF-8"));
            String response = httpConnector.deal(request, new String(signedData, "UTF-8"), null,  fileList);
            log.error("server1101 response:{}",response);
            if(response!=null && response.contains("stubNo")){
                JSONObject jsonObject = JSONObject.parseObject(response);
                //下载pdf存证，并返回url地址
                result=new String[2];
                result[0]=jsonObject.getString("stubNo");
                result[1]=server1103( jsonObject.getString("stubNo"));
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("cfcaServer1101 error",e);
        }
        return result;
    }

    /**
     * 获取链接对应文件的hash值
     * @param url
     * @return
     */
    private String downloadContractHashData(String url){
//            String url = "https://dev.fkhwl.com/uploads/file/contract/20200224/1582534701090_0_1_.pdf";
        byte[] resp = ThirdHttpHelper.invokeGetFile(url);
        try {
            IOUtil.writeFile("d:/test/","d:/test/1582534701090_0_1_.pdf",resp);
            JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
            Session session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);
            byte[] hashData = null;
            hashData = HashUtil.RSAHashData(resp, new Mechanism(Mechanism.SHA256), session, false);
            System.out.println();
            return new String(Base64.encode(hashData), "UTF-8");
            //            HyLOfgdAaW4FnsMe9m0SXEQ0oXLPJJ+DXu4CqqdqC0o=
        } catch (Exception e) {
            log.error("Bad things", e);
        }
        return null;
    }

    @Override
    public String server1103(String stubNo){
        String certPath = cfcaConfig.getCertPathFilePath();
        String password = cfcaConfig.getPassword();
        String certAlias = cfcaConfig.getCertAlias();
        try {
            // https连接,创建httpConnector后执行initSSL方法初始化SSL环境，之后多次调用可使用同一httpConnector对象
            HttpConnectorCfcaEvidence httpConnector = new HttpConnectorCfcaEvidence(cfcaConfig.getUrl(), cfcaConfig.getConnectTimeout(), cfcaConfig.getReadTimeout());
            httpConnector.initSSL(certPath, password.toCharArray(), certPath, password.toCharArray());
            // 模板中标签的值只能为String类型！ 模板中标签的值只能为String类型！ 模板中标签的值只能为String类型！
            Map<String, String> sourceMap = new HashMap<String, String>();

            sourceMap.put("txCode", cfcaConfig.getTxCode1103());
            sourceMap.put("branchCode", cfcaConfig.getBranchCode());  //机构编码
            sourceMap.put("applicationCode", cfcaConfig.getApplicationCode());  //应用编码  模拟生产0002
            //            sourceMap.put("stubNo", "A795C948233F4AFA924182BCA7200415");
            sourceMap.put("stubNo", stubNo);

            ObjectMapper objectMapper = new ObjectMapper();

            Signature signKit = new Signature();
            byte[] signedData = null;
            JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
            Session session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);

            String request = objectMapper.writeValueAsString(sourceMap);
            System.out.println(request);

            PrivateKey privateKey = KeyUtil.getPrivateKeyFromJKS(certPath, password, certAlias);
            X509Cert x509Cert = CertUtil.getCertFromJKS(certPath, password, certAlias);
            signedData = signKit.p7SignMessageDetach(Mechanism.SHA256_RSA, request.getBytes("UTF-8"), privateKey, x509Cert, session);
            //            for(int i = 0; i < 10; i++) {
            byte[] response = httpConnector.download(request, new String(signedData, "UTF-8"), null, null);
            if (response != null) {
                String fileName=stubNo+".pdf";
                String filePath=systemConfig.getCfcaContractEvidencePath()+fileName;
                FileUtils.writeByteArrayToFile(new File(filePath),Base64.decode(response));
                return systemConfig.getCfcaContractEvidencePathUrl()+fileName;
            }
            //            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
