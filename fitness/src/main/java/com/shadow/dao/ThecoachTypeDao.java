package com.shadow.dao;

import com.shadow.entity.ThecoachTypeEntity;

import java.util.List;
import java.util.Map;

public interface ThecoachTypeDao {


    void  add(ThecoachTypeEntity thecoachTypeEntity);

    void  del(int id);

    void  update(ThecoachTypeEntity thecoachTypeEntity);

    List<ThecoachTypeEntity> selectAll(Map<String, Object> map);

    int  selectCount(Map<String, Object> map);

    int  Count();

}
