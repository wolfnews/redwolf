package com.hoteam.wolf.common.vo;

import java.util.Date;

public class UserGroupBean {

	private Long userId;
	private Long groupId;
	private String name;
	private Date expiredTime;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	public UserGroupBean(Long userId, Long groupId, String name, Date expiredTime) {
		super();
		this.userId = userId;
		this.groupId = groupId;
		this.name = name;
		this.expiredTime = expiredTime;
	}
	public UserGroupBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserGroupBean [userId=" + userId + ", groupId=" + groupId + ", name=" + name + ", expiredTime="
				+ expiredTime + "]";
	}
	
}
