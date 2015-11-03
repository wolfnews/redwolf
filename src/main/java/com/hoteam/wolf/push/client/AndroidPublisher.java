package com.hoteam.wolf.push.client;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.hoteam.wolf.common.config.PushConfig;
import com.hoteam.wolf.push.model.PushMessage;

@Component("androidPublisher")
public class AndroidPublisher {

	private static Logger logger = Logger.getLogger(AndroidPublisher.class);
	@Autowired
	private PushConfig pushConfig;

	public void pushMessage(String channelId, PushMessage message) {
		PushKeyPair pair = new PushKeyPair(pushConfig.getApiKey(), pushConfig.getSecretKey());
		BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				logger.info(event.getMessage());
			}
		});
		PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().addChannelId(channelId)
				.addMsgExpires(new Integer(3600)). // message有效时间
				addMessageType(1).// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
				addMessage(JSON.toJSONString(message)).addDeviceType(3);// 3:android.
																		// 4:ios
		try {
			pushClient.pushMsgToSingleDevice(request);
		} catch (Exception e) {
			logger.error("Push message to [" + channelId + "] failed");
		}
	}
}
