package com.hoteam.wolf.common.vo;

import java.util.List;

public class ProfessorBean {

	private Long id;
	private String username;
	private String truename;
	private String occupation;
	private String summary;
	private String iconPath;
	private Integer level;
	private Long rssCount;
	private Long subCount;
	private Long boxCount;
	private List<Integer> prices;
	private List<UserGroupBean> groups;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Long getRssCount() {
		return rssCount;
	}
	public void setRssCount(Long rssCount) {
		this.rssCount = rssCount;
	}
	public Long getSubCount() {
		return subCount;
	}
	public void setSubCount(Long subCount) {
		this.subCount = subCount;
	}
	public Long getBoxCount() {
		return boxCount;
	}
	public void setBoxCount(Long boxCount) {
		this.boxCount = boxCount;
	}
	public List<Integer> getPrices() {
		return prices;
	}
	public void setPrices(List<Integer> prices) {
		this.prices = prices;
	}
	
	public List<UserGroupBean> getGroups() {
		return groups;
	}
	public void setGroups(List<UserGroupBean> groups) {
		this.groups = groups;
	}
	public ProfessorBean(Long id, String username, String truename, String occupation, String summary, String iconPath,
			Integer level, Long rssCount, Long subCount,Long boxCount, List<Integer> prices) {
		super();
		this.id = id;
		this.username = username;
		this.truename = truename;
		this.occupation = occupation;
		this.summary = summary;
		this.iconPath = iconPath;
		this.level = level;
		this.rssCount = rssCount;
		this.subCount = subCount;
		this.boxCount = boxCount;
		this.prices = prices;
	}
	public ProfessorBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProfessorBean [id=" + id + ", username=" + username + ", truename=" + truename + ", occupation="
				+ occupation + ", summary=" + summary + ", iconPath=" + iconPath + ", level=" + level + ", rssCount="
				+ rssCount + ", subCount=" + subCount + ", boxCount=" + boxCount + ", prices=" + prices
				+ ", groups=" + groups + "]";
	}
}
