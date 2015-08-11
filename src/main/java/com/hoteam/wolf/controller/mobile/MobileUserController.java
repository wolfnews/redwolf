package com.hoteam.wolf.controller.mobile;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.User;
import com.hoteam.wolf.service.UserService;

@Controller
@RequestMapping("/mobile/user")
public class MobileUserController {

	private static Logger logger = Logger.getLogger(MobileUserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping("/register")
	@ResponseBody
	public EntityResult register(String mobile, String password, String username) {
		if (null == password || password.isEmpty()) {
			return new EntityResult(false, "Empty Password", null);
		} else if (null == mobile || mobile.isEmpty()) {
			return new EntityResult(false, "Empty Mobile", null);
		}
		User user = new User(username, password, mobile, null, "", 1, true);
		try {
			return this.userService.addUser(user);
		} catch (Exception e) {
			logger.error("user register exception:", e);
			return new EntityResult(false, e.getMessage(), null);
		}
	}

	@RequestMapping("/audit")
	@ResponseBody
	public Result audit(String username, String password) {
		try {
			User user = this.userService.load(username, password);
			if (null == user) {
				return new Result(false, "用户名或密码错误！");
			} else {
				return new Result(true, user.getId().toString());
			}
		} catch (Exception e) {
			logger.error("user " + username + "login exception:", e);
			return new Result(false, "登录异常");
		}
	}
}
