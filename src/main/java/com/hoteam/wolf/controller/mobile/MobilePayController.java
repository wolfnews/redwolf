package com.hoteam.wolf.controller.mobile;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.service.UserService;

@Controller
@RequestMapping("/mobile/pay")
public class MobilePayController {
	private static Logger logger = Logger.getLogger(MobilePayController.class);
	@Autowired
	private UserService userService;

	@RequestMapping("/recharge")
	@ResponseBody
	public Result recharge(Long user, String category, int money) {
		try {
			return this.userService.recharge(user, money, category);
		} catch (Exception e) {
			logger.error("user recharge exception:", e);
			return new Result(false, "Server Exception");
		}
	}
}
