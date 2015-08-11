package com.hoteam.wolf.utils;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.common.config.MailConfig;

/**
 * 邮件发送客户端
 * 
 * @author mingwei.dmw
 *
 */
@Component("mailClient")
public class MailClient {

	private static Logger logger = Logger.getLogger(MailClient.class);
	private static final String CONFIG_KEY = "WolfNews888";
	@Autowired
	private MailConfig mailConfig;

	private MailConfig config;

	public boolean sendEmail(String target, String title, String content) {
		logger.info("start to send an email...");
		Properties props = new Properties();
		try {
			decryptConfig();
			// 开启debug调试
			props.setProperty("mail.debug", "false");
			// 发送服务器需要身份验证
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.transport.protocol", config.getProtocol());
			props.setProperty("mail.smtp.port", config.getPort());
			props.setProperty("mail.smtp.host", config.getServer());
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.socketFactory.port", config.getPort());
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			SimpleAuthenticator simpleAuthenticator = new SimpleAuthenticator(config.getAccount(),
					config.getPassword());
			// 设置环境信息
			Session session = Session.getDefaultInstance(props, simpleAuthenticator);
			// 创建邮件对象
			Message message = new MimeMessage(session);
			message.setSubject(title);
			// 设置邮件内容
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(content, "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			message.setContent(mainPart);
			// 设置发件人
			message.setFrom(new InternetAddress(config.getAccount()));
			Transport transport = session.getTransport();
			// 连接邮件服务器
			transport.connect(config.getAccount(), config.getPassword());
			// 发送邮件
			transport.sendMessage(message, new Address[] { new InternetAddress(target) });
			// 关闭连接
			transport.close();
			logger.warn("send email success!!!");
			return true;
		} catch (Exception e) {
			logger.error("Send an Email occur an exception:", e);
			return false;
		}
	}

	public MailClient() {
		super();
	}

	public MailClient(MailConfig mailConfig) {
		super();
		this.mailConfig = mailConfig;
	}

	@PostConstruct
	protected void decryptConfig() throws Exception {
		config = new MailConfig();
		config.setAccount(DESUtil.decrypt(mailConfig.getAccount(), CONFIG_KEY));
		config.setPassword(DESUtil.decrypt(mailConfig.getPassword(), CONFIG_KEY));
		config.setPort(DESUtil.decrypt(mailConfig.getPort(), CONFIG_KEY));
		config.setProtocol(DESUtil.decrypt(mailConfig.getProtocol(), CONFIG_KEY));
		config.setServer(DESUtil.decrypt(mailConfig.getServer(), CONFIG_KEY));
	}

}
