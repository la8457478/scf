//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fkhwl.scf.sms;

import com.fkhwl.starter.core.util.EnumUtils;
import com.fkhwl.starter.core.util.WebUtils;
import com.fkhwl.starter.notify.AbstractNotifyContent;
import com.fkhwl.starter.sms.config.SmsProperties;
import com.fkhwl.starter.sms.enums.SmsCode;
import com.fkhwl.starter.sms.exception.SmsException;
import com.fkhwl.starter.sms.model.YiMeiNotifyContent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class YiMeiSmsClient {
    private static final Logger log = LoggerFactory.getLogger(YiMeiSmsClient.class);
    private final SmsProperties smsProperties;
    private final RestTemplate restTemplate;

    @Contract(
        pure = true
    )
    public YiMeiSmsClient(@NotNull SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
        this.restTemplate = new RestTemplate();
    }

    public String getMos() throws Throwable {
        String param = "cdkey=" + this.smsProperties.getYiMeiAppId() + "&password=" + this.smsProperties.getYiMeiPassword();
        String url = this.smsProperties.getYiMeiBaseUrl() + this.smsProperties.getYiMeiMoUrl() + "?" + param;
        log.info("【YiMeiHttpClient】Request-Url:{}", url);
        return (String)this.invokeGet(url, (Map)null, String.class);
    }

    public String sendMsg(AbstractNotifyContent notifyContent) {
        YiMeiNotifyContent content = (YiMeiNotifyContent)notifyContent;
        return this.parseSendResponse(this.send(content.getMessageId(), content.getPhone(), content.getContent()));
    }

    private String send(Long messageId, String phoneNumbers, String smsMessage) {
        Map<String, String> params = new HashMap();
        params.put("cdkey", this.smsProperties.getYiMeiAppId());
        params.put("password", this.smsProperties.getYiMeiPassword());
        params.put("phone", phoneNumbers);

//        try {
            params.put("message", smsMessage);
//        } catch (UnsupportedEncodingException var9) {
//            var9.printStackTrace();
//        }

        params.put("seqid", "" + messageId);
        params.put("smspriority", "1");

        String result;
        try {
            String sendUrl = this.smsProperties.getYiMeiBaseUrl() + this.smsProperties.getYiMeiSendUrl();
            sendUrl = this.buildGetUrl(sendUrl, params);
            result = (String)this.invokeGet(sendUrl, params, String.class);
        } catch (Throwable var8) {
            log.error("YiMei sendMsg error", var8);
            result = "";
        }

        log.error("YiMei sendMsg mobileNo:{}, response:{}", phoneNumbers, result);
        return result;
    }

    private String parseSendResponse(String result) {
        String regex = "<error>([^<]+)</error>";
        Matcher m = Pattern.compile(regex).matcher(result);
        return m.find() ? m.group(1) : "-1";
    }

    private String buildGetUrl(String url, Map<String, String> params) {
        String urlParamsByMap = WebUtils.getUrlParamsByMap(params);
        url = url + "?" + urlParamsByMap;
        return url;
    }

    private <T> T invokeGet(String url, Map<String, String> headerMap, Class<T> responseClass) throws Throwable {
        HttpHeaders headers = new HttpHeaders();
        if (headerMap != null && headerMap.size() > 0) {
            headerMap.forEach(headers::add);
        }

        HttpEntity<String> requestEntity = new HttpEntity((Object)null, headers);
        ResponseEntity<T> resEntity = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseClass, new Object[0]);
        log.info("invoke third api result: [{}], url: [{}], header: [{}]", new Object[]{resEntity, url, headerMap});
        if (HttpStatus.OK.equals(resEntity.getStatusCode())) {
            return resEntity.getBody();
        } else {
            Optional var10000 = EnumUtils.of(SmsCode.class, (g) -> {
                return g.getCode() == resEntity.getStatusCodeValue();
            });
            SmsCode var10001 = SmsCode.UNKNOWN;
            var10001.getClass();
            SmsCode smsCode = (SmsCode)var10000.orElseThrow(() -> {
                return var10001.newException(new Object[0]);
            });
            throw new SmsException(smsCode);
        }
    }
}
