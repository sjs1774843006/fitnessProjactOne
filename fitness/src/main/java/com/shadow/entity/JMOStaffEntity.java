package com.shadow.entity;

public class JMOStaffEntity {

//	private int mo_id;
	private int j_id;
	private int m_id;
	private int o_id;

	public JMOStaffEntity() {

	}

	public JMOStaffEntity(int j_id, int m_id, int o_id) {
//		this.mo_id = mo_id;
		this.j_id = j_id;
		this.m_id = m_id;
		this.o_id = o_id;
	}

	@Override
	public String toString() {
		return "J_MOEntity{" +
//				"mo_id=" + mo_id +
				", j_id=" + j_id +
				", m_id=" + m_id +
				", o_id=" + o_id +
				'}';
	}

//	public int getMo_id() {
//		return mo_id;
//	}
//	public void setMo_id(int mo_id) {
//		this.mo_id = mo_id;
//	}
	public int getJ_id() {
		return j_id;
	}
	public void setJ_id(int j_id) {
		this.j_id = j_id;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	
	

}
