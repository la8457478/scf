package com.fkhwl.scf.third.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.fkhwl.scf.config.LoginkConfig;
import com.fkhwl.scf.third.resp.logink.BizContent;
import com.fkhwl.scf.third.service.LoginkService;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title: com.fkhwl.service.logink.service</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: dong4j
 * emali: dongshijie@fkhwl.com
 * version: 1.0
 * date: 2018年04月15日 15点09分
 * updatetime:
 * reason:
 */
@Service
@Slf4j
public class LoginkServiceImpl implements LoginkService {

    /**
     * 返回车辆是否入网正常
     * @param licensePlateNo
     * @return
     */
    public  boolean checkPlatformNetInIsOk(String licensePlateNo){
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = LoginkConfig.loginkUrl + "/logink/checkNetIn?licensePlateNo=" + licensePlateNo;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestUrl, String.class);
        log.error("requestUrl: {}, response: \r\n{}", new Object[]{requestUrl, JSONObject.toJSONString(responseEntity)});
        if(StringUtils.isBlank(responseEntity.getBody())){
            return false;
        }

        JSONObject jsonObject = JSONObject.parseObject(responseEntity.getBody());
        if(jsonObject.getBooleanValue("data")){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取无车承运平台的车辆信息
     * @param licensePlateNo 需要查询的车牌号
     * @return
     */
    public  BizContent getPlatformCarInfo(String licensePlateNo){
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = LoginkConfig.loginkUrl + "/logink/carinfo?licensePlateNo=" + licensePlateNo;
        ResponseEntity<BizContent> responseEntity = restTemplate.getForEntity(requestUrl, BizContent.class);
        log.info("requestUrl: {}, response: \r\n{}", new Object[]{requestUrl, JSONObject.toJSONString(responseEntity)});
        BizContent carInfo = responseEntity.getBody();
        if(null == carInfo || null == carInfo.getHeader() || null == carInfo.getBody()){
            return null;
        }
//        requestUrl: http://192.168.2.20:8091/logink/carinfo?licensePlateNo=渝D32191, response:
//        {"body":{"body":{"drivingLicenceInformation":{"vehicleMaximumTractionWeight":"0","vehicleNumber":"渝D32191"},"licensePlateTypeCode":"2","owner":"重庆市綦江区发家致富汽车运输有限公司","roadTransportCertificateInformation":{"businessScope":"普通货运","businessState":"营运","businessStateCode":"10","certificationUnit":"綦江区运管处","periodEndDate":"20230725","periodStartDate":"20190725","roadTransportCertificateNumber":"500222102487","vehicleClassification":"自卸车","vehicleHeight":"3460","vehicleLength":"8935","vehicleNumber":"渝D32191","vehicleTonnage":"12","vehicleWidth":"2550"},"vehicleNumber":"渝D32191"},"header":{}},"headers":{"X-Application-Context":["fkh-service-logink:8091"],"Content-Type":["application/json;charset=UTF-8"],"Transfer-Encoding":["chunked"],"Date":["Fri, 06 Mar 2020 06:02:20 GMT"]},"statusCode":"OK","statusCodeValue":200}
        return carInfo;
    }
}
