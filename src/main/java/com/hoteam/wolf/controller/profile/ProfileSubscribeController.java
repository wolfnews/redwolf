package com.hoteam.wolf.controller.profile;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.domain.SubscribeRecord;
import com.hoteam.wolf.service.SubscribeService;

@Controller
@RequestMapping("/profile/sr")
public class ProfileSubscribeController {

	private static Logger logger = Logger.getLogger(ProfileSubscribeController.class);
	@Autowired
	private SubscribeService subscribeService;
	
	@RequestMapping("/list")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, HttpSession session) {
		try {
			Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.toString());
			SubscribeRecord record = new SubscribeRecord();
			record.setUserId(userId);
			return subscribeService.pagination(record, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<SubscribeRecord>());
		}
	}
}
