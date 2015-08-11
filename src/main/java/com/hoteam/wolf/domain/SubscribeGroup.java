package com.hoteam.wolf.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

@Table("subscribe_group")
public class SubscribeGroup {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtModify;
	private Long professorId;
	private String name;
	private Integer level;
	private Integer price;
	private Integer maxPerson;

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

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(Integer maxPerson) {
		this.maxPerson = maxPerson;
	}

	public SubscribeGroup(Long professorId, String name, Integer level, Integer price, Integer maxPerson) {
		super();
		this.professorId = professorId;
		this.name = name;
		this.level = level;
		this.price = price;
		this.maxPerson = maxPerson;
	}

	public SubscribeGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SubscribeGroup [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", professorId="
				+ professorId + ", name=" + name + ", level=" + level + ", price=" + price + ", maxPerson=" + maxPerson
				+ "]";
	}

}
