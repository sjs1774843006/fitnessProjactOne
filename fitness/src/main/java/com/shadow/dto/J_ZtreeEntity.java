package com.shadow.dto;

import java.util.List;

public class J_ZtreeEntity {

	private int id;
	private Integer __parentId;
	private String module;
	private String add;
	private String del;
	private String update;
	private String query;
	private String state;
	private String checkState;
	private List<J_ZtreeEntity> children;
	private boolean checked;


	@Override
	public String toString() {
		return "J_ZtreeEntity{" +
				"id=" + id +
				", __parentId=" + __parentId +
				", module='" + module + '\'' +
				", add='" + add + '\'' +
				", del='" + del + '\'' +
				", update='" + update + '\'' +
				", query='" + query + '\'' +
				", state='" + state + '\'' +
				", checkState='" + checkState + '\'' +
				", checked=" + checked +
				'}';
	}

	public List<J_ZtreeEntity> getChildren() {
		return children;
	}

	public void setChildren(List<J_ZtreeEntity> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Integer get__parentId() {
		return __parentId;
	}
	public void set__parentId(Integer __parentId) {
		this.__parentId = __parentId;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	
}
