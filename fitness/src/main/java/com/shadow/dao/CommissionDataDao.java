package com.shadow.dao;

import com.shadow.entity.CommissionDataEntity;

import java.util.List;
import java.util.Map;

public interface CommissionDataDao {


    void  add(CommissionDataEntity royaltyRateEntity);

    void  del(int id);

    void  update(CommissionDataEntity royaltyRateEntity);

    List<CommissionDataEntity> selectAll(Map<String, Object> map);

    CommissionDataEntity selectOne(int id);

    int  selectCount(Map<String, Object> map);

    int  Count();

}
