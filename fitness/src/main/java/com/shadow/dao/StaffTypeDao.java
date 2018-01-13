package com.shadow.dao;

import com.shadow.entity.DataDictionaryEntity;
import com.shadow.entity.StaffTypeEntity;

import java.util.List;
import java.util.Map;

public interface StaffTypeDao {

    void  addStaffType(StaffTypeEntity staffTypeEntity);

    void  delStaffType(int id);

    void  updateStaffType(StaffTypeEntity staffTypeEntity);

    List<StaffTypeEntity> selectAllStaffType(Map<String, Object> map);

    int  selectCountStaffType(Map<String, Object> map);

    int  CountStaffType();

}
