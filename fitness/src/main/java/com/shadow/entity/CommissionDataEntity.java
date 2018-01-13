package com.shadow.entity;

public class CommissionDataEntity {

	private int Commission_data_id;
	private String Commission_data;
	private Integer parentId;

	public CommissionDataEntity() {
	}

	public CommissionDataEntity(int Commission_data_id, String Commission_data) {
		this.Commission_data_id = Commission_data_id;
		this.Commission_data = Commission_data;
	}

	@Override
	public String toString() {
		return "CommissionDataEntity{" +
				"Commission_data_id=" + Commission_data_id +
				", Commission_data='" + Commission_data + '\'' +
				", parentId=" + parentId +
				'}';
	}

	public int getCommission_data_id() {
		return Commission_data_id;
	}

	public void setCommission_data_id(int commission_data_id) {
		Commission_data_id = commission_data_id;
	}

	public String getCommission_data() {
		return Commission_data;
	}

	public void setCommission_data(String commission_data) {
		Commission_data = commission_data;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
