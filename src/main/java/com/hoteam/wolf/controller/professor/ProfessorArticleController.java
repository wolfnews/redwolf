package com.hoteam.wolf.controller.professor;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.News;
import com.hoteam.wolf.service.NewsService;

@Controller
@RequestMapping("/professor/article")
public class ProfessorArticleController {

	private static Logger logger = Logger.getLogger(ProfessorArticleController.class);
	@Autowired
	private NewsService articleService;
	
	@RequestMapping("/pagination")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, HttpSession session) {
		try {
			return this.articleService.pagination(null, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<News>());
		}
	}
	
}
