package com.hoteam.wolf.common.vo;

public class SmsBean {
	private String userName;
	private String secret;
	private String stamp;
	private String moblie;
	private String text;
	private String ext;
	private String sendTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public SmsBean(String userName, String secret, String stamp, String mobile, String text, String ext,
			String sendTime) {
		super();
		this.userName = userName;
		this.secret = secret;
		this.stamp = stamp;
		this.moblie = mobile;
		this.text = text;
		this.ext = ext;
		this.sendTime = sendTime;
	}

	public SmsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}