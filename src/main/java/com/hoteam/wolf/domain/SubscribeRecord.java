package com.hoteam.wolf.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

@Table("subscribe_record")
public class SubscribeRecord {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	private Long userId;
	private Long professorId;
	private int subscribeTime;

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

	public int getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(int subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public SubscribeRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubscribeRecord(Long userId, Long professorId, int subscribeTime) {
		super();
		this.userId = userId;
		this.professorId = professorId;
		this.subscribeTime = subscribeTime;
	}

	@Override
	public String toString() {
		return "SubscribeRecord [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", userId="
				+ userId + ", professorId=" + professorId + ", subscribeTime=" + subscribeTime + "]";
	}

}
