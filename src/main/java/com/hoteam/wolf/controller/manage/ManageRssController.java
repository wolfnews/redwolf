package com.hoteam.wolf.controller.manage;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.vo.RssRecordBean;
import com.hoteam.wolf.service.RssService;

@Controller
@RequestMapping("/manage/rss")
public class ManageRssController {

	private static Logger logger = Logger.getLogger(ManageRssController.class);
	@Autowired
	private RssService rssService;
	
	@RequestMapping("/list")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		try {
			return rssService.list(page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<RssRecordBean>());
		}
	}
}
