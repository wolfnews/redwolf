package com.hoteam.wolf.interceptor;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hoteam.wolf.common.Constants;

/**
 * 讲师访问拦截器
 * 
 * @author mingwei.dmw
 *
 */
@Repository("managerInterceptor")
public class ManagerInterceptor implements HandlerInterceptor {
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
		Enumeration<String> params = request.getParameterNames();
		StringBuffer requestContent = new StringBuffer();
		if (null != params) {
			while (params.hasMoreElements()) {
				String param = params.nextElement();
				String value = request.getParameter(param);
				requestContent.append(param).append(":").append(value).append("|");
			}
		}
		Long managerId = (Long) request.getSession().getAttribute(Constants.MANAGER_TOKEN.toString());
		if (null == managerId) {
			response.sendRedirect(request.getContextPath()+"/manage/login.html");
		} else {
			return true;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
