package com.hoteam.wolf.controller.mobile;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.service.SmsService;
import com.hoteam.wolf.utils.CodeTool;
import com.hoteam.wolf.utils.IpTools;

@Controller
@RequestMapping("/sms")
public class SmsController {

	private static Logger logger = Logger.getLogger(SmsController.class);
	@Autowired
	private SmsService smsService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/send/{mobile}/{code}")
	@ResponseBody
	public Result send(HttpServletRequest request, @PathVariable String mobile, @PathVariable String code) {
		if (!StringUtils.hasText(code)) {
			logger.warn("N-V-C");
			return new Result(false, "请输入图片中的验证码");
		}
		String ip = IpTools.parseIp(request);// 获取访问的IP地址
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(600);
		String sessionCode = (String) session.getAttribute(ip + "_VCODE");
		if (!StringUtils.hasText(sessionCode)) {// 系统中不存在此IP的验证码
			logger.warn("N-I-P-C");
			return new Result(false, "系统异常！请刷新界面后再进行注册！");
		}
		if (!code.equalsIgnoreCase(sessionCode)) {// 系统中此IP的验证码和提交发送短信的验证码不一致
			logger.warn("V-C-E");
			return new Result(false, "验证码错误！请重新输入！");
		}
		// 获取系统中的验证码信息
		Map<String, String> codeMap = (Map<String, String>) session.getAttribute(ip);
		String smscode = CodeTool.randomCode(6);
		String content = "欢迎注册！您的验证码为:" + smscode + "。验证码10分钟内有效！请及时输入！【牛股会】";
		Result result = null;

		if (null == codeMap || codeMap.isEmpty()) {// 说明当前IP没有发送验证码，需要发送
			logger.info("N-C-S");
			codeMap = new HashMap<String, String>();
			result = smsService.sendMessage(mobile, content);
			if (result.isSuccess()) {// 发送成功后把验证码map对象更新到session中
				logger.info("S-M-S");
				codeMap.put(mobile, smscode);
				session.setAttribute(ip, codeMap);
			}
		} else {// session中存在验证码map
			logger.info("C-E-S");
			if (codeMap.keySet().contains(mobile)) {// 当前手机号码的验证短信已经发送，不需要再次发送
				logger.warn("C-A-S");
				return new Result(true, "验证码已发送，请注意查收！");
			} else {// 当前手机号码不存在于map中，需要发送验证码。
				logger.info("M-N-E-S");
				result = smsService.sendMessage(mobile, content);
				if (result.isSuccess()) {
					logger.info("S-M-S");
					codeMap.put(mobile, smscode);
					session.setAttribute(ip, codeMap);
				}
			}
		}
		return result;
	}

}
