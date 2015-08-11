package com.hoteam.wolf.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;
import com.hoteam.wolf.utils.SimpleDateSerializer;

@Table("visit_count")
public class VisitCount {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	@JsonSerialize(using = SimpleDateSerializer.class)
	private Date visitDate;
	private Long amount;
	private String target;
	private String source;

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

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public VisitCount(Date visitDate, Long amount, String target, String source) {
		super();
		this.visitDate = visitDate;
		this.amount = amount;
		this.target = target;
		this.source = source;
	}

	public VisitCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "VisitCount [id=" + id + ", gmtModify=" + gmtModify + ", visitDate=" + visitDate + ", amount=" + amount
				+ ", target=" + target + ", source=" + source + "]";
	}

}
