package com.hoteam.wolf.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 邮件服务器配置
 * 
 * @author mingwei.dmw
 *
 */
@Component("mailConfig")
public class MailConfig {

	/**
	 * 账号
	 */
	@Value("${mail.account}")
	private String account;
	/**
	 * 密码
	 */
	@Value("${mail.password}")
	private String password;
	/**
	 * 邮件服务器地址
	 */
	@Value("${mail.server}")
	private String server;
	/**
	 * 邮件服务器协议
	 */
	@Value("${mail.protocol}")
	private String protocol;
	/**
	 * 邮件服务器端口
	 */
	@Value("${mail.port}")
	private String port;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public MailConfig(String account, String password, String server, String protocol, String port) {
		super();
		this.account = account;
		this.password = password;
		this.server = server;
		this.protocol = protocol;
		this.port = port;
	}
	public MailConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

}
