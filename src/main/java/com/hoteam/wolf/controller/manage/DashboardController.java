package com.hoteam.wolf.controller.manage;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.vo.VisitLineBean;
import com.hoteam.wolf.dao.VisitCountDao;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private static Logger logger = Logger.getLogger(DashboardController.class);
	@Autowired
	private VisitCountDao visitCountDao;

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
}
