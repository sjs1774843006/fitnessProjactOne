package com.shadow.entity;

import java.util.Date;

public class TheLogEntity {

	private int thelog_id;
	private String thelog_contents;
	private String thelog_date;
	private Integer staffmember_id;
	private Integer parentId;

	public TheLogEntity() {
	}

    public TheLogEntity(int thelog_id, String thelog_contents, String thelog_date, Integer staffmember_id) {
        this.thelog_id = thelog_id;
        this.thelog_contents = thelog_contents;
        this.thelog_date = thelog_date;
        this.staffmember_id = staffmember_id;
    }

    @Override
    public String toString() {
        return "TheLogEntity{" +
                "thelog_id=" + thelog_id +
                ", thelog_contents='" + thelog_contents + '\'' +
                ", thelog_date=" + thelog_date +
                ", staffmember_id=" + staffmember_id +
                ", parentId=" + parentId +
                '}';
    }

    public int getThelog_id() {
        return thelog_id;
    }

    public void setThelog_id(int thelog_id) {
        this.thelog_id = thelog_id;
    }

    public String getThelog_contents() {
        return thelog_contents;
    }

    public void setThelog_contents(String thelog_contents) {
        this.thelog_contents = thelog_contents;
    }

    public String getThelog_date() {
        return thelog_date;
    }

    public void setThelog_date(String thelog_date) {
        this.thelog_date = thelog_date;
    }

    public Integer getStaffmember_id() {
        return staffmember_id;
    }

    public void setStaffmember_id(Integer staffmember_id) {
        this.staffmember_id = staffmember_id;
    }

    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
