package com.hoteam.wolf.common.enums;

public enum GroupLevel {

	DEFAULT(0,"默认"),
	PRIMARY(1,"初级"),
	MEDIUM(2,"中级"),
	SENIOR(3,"高级");
	
	private int index;
	private String name;
	private GroupLevel(int index,String name){
		this.index = index;
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLevelName(int index){
		String level = "默认";
		for(GroupLevel gl : GroupLevel.values()){
			if(gl.index == index){
				level = gl.name;
				break;
			}
		}
		return level;
	}
	public Integer getLevel(String name){
		Integer level = null;
		for(GroupLevel gl : GroupLevel.values()){
			if(gl.name.equals(name)){
				level = gl.index;
				break;
			}
		}
		return level;
	}
}
