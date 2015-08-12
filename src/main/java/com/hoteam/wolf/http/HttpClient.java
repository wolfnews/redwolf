package com.hoteam.wolf.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.enums.Method;

/**
 * 通用的HTTP请求客户端 主要有post,get,doPost和doGet四个方法，
 * 
 * @author mingwei.dmw
 *
 */
@Component("httpClient")
public class HttpClient {

	private static Logger logger = Logger.getLogger(HttpClient.class);
	private static final int TIME_OUT = 1000 * 60;
	private static final String SUCCESS = "success";
	private static final String MESSAGE = "message";

	/**
	 * http的Post方法
	 * 
	 * @param param
	 *            请求参数
	 * @param url
	 *            请求路径
	 * @return 返回的response，异常情况时返回空
	 */
	public String post(Map<String, String> param, String url) {
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT)
				.build();// 设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		logger.info("URL:" + url + "\nParam:" + param);
		try {
			logger.info("INIT PARAMS");
			if (null == param || param.isEmpty()) {
				logger.warn("NO PARAM TO POST!!!");
			} else {
				List<NameValuePair> parameters = new ArrayList<NameValuePair>();
				for (String key : param.keySet()) {
					parameters.add(new BasicNameValuePair(key, param.get(key)));
				}
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
				httpPost.setEntity(formEntity);
			}
			logger.info("EXECUTE...");
			CloseableHttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String content = IOUtils.toString(entity.getContent());
			content = new String(content.getBytes(),"UTF-8");
			logger.info("RESPONSE:" + content);
			try {
				logger.debug("GET RESPONSE STATUS AND PARSE IT");
				int status = response.getStatusLine().getStatusCode();
				logger.info("RESPONSE STATUS :" + status);
				if (200 == status) {
					logger.info("SUCCESS RESPONSE !");
					responseContent = content;
				} else {
					logger.warn("FAILED RESPONSE!!");
				}
			} finally {
				logger.debug("CLOSE RESPONSE");
				response.close();
			}
		} catch (Exception e) {
			logger.error("OCCUR AN Exception :\n", e);
		} finally {
			logger.info("CLOSE THE HTTPCLIENT");
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("CLOSE HTTPCLIENT OCCUR AN IOException :\n", e);
			}
		}
		logger.info("METHOD OVER!!");
		return responseContent;
	}

	/**
	 * Get方法
	 * 
	 * @param param
	 *            请求参数
	 * @param url
	 *            请求路径
	 * @return 返回的response，异常情况时返回null
	 */
	public String get(Map<String, String> param, String url) {
		logger.debug("START TO DOGET METHOD...");
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		logger.info("URL:" + url + "\nParam:" + param);
		try {
			logger.info("INIT PARAMS");
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

	/**
	 * http的doPost方法
	 * 
	 * @param param
	 *            请求参数
	 * @param url
	 *            请求路径
	 * @return 返回的response，异常情况时返回空map
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> doPost(Map<String, String> param, String url) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String response = post(param, url);
		ObjectMapper mapper = new ObjectMapper();
		if (null == response) {
			responseMap.put(SUCCESS, false);
			responseMap.put(SUCCESS, "DO POST ERROR!");
			return responseMap;
		}
		try {
			responseMap = mapper.readValue(response, HashMap.class);
		} catch (Exception e) {
			logger.error("Do post Method error:", e);
			responseMap.put(SUCCESS, false);
			responseMap.put(SUCCESS, "FORMAT ERROR!");
			return responseMap;
		}
		return responseMap;
	}

	/**
	 * doGet方法
	 * 
	 * @param param
	 *            请求参数
	 * @param url
	 *            请求路径
	 * @return 返回的response，异常情况时返回空map
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> doGet(Map<String, String> param, String url) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String response = get(param, url);
		ObjectMapper mapper = new ObjectMapper();
		if (null == response) {
			responseMap.put(SUCCESS, false);
			responseMap.put(MESSAGE, "DOGET ERROR!");
			return responseMap;
		}
		try {
			responseMap = mapper.readValue(response, HashMap.class);
		} catch (Exception e) {
			logger.error("Do post Method error:", e);
			responseMap.put(SUCCESS, false);
			responseMap.put(MESSAGE, "FORMAT ERROR!");
			return responseMap;
		}
		return responseMap;
	}

	public Map<String, Object> request(Map<String, String> param, String url, Method method) {
		Map<String, Object> response = null;
		switch (method) {
		case GET:
			response = doGet(param, url);
			break;
		case POST:
			response = doPost(param, url);
			break;
		default:
			response = new HashMap<String, Object>();
			response.put(SUCCESS, false);
			response.put(MESSAGE, "HTTP ERROR!");
			break;
		}
		return response;
	}

	public String request4String(Map<String, String> param, String url, Method method) {
		String response = null;
		switch (method) {
		case GET:
			response = get(param, url);
			break;
		case POST:
			response = post(param, url);
			break;
		default:
			break;
		}
		return response;
	}

	public static void main(String[] args) {
		logger.info(Thread.currentThread().getName() + " start test...");
		long start = System.currentTimeMillis();
		ExecutorService threadPool = Executors.newFixedThreadPool(6);
		CompletionService<Result> cs = new ExecutorCompletionService<Result>(threadPool);
		for (int i = 0; i < 5000; i++) {
			NoticeClient client = new NoticeClient(new HttpClient());
			cs.submit(client);
		}
		int taskNum = 1000;
		while (taskNum > 0) {
			try {
				cs.take().get();
				// System.out.println(result);
				taskNum--;
			} catch (Exception e) {
				logger.error("getNoitce exception:", e);
			}
		}
		long cost = System.currentTimeMillis() - start;
		logger.error("process over!!cost:" + cost / 1000 + "s");
		threadPool.shutdown();
	}
}
