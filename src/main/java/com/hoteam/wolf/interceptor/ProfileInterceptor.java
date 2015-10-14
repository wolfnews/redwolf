package com.hoteam.wolf.interceptor;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.config.SystemConfig;
import com.hoteam.wolf.common.enums.CookiePath;
import com.hoteam.wolf.domain.User;
import com.hoteam.wolf.service.UserService;
import com.hoteam.wolf.utils.CookieUtil;
import com.hoteam.wolf.utils.DESUtil;

/**
 * 讲师访问拦截器
 * 
 * @author mingwei.dmw
 *
 */
@Repository("profileInterceptor")
public class ProfileInterceptor implements HandlerInterceptor {
	private List<String> ignoreUrls;
	@Autowired
	private SystemConfig systemConfig;
	@Autowired
	private UserService userService;
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
		Long userId = (Long) request.getSession().getAttribute(Constants.USER_TOKEN.toString());
		if (null == userId) {
			String cookie = CookieUtil.readCookie(CookiePath.FOLLOWER_COOKIE_PATH.name(), request, response);
			if(null == cookie){
				response.sendRedirect(request.getContextPath()+"/profile/login.html");
				return false;
			}
			String data = DESUtil.decrypt(cookie, systemConfig.getAuthKey());
			String[] items = data.split(":");
			if(items.length !=3){
				response.sendRedirect(request.getContextPath()+"/profile/login.html");
				return false;
			}
			EntityResult result = this.userService.login(items[0], items[1], items[2]);
			if (result.isSuccess()) {
				User user = (User) result.getData();
				request.getSession().setAttribute(Constants.USER_TOKEN.name(), user.getId());
				request.getSession().setAttribute(Constants.USER_NAME.name(), user.getUsername());
				return true;
			} else {
				response.sendRedirect(request.getContextPath()+"/profile/login.html");
				return false;
			}
		} else {
			return true;
		}
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
