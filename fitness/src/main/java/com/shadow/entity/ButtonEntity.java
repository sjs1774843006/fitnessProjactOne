package com.shadow.entity;


public class ButtonEntity {

	private int button_id;
	private ModuleEntity module;
	private int button_name;


	public ModuleEntity getModule() {
		return module;
	}
	public void setModule(ModuleEntity module) {
		this.module = module;
	}

	public int getButton_id() {
		return button_id;
	}

	public void setButton_id(int button_id) {
		this.button_id = button_id;
	}

	public int getButton_name() {
		return button_name;
	}

	public void setButton_name(int button_name) {
		this.button_name = button_name;
	}
}
