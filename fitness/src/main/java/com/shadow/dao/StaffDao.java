package com.shadow.dao;

import com.shadow.dto.M_ODtoEntity;
import com.shadow.dto.staff_typeJurEntity;
import com.shadow.entity.StaffEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface StaffDao {
    void staff_Del(int id);
    void staff_Add(StaffEntity staffEntity);
    void staff_Upd(StaffEntity staffEntity);
    List<StaffEntity> staff_All(Map<String, Object> map);

    StaffEntity staff_One(int id);

    int  selectCount(Map<String, Object> map);
    int  Count();
    StaffEntity  selectOne(Map<String, Object> map);

    List<staff_typeJurEntity>  staff_id_type(int id);

    String  staffOnename(int id);


    List<M_ODtoEntity> j_mo_btn(@Param(value = "type_id")int type_id, @Param(value = "m_id")int m_id);

    List<M_ODtoEntity> jmo_staff_btn(@Param(value = "staff_id")int staff_id,@Param(value = "m_id")int m_id);


}
