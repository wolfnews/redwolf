package com.hoteam.wolf.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

@Table("box_comment")
public class BoxComment {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date createTime;
	@JsonSerialize(using = DateSerializer.class)
	private Date lastModify;
	private String boxId;
	private String opinion;
	private String comment;

	/**
	 * 持久化前预处理
	 */
	public void prePersist() {
		createTime = new Date();
		lastModify = new Date();
	}

	/**
	 * 更新前预处理
	 */
	public void preUpdate() {
		lastModify = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public String getBoxId() {
		return boxId;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BoxComment(String boxId, String opinion, String comment) {
		super();
		this.boxId = boxId;
		this.opinion = opinion;
		this.comment = comment;
	}

	public BoxComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BoxComment [id=" + id + ", createTime=" + createTime + ", lastModify=" + lastModify + ", boxId="
				+ boxId + ", opinion=" + opinion + ", comment=" + comment + "]";
	}

}
