package com.shadow.entity;

public class ModuleEntity {

	private int module_id;
	private String module_name;
	private int module_pid;
	private int _parentId;
	private String  module_jsp;
	private boolean module_parent;


	public ModuleEntity() {
	}

	public ModuleEntity(int module_id, String module_name, int module_pid, int _parentId, String module_jsp, boolean module_parent) {
		this.module_id = module_id;
		this.module_name = module_name;
		this.module_pid = module_pid;
		this._parentId = _parentId;
		this.module_jsp = module_jsp;
		this.module_parent = module_parent;
	}


	@Override
	public String toString() {
		return "ModuleEntity{" +
				"module_id=" + module_id +
				", module_name='" + module_name + '\'' +
				", module_pid=" + module_pid +
				", _parentId=" + _parentId +
				", module_jsp='" + module_jsp + '\'' +
				", module_parent=" + module_parent +
				'}';
	}

	public int get_parentId() {
		return _parentId;
	}

	public void set_parentId(int _parentId) {
		this._parentId = _parentId;
	}

	public boolean isModule_parent() {
		return module_parent;
	}

	public void setModule_parent(boolean module_parent) {
		this.module_parent = module_parent;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public int getModule_pid() {
		return module_pid;
	}

	public void setModule_pid(int module_pid) {
		this.module_pid = module_pid;
	}

	public String getModule_jsp() {
		return module_jsp;
	}

	public void setModule_jsp(String module_jsp) {
		this.module_jsp = module_jsp;
	}



		


}
