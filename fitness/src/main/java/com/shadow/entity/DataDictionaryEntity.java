package com.shadow.entity;

public class DataDictionaryEntity {

	private int data_id;
	private String data_name;
	private String  data_message;
	private String  data_url;
	private Integer parentId;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public DataDictionaryEntity() {
	}

	public DataDictionaryEntity(int data_id, String data_name, String data_message, String data_url) {
		this.data_id = data_id;
		this.data_name = data_name;
		this.data_message = data_message;
		this.data_url = data_url;
	}

	@Override
	public String toString() {
		return "DataDictionaryEntity{" +
				"data_id=" + data_id +
				", data_name='" + data_name + '\'' +
				", data_message='" + data_message + '\'' +
				", data_url='" + data_url + '\'' +
				", parentId=" + parentId +
				'}';
	}

	public String getData_url() {
		return data_url;
	}

	public void setData_url(String data_url) {
		this.data_url = data_url;
	}

	public String getData_message() {
		return data_message;
	}

	public void setData_message(String data_message) {
		this.data_message = data_message;
	}

	public int getData_id() {
		return data_id;
	}

	public void setData_id(int data_id) {
		this.data_id = data_id;
	}

	public String getData_name() {
		return data_name;
	}

	public void setData_name(String data_name) {
		this.data_name = data_name;
	}
}
