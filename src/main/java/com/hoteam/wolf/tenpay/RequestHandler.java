package com.hoteam.wolf.tenpay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hoteam.wolf.tenpay.util.MD5Util;
import com.hoteam.wolf.tenpay.util.TenpayUtil;

/**
 * 请求处理类 请求处理类继承此类，重写createSign方法即可。
 * 
 * @author miklchen
 *
 */
public class RequestHandler {

	private static Logger logger = Logger.getLogger(RequestHandler.class);
	/** 网关url地址 */
	private String gateUrl;

	/** 密钥 */
	private String key;

	/** 请求的参数 */
	private SortedMap<String,String> parameters;

	/** debug信息 */
	private String debugInfo;

	private HttpServletRequest request;

	private HttpServletResponse response;

	/**
	 * 构造函数
	 * 
	 * @param request
	 * @param response
	 */
	public RequestHandler(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

		this.gateUrl = "https://gw.tenpay.com/gateway/pay.htm";
		this.key = "";
		this.parameters = new TreeMap<String,String>();
		this.debugInfo = "";
	}

	/**
	 * 初始化函数。
	 */
	public void init() {
		// nothing to do
	}

	/**
	 * 获取入口地址,不包含参数值
	 */
	public String getGateUrl() {
		return gateUrl;
	}

	/**
	 * 设置入口地址,不包含参数值
	 */
	public void setGateUrl(String gateUrl) {
		this.gateUrl = gateUrl;
	}

	/**
	 * 获取密钥
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置密钥
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取参数值
	 * 
	 * @param parameter
	 *            参数名称
	 * @return String
	 */
	public String getParameter(String parameter) {
		String s = (String) this.parameters.get(parameter);
		return (null == s) ? "" : s;
	}

	/**
	 * 设置参数值
	 * 
	 * @param parameter
	 *            参数名称
	 * @param parameterValue
	 *            参数值
	 */
	public void setParameter(String parameter, String parameterValue) {
		String v = "";
		if (null != parameterValue) {
			v = parameterValue.trim();
		}
		this.parameters.put(parameter, v);
	}

	/**
	 * 返回所有的参数
	 * 
	 * @return SortedMap
	 */
	public SortedMap<String,String> getAllParameters() {
		return this.parameters;
	}

	/**
	 * 获取debug信息
	 */
	public String getDebugInfo() {
		return debugInfo;
	}

	/**
	 * 获取带参数的请求URL
	 * 
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	public String getRequestURL() throws UnsupportedEncodingException {

		this.createSign();

		StringBuffer sb = new StringBuffer();
		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		enc="gb2312";
		Set<Entry<String,String>> es = this.parameters.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String,String> entry = (Entry<String, String>) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if(k.equalsIgnoreCase("spbill_create_ip")){
				sb.append(k + "=" + URLEncoder.encode(v, enc).replace(".", "%2E") + "&");
			}else{
				sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
			}
		}

		// 去掉最后一个&
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));

		return this.getGateUrl() + "?" + reqPars;

	}

	public void doSend() throws UnsupportedEncodingException, IOException {
		this.response.sendRedirect(this.getRequestURL());
	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	protected void createSign() {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String,String>> es = this.parameters.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + this.getKey());

		String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
		enc="gb2312";
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();
		logger.warn("charset:"+enc);
		logger.warn("sign="+sign);
		this.setParameter("sign", sign);
	}

	/**
	 * 设置debug信息
	 */
	protected void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}

	protected HttpServletRequest getHttpServletRequest() {
		return this.request;
	}

	protected HttpServletResponse getHttpServletResponse() {
		return this.response;
	}

}
