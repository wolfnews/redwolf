package com.hoteam.wolf.controller.profile;

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
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.enums.BoxStatus;
import com.hoteam.wolf.common.vo.ProfessorBean;
import com.hoteam.wolf.common.vo.UserProfile;
import com.hoteam.wolf.domain.Box;
import com.hoteam.wolf.domain.Comment;
import com.hoteam.wolf.domain.SubscribeGroup;
import com.hoteam.wolf.service.BoxService;
import com.hoteam.wolf.service.MessageService;
import com.hoteam.wolf.service.ProfessorService;
import com.hoteam.wolf.service.UserBoxService;
import com.hoteam.wolf.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileIndexController {

	private static final int PAGE_ROWS = 20;
	private static Logger logger = Logger.getLogger(ProfileIndexController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private BoxService boxService;
	@Autowired
	private UserBoxService userBoxService;
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private MessageService messageService;

	@RequestMapping("")
	public ModelAndView defaultHtml() {
		return new ModelAndView("profile/index");
	}

	@RequestMapping("/index.html")
	public ModelAndView index(HttpSession session) {
		ModelAndView mav = new ModelAndView("profile/index");
		Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.toString());
		try {
			mav.addObject("profile", this.userService.profile(userId));
			Box box = new Box();
			box.setStatus(BoxStatus.NORMAL.toString());
			List<Box> boxs = this.boxService.queryForDetailList(box, 1, PAGE_ROWS);
			logger.debug(boxs.toString());
			mav.addObject("boxs", JSONArray.toJSONString(boxs, SerializerFeature.WriteDateUseDateFormat));
			mav.addObject("currentPage", 1);
		} catch (Exception e) {
			mav.addObject("profile", new UserProfile(null, null, 0l, 0l, 0l));
			logger.error("Load user profile exception:", e);
		}
		return mav;
	}

	@RequestMapping("/rss.html")
	public ModelAndView rss() {
		return new ModelAndView("profile/rss");
	}

	@RequestMapping("/subscribe.html")
	public ModelAndView subscribe(Long author,Long box) {
		ModelAndView mav =  new ModelAndView("profile/subscribe");
		mav.addObject("box", box);
		try {
			ProfessorBean professor = this.professorService.loadDetail(author);
			List<SubscribeGroup> groups = this.professorService.listGroup(author);
			mav.addObject("professor", professor);
			mav.addObject("groups", JSONArray.toJSONString(groups));
		} catch (Exception e) {
			logger.error("get professor exception:",e);
		}
		return mav;
	}

	@RequestMapping("/order.html")
	public ModelAndView pay(HttpSession session,String type) {
		ModelAndView mav = new ModelAndView("profile/order/index");
		if(null == type||type.isEmpty()){
			type="all";
		}
		mav.addObject("type", type);
		return mav;
	}

	@RequestMapping("/recharge.html")
	public ModelAndView recharge(HttpSession session) {
		ModelAndView mav = new ModelAndView("profile/recharge/index");
		Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.toString());
		try {
			mav.addObject("profile", this.userService.profile(userId));
		} catch (Exception e) {
			mav.addObject("profile", new UserProfile(null, null, 0l, 0l, 0l));
			logger.error("Load user profile exception:", e);
		}
		return mav;
	}

	@RequestMapping("/professor.html")
	public ModelAndView professor(HttpSession session,String type) {
		ModelAndView mav = new ModelAndView("profile/professor");
		try {
			Long userId = (Long)session.getAttribute(Constants.USER_TOKEN.toString());
			List<ProfessorBean> professors = new ArrayList<ProfessorBean>();
			if(null == type||type.isEmpty()||"all".endsWith(type)){
				type= "all";
				professors = this.professorService.loadAll();
			}else if("rss".equals(type)){
				professors = this.userService.getRssProfessors(userId);
			}else if("subs".equals(type)){
				professors = this.userService.getSubProfessors(userId);
			}else{
				professors = this.professorService.loadAll();
			}
			mav.addObject("professors", JSONArray.toJSONString(professors, SerializerFeature.WriteDateUseDateFormat));
			mav.addObject("currentPage", 1);
			mav.addObject("rssList", this.userService.getRssProfessorIds(userId));
			mav.addObject("subList", this.userService.getSubProfessorIds(userId));
			mav.addObject("type",type);
		} catch (Exception e) {
			mav.addObject("professors", new ArrayList<ProfessorBean>());
			logger.error("get all professor error:", e);
		}
		return mav;
	}

	@RequestMapping("/subRecord.html")
	public ModelAndView subscribe() {
		return new ModelAndView("profile/sub_record");
	}

	@RequestMapping("/box.html")
	public ModelAndView box(HttpSession session,String type,String author) {
		ModelAndView mav = new ModelAndView("profile/box");
		Long userId = (Long)session.getAttribute(Constants.USER_TOKEN.name());
		GridBean gridBean =null; 
		if(null == type || type.isEmpty() || !("rss".equals(type)||"subs".equals(type))){
			try {
				gridBean = this.boxService.pagination(null, 1, 10);
			} catch (Exception e) {
				gridBean = new GridBean(0, 0, 0, null);
			}
		}else{
			gridBean = this.userBoxService.boxPagination(type, userId, 1, 10);
		}
		mav.addObject("totalPages", gridBean.getTotal());
		mav.addObject("type",type);
		mav.addObject("author",author);
		return mav;
	}

	@RequestMapping("/login.html")
	public ModelAndView loginHtml() {
		return new ModelAndView("profile/login");
	}

	@RequestMapping("/message.html")
	public ModelAndView message(HttpSession session,String category){
		Long user = (Long)session.getAttribute(Constants.USER_TOKEN.name());
		Comment message = new Comment();
		if("send".equalsIgnoreCase(category)){
			message.setSenderId(user);
		}else if("receive".equalsIgnoreCase(category)){
			message.setReceiverId(user);
		}else{
			category="all";
		}
		int total = this.messageService.list(message, 1, 10).getTotal();
		ModelAndView mav = new ModelAndView("profile/message");
		mav.addObject("total",total);
		mav.addObject("type",category);
		return mav;
	}
	@RequestMapping("/cart.html")
	public ModelAndView carts(){
		ModelAndView mav = new ModelAndView("profile/cart");
		return mav;
	}
	
}
