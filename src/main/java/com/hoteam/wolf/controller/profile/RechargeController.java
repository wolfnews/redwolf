package com.hoteam.wolf.controller.profile;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.utils.DateUtil;

@Controller
@RequestMapping("/profile/recharge")
public class RechargeController {
	@RequestMapping("confirm.html")
	public ModelAndView confirm(HttpSession session, int amount) {
		ModelAndView mav = new ModelAndView("profile/recharge/confirm");
		String username = (String) session.getAttribute(Constants.USER_NAME.toString());
		Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.toString());
		String sn = DateUtil.getRecordSn();
		String content = "牛股会用户【" + username + "】充值订单";
		mav.addObject("orderSn", sn);
		mav.addObject("orderMoney", amount);
		mav.addObject("orderName", content);
		mav.addObject("orderUser", userId);
		return mav;
	}

}
