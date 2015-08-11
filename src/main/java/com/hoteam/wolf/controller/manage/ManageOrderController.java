package com.hoteam.wolf.controller.manage;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.GridBean;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.RechargeRecord;
import com.hoteam.wolf.service.RechargeService;

@Controller
@RequestMapping("/manage/order")
public class ManageOrderController {

	private static Logger logger = Logger.getLogger(ManageOrderController.class);
	@Autowired
	private RechargeService rechargeService;

	@RequestMapping("/list")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, String type, HttpSession session) {
		try {
			RechargeRecord record = new RechargeRecord();
			return rechargeService.pagination(record, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<RechargeRecord>());
		}
	}
	@RequestMapping("/remove")
	@ResponseBody
	public Result delOrder(Long id) {
		boolean success = this.rechargeService.removeRechargeRecord(id);
		return new Result(success, new Boolean(success).toString());
	}
}
