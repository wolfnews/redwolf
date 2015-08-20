package com.hoteam.wolf.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

@Table("message")
public class Message {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	private Long senderId;
	private Long receiverId;
	private Long lastMessage;
	private String content;
	private String sender;
	private String receiver;
	private boolean readed;

	/**
	 * 持久化前预处理
	 */
	public void prePersist() {
		gmtCreate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(Long lastMessage) {
		this.lastMessage = lastMessage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	


	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public boolean isReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", gmtCreate=" + gmtCreate + ", senderId=" + senderId + ", receiverId="
				+ receiverId + ", lastMessage=" + lastMessage + ", content=" + content + ", sender=" + sender
				+ ", receiver=" + receiver + ", readed=" + readed + "]";
	}

	public Message(Long senderId, Long receiverId, Long lastMessage, String content, String sender, String receiver,
			boolean readed) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.lastMessage = lastMessage;
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
		this.readed = readed;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

}
