package com.shadow.entity;

public class ThecoachTypeEntity {

	private int Thecoach_type_id;
	private String Thecoach_type;
	private Integer Site_id;
	private Integer Thecoach_Commission_id;
	private Integer Thecoach_courses_id;
	private Integer parentId;

	public ThecoachTypeEntity() {
	}

    public ThecoachTypeEntity(int Thecoach_type_id, String Thecoach_type, Integer Site_id, Integer Thecoach_Commission_id, Integer Thecoach_courses_id) {
        Thecoach_type_id = Thecoach_type_id;
        Thecoach_type = Thecoach_type;
        Site_id = Site_id;
        Thecoach_Commission_id = Thecoach_Commission_id;
        Thecoach_courses_id = Thecoach_courses_id;
    }

    @Override
    public String toString() {
        return "ThecoachTypeEntity{" +
                "Thecoach_type_id=" + Thecoach_type_id +
                ", Thecoach_type='" + Thecoach_type + '\'' +
                ", Site_id=" + Site_id +
                ", Thecoach_Commission_id=" + Thecoach_Commission_id +
                ", Thecoach_courses_id=" + Thecoach_courses_id +
                ", parentId=" + parentId +
                '}';
    }

    public int getThecoach_type_id() {
        return Thecoach_type_id;
    }

    public void setThecoach_type_id(int thecoach_type_id) {
        Thecoach_type_id = thecoach_type_id;
    }

    public String getThecoach_type() {
        return Thecoach_type;
    }

    public void setThecoach_type(String thecoach_type) {
        Thecoach_type = thecoach_type;
    }

    public Integer getSite_id() {
        return Site_id;
    }

    public void setSite_id(Integer site_id) {
        Site_id = site_id;
    }

    public Integer getThecoach_Commission_id() {
        return Thecoach_Commission_id;
    }

    public void setThecoach_Commission_id(Integer thecoach_Commission_id) {
        Thecoach_Commission_id = thecoach_Commission_id;
    }

    public Integer getThecoach_courses_id() {
        return Thecoach_courses_id;
    }

    public void setThecoach_courses_id(Integer thecoach_courses_id) {
        Thecoach_courses_id = thecoach_courses_id;
    }

    public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
