package com.hoteam.wolf.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

@Table("professor")
public class Professor {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	private String username;
	private String password;
	private String truename;
	private String mobile;
	private String iconPath;
	private String occupation;
	private String summary;
	private int level;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public Professor(String username, String password, String mobile, String iconPath, String occupation,
			String summary, int level) {
		super();
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.iconPath = iconPath;
		this.occupation = occupation;
		this.summary = summary;
		this.level = level;
	}

	public Professor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", username="
				+ username + ", password=" + password + ", mobile=" + mobile + ", iconPath=" + iconPath
				+ ", occupation=" + occupation + ", summary=" + summary + ", level=" + level + "]";
	}

}
