package com.shadow.entity;

public class StaffTypeEntity {

	private int type_id;
	private String type_name;
	private String loginflag;
	private Integer parentId;

	public StaffTypeEntity() {
	}

	public StaffTypeEntity(int type_id, String type_name, String loginflag) {
		this.type_id = type_id;
		this.type_name = type_name;
		this.loginflag = loginflag;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}


	@Override
	public String toString() {
		return "StaffTypeEntity{" +
				"type_id=" + type_id +
				", type_name='" + type_name + '\'' +
				", loginflag='" + loginflag + '\'' +
				", parentId=" + parentId +
				'}';
	}

	public String getLoginflag() {
		return loginflag;
	}

	public void setLoginflag(String loginflag) {
		this.loginflag = loginflag;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
