package com.hoteam.wolf.common;

public class EntityResult extends Result {

	public EntityResult(boolean success, String message) {
		super(success, message);
	}

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public EntityResult(boolean success, String message, Object data) {
		super(success, message);
		this.data = data;
	}

	@Override
	public String toString() {
		return "EntityResult [data=" + data + ", isSuccess()=" + isSuccess() + ", getMessage()=" + getMessage()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
