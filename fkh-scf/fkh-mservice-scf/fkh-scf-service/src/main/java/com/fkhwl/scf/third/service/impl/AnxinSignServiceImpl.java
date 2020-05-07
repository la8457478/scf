package com.fkhwl.scf.third.service.impl;

import com.alibaba.fastjson.JSON;
import com.fkhwl.scf.config.AnxinSignConfig;
import com.fkhwl.scf.config.SystemConfig;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.po.Waybill;
import com.fkhwl.scf.third.anxinsign.constant.Request;
import com.fkhwl.scf.third.anxinsign.converter.HeadRpcVO;
import com.fkhwl.scf.third.anxinsign.converter.JsonObjectMapper;
import com.fkhwl.scf.third.anxinsign.util.CommonUtil;
import com.fkhwl.scf.third.anxinsign.util.SealUtil;
import com.fkhwl.scf.third.anxinsign.util.TimeUtil;
import com.fkhwl.scf.third.service.AnxinSignService;
import com.fkhwl.scf.third.utils.HttpConnectorCfcaAxQian;
import com.fkhwl.scf.third.utils.SecurityUtil;
import com.fkhwl.scf.utils.FileUploadType;
import com.fkhwl.scf.utils.PdfToImgUtils;
import com.fkhwl.scf.utils.ToolsHelper;
import com.fkhwl.starter.core.support.AssertUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cfca.com.itextpdf.text.Document;
import cfca.com.itextpdf.text.DocumentException;
import cfca.com.itextpdf.text.Font;
import cfca.com.itextpdf.text.Paragraph;
import cfca.com.itextpdf.text.Phrase;
import cfca.com.itextpdf.text.Rectangle;
import cfca.com.itextpdf.text.pdf.AcroFields;
import cfca.com.itextpdf.text.pdf.BaseFont;
import cfca.com.itextpdf.text.pdf.PdfCopy;
import cfca.com.itextpdf.text.pdf.PdfPCell;
import cfca.com.itextpdf.text.pdf.PdfPTable;
import cfca.com.itextpdf.text.pdf.PdfReader;
import cfca.com.itextpdf.text.pdf.PdfStamper;
import cfca.com.itextpdf.text.pdf.PdfWriter;
import cfca.sadk.system.FileHelper;
import cfca.sadk.util.Base64;
import cfca.seal.sadk.DonePdfSeal;
import cfca.seal.sadk.PrePdfReader;
import cfca.seal.sadk.PrePdfSeal;
import cfca.seal.sadk.PrePdfSealExtra;
import cfca.seal.sadk.refit.PdfAcrofieldsRefit;
import cfca.seal.sadk.security.deferred.ReservedPdfPKCS7;
import cfca.trustsign.common.vo.cs.EnterpriseTransactorVO;
import cfca.trustsign.common.vo.cs.EnterpriseVO;
import cfca.trustsign.common.vo.cs.HeadVO;
import cfca.trustsign.common.vo.cs.LocalSignVO;
import cfca.trustsign.common.vo.request.tx3.Tx3002ReqVO;
import cfca.trustsign.common.vo.request.tx3.Tx3401ReqVO;
import cfca.trustsign.common.vo.response.tx3.Tx3002ResVO;
import cfca.trustsign.common.vo.response.tx3.Tx3401ResVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 安心签
 * @author wudq
 * @date 2019/11/26
 */
@Slf4j
@Service
public class AnxinSignServiceImpl implements AnxinSignService {

    @Resource
    private SystemConfig systemConfig;

    @Resource
    private AnxinSignConfig anxinSignConfig;

    @Override
    public String createPersonalSeal(String userName) {
        byte[] sealBytes;
        try {
            sealBytes = SealUtil.getSquarePng(userName,0);
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(sealBytes);
        } catch (Exception e) {
            log.error("create local person seal error: {}",e);
        }
        return null;
    }

    @Override
    public String createCompanySeal(String companyName) {
        byte[] sealBytes;
        try {
            sealBytes = SealUtil.getCirclePng_2(companyName);
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(sealBytes);
        } catch (Exception e) {
            log.error("create local company seal error: {}",e);
        }
        return null;
    }


    @Override
    public String addCompanyAccount(CompanyDTO companyDTO) {
        // TODO-SJ: 2020.04.14 金融监管平台：处理企业的签章图片
        HttpConnectorCfcaAxQian httpConnector = new HttpConnectorCfcaAxQian();
        httpConnector.init();

        Tx3002ReqVO tx3002ReqVO = new Tx3002ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        EnterpriseVO enterprise = new EnterpriseVO();
        enterprise.setEnterpriseName(companyDTO.getCompanyName());
        enterprise.setIdentTypeCode("8");
        //企业营业执照
        enterprise.setIdentNo(companyDTO.getBusinessLicenseNo());
        //企业法人手机号
        enterprise.setMobilePhone(companyDTO.getUserMobileNo());
        //企业联系电话
        enterprise.setLandlinePhone(companyDTO.getCompanyTel());
        enterprise.setAuthenticationMode("公安部");

        EnterpriseTransactorVO enterpriseTransactor = new EnterpriseTransactorVO();
        enterpriseTransactor.setTransactorName(companyDTO.getLegalPerson());
        enterpriseTransactor.setIdentTypeCode("1");
        enterpriseTransactor.setIdentNo(companyDTO.getIdCardNo());

        tx3002ReqVO.setHead(head);
        tx3002ReqVO.setEnterprise(enterprise);
        tx3002ReqVO.setEnterpriseTransactor(enterpriseTransactor);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3002ReqVO);

