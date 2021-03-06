package com.hoteam.wolf.common.vo;

public class UserProfile {

	private String level;
	private String username;
	private Long coin;
	private Long rssAccount;
	private Long subAccount;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getCoin() {
		return coin;
	}

	public void setCoin(Long coin) {
		this.coin = coin;
	}

	public Long getRssAccount() {
		return rssAccount;
	}

	public void setRssAccount(Long rssAccount) {
		this.rssAccount = rssAccount;
	}

	public Long getSubAccount() {
		return subAccount;
	}

	public void setSubAccount(Long subAccount) {
		this.subAccount = subAccount;
	}

	public UserProfile(String level, String username, Long coin, Long rssAccount, Long subAccount) {
		super();
		this.level = level;
		this.username = username;
		this.coin = coin;
		this.rssAccount = rssAccount;
		this.subAccount = subAccount;
	}

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserProfile [level=" + level + ", username=" + username + ", coin=" + coin + ", rssAccount="
				+ rssAccount + ", subAccount=" + subAccount + "]";
	}

}
