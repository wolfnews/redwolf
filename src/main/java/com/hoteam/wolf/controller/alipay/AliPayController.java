package com.hoteam.wolf.controller.alipay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.hoteam.wolf.alipay.AlipayConfig;
import com.hoteam.wolf.alipay.AlipayNotify;
import com.hoteam.wolf.alipay.AlipaySubmit;
import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.config.SystemConfig;
import com.hoteam.wolf.common.enums.PayCategory;
import com.hoteam.wolf.domain.RechargeRecord;
import com.hoteam.wolf.service.RechargeService;
import com.hoteam.wolf.utils.DateUtil;

@Controller
@RequestMapping("/alipay")
public class AliPayController {

	private static Logger logger =Logger.getLogger(AliPayController.class);
	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private SystemConfig systemConfig;
	 @RequestMapping("/index.html")
	public ModelAndView index(HttpSession session, int amount,String way,String sn,String content) {
		ModelAndView mav = new ModelAndView("alipay/index");
		String username = (String) session.getAttribute(Constants.USER_NAME.toString());
		Long userId = (Long)session.getAttribute(Constants.USER_TOKEN.toString());
		if(null == sn || sn.isEmpty()){
			sn = DateUtil.getRecordSn();
		}
		if(null == content || content.isEmpty()){
			content = "牛股会用户【" + username + "】充值订单";
		}
		mav.addObject("orderSn", sn);
		mav.addObject("orderMoney", amount);
		mav.addObject("orderName",content );
		RechargeRecord record = new RechargeRecord(sn, userId, new BigDecimal(amount), way, PayCategory.valueOf(way).toString(), false, null, null,content);
		record = this.rechargeService.saveRecord(record);
		return mav;
	}

	@RequestMapping("/pay.html")
	public RedirectView pay(HttpServletRequest request,String sn, String subject, String body, String money) {
		RedirectView redirectView = new RedirectView(systemConfig.getHost()+request.getContextPath()+"/alipay/return_url.html");
//		Map<String,Object> attributes = new HashMap<String, Object>();
		// 支付类型
		String payment_type = "1";
		// 必填，不能修改
		// 服务器异步通知页面路径
		String notify_url = systemConfig.getHost()+request.getContextPath()+"/alipay/notify_url.html";
		// 需http://格式的完整路径，不能加?id=123这类自定义参数
		// 页面跳转同步通知页面路径
		String return_url = systemConfig.getHost()+request.getContextPath()+"/alipay/return_url.html";
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		// 商户订单号
		String out_trade_no;
		//订单金额
		String total_fee;
		try {
			out_trade_no = new String(sn.getBytes("ISO-8859-1"), "UTF-8");
			subject = new String(subject.getBytes("ISO-8859-1"), "UTF-8");
			total_fee = new String(money.getBytes("ISO-8859-1"), "UTF-8");
			body = new String(body.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("parse String exception：",e);
//			attributes.put("submit", false);
//			attributes.put("message", "订单提交失败！");
//			redirectView.setAttributesMap(attributes);
			return redirectView;
		}
		// 防钓鱼时间戳
		String anti_phishing_key = "";
		// 若要使用请调用类文件submit中的query_timestamp函数
		// 客户端的IP地址
		String exter_invoke_ip = "";
		// 非局域网的外网IP地址，如：221.0.0.1
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		Map<String, String> sPara = AlipaySubmit.buildRequestPara(sParaTemp);
		try {
			AlipaySubmit.buildRequest("", "", sPara);
//			attributes.put("submit", true);
//			attributes.put("message", "订单提交成功！");
//			redirectView.setAttributesMap(attributes);
			return redirectView;
		} catch (Exception e) {
			logger.error("Call Alipay exception",e);
//			attributes.put("submit", false);
//			attributes.put("message", "订单提交失败！");
//			redirectView.setAttributesMap(attributes);
			return redirectView;
		}
	}

	@RequestMapping("/api.html")
	public ModelAndView api() {
		return new ModelAndView("alipay/api");
	}

	@RequestMapping("/notify_url.html")
	public ModelAndView notifyUrl() {
		return new ModelAndView("alipay/notify_url");
	}

	@RequestMapping("/return_url.html")
	public ModelAndView returnUrl(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("alipay/return_url");
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();

		if(requestParams.isEmpty()){
			return mav;
		}
		try {
			for (Iterator<String> iterator = requestParams.keySet().iterator(); iterator.hasNext();) {
				String name = (String) iterator.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			mav.addObject("out_trade_no", out_trade_no);
			mav.addObject("trade_no", trade_no);
			mav.addObject("trade_status", trade_status);
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			// 计算得出通知验证结果
			boolean verify_result = AlipayNotify.verify(params);
			if(verify_result){
				mav.addObject("success", true);
				mav.addObject("message","付款成功");
			}else{
				mav.addObject("success", false);
				mav.addObject("message","付款失败");
			}
		} catch (UnsupportedEncodingException e) {
			mav.addObject("success", false);
			mav.addObject("message","付款失败");
			logger.error("get return info cause UnsupportedEncodingException", e);
		}
		return mav;
	}
}
