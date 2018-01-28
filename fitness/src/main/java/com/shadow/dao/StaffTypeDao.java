package com.shadow.dao;

import com.shadow.entity.StaffTypeEntity;

import java.util.List;
import java.util.Map;

public interface StaffTypeDao {

    void  addStaffType(StaffTypeEntity staffTypeEntity);

    void  delStaffType(int id);

    void  updateStaffType(StaffTypeEntity staffTypeEntity);

    List<StaffTypeEntity> selectAllStaffType(Map<String, Object> map);

    StaffTypeEntity selectOne(int id);

    List<StaffTypeEntity> selectAllJurisdictionVo();

    int  selectCountStaffType(Map<String, Object> map);

    int  CountStaffType();


    //登录系统验证
    List<StaffTypeEntity> selectloginjurisdiction(Map<String, Object> map);

    List<StaffTypeEntity> selectstafftype(int staff_id);

    void  addStaff_Type(Map<String, Object> map);

    void  delStaff_Type(int id);

    void  deljmo_staff(int id);


}
