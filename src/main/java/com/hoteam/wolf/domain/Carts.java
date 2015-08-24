package com.hoteam.wolf.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

/**
 * 用户购物车
 * @author dmw
 *
 */
@Table("carts")
public class Carts {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	private Long userId;
	private Long itemId;
	private String name;
	private Integer num;

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

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Carts(Long userId, Long itemId, String name, Integer num) {
		super();
		this.userId = userId;
		this.itemId = itemId;
		this.name = name;
		this.num = num;
	}

	public Carts() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Carts [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", userId=" + userId
				+ ", itemId=" + itemId + ", name=" + name + ", num=" + num + "]";
	}


}
