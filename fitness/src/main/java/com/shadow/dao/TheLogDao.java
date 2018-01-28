package com.shadow.dao;

import com.shadow.entity.TheLogEntity;
import com.shadow.entity.ThecoachTypeEntity;

import java.util.List;
import java.util.Map;

public interface TheLogDao {


    void  add(TheLogEntity theLogEntity);

    void  del(int id);

    void  update(TheLogEntity theLogEntity);

    List<TheLogEntity> selectAll(Map<String, Object> map);

    int  selectCount(Map<String, Object> map);


}
