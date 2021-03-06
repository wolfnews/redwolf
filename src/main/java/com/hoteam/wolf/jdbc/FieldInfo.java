package com.hoteam.wolf.jdbc;

public class FieldInfo {
	// java字段名
	private String pojoFieldName;
	// 数据库字段名
	private String dbFieldName;
	// 是否是主键
	private boolean isPk = false;
	// update时是否需要更新
	private boolean isUpdate = true;
	// insert时是否需要插入
	private boolean isInsert = true;

	private Class<?> type;

	public boolean isPk() {
		return isPk;
	}

	public void setIsPk(boolean isPk) {
		this.isPk = isPk;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getPojoFieldName() {
		return pojoFieldName;
	}

	public void setPojoFieldName(String pojoFieldName) {
		this.pojoFieldName = pojoFieldName;
	}

	public String getDbFieldName() {
		return dbFieldName;
	}

	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}

	public boolean isInsert() {
		return isInsert;
	}

	public void setIsInsert(boolean isInsert) {
		this.isInsert = isInsert;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

}