package com.shadow.dao;

import com.shadow.dto.M_ODtoEntity;
import com.shadow.entity.JMOStaffEntity;
import com.shadow.entity.J_MOEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JMOStaffDao {

    void  addJ_MO(JMOStaffEntity j_moEntity);

    void  delJ_MO(int id);

    void  delJ_MO_mid(@Param(value = "m_id") int m_id,@Param(value = "o_id")int o_id);

    List<M_ODtoEntity>  JurisdictionList(int jid);
    List<M_ODtoEntity>  delList(int jid);

    int ListCount();


}
