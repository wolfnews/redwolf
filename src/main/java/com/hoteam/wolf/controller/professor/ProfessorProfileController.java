package com.hoteam.wolf.controller.professor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.config.SystemConfig;
import com.hoteam.wolf.common.enums.CookiePath;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.service.BoxService;
import com.hoteam.wolf.service.ProfessorService;
import com.hoteam.wolf.utils.CookieUtil;
import com.hoteam.wolf.utils.DESUtil;

@Controller
@RequestMapping("/professor/profile")
public class ProfessorProfileController {

	private static Logger logger = Logger.getLogger(ProfessorProfileController.class);
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private BoxService boxService;
	@Autowired
	private SystemConfig systemConfig;

	@RequestMapping("/login")
	@ResponseBody
	public Result login(String username, String password, HttpSession session,HttpServletResponse response) {
		try {
			Professor professor = this.professorService.login(username, password);
			if (null == professor) {
				return new Result(false, "用户名或密码错误");
			} else {
				session.setAttribute(Constants.PROFESSOR_TOKEN.name(), professor.getId());
				session.setAttribute(Constants.PROFESSOR_NAME.name(), professor.getUsername());
				String cookieValue = DESUtil.encrypt(username+":"+password, systemConfig.getAuthKey());
				CookieUtil.saveCookie(CookiePath.PROFESSOR_COOKIE_PATH.name(), cookieValue, response);
				return new Result(true, "登录成功！");
			}
		} catch (Exception e) {
			logger.error("professor login error:", e);
			return new Result(false, "服务器异常");
		}
	}
	@RequestMapping("/refresh")
	@ResponseBody
	public EntityResult refresh(HttpSession session ){
		Long professorId = (Long) session.getAttribute(Constants.PROFESSOR_TOKEN.toString());
		Box box = new Box();
		box.setAuthor(professorId);
		try {
			List<Box> boxs = this.boxService.queryForSimpleList(box, 1, 10);
			return new EntityResult(true, "success", boxs);
		} catch (Exception e) {
			logger.error("refresh professor latest box error:",e);
			return new EntityResult(false, e.getMessage(), null);
		}
	}
	@RequestMapping("/logout.html")
	public RedirectView logout(HttpSession session,HttpServletRequest request){
		session.removeAttribute(Constants.PROFESSOR_NAME.toString());
		session.removeAttribute(Constants.PROFESSOR_TOKEN.toString());
		return new RedirectView(request.getContextPath()+"/professor/login.html");
	}
}
