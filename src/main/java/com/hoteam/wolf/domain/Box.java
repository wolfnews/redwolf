package com.hoteam.wolf.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

@Table("box")
public class Box {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	private Long author;
	private Long groupId;
	private String title;
	private String keyword;
	private String publicContent;
	private String privateContent;
	private String category;
	private String status;
	private int favorNum;
	private int browseNum;
	private String authorName;

	/**
	 * 持久化前预处理
	 */
	public void prePersist() {
		gmtCreate = new Date();
		gmtModify = new Date();
	}

	/**
	 * 更新前预处理
	 */
	public void preUpdate() {
		gmtModify = new Date();
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

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public Long getAuthor() {
		return author;
	}

	public void setAuthor(Long author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPublicContent() {
		return publicContent;
	}

	public void setPublicContent(String publicContent) {
		this.publicContent = publicContent;
	}

	public String getPrivateContent() {
		return privateContent;
	}

	public void setPrivateContent(String privateContent) {
		this.privateContent = privateContent;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getFavorNum() {
		return favorNum;
	}

	public void setFavorNum(int favorNum) {
		this.favorNum = favorNum;
	}

	public int getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(int browseNum) {
		this.browseNum = browseNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Box() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Box(Long author, Long groupId, String title, String keyword, String publicContent, String privateContent,
			String category, String status, int favorNum, int browseNum, String authorName) {
		super();
		this.author = author;
		this.groupId = groupId;
		this.title = title;
		this.keyword = keyword;
		this.publicContent = publicContent;
		this.privateContent = privateContent;
		this.category = category;
		this.status = status;
		this.favorNum = favorNum;
		this.browseNum = browseNum;
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", author=" + author
				+ ", groupId=" + groupId + ", title=" + title + ", keyword=" + keyword + ", publicContent="
				+ publicContent + ", privateContent=" + privateContent + ", category=" + category + ", status="
				+ status + ", favorNum=" + favorNum + ", browseNum=" + browseNum + ", authorName=" + authorName + "]";
	}

}
