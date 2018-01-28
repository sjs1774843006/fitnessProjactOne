package com.shadow.dto;


public class TheLogDtoEntity {

	private int thelog_id;
	private String thelog_contents;
	private String thelog_date;
	private String staffmember_name;
	private Integer parentId;

	public TheLogDtoEntity() {
	}

    public TheLogDtoEntity(int thelog_id, String thelog_contents, String thelog_date, String staffmember_name) {
        this.thelog_id = thelog_id;
        this.thelog_contents = thelog_contents;
        this.thelog_date = thelog_date;
        this.staffmember_name = staffmember_name;
    }

    @Override
    public String toString() {
        return "TheLogDtoEntity{" +
                "thelog_id=" + thelog_id +
                ", thelog_contents='" + thelog_contents + '\'' +
                ", thelog_date='" + thelog_date + '\'' +
                ", staffmember_name='" + staffmember_name + '\'' +
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

    public String getStaffmember_name() {
        return staffmember_name;
    }

    public void setStaffmember_name(String staffmember_name) {
        this.staffmember_name = staffmember_name;
    }

    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
