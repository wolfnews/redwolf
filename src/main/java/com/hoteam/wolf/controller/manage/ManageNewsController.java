package com.hoteam.wolf.controller.manage;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.News;
import com.hoteam.wolf.service.NewsService;

@Controller
@RequestMapping("/manage/news")
public class ManageNewsController {

	private static Logger logger = Logger.getLogger(ManageNewsController.class);
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

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result addNews(String title, String tag, String category, String summary, String content) {
		News news = new News(title, tag, summary, content, 0, category);
		try {
			newsService.saveNews(news);
			return new Result(true, "add news success");
		} catch (Exception e) {
			logger.error("add news error:" + e.getMessage());
			return new Result(false, e.getMessage());
		}
	}

	@RequestMapping("/remove")
	@ResponseBody
	public Result remove(Long id) {
		try {
			return newsService.removeNews(id);
		} catch (Exception e) {
			logger.error("remove news exception:", e);
			return new Result(false, e.getMessage());
		}
	}
}
