package com.hoteam.wolf.interceptor;

import java.util.Date;

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
 * 移动访问拦截器
 * 
 * @author mingwei.dmw
 *
 */
@Repository("mobileInterceptor")
public class MobileInterceptor implements HandlerInterceptor {

	@Autowired
	private VisitCountDao visitCountDao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String source = getRemoteHost(request);
		if ("127.0.0.1".equals(source)) {
			return true;
		}
		VisitCount visitCount = visitCountDao.load(source, DateUtil.getCommonDate(), Target.MOBILE.name());
		if (null == visitCount) {
			visitCount = new VisitCount(new Date(), 1l, Target.MOBILE.name(), source);
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
