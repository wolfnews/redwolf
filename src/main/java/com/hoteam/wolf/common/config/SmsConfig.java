package com.hoteam.wolf.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 短信服务配置
 * 
 * @author mingwei.dmw
 *
 */
@Component("smsConfig")
public class SmsConfig {

	/**
	 * 短信服务地址
	 */
	@Value("${sms.host}")
	private String host;
	/**
	 * 短信服务密码
	 */
	@Value("${sms.password}")
	private String password;
	/**
	 * 短信服务用户名
	 */
	@Value("${sms.username}")
	private String username;
	/**
	 * 短信服务用户ID
	 */
	@Value("${sms.userId}")
	private String userId;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public SmsConfig(String host, String password, String username, String userId) {
		super();
		this.host = host;
		this.password = password;
		this.username = username;
		this.userId = userId;
	}

	public SmsConfig() {
		super();
	}

}
