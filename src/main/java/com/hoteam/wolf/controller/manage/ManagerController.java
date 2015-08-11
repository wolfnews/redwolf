package com.hoteam.wolf.controller.manage;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.Manager;
import com.hoteam.wolf.service.ManagerService;

@Controller
@RequestMapping("/manage/manager")
public class ManagerController {

	private static Logger logger = Logger.getLogger(ManagerController.class);
	@Autowired
	private ManagerService managerService;

	@RequestMapping("/add")
	@ResponseBody
	public Result addManager(String username, String password, String mobile, String summary, int level) {
		Manager manager = new Manager(username, password, mobile, summary, level);
		try {
			this.managerService.addNew(manager);
			return new Result(true, "add manager success");
		} catch (Exception e) {
			logger.error("add manager excetpion:" + e.getMessage());
			return new Result(false, e.getMessage());
		}
	}

	@RequestMapping("/pagination")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, Manager manager) {
		try {
			return this.managerService.pagination(manager, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<Manager>());
		}
	}

	@RequestMapping("/remove")
	@ResponseBody
	public Result remove(Long managerId) {
		try {
			this.managerService.remove(managerId);
			return new Result(true, "success");
		} catch (Exception e) {
			logger.error("remove manager exception:", e);
			return new Result(false, e.getMessage());
		}
	}

	@RequestMapping("/login")
	@ResponseBody
	public Result login(String username, String password, HttpSession session) {
		try {
			Manager manager = this.managerService.login(username, password);
			if (null == manager) {
				return new Result(false, "用户名或密码错误");
			} else {
				session.setAttribute(Constants.MANAGER_TOKEN.toString(), manager.getId());
				session.setAttribute(Constants.MANAGER_NAME.toString(), manager.getUsername());
				return new Result(true, "登录成功！");
			}
		} catch (Exception e) {
			logger.error("manager login error:", e);
			return new Result(false, "服务器异常");
		}
	}

	@RequestMapping("/logout.html")
	public RedirectView logout(HttpSession session, HttpServletRequest request) {
		session.removeAttribute(Constants.MANAGER_NAME.toString());
		session.removeAttribute(Constants.MANAGER_TOKEN.toString());
		return new RedirectView(request.getContextPath() + "/manage/login.html");
	}

	@RequestMapping("/detail")
	@ResponseBody
	public EntityResult detail(Long managerId) {
		try {
			Manager manager = this.managerService.load(managerId);
			if (null == manager) {
				return new EntityResult(false, "none manager exists", null);
			} else {
				return new EntityResult(true, "success", manager);
			}
		} catch (Exception e) {
			return new EntityResult(false, e.getMessage(), null);
		}
	}

}
