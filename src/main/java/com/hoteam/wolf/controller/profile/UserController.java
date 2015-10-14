package com.hoteam.wolf.controller.profile;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSONObject;
import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.common.config.SystemConfig;
import com.hoteam.wolf.common.enums.CookiePath;
import com.hoteam.wolf.domain.User;
import com.hoteam.wolf.service.UserService;
import com.hoteam.wolf.utils.CookieUtil;
import com.hoteam.wolf.utils.DESUtil;

@Controller
@RequestMapping("/profile/user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);

	private static enum ResultMessage {
		NO_ACCOUNT, LESS_ONE_DAY, NO_MORE_GRADE, SUCCESS
	};

	@Autowired
	private UserService userService;
	@Autowired
	private SystemConfig systemConfig;
	
	@RequestMapping("/identify.html")
	public ModelAndView identify(String identify_key) {
		ModelAndView mav = new ModelAndView("profile/identify");
		Result result;
		try {
			result = this.userService.identify(identify_key);
			mav.addObject("username",DESUtil.decrypt(identify_key, systemConfig.getAuthKey()));
		} catch (Exception e) {
			logger.error("Identify user Exception:", e);
			result = new Result(false, "系统异常");
		}
		mav.addObject("result", JSONObject.toJSONString(result));
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Result register(HttpSession session,String username, String password, String mobile, String code, String email) {
		Map<String,String> codeMap = (Map<String, String>) session.getAttribute(Constants.CODE_MAP.name());
		if(null == codeMap|| codeMap.isEmpty() || !codeMap.keySet().contains(mobile)){
			logger.error("注册用户失败：短信验证码失效！");
			return new Result(false, "验证码已经失效！");
		}else{
			if(code.equals(codeMap.get(mobile))){
				logger.info("短信验证码验证成功！可以注册用户！");
				User user = new User(username, password, mobile, "", email, 1, true);
				try {
					EntityResult result = this.userService.addUser(user);
					logger.info("用户注册成功！");
					return new Result(result.isSuccess(), result.getMessage());
				} catch (Exception e) {
					logger.error("user register error:" + e.getMessage());
					return new Result(false, e.getMessage());
				}
			}else{
				return new Result(false, "注册失败：短信验证码错误！");
			}
		}
	}
	@RequestMapping(value = "/retrieve")
	@ResponseBody
	public Result retrievePassword(String email) {
		logger.info("email="+email);
		return this.userService.findUserPassword(email);
	}
	@RequestMapping("/login")
	@ResponseBody
	public Result login(String username, String password, String mobile, HttpSession session,HttpServletResponse response) {
		try {
			EntityResult result = this.userService.login(mobile, username, password);
			if (result.isSuccess()) {
				User user = (User) result.getData();
				session.setAttribute(Constants.USER_TOKEN.name(), user.getId());
				session.setAttribute(Constants.USER_NAME.name(), user.getUsername());
				String cookieValue = DESUtil.encrypt(mobile+":"+username+":"+password, systemConfig.getAuthKey());
				CookieUtil.saveCookie(CookiePath.FOLLOWER_COOKIE_PATH.name(), cookieValue, response);
				return new Result(true, "登录成功！");
			} else {
				return new Result(false, result.getMessage());
			}
		} catch (Exception e) {
			logger.error("professor login error:", e);
			return new Result(false, "服务器异常");
		}
	}

	@RequestMapping("/logout.html")
	public RedirectView logout(HttpSession session, HttpServletRequest request) {
		session.removeAttribute(Constants.USER_NAME.toString());
		session.removeAttribute(Constants.USER_TOKEN.toString());
		return new RedirectView(request.getContextPath() + "/index.html");
	}

	@RequestMapping("/rssProf")
	@ResponseBody
	public Result rssProfessor(Long professorId, HttpSession session) {
		Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.toString());
		try {
			return this.userService.rssProfessor(userId, professorId);
		} catch (Exception e) {
			return new Result(false, "关注讲师失败！");
		}
	}

	@RequestMapping("/subsProf")
	@ResponseBody
	public Result subscribeProfessor(Long professor, Long group, HttpSession session, int time) {
		Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.toString());
		try {
			Result result = this.userService.subscribeProfessor(userId, professor, group, time);
			transferResult(result);
			return result;
		} catch (Exception e) {
			return new Result(false, "订阅讲师失败！");
		}
	}

	@RequestMapping("/isLogin")
	@ResponseBody
	public Result isLogin(HttpSession session){
		Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.toString());
		if(null == userId){
			return new Result(true, "not login");
		}else{
			return new Result(true, "login");
		}
	}
	private void transferResult(Result result) {
		String message = "";
		switch (ResultMessage.valueOf(result.getMessage())) {
		case LESS_ONE_DAY:
			message = "订阅时间小于一天";
			break;
		case NO_ACCOUNT:
			message = "用户账户缺失！找客服解决";
			break;
		case NO_MORE_GRADE:
			message = "积分余额不足";
			break;
		case SUCCESS:
			message = "订阅成功";
			break;
		default:
			message = "未知信息";
			break;
		}
		result.setMessage(message);
	}
}
