package com.fkhwl.scf.third.utils;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.util.Base64;
import cfca.sadk.util.HashUtil;

public class ThirdHttpHelper {
	public static final String CHARSET = "UTF-8";
	
	private static CloseableHttpClient httpClient = createSSLClientDefault();

	private static Logger log = LoggerFactory.getLogger(ThirdHttpHelper.class);
	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();

	public static String invokePost(String url, Map<String, String> params) {
		log.info("invoke third api.");
		log.info("url:" + url);
		log.info("params:" + (params == null ? null : JSONObject.toJSONString(params)));
		String result = "";
		if (StringUtils.isBlank(url))
			return result;
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null) {
			for (String key : params.keySet()) {
				String value = params.get(key);
				if (StringUtils.isNotBlank(value))
					nvps.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			httpPost.setConfig(requestConfig);
			CloseableHttpResponse resp = httpClient.execute(httpPost);
			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			resp.close();
		} catch (Exception e) {
			log.error("invoke third api error", e);
			result = "EXCEPTION";
		}

		log.info("invoke third api result=" + result);
		return result;
	}

	public static String invokePost(String url, Map<String, String> params,String encode) {
		log.info("invoke third api.");
		log.info("url:" + url);
		log.info("params:" + params);
		String result = "";
		if (StringUtils.isBlank(url))
			return result;
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null) {
			for (String key : params.keySet()) {
				String value = params.get(key);
				if (StringUtils.isNotBlank(value))
					nvps.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, encode));
			CloseableHttpResponse resp = httpClient.execute(httpPost);
			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "gbk");

			resp.close();
		} catch (Exception e) {
			log.error("invoke third api error", e);
			e.printStackTrace();
		}
		log.info("invoke third api result=" + result);
		return result;
	}



	public static String invokePost(String url, String content) {
		log.info("invoke third api.");
		log.info("url:" + url);
		log.info("content:" + content);
		String result = "";
		if (StringUtils.isBlank(url) || StringUtils.isBlank(content))
			return result;
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.setEntity(new StringEntity(content,"UTF-8"));
			CloseableHttpResponse resp = httpClient.execute(httpPost);
			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			resp.close();
		} catch (Exception e) {
			log.error("invoke third api error", e);
			e.printStackTrace();
		}
		log.info("invoke third api result=" + result);
		return result;
	}

    public static String invokePostJson(String url, String content) {
        log.info("invoke third api.");
        log.info("url:" + url);
        log.info("content:" + content);
        String result = "";
        if (StringUtils.isBlank(url) || StringUtils.isBlank(content))
            return result;
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.addHeader("Content-type","application/json; charset=GB2312");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setEntity(new StringEntity(content,"GB2312"));
            CloseableHttpResponse resp = httpClient.execute(httpPost);
            HttpEntity entity = resp.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            resp.close();
        } catch (Exception e) {
            log.error("invoke third api error", e);
            e.printStackTrace();
        }
        log.info("invoke third api result=" + result);
        return result;
    }

	/**
	 *
	 * @param url 请求地址
	 * @param content json串
	 * @param timeOut 请求超时时间
	 * @return
	 */
	public static String invokePostJson(String url, String content,int timeOut) {
		log.info("invoke third api.");
		log.info("url:" + url);
		log.info("content:" + content);
		String result = "";
		if (StringUtils.isBlank(url) || StringUtils.isBlank(content))
			return result;
		HttpPost httpPost = new HttpPost(url);
		try {
			RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(timeOut)
				.setSocketTimeout(timeOut).build();
			httpPost.setConfig(requestConfig);
			httpPost.addHeader("Content-type","application/json; charset=UTF-8");
			httpPost.setHeader("Accept", "application/json;charset=utf-8");
			httpPost.setEntity(new StringEntity(content,"UTF-8"));
			CloseableHttpResponse resp = httpClient.execute(httpPost);
			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			resp.close();
		} catch (Exception e) {
			log.error("invoke third api error", e);
			e.printStackTrace();
		}
		log.info("invoke third api result=" + result);
		return result;
	}

	public static String invokePostJson(String url, String content,String encode,Map<String,String> headerMap) {
		log.info("invoke third api.");
		log.info("url:" + url);
		log.info("content:" + content);
		String result = "";
		if (StringUtils.isBlank(url) || StringUtils.isBlank(content))
			return result;
		HttpPost httpPost = new HttpPost(url);
		try {
			for (String key : headerMap.keySet()) {
				String value = headerMap.get(key);
				if (StringUtils.isNotBlank(value)){
					httpPost.setHeader(key, value);
				}
			}
			httpPost.setEntity(new StringEntity(content,encode));
			CloseableHttpResponse resp = httpClient.execute(httpPost);
			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			resp.close();
		} catch (Exception e) {
			log.error("invoke third api error", e);
			e.printStackTrace();
		}
		log.info("invoke third api result=" + result);
		return result;
	}
	public static String invokeGet(String url) {
		log.info("invoke third api.");
		log.info("url:" + url);
		String result = "";
		if (StringUtils.isBlank(url))
			return result;

		log.info("request url:" + url);
		HttpGet httpGet = new HttpGet(url);
		try {
			CloseableHttpResponse resp = httpClient.execute(httpGet);
			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			resp.close();
		} catch (Exception e) {
			log.error("invoke third api error", e);
			e.printStackTrace();
		}
		log.info("invoke third api result=" + result);
		return result;
	}

    public static byte[] invokeGetFile(String url) {
        log.info("invoke third api.");
        log.info("url:" + url);
        byte[] result = null;
        if (StringUtils.isBlank(url)){
            return result;
        }
        log.info("request url:" + url);
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse resp = httpClient.execute(httpGet);
            HttpEntity entity = resp.getEntity();
            result = EntityUtils.toByteArray(entity);
            resp.close();
        } catch (Exception e) {
            log.error("invoke third api error", e);
            e.printStackTrace();
        }
        log.info("invoke third api result=" + result);
        return result;
    }
	public static String invokeGet(String url, Map<String, String> params) {
		log.info("invoke third api.");
		log.info("url:" + url);
		log.info("params:" + params);
		String result = "";
		if (StringUtils.isBlank(url))
			return result;

		String u = buildGetUrl(url, params);
		log.info("request url:" + u);
		HttpGet httpGet = new HttpGet(u);
		try {
			CloseableHttpResponse resp = httpClient.execute(httpGet);
			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			resp.close();
		} catch (Exception e) {
			log.error("invoke third api error", e);
			e.printStackTrace();
		}
		log.info("invoke third api result=" + result);
		return result;
	}
	public static String invokeGet(String url, Map<String, String> params,int timeOut) {
		log.info("invoke third api.");
		log.info("url:" + url);
		log.info("params:" + params);
		String result = "";
		if (StringUtils.isBlank(url))
			return result;

		String u = buildGetUrl(url, params);
		log.info("request url:" + u);
		HttpGet httpGet = new HttpGet(u);
		try {
			RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(timeOut)
				.setSocketTimeout(timeOut).build();
			httpGet.setConfig(requestConfig);
			CloseableHttpResponse resp = httpClient.execute(httpGet);
			HttpEntity entity = resp.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			resp.close();
		} catch (Exception e) {
			log.error("invoke third api error", e);
			e.printStackTrace();
		}
		log.info("invoke third api result=" + result);
		return result;
	}
	private static String buildGetUrl(String url, Map<String, String> params) {
		if (params == null || params.size() == 0)
			return url;
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (StringUtils.isNotBlank(value))
				nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		String paramString = URLEncodedUtils.format(nvps, "utf-8");
		if (url.indexOf('?') == -1)
			return url + "?" + paramString;
		else
			return url + "&" + paramString;
	}

	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sc = SSLContext.getInstance("TLSv1.2");
			sc.init(null, new TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) {
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} }, new SecureRandom());
			X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
				public void verify(String host, SSLSocket ssl) {
				}

				public void verify(String host, X509Certificate cert) {
				}

				public void verify(String host, String[] cns,
						String[] subjectAlts) {
				}

				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			};
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sc, hostnameVerifier);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}
	
	public static String doPost(String url,Map<String,String> map,String charset){
        HttpPost httpPost = null;
        String result = null;  
        try{  
            httpPost = new HttpPost(url);
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){  
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
            log.info("=国采支付url==="+url);
        }  
        return result;  
    }  
	
	public  void main123(String[] args) {
//		String url = "https://apitest.yiji.com";
//		String result =invokeGet(url,null,1000*10);
//		System.out.println(result);[{"name":"测试组周例会记录表-2-10~214.pdf","path":"https://dev.fkhwl.com/uploads/file/contract/20200224/1582534701090_0_1_.pdf"}]
        String url = "https://dev.fkhwl.com/uploads/file/contract/20200224/1582534701090_0_1_.pdf";
        byte[] resp = ThirdHttpHelper.invokeGetFile(url);
        try {
            IOUtil.writeFile("d:/test/","d:/test/1582534701090_0_1_.pdf",resp);
            JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
            Session session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);
            byte[] hashData = null;
            hashData = HashUtil.RSAHashData(resp, new Mechanism(Mechanism.SHA256), session, false);
            System.out.println(new String(Base64.encode(hashData), "UTF-8"));
//            HyLOfgdAaW4FnsMe9m0SXEQ0oXLPJJ+DXu4CqqdqC0o=
        } catch (Exception e) {
            log.error("Bad things", e);
        }
	}

    public  static void main(String[] args) {
        //		String url = "https://apitest.yiji.com";
        //		String result =invokeGet(url,null,1000*10);
        //		System.out.println(result);[{"name":"测试组周例会记录表-2-10~214.pdf","path":"https://dev.fkhwl.com/uploads/file/contract/20200224/1582534701090_0_1_.pdf"}]
        String url = "https://dev.fkhwl.com/uploads/file/contract/20200224/1582534701090_0_1_.pdf";
        byte[] resp = ThirdHttpHelper.invokeGetFile(url);
        try {

            File tmp=new File("1582534701090_0_1_.pdf");
            try (OutputStream os = new FileOutputStream(new File("1582534701090_0_1_.pdf"))) {
                os.write(resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            InputStream inputStream = new ByteArrayInputStream(resp);

            JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
            Session session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);
            byte[] hashData = null;
            hashData = HashUtil.RSAHashData(IOUtil.readFile(tmp), new Mechanism(Mechanism.SHA256), session, false);
            System.out.println(new String(Base64.encode(hashData), "UTF-8"));
            //            HyLOfgdAaW4FnsMe9m0SXEQ0oXLPJJ+DXu4CqqdqC0o=
        } catch (Exception e) {
            log.error("Bad things", e);
        }
    }

    public static void main42342(String[] args) {
        //		String url = "https://apitest.yiji.com";
        //		String result =invokeGet(url,null,1000*10);
        //		System.out.println(result);[{"name":"测试组周例会记录表-2-10~214.pdf","path":"https://dev.fkhwl.com/uploads/file/contract/20200224/1582534701090_0_1_.pdf"}]


        try {



            JCrypto.getInstance().initialize(JCrypto.JSOFT_LIB, null);
            Session session = JCrypto.getInstance().openSession(JCrypto.JSOFT_LIB);
            byte[] hashData = null;
            hashData = HashUtil.RSAHashData(IOUtil.readFile(new File("d:/test/1582534701090_0_1_.pdf")), new Mechanism(Mechanism.SHA256), session, false);
            System.out.println(new String(Base64.encode(hashData), "UTF-8"));
            //            HyLOfgdAaW4FnsMe9m0SXEQ0oXLPJJ+DXu4CqqdqC0o=
            //            HyLOfgdAaW4FnsMe9m0SXEQ0oXLPJJ+DXu4CqqdqC0o=
        } catch (Exception e) {
            log.error("Bad things", e);
        }
    }
}
