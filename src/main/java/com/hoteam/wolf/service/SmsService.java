package com.hoteam.wolf.service;

import com.hoteam.wolf.common.Result;

/**
 * 短信服务
 * @author mingwei.dmw
 *
 */
public interface SmsService { 
	/**发送验证码
	 * @param mobile
	 * @param content
	 * @return
	 */
	public Result sendMessage(String mobile,String content);
}
