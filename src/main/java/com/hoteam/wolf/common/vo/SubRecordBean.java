package com.hoteam.wolf.common.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.utils.DateSerializer;

public class SubRecordBean {
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	private Long userId;
	private String user;
	private Long professorId;
	private String professor;
	private int subscribeTime;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProfessorId() {
		return professorId;
	}
	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public int getSubscribeTime() {
		return subscribeTime;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "SubRecordBean [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", userId="
				+ userId + ", professorId=" + professorId + ", professor=" + professor + ", subscribeTime="
				+ subscribeTime + "]";
	}
	public void setSubscribeTime(int subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public SubRecordBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
