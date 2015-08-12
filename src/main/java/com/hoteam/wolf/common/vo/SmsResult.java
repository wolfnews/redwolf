package com.hoteam.wolf.common.vo;

import java.util.Arrays;

public class SmsResult {
	private int statusCode;
	private String description;
	private String msgId;
	private long amount;
	private int successCounts;
	private String[] errors;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getSuccessCounts() {
		return successCounts;
	}

	public void setSuccessCounts(int successCounts) {
		this.successCounts = successCounts;
	}

	public String[] getErrors() {
		return errors;
	}

	public void setErrors(String[] errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "JsonResult [statusCode=" + statusCode + ", description=" + description + ", msgId=" + msgId
				+ ", amount=" + amount + ", successCounts=" + successCounts + ", errors=" + Arrays.toString(errors)
				+ "]";
	}
	
}
