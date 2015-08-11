package com.hoteam.wolf.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hoteam.wolf.jdbc.annotations.PK;
import com.hoteam.wolf.jdbc.annotations.Table;
import com.hoteam.wolf.utils.DateSerializer;

@Table("app_download")
public class AppDownload {
	@PK
	private Long id;
	@JsonSerialize(using = DateSerializer.class)
	private Date gmtCreate;
	private String address;
	private String appVersion;
	private String appCategory;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppCategory() {
		return appCategory;
	}

	public void setAppCategory(String appCategory) {
		this.appCategory = appCategory;
	}

	public AppDownload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppDownload(String address, String appVersion, String appCategory) {
		super();
		this.address = address;
		this.appVersion = appVersion;
		this.appCategory = appCategory;
	}

	@Override
	public String toString() {
		return "AppDownload [id=" + id + ", gmtCreate=" + gmtCreate + ", address=" + address + ", appVersion="
				+ appVersion + ", appCategory=" + appCategory + "]";
	}

}
