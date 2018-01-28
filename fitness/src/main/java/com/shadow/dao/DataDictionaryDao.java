package com.shadow.dao;

import com.shadow.entity.DataDictionaryEntity;

import java.util.List;
import java.util.Map;

public interface DataDictionaryDao {

    List<DataDictionaryEntity> selectAll(Map<String,Object> map);
    DataDictionaryEntity selectOne(int id);

    int  selectCount(Map<String,Object> map);

    void  del(int id);

    void  update(DataDictionaryEntity dataDictionaryEntity);

}
