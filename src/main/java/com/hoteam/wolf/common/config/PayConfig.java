package com.hoteam.wolf.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("payConfig")
public class PayConfig {
	@Value("${pay.spname}")
	private String spname;
	@Value("${pay.partner}")
	private String partner;
	@Value("${pay.key}")
	private String key;

	@Value("${pay.notify.url:http://www.niuguhui.org/profile/pay/notity.html}")
	private String notifyUrl;
	@Value("${pay.return.url:http://www.niuguhui.org/profile/pay/return.html}")
	private String returnUrl;

	public String getSpname() {
		return spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

}
