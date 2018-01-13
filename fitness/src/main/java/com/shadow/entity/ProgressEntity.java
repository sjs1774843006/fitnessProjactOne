package com.shadow.entity;

public class ProgressEntity {

	private int progress_id;
	private String progress;
	private Integer parentId;

	public ProgressEntity() {
	}

	public ProgressEntity(int progress_id, String progress) {
		this.progress_id = progress_id;
		this.progress = progress;
	}

	@Override
	public String toString() {
		return "ProgressEntity{" +
				"progress_id=" + progress_id +
				", progress='" + progress + '\'' +
				", parentId=" + parentId +
				'}';
	}

	public int getProgress_id() {
		return progress_id;
	}

	public void setProgress_id(int progress_id) {
		this.progress_id = progress_id;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
