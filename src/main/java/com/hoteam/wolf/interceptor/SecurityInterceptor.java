package com.hoteam.wolf.interceptor;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.utils.SendSMSRequestCache;

/**
 * 讲师访问拦截器
 * 
 * @author mingwei.dmw
 *
 */
@Repository("securityInterceptor")
public class SecurityInterceptor implements HandlerInterceptor {

	private static Logger logger = Logger.getLogger(SecurityInterceptor.class);
	private List<String> ignoreUrls;
	@Autowired
	private SendSMSRequestCache sendSMSRequestCache;

	public void setIgnoreUrls(List<String> ignoreUrls) {
		this.ignoreUrls = ignoreUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestPath = request.getServletPath();
		if (isSqlInject(request)) {
			String ip = this.getRemoteHost(request);
			logger.warn("SQL INJECT IS DETECTED FROM [" + ip + "]");
			response.getWriter().println("Fuck You Hacker!!!!");
			response.getWriter().close();
			return false;
		}
		String ip = getRemoteHost(request);
		if (requestPath.contains("sms/send")) {
			sendSMSRequestCache.putIP(ip);
		}
		if (5 < sendSMSRequestCache.getIP(ip)) {
			logger.warn("Somebody Is Trying To Attack The Site!!!!!");
			Result json = new Result(false, "请不要如此亲密的访问我！");
			response.setHeader("Content-type", "application/json;charset=UTF-8"); 
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(JSON.toJSONString(json));
			response.getWriter().close();
			return false;
		}
		if (null != ignoreUrls) {
			for (String url : ignoreUrls) {
				if (requestPath.contains(url)) {
					return true;
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private boolean isSqlInject(HttpServletRequest request) {
		String requestUrl = request.getServletPath() + "?";
		String param = "";
		String value = "";

		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			param = params.nextElement();
			requestUrl += "|" + param;
			value = request.getParameter(param);
			requestUrl += "|" + value;
		}
		requestUrl = requestUrl.toUpperCase();
		if (requestUrl.contains("SELECT") || requestUrl.contains("INSERT") || requestUrl.contains("CONCAT")
				|| requestUrl.contains("UNION") || requestUrl.contains("ALTER") || requestUrl.contains("UPDATE")
				|| requestUrl.contains("COUNT") || requestUrl.contains("IFNULL") || requestUrl.contains("FROM")
				|| requestUrl.contains("WHERE") || requestUrl.contains("JOIN") || requestUrl.contains("LIMIT")
				|| requestUrl.contains("SCHEMA") || requestUrl.contains("TABLES") || requestUrl.contains("MID")
				|| requestUrl.contains("SUM") || requestUrl.contains("AVG") || requestUrl.contains("CAST")
				|| requestUrl.contains("DELETE")) {
			return true;
		} else {
			return false;
		}
	}

	private String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

}
