package com.shadow.controler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shadow.dao.*;
import com.shadow.dto.J_MZtree;
import com.shadow.dto.J_ZtreeEntity;
import com.shadow.dto.M_ODtoEntity;
import com.shadow.dto.staff_typeJurEntity;
import com.shadow.entity.ButtonEntity;
import com.shadow.entity.J_MOEntity;
import com.shadow.entity.ModuleEntity;
import com.shadow.entity.StaffEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class J_MOControler {

    @Resource
    private J_MODao j_moDao;

    @Resource
    private ButtonDao  buttonDao;

    @Resource
    private ModuleDao moduleDao;

    @Resource
    private StaffTypeDao staffTypeDao;

    @Resource
    private StaffDao staffDao;

    @Resource
    private TheLogDao theLogDao;


    private J_MOEntity jmoAdd = null;
    private J_MOEntity jmoUpdate = null;
    private J_MOEntity jmoDel = null;
    private J_MOEntity jmoQuery = null;
    private J_MOEntity jmoChecked = null;


    //查询
    @RequestMapping(value = "jurisdiction_data")
    public void jurisdiction(HttpServletRequest request, HttpServletResponse response) throws IOException {

        InterfaceJumpControler.theLogAdd(request,theLogDao,"权限","进行了查询及分页");

        String  jid= request.getParameter("jid");
        Map<String, Object> map = new HashMap<String, Object>();
        List<J_ZtreeEntity> jlist = new ArrayList<J_ZtreeEntity>();
        Map<Integer,List<Integer>> jmap = getJurisdiction(Integer.parseInt(jid));
        List<ModuleEntity> list=moduleDao.selectAll();
        if(list.size()!=0&&list!=null){
            J_ZtreeEntity entity;
            for (ModuleEntity moduleEntity : list) {
                List<ButtonEntity> blist = buttonDao.getButton(moduleEntity.getModule_id());
                entity = new J_ZtreeEntity();
                if(moduleEntity.getModule_id()==0){
                    entity.setId(moduleEntity.getModule_id());
                    entity.setModule(moduleEntity.getModule_name());
                }
                else{
                    if(blist!=null&&blist.size()!=0){
                        for (ButtonEntity buttonEntity : blist) {
                            if(buttonEntity.getButton_name()==1){
                                entity.setAdd("2");
                            }
                            else if(buttonEntity.getButton_name()==2){
                                entity.setDel("2");
                            }
                            else if(buttonEntity.getButton_name()==3){
                                entity.setUpdate("2");
                            }
                            else if(buttonEntity.getButton_name()==4){
                                entity.setQuery("2");
                            }
                        }
                    }
                    else{
                        entity.setId(moduleEntity.getModule_id());
                        entity.setModule(moduleEntity.getModule_name());
                    }
                    if(moduleEntity.getModule_pid()!=0){
                        entity.set__parentId(moduleEntity.getModule_pid());
                    }
                    entity.setId(moduleEntity.getModule_id());
                    entity.setModule(moduleEntity.getModule_name());
                    int s=1;
                    for (Integer key : jmap.keySet()) {
                        List<Integer> tempList = jmap.get(key);
                        for (Integer integer : tempList) {
                            if(key==moduleEntity.getModule_id()){
                                if(integer==1){
                                    entity.setAdd("1");
                                }
                                if(integer==2){
                                    entity.setDel("1");
                                }
                                if(integer==3){
                                    entity.setUpdate("1");
                                }
                                if(integer==4){
                                    entity.setQuery("1");
                                }
                                if(integer==11){
                                    entity.setChecked(true);
                                }
                            }
                        }
                    }
                }
                jlist.add(entity);
            }
        }
        map.put("total", jlist.size());
        map.put("rows", jlist);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }
    public   Map<Integer, List<Integer>> getJurisdiction(int jid) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        int mid = 0;
        int i=0;
        List<M_ODtoEntity> object = j_moDao.JurisdictionList(jid);
        List<Integer> list = null;
        for (M_ODtoEntity objects : object) {
            if(mid!=object.get(i).getM_id()){
                list = new ArrayList<Integer>();
                mid = object.get(i).getM_id();
            }
            list.add(object.get(i).getO_id());
            map.put(mid, list);
            i++;

        }
        return map;
    }


    //增加
    @RequestMapping(value="save_jurisdiction")
    public void save(@RequestParam(value = "jid") int jid, HttpServletRequest  request, HttpServletResponse response) throws IOException{
        String content = request.getParameter("content");
        InterfaceJumpControler.theLogAdd(request,theLogDao,"权限","进行了修改");
        j_moDao.delJ_MO(jid);
        List<J_MZtree> list = JSONObject.parseArray(content, J_MZtree.class);
        String ReturnStr = addJurisdiction(list,jid);
        response.setCharacterEncoding("utf-8");
        if(ReturnStr.equals("success")){
            response.getWriter().write("success");
        }
    }
    public String addJurisdiction(List<J_MZtree> list, int jid) {
        for (J_MZtree j_MZtree : list) {
            List<J_ZtreeEntity> jztree = j_MZtree.getChildren();
            for (J_ZtreeEntity j_ZtreeEntity : jztree) {
                if(j_ZtreeEntity.isChecked()==true){
                    if(j_ZtreeEntity.getChildren()!=null){
                        List<J_ZtreeEntity>  listjztreeEntity=j_ZtreeEntity.getChildren();
                        for (J_ZtreeEntity j_ztreelistEntity : listjztreeEntity) {
                            listJurisdiction(j_ztreelistEntity,jid);
                        }
                    }
                    listJurisdiction(j_ZtreeEntity,jid);
                }
            }
        }
        return "success";
    }
    public  void  listJurisdiction(J_ZtreeEntity j_ZtreeEntity,int jid){
        jmoChecked = new J_MOEntity();
//        jmoChecked.setMo_id(j_moDao.ListCount()+1);
        jmoChecked.setM_id(j_ZtreeEntity.getId());
        jmoChecked.setJ_id(jid);
        jmoChecked.setO_id(11);
        j_moDao.addJ_MO(jmoChecked);
        if ("1".equals(j_ZtreeEntity.getAdd())) {
            jmoAdd = new J_MOEntity();
//            jmoAdd.setMo_id(j_moDao.ListCount()+1);
            jmoAdd.setM_id(j_ZtreeEntity.getId());
            jmoAdd.setJ_id(jid);
            jmoAdd.setO_id(1);
            j_moDao.addJ_MO(jmoAdd);
        }
        if ("1".equals(j_ZtreeEntity.getDel())) {
            jmoDel = new J_MOEntity();
//            jmoDel.setMo_id(j_moDao.ListCount()+1);
            jmoDel.setM_id(j_ZtreeEntity.getId());
            jmoDel.setJ_id(jid);
            jmoDel.setO_id(2);
            j_moDao.addJ_MO(jmoDel);
        }
        if ("1".equals(j_ZtreeEntity.getUpdate())) {
            jmoUpdate = new J_MOEntity();
//            jmoUpdate.setMo_id(j_moDao.ListCount()+1);
            jmoUpdate.setM_id(j_ZtreeEntity.getId());
            jmoUpdate.setJ_id(jid);
            jmoUpdate.setO_id(3);
            j_moDao.addJ_MO(jmoUpdate);
        }
        if ("1".equals(j_ZtreeEntity.getQuery())) {
            jmoQuery = new J_MOEntity();
//            jmoQuery.setMo_id(j_moDao.ListCount()+1);
            jmoQuery.setM_id(j_ZtreeEntity.getId());
            jmoQuery.setJ_id(jid);
            jmoQuery.setO_id(4);
            j_moDao.addJ_MO(jmoQuery);
        }
    }

    @RequestMapping(value="controlbutton")
    public void controlbutton(@RequestParam(value = "m_id")int m_id,HttpServletRequest  request,HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        StaffEntity  staff = (StaffEntity) session.getAttribute("staff");
        List<staff_typeJurEntity>  staffdetype = staffDao.staff_id_type(staff.getStaff_id());
        String strJson ="[";
        for (staff_typeJurEntity integer : staffdetype) {
            List<M_ODtoEntity> j_mo_btn = staffDao.j_mo_btn(integer.getType_id(),m_id);
            if(j_mo_btn.size()>0){
                for (M_ODtoEntity integer1 : j_mo_btn) {
                    if(1==integer1.getO_id()){
                        strJson +="{\"text\":\"add\"},";
                    }else if(2==integer1.getO_id()){
                        strJson +="{\"text\":\"del\"},";
                    }else if(3==integer1.getO_id()){
                        strJson +="{\"text\":\"update\"},";
                    }else if(4==integer1.getO_id()){
                        strJson +="{\"text\":\"all\"},{\"staff\":\""+staff.getStaff_name()+"\"}";
                    }
                }
            }
        }
        List<M_ODtoEntity> jmo_staff_btn = staffDao.jmo_staff_btn(staff.getStaff_id(),m_id);
          if(jmo_staff_btn.size()>0){
              for (M_ODtoEntity m_oDtoEntity : jmo_staff_btn) {
                  if(1==m_oDtoEntity.getO_id()){
                      strJson +="{\"text\":\"add\"},";
                  }else if(2==m_oDtoEntity.getO_id()){
                      strJson +="{\"text\":\"del\"},";
                  }else if(3==m_oDtoEntity.getO_id()){
                      strJson +="{\"text\":\"update\"},";
                  }else if(4==m_oDtoEntity.getO_id()){
                      strJson +="{\"text\":\"all\"},{\"staff\":\""+staff.getStaff_name()+"\"}";
                  }
              }
          }

        strJson+="]";
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(strJson);
    }


}
