package com.hoteam.wolf.controller.site;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.News;
import com.hoteam.wolf.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

	private static Logger logger = Logger.getLogger(NewsController.class);
	@Autowired
	private NewsService newsService;

	@RequestMapping("/list")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, News news) {
		try {
			return newsService.pagination(news, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<News>());
		}
	}

	@RequestMapping("/detail.html")
	public ModelAndView news(Long id) {
		ModelAndView mav = new ModelAndView("/site/new_detail");
		try {
			News news = this.newsService.loadNews(id);
			if (null == news) {
				mav.addObject("exist", false);
			} else {
				news.setBrowseTimes(news.getBrowseTimes()+1);
				this.newsService.modifyNews(news);
				mav.addObject("exist", true);
				mav.addObject("news", news);
			}
		} catch (Exception e) {
			mav.addObject("exist", false);
		}
		return mav;
	}
}
