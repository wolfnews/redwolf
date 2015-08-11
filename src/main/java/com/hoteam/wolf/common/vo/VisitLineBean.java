package com.hoteam.wolf.common.vo;

import java.util.List;

public class VisitLineBean {
	private List<String> dates;
	private List<Long> pvs;
	private List<Long> uvs;
	public List<String> getDates() {
		return dates;
	}
	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	public List<Long> getPvs() {
		return pvs;
	}
	public void setPvs(List<Long> pvs) {
		this.pvs = pvs;
	}
	public List<Long> getUvs() {
		return uvs;
	}
	public void setUvs(List<Long> uvs) {
		this.uvs = uvs;
	}
	public VisitLineBean(List<String> dates, List<Long> pvs, List<Long> uvs) {
		super();
		this.dates = dates;
		this.pvs = pvs;
		this.uvs = uvs;
	}

	public VisitLineBean() {
		super();
	}
	
}
