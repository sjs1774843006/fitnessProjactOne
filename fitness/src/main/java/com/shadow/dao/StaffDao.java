package com.shadow.dao;

import com.shadow.dto.M_ODtoEntity;
import com.shadow.dto.staff_typeJurEntity;
import com.shadow.entity.StaffEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface StaffDao {
    void staff_Del(int id);
    void staff_Add(StaffEntity staffEntity);
    void staff_Upd(StaffEntity staffEntity);
    List<StaffEntity> staff_All(Map<String, Object> map);
    int  selectCount(Map<String, Object> map);
    int  Count();
    StaffEntity  selectOne(Map<String, Object> map);

    List<staff_typeJurEntity>  staff_id_type(int id);

}
