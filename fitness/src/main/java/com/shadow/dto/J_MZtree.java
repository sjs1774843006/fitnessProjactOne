package com.shadow.dto;

import java.util.List;

public class J_MZtree {

	private boolean checked;
	private int id;
	private String module;
	private List<J_ZtreeEntity> children;
	private String state;
	private String checkState;
	private boolean _checked;

	@Override
	public String toString() {
		return "J_MZtree{" +
				"checked=" + checked +
				", id=" + id +
				", module='" + module + '\'' +
				", children=" + children +
				", state='" + state + '\'' +
				", checkState='" + checkState + '\'' +
				", _checked=" + _checked +
				'}';
	}

	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
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
	public boolean is_checked() {
		return _checked;
	}
	public void set_checked(boolean _checked) {
		this._checked = _checked;
	}
	
}
