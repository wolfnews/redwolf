package com.hoteam.wolf.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.hoteam.wolf.domain.User;

@Component("messageHandler")
public class MessageHandler implements WebSocketHandler {

	private static Logger logger = Logger.getLogger(MessageHandler.class);
	private List<WebSocketSession> socketSessions = new ArrayList<WebSocketSession>();

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		socketSessions.add(session);
	}

	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
	}

	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		socketSessions.remove(session);
	}

	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		socketSessions.remove(session);
	}

	public boolean supportsPartialMessages() {
		return false;
	}

	public void sendMessageToUsers(Message message) {
		for (WebSocketSession session : socketSessions) {
			try {
				if (session.isOpen()) {
					session.sendMessage(new TextMessage(JSONObject.toJSONString(message)));
				}
			} catch (IOException e) {
				logger.error("发送消息异常：", e);
			}
		}
	}

	/**
	 * @author langzi
	 * @param userId
	 * @param message
	 * @version 1.0 2015年8月17日
	 */
	public void sendMessageToUser(Long userId, Message message) {
		for (WebSocketSession session : socketSessions) {
			User user = (User) session.getAttributes().get("user");
			if (null == user) {
				continue;
			}
			if (user.getId() == userId) {
				if (session.isOpen()) {
					try {
						session.sendMessage(new TextMessage(JSONObject.toJSONString(message)));
					} catch (IOException e) {
						logger.error("Send message to user[" + userId + "] exception:", e);
					}
				} else {
					continue;
				}
			}
		}
	}

}