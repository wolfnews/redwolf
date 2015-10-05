package com.hoteam.wolf.controller.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hoteam.wolf.service.PayService;

@Controller
@RequestMapping("/profile/pay")
public class ProfilePayController {

	private static Logger logger = Logger.getLogger(ProfilePayController.class);
	@Autowired
	private PayService payService;
	@RequestMapping("/notify.html")
	public ModelAndView notifyUrl(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("profile/order/notify");
		try {
			boolean success = payService.processNotify("TENPAY", request, response);
			mav.addObject("success", success);
		} catch (Exception e) {
			logger.error("处理支付平台通知异常：",e);
			mav.addObject("success", false);
			mav.addObject("reason","订单处理异常！");
		}
		return mav;
	}
	
	@RequestMapping("/return.html")
	public ModelAndView returnUrl(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("profile/order/return");
		try {
			boolean success = payService.processReturn("TENPAY", request, response);
			mav.addObject("success", success);
		} catch (Exception e) {
			logger.error("处理支付平台返回异常：",e);
			mav.addObject("success", false);
			mav.addObject("reason","订单处理异常！");
		}
		return mav;
	}
}
