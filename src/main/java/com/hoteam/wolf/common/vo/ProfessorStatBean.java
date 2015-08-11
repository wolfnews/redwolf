package com.hoteam.wolf.common.vo;

public class ProfessorStatBean {

	private long noticeAccount;
	private long noticeBrowseAccount;
	private long userRssAccount;
	private long userSubscribeAccount;
	public long getNoticeAccount() {
		return noticeAccount;
	}
	public void setNoticeAccount(long noticeAccount) {
		this.noticeAccount = noticeAccount;
	}
	public long getNoticeBrowseAccount() {
		return noticeBrowseAccount;
	}
	public void setNoticeBrowseAccount(long noticeBrowseAccount) {
		this.noticeBrowseAccount = noticeBrowseAccount;
	}
	public long getUserRssAccount() {
		return userRssAccount;
	}
	public void setUserRssAccount(long userRssAccount) {
		this.userRssAccount = userRssAccount;
	}
	public long getUserSubscribeAccount() {
		return userSubscribeAccount;
	}
	public void setUserSubscribeAccount(long userSubscribeAccount) {
		this.userSubscribeAccount = userSubscribeAccount;
	}
	public ProfessorStatBean(long noticeAccount, long noticeBrowseAccount, long userRssAccount, long userSubscribeAccount) {
		super();
		this.noticeAccount = noticeAccount;
		this.noticeBrowseAccount = noticeBrowseAccount;
		this.userRssAccount = userRssAccount;
		this.userSubscribeAccount = userSubscribeAccount;
	}
	public ProfessorStatBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProfessorStatBean [noticeAccount=" + noticeAccount + ", noticeBrowseAccount=" + noticeBrowseAccount
				+ ", userRssAccount=" + userRssAccount + ", userSubscribeAccount=" + userSubscribeAccount + "]";
	}
	
	
}
