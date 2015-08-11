package com.hoteam.wolf.controller.manage;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.User;
import com.hoteam.wolf.service.UserService;

@Controller
@RequestMapping("/manage/user")
public class ManageUserController {

	private static Logger logger = Logger.getLogger(ManageUserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/pagination")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, User user) {
		try {
			return this.userService.pagination(user, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<User>());
		}
	}

	@RequestMapping("/remove")
	@ResponseBody
	public Result remove(Long userId) {
		try {
			this.userService.removeUser(userId);
			return new Result(true, "success");
		} catch (Exception e) {
			logger.error("remove user exception:", e);
			return new Result(false, e.getMessage());
		}
	}

	@RequestMapping("/detail")
	@ResponseBody
	public EntityResult detail(Long userId) {
		try {
			User user = this.userService.load(userId);
			if (null == user) {
				return new EntityResult(false, "用户不存在", null);
			} else {
				return new EntityResult(true, "success", user);
			}
		} catch (Exception e) {
			return new EntityResult(false, e.getMessage(), null);
		}
	}

}
