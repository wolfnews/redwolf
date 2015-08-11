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
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.domain.RechargeRecord;
import com.hoteam.wolf.service.RechargeService;
import com.hoteam.wolf.service.UserService;

@Controller
@RequestMapping("/profile/recharge")
public class RechargeController {

	private static Logger logger = Logger.getLogger(RechargeController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private RechargeService rechargeService;

	@RequestMapping("/recharge")
	@ResponseBody
	public Result recharge(HttpSession session, int money, String category) {
		Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.toString());
		try {
			return this.userService.recharge(userId, money, category);
		} catch (Exception e) {
			logger.error("recharge exception!");
			return new Result(false, "充值失败！");
		}
	}

	@RequestMapping("/delOrder")
	@ResponseBody
	public Result delOrder(Long oid) {
		boolean success = this.rechargeService.removeRechargeRecord(oid);
		return new Result(success, new Boolean(success).toString());
	}
	@RequestMapping("/pagination")
	@ResponseBody
	public GridBean pagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows, String type, HttpSession session) {
		try {
			Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.toString());
			RechargeRecord record = new RechargeRecord();
			record.setUserId(userId);
			if (null != type && "finish".equals(type)) {
				record.setPaid(true);
			}else if(null != type && "unfinish".equals(type)){
				record.setPaid(false);
			}
			return rechargeService.pagination(record, page, rows);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new GridBean(0, 0, 0, new ArrayList<RechargeRecord>());
		}
	}

}
