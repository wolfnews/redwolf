package com.hoteam.wolf.common;

import java.util.List;

public class GridBean {

	private int page;
	private int total;
	private int records;
	private List<?> rows;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public GridBean(int page, int total, int records, List<?> rows) {
		super();
		this.page = page;
		this.total = total;
		this.records = records;
		this.rows = rows;
	}
}
