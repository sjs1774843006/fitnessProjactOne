package com.shadow.entity;

public class MemberCardTypeEntity {

	private int member_card_type_id;
	private String member_card_typename;
	private int  site_id ;
	private Integer parentId;

	public MemberCardTypeEntity() {
	}

	public MemberCardTypeEntity(int member_card_type_id, String member_card_typename,int site_id) {
		this.member_card_type_id = member_card_type_id;
		this.member_card_typename = member_card_typename;
		this.site_id = site_id;
	}

	public int getMember_card_type_id() {
		return member_card_type_id;
	}

	public void setMember_card_type_id(int member_card_type_id) {
		this.member_card_type_id = member_card_type_id;
	}

	public String getMember_card_typename() {
		return member_card_typename;
	}

	public void setMember_card_typename(String member_card_typename) {
		this.member_card_typename = member_card_typename;
	}

	public int getSite_id() {
		return site_id;
	}

	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}

	@Override
	public String toString() {
		return "MemberCardTypeEntity{" +
				"member_card_type_id=" + member_card_type_id +
				", member_card_typename='" + member_card_typename + '\'' +
				", site_id=" + site_id +
				", parentId=" + parentId +
				'}';
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
