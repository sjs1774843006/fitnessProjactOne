package com.shadow.entity;

public class StaffEntity {

      private int staff_id;
      private String staff_name;
      private String staff_password;
      private int staff_sex;
      private int staff_age;
      private String staff_tel;
      private String staff_address;
      private String staff_idcard;
      private String  headportrait;
      private Integer parentId;

    @Override
    public String toString() {
        return "StaffEntity{" +
                "staff_id=" + staff_id +
                ", staff_name='" + staff_name + '\'' +
                ", staff_password='" + staff_password + '\'' +
                ", staff_sex=" + staff_sex +
                ", staff_age=" + staff_age +
                ", staff_tel='" + staff_tel + '\'' +
                ", staff_address='" + staff_address + '\'' +
                ", staff_idcard='" + staff_idcard + '\'' +
                ", headportrait='" + headportrait + '\'' +
                ", parentId=" + parentId +
                '}';
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public StaffEntity() {
    }

    public StaffEntity(int staff_id, String staff_name, String staff_password, int staff_sex, int staff_age, String staff_tel, String staff_address, int stafftype_id, String staff_idcard, String headportrait) {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.staff_password = staff_password;
        this.staff_sex = staff_sex;
        this.staff_age = staff_age;
        this.staff_tel = staff_tel;
        this.staff_address = staff_address;
        this.staff_idcard = staff_idcard;
        this.headportrait = headportrait;
    }

    public String getStaff_password() {
        return staff_password;
    }

    public void setStaff_password(String staff_password) {
        this.staff_password = staff_password;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public int getStaff_sex() {
        return staff_sex;
    }

    public void setStaff_sex(int staff_sex) {
        this.staff_sex = staff_sex;
    }

    public int getStaff_age() {
        return staff_age;
    }

    public void setStaff_age(int staff_age) {
        this.staff_age = staff_age;
    }

    public String getStaff_tel() {
        return staff_tel;
    }

    public void setStaff_tel(String staff_tel) {
        this.staff_tel = staff_tel;
    }

    public String getStaff_address() {
        return staff_address;
    }

    public void setStaff_address(String staff_address) {
        this.staff_address = staff_address;
    }


    public String getStaff_idcard() {
        return staff_idcard;
    }

    public void setStaff_idcard(String staff_idcard) {
        this.staff_idcard = staff_idcard;
    }

    public String getHeadportrait() {
        return headportrait;
    }

    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait;
    }
}
