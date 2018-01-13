package com.shadow.entity;

public class ConsumptionTypeEntity {

	private int consumption_type_id;
	private String consumption_typename;
	private Integer parentId;

	public ConsumptionTypeEntity() {
	}

	public ConsumptionTypeEntity(int consumption_type_id, String consumption_typename) {
		this.consumption_type_id = consumption_type_id;
		this.consumption_typename = consumption_typename;
	}

	@Override
	public String toString() {
		return "ConsumptionTypeEntity{" +
				"consumption_type_id=" + consumption_type_id +
				", consumption_typename='" + consumption_typename + '\'' +
				", parentId=" + parentId +
				'}';
	}

	public int getConsumption_type_id() {
		return consumption_type_id;
	}

	public void setConsumption_type_id(int consumption_type_id) {
		this.consumption_type_id = consumption_type_id;
	}

	public String getConsumption_typename() {
		return consumption_typename;
	}

	public void setConsumption_typename(String consumption_typename) {
		this.consumption_typename = consumption_typename;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
