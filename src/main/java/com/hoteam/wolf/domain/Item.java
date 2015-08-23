package com.hoteam.wolf.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

/**
 * 商品表
 * 
 * @author dmw
 *
 */
@Table("item")
public class Item {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	private String name;
	private String desp;
	private String category;
	private int sku;
	private BigDecimal price;
	private int value;
	private Long categoryId;
	private Long extend;// 主要是讲师的ID

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

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getExtend() {
		return extend;
	}

	public void setExtend(Long extend) {
		this.extend = extend;
	}


	@Override
	public String toString() {
		return "Item [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", name=" + name + ", desp="
				+ desp + ", category=" + category + ", sku=" + sku + ", price=" + price + ", value=" + value
				+ ", categoryId=" + categoryId + ", extend=" + extend + "]";
	}

	public Item(String name, String desp, String category, int sku, BigDecimal price, int value, Long extend,Long categoryId) {
		super();
		this.name = name;
		this.desp = desp;
		this.category = category;
		this.sku = sku;
		this.price = price;
		this.value = value;
		this.extend = extend;
		this.categoryId = categoryId;
	}

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

}
