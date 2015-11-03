package com.hoteam.wolf.controller.mobile;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.service.UserService;

@Controller
@RequestMapping("/mobile/rss")
public class MobileRSSController {
	private static Logger logger = Logger.getLogger(MobileRSSController.class);
	@Autowired
	private UserService userService;

	@RequestMapping("/prof/{user}/{professor}")
	@ResponseBody
	public Result rssProfessor(@PathVariable Long user, @PathVariable Long professor) {
		try {
			return this.userService.rssProfessor(user, professor);
		} catch (Exception e) {
			logger.error("user rss professor exception:", e);
			return new Result(false, "Server Exception");
		}
	}
}
