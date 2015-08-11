package com.hoteam.wolf.controller.mobile;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.EntityResult;
import com.hoteam.wolf.domain.News;
import com.hoteam.wolf.service.NewsService;

@Controller
@RequestMapping("/mobile/news")
public class MobileNewsController {

	private static final int PAGE_ROWS = 20;
	private static Logger logger = Logger.getLogger(MobileNewsController.class);

	@Autowired
	private NewsService newsService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/list")
	@ResponseBody
	public EntityResult list(Integer pages, String category) {
		if (null == pages) {
			pages = 1;
		}
		try {
			News news = new News();
			if (null != category && StringUtils.hasText(category)) {
				news.setCategory(category);
			}
			List<News> boxs = (List<News>) this.newsService.pagination(news, pages, PAGE_ROWS).getRows();
			return new EntityResult(true, "success", boxs);
		} catch (Exception e) {
			logger.error("load mobile news exception:", e);
			return new EntityResult(false, "load news error!", null);
		}
	}

	@RequestMapping("/detail")
	@ResponseBody
	public EntityResult detail(Long id) {
		try {
			News news = this.newsService.loadNews(id);
			if (null != news) {
				return new EntityResult(true, "success", news);
			} else {
				return new EntityResult(false, "新闻不存在", null);
			}
		} catch (Exception e) {
			logger.error("mobile load news error:", e);
			return new EntityResult(false, "加载失败！", null);
		}
	}
}
