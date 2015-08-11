package com.hoteam.wolf.domain;

import java.math.BigDecimal;
import java.util.Date;

public class VisitBean {

	private Date date;
	private BigDecimal pv;
	private Long uv;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getPv() {
		return pv;
	}

	public void setPv(BigDecimal pv) {
		this.pv = pv;
	}

	public Long getUv() {
		return uv;
	}

	public void setUv(Long uv) {
		this.uv = uv;
	}

	public VisitBean(Date date, BigDecimal pv, Long uv) {
		super();
		this.date = date;
		this.pv = pv;
		this.uv = uv;
	}

	public VisitBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
