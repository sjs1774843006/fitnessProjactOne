package com.shadow.dao;

import com.shadow.entity.MemberCardTypeEntity;

import java.util.List;
import java.util.Map;

public interface MemberCardTypeDao {


    void  add(MemberCardTypeEntity memberCardTypeEntity);

    void  del(int id);

    void  update(MemberCardTypeEntity memberCardTypeEntity);

    List<MemberCardTypeEntity> selectAll(Map<String, Object> map);

    int  selectCount(Map<String, Object> map);

    int  Count();

}