        String txCode = "3002";
        String response;
        try {
            String signature = SecurityUtil.p7SignMessageDetach(HttpConnectorCfcaAxQian.JKS_PATH, HttpConnectorCfcaAxQian.JKS_PWD, HttpConnectorCfcaAxQian.ALIAS, req);
            response = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", req, signature);
            log.error("anxinsign register company account response : {}", response);
            Tx3002ResVO tx3002ResVO = jsonObjectMapper.readValue(response, Tx3002ResVO.class);
            if (tx3002ResVO != null && tx3002ResVO.getEnterprise() != null) {
                return tx3002ResVO.getEnterprise().getUserId();
            } else {
                return response;
            }
        } catch (Exception e) {
            log.error("anxinsign register company account error: {}", e);
        }
        return null;
    }


    @Override
    public String createFileFromTemplate(Map<String, String> txtFields, List<Waybill> waybillList){
        log.error("createFileFromTemplate:{}",JSON.toJSONString(txtFields));
//        String dateAndFileName = srcFile.replace(systemConfig.getCfcaContractAxQianPathUrlNoDate(),"");
        //        String fileName = getFileName(txtFields.get("projectId"),txtFields.get("userId"));
        String fileName = getFileName();
//        String fileHttpUrl = systemConfig.getCfcaContractAxQianPathUrl() + fileName;
//        //pdf磁盘路径
//        String srcPdfFile = systemConfig.getCfcaContractAxQianPathNoDate()+dateAndFileName;
//        String dstPdfFile = systemConfig.getCfcaContractAxQianPath()+fileName;
        //没有channelId，表示驾驶员主动获取短信，从缓存中查看驾驶员是否标记有渠道，标记了就使用该渠道名称作为前缀
        //        Object obj = this.redisCachedBaseService.get(CacheKeyFromSSDB.SMS_DRIVER_MOBILE_CHANNEL + txtFields.get("driverMobile"));
        String srcFile= anxinSignConfig.getContractPathFilePath();//模版位置
        log.error("srcFile:{}",srcFile);
//        fileBean.setSrcPdfFile(srcFile);
//        String fileName = getFileName(txtFields.get("projectId"),txtFields.get("userId"));
        String fileHttpUrl = systemConfig.getCfcaContractAxQianPathUrl() + fileName;
        String dstPdfFile=systemConfig.getCfcaContractAxQianPath()+fileName;
        String dstPdfFileTmp1 = systemConfig.getCfcaContractAxQianPath() + System.currentTimeMillis()+"_"+ FileUploadType
            .PORJECT_DRIVER_SIGN_CONTRACT.getValue() +"_tmp1_.pdf";
        String dstPdfFileTmp2 = systemConfig.getCfcaContractAxQianPath() + System.currentTimeMillis()+"_"+ FileUploadType
            .PORJECT_DRIVER_SIGN_CONTRACT.getValue() +"_tmp2_.pdf";
        log.error("dstPdfFile:{}",dstPdfFile);
        //读取pdf
        try {
            // 获取要替换参数
            //打印准备
            byte[] pdfBytes = Files.readAllBytes(Paths.get(srcFile));
            PdfReader reader = new PdfReader(pdfBytes);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            /* 读取 */
            PdfStamper pdfStamper = new PdfStamper(reader, bos);
            log.error("BaseFont file:{}",anxinSignConfig.getFontPathFilePath());
            BaseFont baseFont = BaseFont.createFont(anxinSignConfig.getFontPathFilePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 获取字段设置对象
            AcroFields s = pdfStamper.getAcroFields();
            /* 需要注意的是 setField的name和命名的表单域名字要一致 */
            Iterator<String> it = s.getFields().keySet().iterator();
            String nameValue = "";
            while (it.hasNext()) {
                // 获取文本域名称
                String name = it.next();
                nameValue = txtFields.get(name);// 获取要替换的内容
                if(StringUtils.isNotBlank(nameValue)){
                    // 设置文本域字体
                    s.setFieldProperty(name, "textfont", baseFont, null); // 设置字体
//                    s.setFieldProperty(name, "textcolor", BaseColor.BLUE, null); // 设置颜色
                    s.setFieldProperty(name, "textsize", (float) 10, null); // 设置字体大小
                    s.setField(name, nameValue);
                }
            }
            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.setFormFlattening(false);
            pdfStamper.close();
            FileUtils.writeByteArrayToFile(new File(dstPdfFileTmp1),bos.toByteArray());
            //生成附件表格的pdf
            generatePdfWithTable(srcFile, dstPdfFileTmp2, waybillList, Integer.valueOf(txtFields.get("paymentDays")));
            //合并文件
            FileOutputStream outputStream = new FileOutputStream(dstPdfFile);
            String[] files = new String[]{dstPdfFileTmp1,dstPdfFileTmp2};
            //合并文件
            mergePdfFiles(files, outputStream);
            //删除临时文件
            File pdfFileTmp1 = new File(dstPdfFileTmp1);
            if (pdfFileTmp1.exists()) {
                pdfFileTmp1.delete();
            }
            File pdfFileTmp2 = new File(dstPdfFileTmp2);
            if (pdfFileTmp2.exists()) {
                pdfFileTmp2.delete();
            }
            //模版生成完成后，pdf转图片保存
            this.pdfToPng(dstPdfFile);
            return fileHttpUrl;
        } catch (Exception e) {
            log.error("安心签生成合同文件异常", e);
//            AssertUtils.isTrue(false,"安心签添加签章日期异常");
        }
        return null;
    }


    @Override
    public Map<String, Object> multiSign(String srcFile, String companyAccountId, String companySeal, String driverAccountId,
                                          String driverSeal, Map<String, Object> txtFields) {

        HttpConnectorCfcaAxQian httpConnector = new HttpConnectorCfcaAxQian();
        httpConnector.init();
        Map<String,Object> resultMap=new HashMap<>();
        try {
            Map<String, String> pathInfoMap = getPdfPathInfo(srcFile,txtFields);
            byte[] pdfBytes = Files.readAllBytes(Paths.get(pathInfoMap.get("srcPdfFile")));
            //第一个章
//            AnxinSignConfig anxinSignConfig=new AnxinSignConfig();
            resultMap = doAnxinSign(pdfBytes, companySeal, anxinSignConfig.getPdfTemplateCompany(), httpConnector,companyAccountId);
            if (resultMap.get("head") == null) {
                return resultMap;
            }
            //存单号作为安心签签章的唯一标识保存
            String companySignId = (String) resultMap.get("stubNo");
            pdfBytes = (byte[]) resultMap.get("pdfBytes");
            //第二个章
            resultMap = doAnxinSign(pdfBytes, driverSeal, anxinSignConfig.getPdfTemplateDriver(), httpConnector,driverAccountId);
            if (resultMap.get("head") == null) {
                return resultMap;
            }
            resultMap.put("companySignId",companySignId);
            resultMap.put("driverSignId",resultMap.get("stubNo"));
            pdfBytes = (byte[]) resultMap.get("pdfBytes");
            //保存签章后的合同
            Map<String,String> savePdfResultMap = savePdf(pdfBytes, pathInfoMap);
            if (savePdfResultMap.get("reason") != null) {
                resultMap.put("reason", savePdfResultMap.get("reason"));
               return  resultMap;
            }
            String dstPdfFile = savePdfResultMap.get("dstPdfFile");
            String fileHttpUrl = savePdfResultMap.get("fileHttpUrl");
            log.error("anxinsign mulitiSign succeed, contract path : {}, url : {} ", dstPdfFile, fileHttpUrl);
            resultMap.put("filePath", StringUtils.isNotBlank(dstPdfFile) ? fileHttpUrl : null);
            resultMap.remove("head");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("anxinsign mulitiSign error: ", e);
            resultMap.put("reason", e.getMessage());
        }
        return resultMap;
    }

    /**
     * 发送安心签进行印章签名
     * @param pdfBytes
     * @param seal
     * @param signatureFiled
     * @param httpConnector
     * @param accountId
     * @return
     */
    private Map<String,Object> doAnxinSign(byte[] pdfBytes, String seal, String signatureFiled, HttpConnectorCfcaAxQian httpConnector, String accountId) {
        Map<String,Object> resultMap=new HashMap<>();
        ReservedPdfPKCS7 reservedPdfPKCS7 = new ReservedPdfPKCS7();
        try {
            //合同添加印章
            pdfBytes = signSeal(pdfBytes, seal, reservedPdfPKCS7, signatureFiled);
            //P7签名
            String signatureAttr = Base64.toBase64String(reservedPdfPKCS7.hashPdf);
            String stubNo = String.valueOf(System.currentTimeMillis());
            //安心签request
            String request = initSignReqVo(signatureAttr, stubNo, accountId);
            String txCode = "3401";
            String signature = SecurityUtil.p7SignMessageDetach(HttpConnectorCfcaAxQian.JKS_PATH, HttpConnectorCfcaAxQian.JKS_PWD, HttpConnectorCfcaAxQian.ALIAS, request);
            //安心签完成用户证书进行印章签名
            String response = httpConnector.post("platId/" + Request.PLAT_ID + "/txCode/" + txCode + "/transaction", request, signature);
            log.error("anxinsign sign response : "+response);

            JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
            Tx3401ResVO tx3401ResVO = jsonObjectMapper.readValue(response, Tx3401ResVO.class);
            if (tx3401ResVO.getLocalSign() != null) {
                String signatureOfAttr = tx3401ResVO.getLocalSign().getSignatureOfAttr();
                pdfBytes = new DonePdfSeal().mergeReservedPdfSeal(new PdfReader(pdfBytes), reservedPdfPKCS7.fieldName, Base64.decode(signatureOfAttr));
                resultMap.put("pdfBytes", pdfBytes);
                HeadRpcVO headVO = jsonObjectMapper.readValue(response, HeadRpcVO.class);
                resultMap.put("head", headVO);
                resultMap.put("stubNo", stubNo);
            }else {
                resultMap.put("reason", JSON.toJSONString(response));
            }
            return resultMap;
        } catch (Exception e) {
            log.error("anxinsign sign error: ", e);
            resultMap.put("reason", e.getMessage());
        }
        return resultMap;
    }

    /**
     * 安心签request
     * @param signatureAttr
     * @param stubNo
     * @param accountId
     * @return
     */
    private String initSignReqVo(String signatureAttr, String stubNo, String accountId) {

        Tx3401ReqVO tx3401ReqVO = new Tx3401ReqVO();
        HeadVO head = new HeadVO();
        head.setTxTime(TimeUtil.getCurrentTime(TimeUtil.FORMAT_14));

        LocalSignVO localSign = new LocalSignVO();
        //存单号
        localSign.setStubNo(stubNo);
        localSign.setUserId(accountId);
        // 项目编号暂定FKHWL000001 add by wudq 2020/3/11
        localSign.setProjectCode("FKHWL000001");
        //不进行项目权限检查
        localSign.setIsCheckProjectCode(0);

        localSign.setSignatureAttr(signatureAttr);

        tx3401ReqVO.setHead(head);
        tx3401ReqVO.setLocalSign(localSign);

        JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
        String req = jsonObjectMapper.writeValueAsString(tx3401ReqVO);
        if (CommonUtil.isEmpty(req)) {
            AssertUtils.isTrue(false,"request is null");
        }
        return req;
    }

    /**
     * 添加印章
     * @param pdfBytes
     * @param seal 印章图片base64字符串
     * @param signatureField 印章表单域字段
     * @return
     * @throws Exception
     */
    private byte[] signSeal(byte[] pdfBytes, String seal,ReservedPdfPKCS7 reservedPdfPKCS7, String signatureField ) throws Exception{
        byte[] imageBytes = Base64.decode(seal);
        PrePdfSeal prePdfSeal = new PrePdfSeal(null, null, imageBytes, 0.68f, PrePdfSeal.EncryptionAlgorithm.RSA, PrePdfSeal.HashAlgorithm.SHA256,
            ReservedPdfPKCS7.Type.EXTERNAL_RSA_PKCS7);
        //印章
        PrePdfSealExtra prePdfSealExtra = new PrePdfSealExtra("签章", ToolsHelper.formatDate2StrLong(new Date()));
        PrePdfReader sealPdfReader = new PrePdfReader(pdfBytes);

        DonePdfSeal donePdfSeal = new DonePdfSeal();
        donePdfSeal.initPdfSeal(prePdfSeal);
        donePdfSeal.initPdfSealExtra(prePdfSealExtra);
        donePdfSeal.initPdfReader(sealPdfReader);
        donePdfSeal.updateLocationInfoByBlankSignature(signatureField, true);

        return donePdfSeal.createReservedPdfSeal(null, "", "", 0, reservedPdfPKCS7);
    }

    /**
     * 设置合同日期，并将表单设为只读
     * @param pdfBytes
     * @return
     */
    private static byte[] setSignDate(byte[] pdfBytes, Map<String, Object> txtFields) {
        Calendar validityTime = Calendar.getInstance();
        Map<String, String> dayTxtFields = new HashMap<>(3);
        dayTxtFields.put("year", String.valueOf(validityTime.get(Calendar.YEAR)));
        dayTxtFields.put("month", String.valueOf((validityTime.get(Calendar.MONTH) + 1)));
        dayTxtFields.put("day", String.valueOf(validityTime.get(Calendar.DAY_OF_MONTH)));


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfAcrofieldsRefit pdfAcrofieldsRefit = null;
        try {
            pdfAcrofieldsRefit = new PdfAcrofieldsRefit(new PdfReader(pdfBytes), baos);
            pdfAcrofieldsRefit.setAppend(false);
//            pdfAcrofieldsRefit.setFieldMap(txtFields1);
            pdfAcrofieldsRefit.setFormFlattening(true);
//            //日期表单只读
//            String[] dayFields = new String[3];
//            dayFields = dayTxtFields.keySet().toArray(dayFields);
//            pdfAcrofieldsRefit.setPartialFlattening(dayFields);
//            //合同编号、甲方、乙方 表单只读
            String[] fields = new String[5];
            fields = txtFields.keySet().toArray(fields);
            pdfAcrofieldsRefit.setPartialFlattening(fields);
//            pdfAcrofieldsRefit.setFieldProperty()
            pdfAcrofieldsRefit.close();
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("安心签添加签章日期异常", e);
            AssertUtils.isTrue(false,"安心签添加签章日期异常");
        }
        return null;
    }


    /**
     * 保存签署后的合同
     * @param pdfBytes
     * @return
     */
    private Map<String, String> savePdf(byte[] pdfBytes, Map<String, String> pathInfoMap) {
        Map<String, String> resultMap = new HashMap<>(2);
        String dstPdfFile = pathInfoMap.get("dstPdfFile");
        String fileHttpUrl = pathInfoMap.get("fileHttpUrl");
        try {
            FileHelper.write(dstPdfFile, pdfBytes);
            resultMap.put("dstPdfFile", dstPdfFile);
            resultMap.put("fileHttpUrl", fileHttpUrl);
            //盖章生成完成后，pdf转图片保存
            if(StringUtils.isNotBlank(dstPdfFile)){
                this.pdfToPng(dstPdfFile);
            }
            log.error("anxingsign save dist success");
        } catch (Exception e) {
            log.error("anxingsign 验签后保存合同异常", e);
            resultMap.put("reason",e.getMessage());
        }
        return resultMap;
    }

    private String getFileName(){
       return System.currentTimeMillis()+"_"+ FileUploadType
            .PORJECT_DRIVER_SIGN_CONTRACT.getValue()+"_.pdf";
    }
    /**
     * 合同文件路径
     * @param srcFile
     * @param txtFields
     * @return
     */
    private Map<String, String> getPdfPathInfo(String srcFile, Map<String,Object> txtFields){
        Map<String, String> pathInfoMap = new HashMap<>(2);
        String dateAndFileName = srcFile.replace(systemConfig.getCfcaContractAxQianPathUrlNoDate(),"");
//        String fileName = getFileName(txtFields.get("projectId"),txtFields.get("userId"));
        String fileName = getFileName();
        String fileHttpUrl = systemConfig.getCfcaContractAxQianPathUrl() + fileName;
        //pdf磁盘路径
        String srcPdfFile = systemConfig.getCfcaContractAxQianPathNoDate()+dateAndFileName;
        String dstPdfFile = systemConfig.getCfcaContractAxQianPath()+fileName;
        pathInfoMap.put("fileHttpUrl",fileHttpUrl);
        pathInfoMap.put("dstPdfFile",dstPdfFile);
        pathInfoMap.put("srcPdfFile",srcPdfFile);
        pathInfoMap.put("dateAndFileName",dateAndFileName);
        log.info("sign srcPdfFile: {}", srcPdfFile);
        log.info("sign dstPdfFile: {}", dstPdfFile);
        return pathInfoMap;
    }

//    private Map<String, String> getPdfPathInfo(String srcFile, Map<String,Object> txtFields){
//        Map<String, String> pathInfoMap = new HashMap<>(2);
//        pathInfoMap.put("fileHttpUrl","http://test/");
//        pathInfoMap.put("dstPdfFile","D:\\data\\devuploads\\file\\contract\\cfca\\axqian\\2020-03-28\\1584676569698_1982_485_2_over.pdf");
//        pathInfoMap.put("srcPdfFile",srcFile);
//        pathInfoMap.put("dateAndFileName","1584676569698_1982_485_2_.pdf");
//        return pathInfoMap;
//    }



    /**
     * PDF最后一页中添加表格
     * @param srcPath       已写入数据的pdf模板路径
     * @param destPath     最终版本的pdf生成路径
     * @throws Exception
     */
    private void generatePdfWithTable(String srcPath, String destPath, List<Waybill> waybillList, int paymentDays) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(destPath);
        PdfReader reader = new PdfReader(srcPath);// 读取pdf模板
        log.error("AnxinSignServiceImpl generatePdfWithTable, pdf总页数: " + reader.getNumberOfPages());
        Rectangle pageSize = reader.getPageSize(1);
        Document document = new Document(pageSize);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        writer.getDirectContentUnder();
//        PdfContentByte cbUnder = writer.getDirectContentUnder();
//        for (int i=1; i<=reader.getNumberOfPages(); i++) {
//            PdfImportedPage pageTemplate = writer.getImportedPage(reader, i);
//            cbUnder.addTemplate(pageTemplate, 0, 0);
//            document.newPage();//新创建一页来存放后面生成的表格
//        }
        document.newPage();
        Paragraph paragraph = generateWaybillTable(waybillList, paymentDays);
        document.add(paragraph);
        document.close();
        reader.close();

    }

    /**
     * 生成pdf表格
     * @return
     * @see
     */
    private Paragraph generateWaybillTable(List<Waybill> waybillList, int paymentDays) throws Exception {
        BaseFont bfChinese = BaseFont.createFont(anxinSignConfig.getFontPathFilePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 5.5F, Font.NORMAL);// 五号
        //        Paragraph ret = new Paragraph("附件：《运单明细表》", fontChinese);
        Paragraph ret = new Paragraph();
        PdfPTable tableBox = new PdfPTable(7);
        tableBox.setWidths(new float[] {0.05f, 0.175f, 0.15f, 0.175f, 0.15f, 0.15f, 0.15f});// 每个单元格占多宽
        tableBox.setWidthPercentage(80f);

        // 遍历查询出的结果
        System.err.println("AnxinSignServiceImpl waybill size: " + waybillList.size());
        BigDecimal totalInvoiceMoney = waybillList.stream().map(Waybill::getInvoiceMoney).reduce(BigDecimal.ZERO, BigDecimal::add);

        // 创建表格格式及内容
        tableBox.addCell(getCell(new Phrase("序号", fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase("运单号", fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase("结算金额（单位：元）", fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase("应收账款金额（单位：元）", fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase("转让金额（单位：元）", fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase("运单核验时间", fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase("应收账款到期日", fontChinese),  1, 1));

        for (int i=0;i<waybillList.size();i++) {
            Waybill waybill = waybillList.get(i);
            tableBox.addCell(getCell(new Phrase(String.valueOf(i+1), fontChinese),  1, 1));
            tableBox.addCell(getCell(new Phrase(String.valueOf(waybill.getWaybillNo()), fontChinese),  1, 1));
            tableBox.addCell(getCell(new Phrase(String.valueOf(waybill.getInvoiceMoney()), fontChinese),  1, 1));
            tableBox.addCell(getCell(new Phrase(String.valueOf(waybill.getInvoiceMoney()), fontChinese),  1, 1));
            tableBox.addCell(getCell(new Phrase(String.valueOf(waybill.getInvoiceMoney()), fontChinese),  1, 1));
            tableBox.addCell(getCell(new Phrase(ToolsHelper.formatDate2Str(waybill.getBillPassTime()), fontChinese),  1, 1));
            tableBox.addCell(getCell(new Phrase(ToolsHelper.formatDate2Str(ToolsHelper.dateAddDay(waybill.getBillPassTime(), paymentDays)), fontChinese),  1, 1));
        }

        //添加合计
        tableBox.addCell(getCell(new Phrase("合计", fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase(StringUtils.EMPTY, fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase(String.valueOf(totalInvoiceMoney), fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase(String.valueOf(totalInvoiceMoney), fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase(String.valueOf(totalInvoiceMoney), fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase(StringUtils.EMPTY, fontChinese),  1, 1));
        tableBox.addCell(getCell(new Phrase(StringUtils.EMPTY, fontChinese),  1, 1));

        ret.add(tableBox);
        return ret;

    }

    /***
     * 每个cell的格式，合并单元格情况
     * @param phrase
     * @param colSpan
     * @param rowSpan
     * @return
     */
    private PdfPCell getCell(Phrase phrase, int colSpan, int rowSpan) {
        PdfPCell cells = new PdfPCell(phrase);
        cells.setUseAscender(true);
//        cells.setMinimumHeight(20f);
        cells.setFixedHeight(10f);
        cells.setHorizontalAlignment(1);
        cells.setVerticalAlignment(5);
        cells.setColspan(colSpan);
        cells.setRowspan(rowSpan);
        cells.setNoWrap(false);
        return cells;
    }


    private static void mergePdfFiles(String[] files, OutputStream os) {
        try {
            List<PdfReader> readers = new ArrayList<>();
            PdfReader reader1 = new PdfReader(files[0]);
            readers.add(reader1);
            Document document = new Document(reader1.getPageSize(1));
            PdfCopy copy = new PdfCopy(document, os);
            copy.setMergeFields();
            document.open();
            for (String file : files) {
                PdfReader reader = new PdfReader(file);
                copy.addDocument(reader);
                readers.add(reader);
            }
            document.close();

            for (PdfReader reader : readers) {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * pdf转图片保存
     * @param srcFile
     * @return
     */
    private boolean pdfToPng(String srcFile){
        //模版生成完成后，pdf转图片保存
        try {
            new PdfToImgUtils().pdfToImage(srcFile, null,  "png", 123f, true);
        } catch (Exception e) {
            log.error("pdfToPng:{}",srcFile,e);
            AssertUtils.isTrue(false,"pdf转图片失败");
        }
        return true;
    }

    public static void main222(String[] args) {
        AnxinSignConfig anxinSignConfig=new AnxinSignConfig();
        HttpConnectorCfcaAxQian.JKS_PATH = anxinSignConfig.getJksPath();
        HttpConnectorCfcaAxQian.JKS_PWD = anxinSignConfig.getJksPwd();
        //        HttpConnectorCfcaAxQian.ALIAS = "";
        HttpConnectorCfcaAxQian.url = anxinSignConfig.getUrl();
        Request.PLAT_ID = anxinSignConfig.getPlatId();
        log.error("anxinsign init success");
        AnxinSignServiceImpl anxinSignService=new AnxinSignServiceImpl();
//        String accountId =  anxinSignService.addPersonAccount("张丹丹", "110102200001071156", "13739484373");
//        log.error("accountId:"+accountId);
        anxinSignService.test_sign(anxinSignService);
    }

    public void test_sign(AnxinSignServiceImpl anxinSignService) {
        String companySeal = "iVBORw0KGgoAAAANSUhEUgAAAP8AAAD/CAMAAAAJ1vD4AAADAFBMVEX/////AAAAAP8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABLakbNAAAAAXRSTlMAQObYZgAAB1lJREFUeNrtndua2yAMhDMs7//K03a/dpvEBgSSCNjKxV5sDuYfSVhgEMDj1q/0CP7gv+8r//nDe7Ij7B/8wR/8wR/8wR/8wR/8wR/8lgOMW9vfTQBgD//H6goklQlRbQV8Y8BEgdTEr1wGklbAL64MFEgaoVl9G+4xYCBui7+OSFkr4EetFTfpgo2yVji6AJ35KTMiqv8hvZye7vFfF0AiDxeeX06DujcFgJmJqs2APz8foyHggv/J/BdieeCGD9N7i4Rfg+BpfRoIkB6GDnD8nD3+sV+BOz9NrG+SBMDYuXzGfzjDN84DadIFJMOWsGp9k0T4zfupFiD1ac1Rg8BnJKAWIDn448k/YHO7KiZVcOZn1fy/E9znHBdlfJ0AKDvk6M9+d8qUXZs2Lkw9P01utX+++QWFFSbPWdTnWcZA8oD9SlHol/OKRlQYuewXBltCe8cYxcfg9Qft37Sv84iPRYcc8IBtnn9BoghW4KdHXNSDf1yAZGcTFD8FZ/xnATDT/pLhMExvmTQeDSUd7FHu884ZXrGlFSB14OMhUsDk3tSfWHBooiH1tAGPpgLFcQmc8V/elSsgzf9RTfhc8355Wtk5OdSx/tvEjb3xR6bbcl8bzlMsanpuO/yXocn3VznKX3nmP8gxA/9dAIECWfQ7eo45+Ee7tbq33BCSU4a2bvivUzc98c9T39kR/+cddvV/H39uaTRpuOv415yZ4/e//fGpuv9/Dp+l2G1P+4qWyeS1jU9FR0RJ/5lXDFWrp+c8eRSzvv0t1w6wZRwTflq6Boxvv/Tntzf+tOQjr4g/MfXK6+FPTTxX8//ZWXe+NX3s/wr+4L/aq+f5x5L5v7o18kna69m/b6FluiS+XID1xj9GoSgMgXRJ68uVz7vg9H9E5AFr25/e6i1u///P4QbmPy/g/6XHUNcd/84eEkb+H/x3fvWuf9lg/GNvf9za/mjmVNxXunb9l9dE42qukKTG5zUFSDLjPz2IHtrGyx358b54cXS35cIFMLKo6+LrcOSlF+Supq/zn68e+SdAcc/zXvRl/y8tnnndb8rlK4djkJ8lE/4IAGxQN7259yg1Hbe8qx/ABtbHYP9f3PL9svl4aQkEe48q/T/LZd+OlW669mZwbuxDsf6LAgUWTQsbmzba+Q8rlhrv+TnppgGRder7n+pT6D6JsI8nlYoUfcFOZaNxALy9wZLfo8WzBDDjFze8Zx8Hp/QQXzc+/yrOvwn+4A/+4A/+4A/+4A/+4A/+4A/+4A/+4A/+4A/+4A/+4A/+4A/+4A/+4A/+4A/+4A/+4A/+4L8vP3Brfucjn7A2P+AqAdQKwJUfn/EBWHpnMhDXS4DS4fJSp5CcuJos7OIswKACokJgycQrvQV4DASBLDqTTUy6vw57kQGT6GztfzrZAYNTMzWqTQ0em0Lo772ggv/wbRQ8tH4ZPAZ3i9LA1aCqf47S0WKsfNDQBR6uAkjqv/wFa58uVbwOxgVgufyIhZ7C+jeUbd8suACMPGA4irT88pNo6g2E8Kg1Hh2APlHUU/+MIj+FshNAUSePbWHZBN5IwychUSg7YP2yr39XPRi54xC3HwWIfvND+sm58z/s+tRT5aVO88vH5UvPf/3Lcb93jHdHP7blP0u40E9+gfqn1fhBWwfu6f9HBxgcBmDr+H+3OJUOs4//vw/70AXXURt4j+cffscCpbX93n3InO5t/kvXP9+an1PMv7D9OaXkar6z82/R//meCJgMmjYDf0X/x8QpbTpJo3z+i83Nr7C/Yk5f8NtvXX9f2fEZ539Zzel/sGvR+D9cW2pTIpGW9leeM9TfdmXBUHrHv7MAJzVGB+CbM2Czn390UUCP3zLOpdd/ClYAbcFPBX5DgHRx67cE2I+/Y/WfQIB8BQMr7lBpOz6aSrWh/Wl09N+2/Z/l3PiW/b/hvOCe9z/FSUTXuP//LI3HPfmfUnzBKoBN+Xuqpdfe3jb/k02DsKkOtvV/sglIgXNg3/zX5gjoYgJ87f2fvvvf/nfBWB4fXv7/8bM/pMNh2vPDJkhPBzf2zkBb/r5Fzd0CdJwpq7l+1nqp+bxw88R3yGc3XPi9TzKjkWt78EPY0JcpCtuQhqkAefjKdPrKUHc/vC9q9Pkfmz7s05Gj1GEOukB2i1B6KADrTiD1X0/8cPr9gzTEPzmfFQ78CvrDh53wK2UC9PzU3pzoj18rE2Bsf47vQHDE75oN6+Snjt7gq6J8+/lQYlv7qx2YNst5GsMNDg4hJ6z/MM562Bw49iRDSdj8zx71DUEgjR1LnWT2WwZf3MfAcv/rDviHt0VOkK9Df5Jzo/mt5ee/O6eZeF4piZvy90+ysXjU8+lPpIvh9+Za+Wr0BR/gdvyq+WVKp2vy8viDhcOEAuZL0ld6wh34rZ4rsV00JNuHq9UvWaRd1Nb/+5jtzZLOrfI/382eE/g5+Se4FD/3gjfmn2x7kyD52rx+ubazifr3wR/8wR/8wR/8wR/8wX+rHBhh/+AP/ru+fgGBCtYtS0ajSAAAAABJRU5ErkJggg==";

        String driverSeal = "iVBORw0KGgoAAAANSUhEUgAAAP8AAAD/CAMAAAAJ1vD4AAADAFBMVEX/////AAAAAP8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABLakbNAAAAAXRSTlMAQObYZgAAB0FJREFUeNrtnduy3CoMRN2E///lPpVK6uzJ2AYkJBnbmodU7YwHWGqBEVdge/WnbMmf/O/91N//8J3sSP2TP/mTP/mTP/mTP/mTP/mTP/nXidHwdv19LVAngueoYQM45lT0+O6+GeEDWn6G1E69BeBd/ynLJ9gCGC1YWdc1p1qMUWX0/Iy2AIXOD2f9Ka5sU/QU131E+H9wKyAqDAarLw2w6UxDlbnZS7rE1MhrWwKP/t8up0hjEOd2kDlltSjHBR5wam7I3KREeWJQFRDi2/n/am3/oDJlu6kD2ODbxf+8Jb7j+AfC01Xgz/PzJEc4GeA8XQ2+m/5w8oDzdFX4Bvw8yhJOVeA8XR2+hf48w7c3wHm6SnwT/ydnIjCTyE6L34z/TApPLaIkXR1+L/6jrWa25oKF+tv2C8Ydih0zdAZjI/43cn50+TfV6AjmvAjCoQ7M1LVq36vFZALqFkNTWPv4L2JQzAzfnv8qfGVeZW18OON7jn9wdd930B/nncKr8BHIj0uqficzhPGb42M+M7QnKMvD1e8F4sWjRC680OM3ylY98Omk+OGMM8Q/dtHfo+U/i/JVKw7gyh9T9wF9FvT0/7Cm748f85+o+KsT+Plnt1xlYfzGOC/H8PtNUrmL+jwwgEFuZV18NL9gW+9A/qC6z28DUBAR0o8/tNdnHgGXZfGhiqel5ij3Vv/q8R8/fMgGVKg0R7m1+vMxV1kc/7AVZ1f+EH5PfIieotoeZW3nl41sIdL/XfHRMge7bX/A+p+Qpo+ipzTvwjKL7zLOjVY7J5gZ7n/qLH7wC4/nbZ+qTGVFfAwkLlKftvyXqd/C1xWqLIiP8dRn8TX8QerTyETW/O740OAflgoO/AvU/VF8D/398fvyN5xfXKp6O/y5DXFT/GHhPmXiT6hSF6v6iMWX8IfhC2vHVLnqcr7fyOCw4RvA5zz/OvicipV1/GHbPNk764SCOAhG/F8rT3iJ+Cffzg4CVLn2WGjb9/QYSK//97nUhOFnnsijYGP+L3rujXJv/DY/dmu6uC3kAh38oX5BHaz4/Gmg/37J+4vf4j/bxMKPJYW8Pf6p/5/v4fmMuq9rCPr4mNKf5xsZ+L31TL5njUb48w5Yt74BuHsPY1wL2Ojcz/ArchzssNetawB2vEOK6iM+/jUCZvVvF5dqXb1c/6NEkv2Hrf2PsA+KOO3/GHrlD6fd3f/biKp5gfho5kxxlnUmGuUW2xPsDw3v6mXP4r9gWDIH+XdvGop+0Z1FMVr/zqDxkgELUrQRpV5VzIB0A87/WvFDSfNcb8Vk/3x9IL3k8fo87W3in1fQr9/+eQ+x5P0HyZ/8yZ/8yZ/8yZ/8yZ/8yZ/8yZ/8yZ/8yZ/8yZ/8yZ/8yZ/8yZ/8yZ/8yZ/8yb/k9c8/pfPmx+LyduUp06ljQVXHLWBw/p3TPX+NgsPOAhbn37nd8xdggWJRsxwMwF7BjSxQTNoVNwOYWcD0/O+Qlx43kQXaZVKf/4wRetJls5Dwtw0LnK+i7K5/RXtrA08f/PqVfMMwPxpYDmHB4/yjk2s35LlDb4Dh34o3JY+df8EufLuU0BlAWVvs17+DY/eaob0HVmoAqloNbub84zuX0Tm5zN8F7P1/KGWO3fkrcwGFAwgNPPz+p81BtxNbWVw6HTXC/+YPj/q/Aaa6WfAZ/3BV3yGBa/m1wH4bjXP87wYO4PjSXH//3+ehW0z/T/5X8ju5/3L80aPpdTX84GPFFtP/Tz8XcW6xZv0/HsrjC/gHRn2frT+PfMByxm91/+cw9fGoO0TmMrr/zMkA30OuAL7mPDBZsLn5X/dKii7O8TWRCOB3m/znKGsDFe788KsCnLKKyADT958FvKcg+1t0SvX0/WcuBuCcgTz6/41Z6Kj++r4IzZFgRJ1/5rf64WPgk/ISwFL/q4+77kjAUa+4yfgH90aHh2MuPf7z8X73cr+6NPuRmNyUrn4//d8Y/1r2s/gY/Xt+TiX+/fyfRs/chH9sjbVa/pvoTwXpmBPUW8j/d6iLe18gzq6J3p7A32KiRcco3/83cH++lT/7f6/mn7ll5kH6U/C/V8S/XlP2EVOgdd1ChswAV5MSXngl6GX8LjfifmwQQLdHfB0//NQJXgBV50poTc/F13+NXi70j5cK92k2b3Aydn/d/e/jJVD8ZGNkJdDO/9GVIa4aqNo/jj0EewNYu7/g/uOfso1m/v0grU18hf6y/Wueb8oI/TmrC03xzd1fGP9NXPk4tfPtOv+njQPbUNjL39d/2oGpcpuoF2DA+g8ztRzkH6j/jKiGl7UIZSy/FfBdqkSJN/kkK8P5uYTzz8yFOJz/9JDPbfidpsLKu/FvNP89hM+H8o8tguRj/J8KfNVrqj5FfWXLsOz6ZxoBRvHDzwLww1/a/81OvuCd6/80PiP1p6cnOPy0Lk9PeI691OW19x12rIvDZ/yX/Mmf/Mmf/Mmf/Mmf/MlvGF0j9U/+5H/r5z8H/gjUz0HrNgAAAABJRU5ErkJggg==";
        String filePath = "D:\\data\\devuploads\\file\\contract\\cfca\\axqian\\2020-03-28\\1585386501725_null_null_2_.pdf";
        Map<String, Object> txtFields = new HashMap<>();
        txtFields.put("projectId", 21);
        txtFields.put("userId", 222);

        Map<String, Object> res = anxinSignService.multiSign(filePath, "99306903952C7D41E05311016B0A56D4", companySeal, "A0E4FAC5153E2A09E05311016B0A370E", driverSeal, txtFields);
        for (Object value : res.values()) {
            System.out.println(value);
        }
    }

    public static void main34343(String[] args) {
        //        String templatePath= "C:\\fkhgit\\supply-chain-finance\\fkh-scf\\fkh-mservice-scf\\fkh-scf-service\\src\\main" +
        //            "\\resources\\cfca\\cantract_template_anxinqian1.pdf";//模版位置

        String templatePath = "D:\\data\\devuploads\\file\\contract\\cfca\\axqian\\2020-03-28\\1585386501725_null_null_2_.pdf";//模版位置

        //        log.error("srcFile:{}",srcFile);
        //        String templatePath ="D:/test.pdf";
        String newPDFPath = "D:/ss" + System.currentTimeMillis() + ".pdf";
            System.out.println("ss");
        try {
            Map<String,String> txtFields=new HashMap<>();
            txtFields.put("company_borrower_name","三大公司个梵蒂冈");
            txtFields.put("credit_apply_no","creditApplyDTO.getCreditApplyNo()");
            txtFields.put("company_capital_name","company.getCompanyName()");
            Calendar validityTime = Calendar.getInstance();
            txtFields.put("curry_year", validityTime.get(Calendar.YEAR)+"");
            txtFields.put("curry_month", (validityTime.get(Calendar.MONTH) + 1)+"");
            txtFields.put("curry_day", validityTime.get(Calendar.DAY_OF_MONTH)+"");
            txtFields.put("bill_date"," ToolsHelper.formatDate2Str(creditApplyDTO.getCreateTime())");
            txtFields.put("apply_balance_lower","creditApplyDTO.getApplyBalance().toString()");
            txtFields.put("apply_balance_upper","creditApplyDTO.getApplyBalance().toString()");
            txtFields.put("bank_name","companyContract.getBankName()");
            txtFields.put("bank_account_name","companyContract.getBankAccountName()");
            txtFields.put("bank_account_no","companyContract.getBankAccountNo()");
            txtFields.put("subject_claims_order_no","subjectClaimsOrder1.getSubjectClaimsOrderNo()");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfAcrofieldsRefit pdfAcrofieldsRefit = null;

            byte[] pdfBytes = Files.readAllBytes(Paths.get(templatePath));
                pdfAcrofieldsRefit = new PdfAcrofieldsRefit(new PdfReader(pdfBytes), baos);
                pdfAcrofieldsRefit.setAppend(false);
                pdfAcrofieldsRefit.setFieldMap(txtFields);
            pdfAcrofieldsRefit.close();
            FileUtils.writeByteArrayToFile(new File(newPDFPath),baos.toByteArray());
        } catch (Exception e) {
            log.error("Bad things", e);
        }
    }
    public static void main(String[] args) {
//        String templatePath= "C:\\fkhgit\\supply-chain-finance\\fkh-scf\\fkh-mservice-scf\\fkh-scf-service\\src\\main" +
//            "\\resources\\cfca\\cantract_template_anxinqian1.pdf";//模版位置

        String templatePath= "D:\\data\\devuploads\\file\\contract\\cfca\\axqian\\2020-03-28\\1585386501725_null_null_2_.pdf";//模版位置

        //        log.error("srcFile:{}",srcFile);
        //        String templatePath ="D:/test.pdf";
        String newPDFPath = "D:/ss"+System.currentTimeMillis()+".pdf";
        try {
            System.out.println("ss");
            Map<String,String> txtFields=new HashMap<>();
            txtFields.put("company_borrower_name","三大公司个梵蒂冈");
            txtFields.put("credit_apply_no","creditApplyDTO.getCreditApplyNo()");
            txtFields.put("company_capital_name","company.getCompanyName()");
            Calendar validityTime = Calendar.getInstance();
            txtFields.put("curry_year", validityTime.get(Calendar.YEAR)+"");
            txtFields.put("curry_month", (validityTime.get(Calendar.MONTH) + 1)+"");
            txtFields.put("curry_day", validityTime.get(Calendar.DAY_OF_MONTH)+"");
            txtFields.put("bill_date"," ToolsHelper.formatDate2Str(creditApplyDTO.getCreateTime())");
            txtFields.put("apply_balance_lower","creditApplyDTO.getApplyBalance().toString()");
            txtFields.put("apply_balance_upper","creditApplyDTO.getApplyBalance().toString()");
            txtFields.put("bank_name","companyContract.getBankName()");
            txtFields.put("bank_account_name","companyContract.getBankAccountName()");
            txtFields.put("bank_account_no","companyContract.getBankAccountNo()");
            txtFields.put("subject_claims_order_no","subjectClaimsOrder1.getSubjectClaimsOrderNo()");

            byte[] pdfBytes = Files.readAllBytes(Paths.get(templatePath));
            PdfReader reader = new PdfReader(pdfBytes);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            /* 读取 */
            PdfStamper pdfStamper = new PdfStamper(reader, bos);
            //            BaseFont bf = BaseFont.createFont("simsun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//            BaseFont baseFont = BaseFont.createFont("STSong-Light1", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//            BaseFont baseFont = BaseFont.createFont("simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            BaseFont baseFont = BaseFont.createFont("C:\\Windows\\Fonts\\simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 获取字段设置对象
            AcroFields s = pdfStamper.getAcroFields();
            ArrayList<String>  list=s.getBlankSignatureNames();
            /* 需要注意的是 setField的name和命名的表单域名字要一致 */
            Iterator<String> it = s.getFields().keySet().iterator();
            String nameValue = "";
            while (it.hasNext()) {
                // 获取文本域名称
                String name = it.next();
                nameValue = txtFields.get(name);// 获取要替换的内容
                // 设置文本域字体
                s.setFieldProperty(name, "textfont", baseFont, null); // 设置字体
//                s.setFieldProperty(name, "textcolor", BaseColor.RED, null); // 设置颜色
                //                if("commonSuperviseNoticeInfo".equals(type)){
                                    s.setFieldProperty(name, "textsize", (float) 13, null); // 设置字体大小
                //                }else{
                //                    s.setFieldProperty(name.toString(), "textsize", (float) 10, null); // 设置字体大小-五号字体对应10.5字号
                //                }
                s.setField(name, nameValue);
            }
            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.setFormFlattening(true);
            pdfStamper.close();
            FileUtils.writeByteArrayToFile(new File(newPDFPath),bos.toByteArray());
            //模版生成完成后，pdf转图片保存
//            pdfToPng(dstPdfFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }

    }
//    {"head":{"txTime":"20200325182057","locale":"zh_CN","retCode":"60000000","retMessage":"OK"},"localSign":{"userId":"A0E4FAC5153E2A09E05311016B0A370E","stubNo":"1585131657460","projectCode":"FKHWL000001","isCheckProjectCode":0,"signatureAttr":"od581KsPJlEsuAGWIKZF0WoRXHZyYMwd8nANGIwBEc0=","signatureOfAttr":"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"}}
}
