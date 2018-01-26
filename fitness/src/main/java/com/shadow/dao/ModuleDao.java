package com.shadow.dao;

import com.shadow.entity.ModuleEntity;

import java.util.List;

public interface ModuleDao {

    List<ModuleEntity>  selectAll();
    List<ModuleEntity>  selectOneAll(int id);
    int  selectCount(int id);

}
