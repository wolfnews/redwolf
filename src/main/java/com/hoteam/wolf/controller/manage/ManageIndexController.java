package com.hoteam.wolf.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage")
public class ManageIndexController {

	@RequestMapping("")
	public ModelAndView index() {
		return new ModelAndView("manager/index");
	}

	@RequestMapping("/index.html")
	public ModelAndView main() {
		return new ModelAndView("manager/index");
	}

	@RequestMapping("/login.html")
	public ModelAndView loginHtml() {
		return new ModelAndView("manager/login");
	}

	@RequestMapping("/box.html")
	public ModelAndView notice() {
		return new ModelAndView("manager/box/index");
	}

	@RequestMapping("/user.html")
	public ModelAndView user() {
		return new ModelAndView("manager/user/index");
	}

	@RequestMapping("/activity.html")
	public ModelAndView activity() {
		return new ModelAndView("manager/activity/index");
	}

	@RequestMapping("/professor.html")
	public ModelAndView professor() {
		return new ModelAndView("manager/professor/index");
	}

	@RequestMapping("/message.html")
	public ModelAndView message() {
		return new ModelAndView("manager/message");
	}

	@RequestMapping("/news.html")
	public ModelAndView news() {
		return new ModelAndView("manager/news/index");
	}

	@RequestMapping("/news/add.html")
	public ModelAndView addNews() {
		return new ModelAndView("manager/news/add");
	}

	@RequestMapping("/rss.html")
	public ModelAndView rss() {
		return new ModelAndView("manager/rss");
	}

	@RequestMapping("/subscribe.html")
	public ModelAndView subscribe() {
		return new ModelAndView("manager/subscribe");
	}

	@RequestMapping("/order.html")
	public ModelAndView pay() {
		return new ModelAndView("manager/order");
	}

	@RequestMapping("/item.html")
	public ModelAndView item() {
		return new ModelAndView("manager/item");
	}
	
	@RequestMapping("/manager.html")
	public ModelAndView manager() {
		return new ModelAndView("manager/manager/index");
	}

	@RequestMapping("/manager/add.html")
	public ModelAndView addManager() {
		return new ModelAndView("manager/manager/add");
	}

	@RequestMapping("/professor/add.html")
	public ModelAndView addProfessor() {
		return new ModelAndView("manager/professor/add");
	}

	@RequestMapping("/activity/add.html")
	public ModelAndView addActivity() {
		ModelAndView mav = new ModelAndView("manager/activity/add");
		return mav;
	}
}
