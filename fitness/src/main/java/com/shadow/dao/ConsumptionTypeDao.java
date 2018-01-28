package com.shadow.dao;

import com.shadow.entity.ConsumptionTypeEntity;
import com.shadow.entity.MemberCardTypeEntity;

import java.util.List;
import java.util.Map;

public interface ConsumptionTypeDao {


    void  add(ConsumptionTypeEntity consumptionTypeEntity);

    void  del(int id);

    void  update(ConsumptionTypeEntity consumptionTypeEntity);

    List<ConsumptionTypeEntity> selectAll(Map<String, Object> map);
    ConsumptionTypeEntity selectOne(int id);

    int  selectCount(Map<String, Object> map);

    int  Count();

}
