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

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.config.SystemConfig;
import com.hoteam.wolf.common.enums.CookiePath;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.service.ProfessorService;
import com.hoteam.wolf.utils.CookieUtil;
import com.hoteam.wolf.utils.DESUtil;

/**
 * 讲师访问拦截器
 * 
 * @author mingwei.dmw
 *
 */
@Repository("professorInterceptor")
public class ProfessorInterceptor implements HandlerInterceptor {
	private static Logger logger = Logger.getLogger(ProfessorInterceptor.class);
	private List<String> ignoreUrls;
	@Autowired
	private SystemConfig systemConfig;
	@Autowired
	private ProfessorService professorService;

	public void setIgnoreUrls(List<String> ignoreUrls) {
		this.ignoreUrls = ignoreUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
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
		Long professorId = (Long) request.getSession().getAttribute(Constants.PROFESSOR_TOKEN.toString());
		if (null == professorId) {
			String cookie = CookieUtil.readCookie(CookiePath.PROFESSOR_COOKIE_PATH.name(), request, response);
			if (null == cookie) {
				logger.error("professor cookie is null");
				response.sendRedirect(request.getContextPath() + "/professor/login.html");
				return false;
			}
			String data = DESUtil.decrypt(cookie, systemConfig.getAuthKey());
			String[] items = data.split(":");
			if (items.length != 2) {
				logger.error("professor cookie is wrong！");
				response.sendRedirect(request.getContextPath() + "/professor/login.html");
				return false;
			}
			try {
				Professor professor = this.professorService.login(items[0], items[1]);
				if (null == professor) {
					logger.error("professor does not exist!");
					response.sendRedirect(request.getContextPath() + "/professor/login.html");
					return false;
				}
				request.getSession().setAttribute(Constants.PROFESSOR_TOKEN.name(), professor.getId());
				request.getSession().setAttribute(Constants.PROFESSOR_NAME.name(), professor.getUsername());
				logger.info("professor auto login success!");
				return true;
			} catch (Exception e) {
				logger.error("professor auto login exception:", e);
				response.sendRedirect(request.getContextPath() + "/professor/login.html");
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
