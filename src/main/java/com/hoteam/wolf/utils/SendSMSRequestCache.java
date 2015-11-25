package com.hoteam.wolf.utils;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component("sendSMSRequestCache")
public class SendSMSRequestCache {

	private static ConcurrentHashMap<String, Integer> cache;

	@PostConstruct
	public void init() {
		cache = new ConcurrentHashMap<String, Integer>(100, 0.9f);
	}

	public void putIP(String ip) {
		if (null == cache.get(ip)) {
			cache.put(ip, 1);
		} else {
			cache.put(ip, cache.get(ip) + 1);
		}
	}

	public int getIP(String ip) {
		Integer num = cache.get(ip);
		return null == num ? 0 : num;
	}
	
	public void clean(){
		cache=new ConcurrentHashMap<String, Integer>(100,0.9f);
	}
}
