package com.hoteam.wolf.controller.professor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.vo.ProfessorStatBean;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.domain.Comment;
import com.hoteam.wolf.domain.Professor;
import com.hoteam.wolf.domain.SubscribeGroup;
import com.hoteam.wolf.service.BoxService;
import com.hoteam.wolf.service.MessageService;
import com.hoteam.wolf.service.ProfessorService;

@Controller
@RequestMapping("/professor")
public class ProfessorIndexController {

	private static Logger logger = Logger.getLogger(ProfessorIndexController.class);
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private BoxService boxService;
	@Autowired
	private MessageService messageService;


	@RequestMapping("")
	public ModelAndView defaultHtml() {
		return new ModelAndView("professor/index");
	}

	@RequestMapping("/login.html")
	public ModelAndView login() {
		return new ModelAndView("professor/login");
	}

	@RequestMapping("/index.html")
	public ModelAndView index(HttpSession session) {
		ModelAndView mav = new ModelAndView("professor/index");
		Long token = (Long) session.getAttribute(Constants.PROFESSOR_TOKEN.toString());
		if (null == token) {
			mav.addObject("isLogin", false);
		} else {
			try {
				Professor professor = this.professorService.load(token);
				if (null == professor) {
					mav.addObject("isLogin", false);
				} else {
					Box box = new Box();
					box.setAuthor(token);
					List<Box> boxs = this.boxService.queryForSimpleList(box, 1,10);
					ProfessorStatBean accounts = professorService.statisProfessor(token);
					mav.addObject("boxs", JSONArray.toJSONString(boxs, SerializerFeature.WriteDateUseDateFormat));
					mav.addObject("isLogin", true);
					mav.addObject("professor", professor);
					mav.addObject("accounts", accounts);
				}
			} catch (Exception e) {
				mav.addObject("isLogin", false);
				logger.error("professor index exception:", e);
			}
		}
		return mav;
	}

	@RequestMapping("/article.html")
	public ModelAndView article() {
		return new ModelAndView("professor/article/index");
	}

	@RequestMapping("/box.html")
	public ModelAndView box() {
		return new ModelAndView("professor/box/index");
	}

	@RequestMapping("/rss.html")
	public ModelAndView rss() {
		return new ModelAndView("professor/rss/index");
	}

	@RequestMapping("/subscribe.html")
	public ModelAndView subscribe() {
		return new ModelAndView("professor/subscribe/index");
	}

	@RequestMapping("/box/add.html")
	public ModelAndView addBox(HttpSession session) {
		ModelAndView mav = new ModelAndView("professor/box/add");
		Long professorId = (Long) session.getAttribute(Constants.PROFESSOR_TOKEN.toString());
		try {
			List<SubscribeGroup> groups = this.professorService.listGroup(professorId);
			mav.addObject("groups", JSONArray.toJSONString(groups, SerializerFeature.WriteDateUseDateFormat));
		} catch (Exception e) {
			logger.error("get professor subscribe group error:", e);
			mav.addObject("groups", new ArrayList<SubscribeGroup>());
		}
		return mav;
	}
	
	@RequestMapping("/message.html")
	public ModelAndView message(HttpSession session,String category){
		Long user = (Long)session.getAttribute(Constants.PROFESSOR_TOKEN.name());
		Comment message = new Comment();
		if("send".equalsIgnoreCase(category)){
			message.setSenderId(user);
		}else if("receive".equalsIgnoreCase(category)){
			message.setReceiverId(user);
		}else{
			category="all";
		}
		int total = this.messageService.list(message, 1, 10).getTotal();
		ModelAndView mav = new ModelAndView("professor/message");
		mav.addObject("total",total);
		mav.addObject("type",category);
		return mav;
	}
}
