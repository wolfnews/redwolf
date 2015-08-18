package com.hoteam.wolf.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

@Table("consumer_record")
public class ConsumeRecord {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	private String purpose;
	private Long userId;
	private Long coin;
	
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

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCoin() {
		return coin;
	}

	public void setCoin(Long coin) {
		this.coin = coin;
	}

	public ConsumeRecord(String purpose, Long userId, Long coin) {
		super();
		this.purpose = purpose;
		this.userId = userId;
		this.coin = coin;
	}

	public ConsumeRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ConsumeRecord [id=" + id + ", gmtCreate=" + gmtCreate + ", purpose=" + purpose + ", userId=" + userId
				+ ", coin=" + coin + "]";
	}


}
