package com.hoteam.wolf.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

@Table("activity")
public class Activity {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	private String name;
	private String category;
	private String status;
	private BigDecimal rate;
	private String description;
	@JsonSerialize(using = DateSerializer.class)
	private Date startTime;
	@JsonSerialize(using = DateSerializer.class)
	private Date endTime;
	private boolean valid;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Activity(String name, String category, BigDecimal rate, Date startTime, Date endTime, boolean valid,
			String status, String description) {
		super();
		this.name = name;
		this.category = category;
		this.rate = rate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.valid = valid;
		this.status = status;
		this.description = description;
	}

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", name=" + name
				+ ", category=" + category + ", status=" + status + ", rate=" + rate + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", valid=" + valid + "]";
	}

}
