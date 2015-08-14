package com.hoteam.wolf.controller.mobile;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoteam.wolf.common.Constants;
import com.hoteam.wolf.common.Result;
import com.hoteam.wolf.service.SmsService;
import com.hoteam.wolf.utils.CodeTool;

@Controller
@RequestMapping("/sms")
public class SmsController {

	@Autowired
	private SmsService smsService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/send/{mobile}")
	@ResponseBody
	public Result send(HttpSession session, @PathVariable String mobile) {
		Map<String, String> codeMap = (Map<String, String>) session.getAttribute(Constants.CODE_MAP.name());
		Result result = null;
		String code = CodeTool.randomCode(6);
		String content = "欢迎注册！您的验证码为:" + code + "。验证码10分钟内有效！请及时输入！【牛股会】";
		if (null == codeMap || codeMap.isEmpty()) {// session中不存在，需要重新发送
			codeMap = new HashMap<String, String>();
			result = smsService.sendMessage(mobile, content);
			if (result.isSuccess()) {// 发送成功后把验证码map对象更新到session中
				codeMap.put(mobile, code);
				session.setAttribute(Constants.CODE_MAP.name(), codeMap);
			}
		} else {// session中存在验证码map
			if (codeMap.keySet().contains(mobile)) {// 当前手机号码的验证短信已经发送，不需要再次发送
				return new Result(true, "验证码已发送，请注意查收！");
			} else {// 当前手机号码不存在于map中，需要发送验证码。
				result = smsService.sendMessage(mobile, content);
				if (result.isSuccess()) {
					codeMap.put(mobile, code);
					session.setAttribute(Constants.CODE_MAP.name(), codeMap);
				}
			}
		}
		return result;
	}
	
	
}
