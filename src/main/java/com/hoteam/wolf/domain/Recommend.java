package com.hoteam.wolf.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;
import com.hoteam.wolf.utils.SimpleDateSerializer;

@Table("recommend")
public class Recommend {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	@JsonSerialize(using = SimpleDateSerializer.class)
	private Date gmtCreate;
	private Long userId;
	private String code;

	/**
	 * 持久化前预处理
	 */
	public void prePersist() {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Recommend(Long userId, String code) {
		super();
		this.userId = userId;
		this.code = code;
	}

	public Recommend() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserRecommend [id=" + id + ", gmtModify=" + gmtModify + ", gmtCreate=" + gmtCreate + ", userId="
				+ userId + ", code=" + code + "]";
	}

}
