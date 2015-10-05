package com.hoteam.wolf.message;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("messageSender")
public class MessageSender {

	@Resource
	private MessageHandler messageHandler;

	public void pushMessage(final Long userId, final String content) {
		messageHandler.sendMessageToUser(userId, new Message(content));
	}

	public void pushMessage(final String content) {
		messageHandler.sendMessageToUsers(new Message(content));
	}

	public MessageSender() {
		super();
	}
}
