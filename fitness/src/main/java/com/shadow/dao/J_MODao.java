package com.shadow.dao;

import com.shadow.dto.M_ODtoEntity;
import com.shadow.entity.J_MOEntity;

import java.util.List;

public interface J_MODao {

    void  addJ_MO(J_MOEntity j_moEntity);

    void  delJ_MO(int id);

    List<M_ODtoEntity>  JurisdictionList(int jid);

    int ListCount();


}
