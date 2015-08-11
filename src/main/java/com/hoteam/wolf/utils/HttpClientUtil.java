package com.hoteam.wolf.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	private static int TIME_OUT = 1000 * 120;

	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType)
						throws java.security.cert.CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			logger.error("", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("", e);
		} catch (KeyStoreException e) {
			logger.error("", e);
		}
		return HttpClients.createDefault();
	}

	public static String get(Map<String, String> param, String url) {
		logger.debug("START TO DOGET METHOD...");
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClientUtil.createSSLClientDefault();
		logger.debug("URL:" + url + "\nParam:" + param);
		try {
			if (null == param || param.isEmpty()) {
				logger.info("NO PARAM TO POST!!!");
			} else {
				List<NameValuePair> parameters = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					parameters.add(new BasicNameValuePair(key, param.get(key)));
				}
				String params = URLEncodedUtils.format(parameters, "UTF-8");
				logger.debug("PARAM IS :" + params);
				url = url + "?" + params;
			}
			HttpGet httpGet = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT)
					.build();// 设置请求和传输超时时间
			httpGet.setConfig(requestConfig);
			logger.info("EXECUTE...");
			CloseableHttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String content = IOUtils.toString(entity.getContent());
			logger.info("RESPONSE:" + content);
			try {
				logger.debug("GET RESPONSE STATUS AND PARSE IT");
				int status = response.getStatusLine().getStatusCode();
				logger.info("RESPONSE STATUS IS :" + status);
				if (200 == status) {
					logger.info("SUCCESS RESPONSE !");
					responseContent = content;
				} else {
					logger.warn("FAILED RESPONSE!!");
				}
			} finally {
				logger.info("CLOSE RESPONSE");
				response.close();
			}
		} catch (Exception e) {
			logger.error("OCCUR AN Exception :\n", e);
		} finally {
			logger.info("CLOSE THE HTTPCLIENT");
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("CLOSE HTTPCLIENT OCCUR IOException :\n", e);
			}
		}
		logger.info("METHOD OVER!!");
		return responseContent;
	}

}
