package com.hoteam.wolf.common.vo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.utils.DateSerializer;

public class RssRecordBean {
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	private Long userId;
	private String user;
	private Long professorId;
	private String professor;
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public RssRecordBean() {
		super();
	}
}
