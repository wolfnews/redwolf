/**
 * @author hty
 * @time 上午9:39:37
 * @date 2014年12月5日
 */
package com.hoteam.wolf.message;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.hoteam.wolf.common.Constants;

@Component
public class MessageHandshakeInterceptor implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			if (session != null) {
				Long userId = (Long) session.getAttribute(Constants.USER_TOKEN.name());
				if(null != userId){
					attributes.put(Constants.USER_TOKEN.name(), userId);
				}
			}
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {

	}
}