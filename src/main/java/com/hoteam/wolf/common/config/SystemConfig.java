package com.hoteam.wolf.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("systemConfig")
public class SystemConfig {
	@Value("${current.host}")
	private String host;
	@Value("${auth.key}")
	private String authKey;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

}
