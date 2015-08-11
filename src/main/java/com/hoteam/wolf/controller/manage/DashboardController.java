package com.hoteam.wolf.controller.manage;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.vo.VisitLineBean;
import com.hoteam.wolf.dao.VisitCountDao;
import com.hoteam.wolf.domain.AppDownload;
import com.hoteam.wolf.service.DownLoadService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private static Logger logger = Logger.getLogger(DashboardController.class);
	@Autowired
	private VisitCountDao visitCountDao;
	@Autowired
	private DownLoadService downLoadService;
	@RequestMapping("/visitCount")
	@ResponseBody
	public VisitLineBean visitCount() {
		try {
			return visitCountDao.visitCount();
		} catch (Exception e) {
			logger.error("visitCount exceptionL:", e);
			return new VisitLineBean(new ArrayList<String>(), new ArrayList<Long>(), new ArrayList<Long>());
		}
	}
	@RequestMapping("/download/list")
	@ResponseBody
	public GridBean downloadList(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows,
			String category,String version){
		AppDownload download = new AppDownload(null, version, category);
		return this.downLoadService.list(download, page, rows);
	}
}
