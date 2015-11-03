package com.hoteam.wolf.push.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hoteam.wolf.push.client.AndroidPublisher;
import com.hoteam.wolf.push.model.PushMessage;

@Component("pushService")
public class PushServiceImpl implements PushService {

	@Autowired
	private AndroidPublisher androidPublisher;

	@Override
	public void pushMessageToAndroid(PushMessage message, List<String> channelList) {
		if (null == message || null == channelList || channelList.isEmpty()) {
			return;
		}
		for (String channel : channelList) {
			androidPublisher.pushMessage(channel, message);
		}
	}

	@Override
	public void pushMessageToIos() {
		// TODO Auto-generated method stub

	}

}
