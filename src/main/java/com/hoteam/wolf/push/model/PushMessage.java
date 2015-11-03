package com.hoteam.wolf.push.model;

import java.util.Map;

public class PushMessage {

	private String type;
	private String title;
	private String app;
	private String page;
	private String description;
	private Map<String, Object> custom_content;

	public Map<String, Object> getCustom_content() {
		return custom_content;
	}

	public void setCustom_content(Map<String, Object> custom_content) {
		this.custom_content = custom_content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PushMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PushMessage(String type, String title, String app, String page, String description,Map<String,Object> custom_contents) {
		super();
		this.type = type;
		this.title = title;
		this.app = app;
		this.page = page;
		this.description = description;
		this.custom_content = custom_contents;
	}

	public PushMessage(String description) {
		super();
		this.description = description;
		this.type = "app";
		this.title = "新宝盒提醒";
		this.app = "7003153";
		this.page = "page/boxes.uixml";
		this.description = description;
	}

	@Override
	public String toString() {
		return "PushMessage [type=" + type + ", title=" + title + ", app=" + app + ", page=" + page + ", description="
				+ description + "]";
	}

}
