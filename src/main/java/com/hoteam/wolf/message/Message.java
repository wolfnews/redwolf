package com.hoteam.wolf.message;

import com.hoteam.wolf.common.enums.MessageCategory;

public class Message {

	private String category;
	private String content;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Message() {
		super();
	}
	
	public Message(String content){
		super();
		this.category = MessageCategory.TEXT.name();
		this.content = content;
	}
	public Message(MessageCategory category, String content) {
		super();
		this.category = category.name();
		this.content = content;
	}
	
}
