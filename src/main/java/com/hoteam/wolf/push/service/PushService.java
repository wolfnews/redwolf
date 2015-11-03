package com.hoteam.wolf.push.service;

import java.util.List;

import com.hoteam.wolf.push.model.PushMessage;

/**
 * 消息推送服务
 * @author dmw
 *
 */
public interface PushService {

	/**推送消息给Android客户端
	 * @param message 消息内容
	 * @param channelList 客户端ID列表
	 */
	public void pushMessageToAndroid(PushMessage message,List<String> channelList);
	
	public void pushMessageToIos();
}
