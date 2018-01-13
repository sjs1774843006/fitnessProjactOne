package com.shadow.entity;

public class StaffTypeEntity {

	private int type_id;
	private String type_name;
	private Integer parentId;

	public StaffTypeEntity() {
	}

	public StaffTypeEntity(int type_id, String type_name) {
		this.type_id = type_id;
		this.type_name = type_name;
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
				", parentId=" + parentId +
				'}';
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
