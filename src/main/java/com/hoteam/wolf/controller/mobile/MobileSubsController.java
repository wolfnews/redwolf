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
@RequestMapping("/mobile/subs")
public class MobileSubsController {
	private static Logger logger = Logger.getLogger(MobileSubsController.class);
	@Autowired
	private UserService userService;

	@RequestMapping("/prof/{user}/{professor}/{group}/{time}")
	@ResponseBody
	public Result subsProfessor(@PathVariable Long user, @PathVariable Long professor, @PathVariable Long group,
			@PathVariable int time) {
		try {
			return this.userService.subscribeProfessor(user, professor, group, time);
		} catch (Exception e) {
			logger.error("user subscribe professor exception:", e);
			return new Result(false, "Server Exception");
		}
	}
}
