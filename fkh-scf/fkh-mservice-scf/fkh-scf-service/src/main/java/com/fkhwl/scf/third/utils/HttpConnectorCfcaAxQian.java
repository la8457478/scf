package com.fkhwl.scf.third.utils;


import com.fkhwl.scf.third.anxinsign.constant.MIMEType;
import com.fkhwl.scf.third.anxinsign.constant.Request;
import com.fkhwl.scf.third.anxinsign.constant.SystemConst;
import com.fkhwl.scf.third.anxinsign.util.CommonUtil;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpConnectorCfcaAxQian {
    public static String JKS_PATH = "";
    public static String JKS_PWD = "";
    public static String ALIAS = "anxinsign";

    public static String url = "";

    public int connectTimeout = 3000;
    public int readTimeout = 10000;
    public String channel = "Test";
    public boolean isSSL = true;
    public String keyStorePath = JKS_PATH;
    public String keyStorePassword = JKS_PWD;
    public String trustStorePath = JKS_PATH;
    public String trustStorePassword = JKS_PWD;

    private HttpClientCfcaAxQian httpClientCfcaAxQian;

    public void init() {
        httpClientCfcaAxQian = new HttpClientCfcaAxQian();
        httpClientCfcaAxQian.config.connectTimeout = connectTimeout;
        httpClientCfcaAxQian.config.readTimeout = readTimeout;
        httpClientCfcaAxQian.httpConfig.userAgent = "TrustSign FEP";
        httpClientCfcaAxQian.httpConfig.contentType = MIMEType.FORM;
        httpClientCfcaAxQian.httpConfig.accept = MIMEType.JSON;
        try {
            if (isSSL) {
                httpClientCfcaAxQian.initSSL(keyStorePath, keyStorePassword.toCharArray(), trustStorePath, trustStorePassword.toCharArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!url.endsWith("/")) {
            url += "/";
        }
    }

    public String post(String uri, String data, String signature) {
        return deal(uri, "POST", prepare(data, signature, null));
    }

    public String post(String uri, String data, String signature, Map<String, String> map) {
        return deal(uri, "POST", prepare(data, signature, map));
    }

    public String post(String uri, String data, String signature, File file) {
        return deal(uri, "POST", data, file, signature);
    }

    public byte[] getFile(String uri) {
        HttpURLConnection connection = null;
        try {
            connection = httpClientCfcaAxQian.connect(url + uri, "GET");
            int responseCode = httpClientCfcaAxQian.send(connection, null);
            System.out.println("responseCode:" + responseCode);
            if (responseCode != 200) {
                System.out.println(CommonUtil.getString(httpClientCfcaAxQian.receive(connection)));
                return null;
            }
            return httpClientCfcaAxQian.receive(connection);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(null != connection){
                httpClientCfcaAxQian.disconnect(connection);
            }
        }
    }

    private String prepare(String data, String signature, Map<String, String> map) {
        try {
            StringBuilder request = new StringBuilder();
            request.append(Request.CHANNEL).append("=").append(URLEncoder.encode(channel, SystemConst.DEFAULT_CHARSET));
            if (CommonUtil.isNotEmpty(data)) {
                request.append("&").append(Request.DATA).append("=").append(URLEncoder.encode(data, SystemConst.DEFAULT_CHARSET));
            }
            if (CommonUtil.isNotEmpty(signature)) {
                request.append("&").append(Request.SIGNATURE).append("=").append(URLEncoder.encode(signature, SystemConst.DEFAULT_CHARSET));
            }
            if (CommonUtil.isNotEmpty(map)) {
                for (Entry<String, String> pair : map.entrySet()) {
                    request.append("&").append(pair.getKey()).append("=")
                            .append(pair.getValue() == null ? "" : URLEncoder.encode(pair.getValue(), SystemConst.DEFAULT_CHARSET));
                }
            }
            return request.toString();
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private String deal(String uri, String method, String request) {
        HttpURLConnection connection = null;
        try {
            log.info("anxinsign request url : {}",url + uri);
            connection = httpClientCfcaAxQian.connect(url + uri, method);
            int responseCode = httpClientCfcaAxQian.send(connection, request == null ? null : CommonUtil.getBytes(request));
            return CommonUtil.getString(httpClientCfcaAxQian.receive(connection));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if(null != connection){
                httpClientCfcaAxQian.disconnect(connection);
            }
        }
    }

    private String deal(String uri, String method, String request, File file, String signature) {
        HttpURLConnection connection = null;
        try {
            connection = httpClientCfcaAxQian.connect(url + uri, method);
            System.out.println(url + uri);
            System.out.println(method);
            System.out.println(request);
            int responseCode = httpClientCfcaAxQian.send(connection, request, file, signature);
            System.out.println("responseCode:" + responseCode);
            return CommonUtil.getString(httpClientCfcaAxQian.receive(connection));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if(null != connection){
                httpClientCfcaAxQian.disconnect(connection);
            }
        }
    }

}
