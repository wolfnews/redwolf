package com.hoteam.wolf.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

@Table("recharge_record")
public class RechargeRecord {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	private String sn;// 记录编号
	private Long userId;// 用户主键
	private BigDecimal money;// 充值金额
	private String payWay;// 充值方式：支付宝，财付通
	private String category;//
	private Boolean paid;// 是否支付
	private String content;// 订单基本内容
	@JsonSerialize(using = DateSerializer.class)
	private Date payTime;// 支付时间
	private Long activityId;// 活动ID

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

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public RechargeRecord(String sn, Long userId, BigDecimal money, String payWay, String category, Boolean paid,
			Long activityId, Date payTime, String content) {
		super();
		this.sn = sn;
		this.userId = userId;
		this.money = money;
		this.payWay = payWay;
		this.category = category;
		this.paid = paid;
		this.activityId = activityId;
		this.payTime = payTime;
		this.content = content;
	}

	public RechargeRecord() {
		super();
	}

	@Override
	public String toString() {
		return "RechargeRecord [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", sn=" + sn
				+ ", userId=" + userId + ", money=" + money + ", payWay=" + payWay + ", category=" + category
				+ ", paid=" + paid + ", content=" + content + ", payTime=" + payTime + ", activityId=" + activityId
				+ "]";
	}

}
