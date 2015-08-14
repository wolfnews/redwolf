package com.hoteam.wolf.controller.site;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.vo.ProfessorBean;
import com.hoteam.wolf.domain.Activity;
import com.hoteam.wolf.domain.News;
import com.hoteam.wolf.service.ActivityService;
import com.hoteam.wolf.service.BoxService;
import com.hoteam.wolf.service.NewsService;
import com.hoteam.wolf.service.ProfessorService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private BoxService boxService;
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private ActivityService activityService;

	@RequestMapping("")
	public ModelAndView defaultPage() {
		ModelAndView mav = new ModelAndView("site/index");
		try {
			List<Activity> activitys = this.activityService.hostActivity();
			if (activitys.isEmpty()) {
				mav.addObject("hasAct", false);
			} else {
				mav.addObject("hasAct", true);
				mav.addObject("acts", JSONArray.toJSONString(activitys, SerializerFeature.WriteDateUseDateFormat));
			}
		} catch (Exception e) {
			mav.addObject("hasAct", false);
		}
		return mav;
	}

	@RequestMapping("/index.html")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("site/index");
		try {
			List<Activity> activitys = this.activityService.hostActivity();
			if(activitys.isEmpty()){
				mav.addObject("hasAct", false);
			}else{
				mav.addObject("hasAct",true);
				mav.addObject("acts", JSONArray.toJSONString(activitys, SerializerFeature.WriteDateUseDateFormat));
			}
		} catch (Exception e) {
			mav.addObject("hasAct", false);
		}
		return mav;
	}

	@RequestMapping("/news.html")
	public ModelAndView news() {
		ModelAndView mav = new ModelAndView("site/news");
		GridBean gridBean;
		try {
			gridBean = newsService.pagination(null, 1, 20);
		} catch (Exception e) {
			gridBean = new GridBean(0, 0, 0, new ArrayList<News>());
		}
		mav.addObject("total", gridBean.getTotal());
		return mav;
	}

	@RequestMapping("/box.html")
	public ModelAndView box(Long author) {
		ModelAndView mav = new ModelAndView("site/box");
		if(null == author|| 0l==author){
			mav.addObject("author", 0l);
		}else{
			mav.addObject("author", author);
		}
		GridBean gridBean;
		try {
			gridBean = boxService.pagination(null, 1, 20);
		} catch (Exception e) {
			gridBean = new GridBean(0, 0, 0, new ArrayList<News>());
		}
		mav.addObject("total", gridBean.getTotal());
		return mav;
	}

	@RequestMapping("/professor.html")
	public ModelAndView professor() {
		ModelAndView mav = new ModelAndView("site/professor");
		GridBean gridBean;
		try {
			gridBean = professorService.list(null, 1, 20);
		} catch (Exception e) {
			gridBean = new GridBean(0, 0, 0, new ArrayList<ProfessorBean>());
		}
		mav.addObject("total", gridBean.getTotal());
		return mav;
	}

	@RequestMapping("/register.html")
	public ModelAndView registerHtml() {
		return new ModelAndView("site/register");
	}
}
