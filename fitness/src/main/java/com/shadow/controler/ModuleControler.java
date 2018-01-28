package com.shadow.controler;


import com.alibaba.fastjson.JSON;
import com.shadow.dao.ModuleDao;
import com.shadow.dao.TheLogDao;
import com.shadow.dto.StaffDtoEntity;
import com.shadow.entity.ModuleEntity;
import com.shadow.entity.StaffEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ModuleControler {

    @Resource
    private ModuleDao  moduleDao;

    @Resource
    private TheLogDao theLogDao;


    //手风琴.
    @RequestMapping(value = "queryModulelist")
    public void  query4List(HttpServletRequest request, HttpServletResponse  response) throws Exception{

        InterfaceJumpControler.theLogAdd(request,theLogDao,"模块","进行了查询及分页");

        List<ModuleEntity>  list = new ArrayList<ModuleEntity>();
        ModuleEntity module;
        HttpSession session = request.getSession();
        StaffEntity staffEntity = (StaffEntity) session.getAttribute("staff");
        List<ModuleEntity> j_mo_module = moduleDao.selectModuleJ_mo(staffEntity.getStaff_id());
        List<ModuleEntity> jmo_staff = moduleDao.selectModuleJmo_staff(staffEntity.getStaff_id());
        for (ModuleEntity moduleEntity : j_mo_module) {
            module = new ModuleEntity();
            module.setModule_id(moduleEntity.getModule_id());
            module.setModule_name(moduleEntity.getModule_name());
            module.setModule_jsp(moduleEntity.getModule_jsp());
            module.setModule_pid(moduleEntity.getModule_pid());
            module.set_parentId(moduleEntity.get_parentId());
            module.setModule_parent(moduleEntity.getModule_parent());
            list.add(module);
            if(moduleEntity.getModule_parent()==true){
                List<ModuleEntity> moduleEntities = moduleDao.selectOneAll(moduleEntity.getModule_pid());
                if(moduleEntities.size()>0){
                    for (ModuleEntity entity : moduleEntities) {
                        module = new ModuleEntity();
                        module.setModule_id(entity.getModule_id());
                        module.setModule_name(entity.getModule_name());
                        module.setModule_jsp(entity.getModule_jsp());
                        module.setModule_pid(entity.getModule_pid());
                        module.set_parentId(entity.get_parentId());
                        module.setModule_parent(entity.getModule_parent());
                        list.add(module);

                    }
                }
            }
        }
        for (ModuleEntity module_Entity : jmo_staff) {
            module = new ModuleEntity();
            module.setModule_id(module_Entity.getModule_id());
            module.setModule_name(module_Entity.getModule_name());
            module.setModule_jsp(module_Entity.getModule_jsp());
            module.setModule_pid(module_Entity.getModule_pid());
            module.set_parentId(module_Entity.get_parentId());
            module.setModule_parent(module_Entity.getModule_parent());
            list.add(module);
            if(module_Entity.getModule_parent()==true){
                List<ModuleEntity> moduleEntitie_s =  moduleDao.selectOneAll(module_Entity.getModule_pid());
                if(moduleEntitie_s.size()>0){
                    for (ModuleEntity entitys : moduleEntitie_s) {
                        module = new ModuleEntity();
                        module.setModule_id(entitys.getModule_id());
                        module.setModule_name(entitys.getModule_name());
                        module.setModule_jsp(entitys.getModule_jsp());
                        module.setModule_pid(entitys.getModule_pid());
                        module.set_parentId(entitys.get_parentId());
                        module.setModule_parent(entitys.getModule_parent());
                        list.add(module);
                    }
                }
            }
        }
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(list));

    }



}
