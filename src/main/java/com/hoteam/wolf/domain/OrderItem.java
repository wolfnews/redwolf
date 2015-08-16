package com.hoteam.wolf.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

/**
 * 订单商品关联表
 * 
 * @author dmw
 *
 */
@Table("order_item")
public class OrderItem {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	private Long orderId;
	private Long itemId;
	private int amount;
	private BigDecimal price;
	private Boolean hasPref;
	private int prefValue;
	private String prefContent;

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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getHasPref() {
		return hasPref;
	}

	public void setHasPref(Boolean hasPref) {
		this.hasPref = hasPref;
	}

	public int getPrefValue() {
		return prefValue;
	}

	public void setPrefValue(int prefValue) {
		this.prefValue = prefValue;
	}

	public String getPrefContent() {
		return prefContent;
	}

	public void setPrefContent(String prefContent) {
		this.prefContent = prefContent;
	}

	public OrderItem(Long orderId, Long itemId, int amount, BigDecimal price, Boolean hasPref, int prefValue) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
		this.amount = amount;
		this.price = price;
		this.hasPref = hasPref;
		this.prefValue = prefValue;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", orderId=" + orderId
				+ ", itemId=" + itemId + ", amount=" + amount + ", price=" + price + ", hasPref=" + hasPref
				+ ", prefValue=" + prefValue + "]";
	}

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

}
