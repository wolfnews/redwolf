package com.hoteam.wolf.interceptor;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hoteam.wolf.common.enums.Target;
import com.hoteam.wolf.dao.VisitCountDao;
import com.hoteam.wolf.domain.VisitCount;
import com.hoteam.wolf.utils.DateUtil;

/**
 * 讲师访问拦截器
 * 
 * @author mingwei.dmw
 *
 */
@Repository("accessInterceptor")
public class AccessInterceptor implements HandlerInterceptor {

	@Autowired
	private VisitCountDao visitCountDao;
	private List<String> ignoreUrls;

	public void setIgnoreUrls(List<String> ignoreUrls) {
		this.ignoreUrls = ignoreUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestPath = request.getServletPath();
		if (null != ignoreUrls) {
			for (String url : ignoreUrls) {
				if (requestPath.contains(url)) {
					return true;
				}
			}
		}
		String source = getRemoteHost(request);
		if("127.0.0.1".equals(source)){
			return true;
		}
		VisitCount visitCount = visitCountDao.load(source,DateUtil.getCommonDate(), Target.SITE.name());
		if (null == visitCount) {
			visitCount = new VisitCount(new Date(), 1l, Target.SITE.name(), source);
			this.visitCountDao.save(visitCount);
		} else {
			visitCount.setAmount(visitCount.getAmount() + 1);
			this.visitCountDao.update(visitCount);
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
