package com.shadow.dao;

import com.shadow.entity.ProgressEntity;
import com.shadow.entity.StaffTypeEntity;

import java.util.List;
import java.util.Map;

public interface ProgressDao {

    void  add(ProgressEntity progressEntity);

    void  del(int id);

    void  update(ProgressEntity progressEntity);

    List<ProgressEntity> selectAll(Map<String, Object> map);

    ProgressEntity selectOne(int id);

    int  selectCount(Map<String, Object> map);

    int  Count();

}
