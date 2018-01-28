package com.shadow.dao;

import com.shadow.entity.ModuleEntity;

import java.util.List;

public interface ModuleDao {

    List<ModuleEntity>  selectAll();
    List<ModuleEntity>  selectOneAll(int id);

    List<ModuleEntity>  selectModuleJmo_staff(int id);
    List<ModuleEntity>  selectModuleJ_mo(int id);


//    List<ModuleEntity>  selectModulebooleag(int id);


    int  selectCount(int id);

}
