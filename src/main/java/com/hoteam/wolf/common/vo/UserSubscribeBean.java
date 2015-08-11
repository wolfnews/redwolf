package com.hoteam.wolf.common.vo;

import java.math.BigDecimal;
import java.util.Date;

public class UserSubscribeBean {

	private Long    userId;
	private Long    groupId;
	private Long    professorId;
	private String  username;
	private String  groupname;
	private String  professor;
	private Date    expiedTime;
	private boolean expied;
	private int     level;
	private BigDecimal price;
	private int     maxPerson;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public Date getExpiedTime() {
		return expiedTime;
	}

	public void setExpiedTime(Date expiedTime) {
		this.expiedTime = expiedTime;
	}

	public boolean isExpied() {
		return expied;
	}

	public void setExpied(boolean expied) {
		this.expied = expied;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(int maxPerson) {
		this.maxPerson = maxPerson;
	}


	public UserSubscribeBean(Long userId, Long groupId, Long professorId, String username, String groupname,
			String professor, Date expiedTime, boolean expied, int level, BigDecimal price, int maxPerson) {
		super();
		this.userId = userId;
		this.groupId = groupId;
		this.professorId = professorId;
		this.username = username;
		this.groupname = groupname;
		this.professor = professor;
		this.expiedTime = expiedTime;
		this.expied = expied;
		this.level = level;
		this.price = price;
		this.maxPerson = maxPerson;
	}

	public UserSubscribeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserSubscribeBean [userId=" + userId + ", groupId=" + groupId + ", professorId=" + professorId
				+ ", username=" + username + ", groupname=" + groupname + ", professor=" + professor + ", expiedTime="
				+ expiedTime + ", expied=" + expied + "]";
	}

}
