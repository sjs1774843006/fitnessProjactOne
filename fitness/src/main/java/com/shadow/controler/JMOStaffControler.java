package com.shadow.controler;


import com.alibaba.fastjson.JSONObject;
import com.shadow.dao.*;
import com.shadow.dto.J_MZtree;
import com.shadow.dto.J_ZtreeEntity;
import com.shadow.dto.M_ODtoEntity;
import com.shadow.entity.JMOStaffEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class JMOStaffControler {

    @Resource
    private StaffDao staffDao;

    @Resource
    private JMOStaffDao jmoStaffDao;

    @Resource
    private TheLogDao theLogDao;


    private JMOStaffEntity jmoAdd = null;
    private JMOStaffEntity jmoUpdate = null;
    private JMOStaffEntity jmoDel = null;
    private JMOStaffEntity jmoQuery = null;
    private JMOStaffEntity jmoChecked = null;

    //增加
    @RequestMapping(value="savestaff_jurisdiction")
    public void save(@RequestParam(value = "jid")int jid, HttpServletRequest  request, HttpServletResponse response) throws IOException{
        InterfaceJumpControler.theLogAdd(request,theLogDao,"员工权限","进行了修改");
        String content = request.getParameter("content");
        List<M_ODtoEntity>  dellist = jmoStaffDao.delList(jid);
        jmoStaffDao.delJ_MO(jid);
        List<J_MZtree> list = JSONObject.parseArray(content, J_MZtree.class);
        String ReturnStr = addJurisdiction(list,jid);
        for (M_ODtoEntity m_oDtoEntity : dellist) {
            jmoStaffDao.delJ_MO_mid(m_oDtoEntity.getM_id(),m_oDtoEntity.getO_id());
        }
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
        jmoChecked = new JMOStaffEntity();
//        jmoChecked.setMo_id(jmoStaffDao.ListCount()+1);
        jmoChecked.setM_id(j_ZtreeEntity.getId());
        jmoChecked.setJ_id(jid);
        jmoChecked.setO_id(11);
        jmoStaffDao.addJ_MO(jmoChecked);
        if ("1".equals(j_ZtreeEntity.getAdd())) {
            jmoAdd = new JMOStaffEntity();
//            jmoAdd.setMo_id(jmoStaffDao.ListCount()+1);
            jmoAdd.setM_id(j_ZtreeEntity.getId());
            jmoAdd.setJ_id(jid);
            jmoAdd.setO_id(1);
            jmoStaffDao.addJ_MO(jmoAdd);
        }
        if ("1".equals(j_ZtreeEntity.getDel())) {
            jmoDel = new JMOStaffEntity();
//            jmoDel.setMo_id(jmoStaffDao.ListCount()+1);
            jmoDel.setM_id(j_ZtreeEntity.getId());
            jmoDel.setJ_id(jid);
            jmoDel.setO_id(2);
            jmoStaffDao.addJ_MO(jmoDel);
        }
        if ("1".equals(j_ZtreeEntity.getUpdate())) {
            jmoUpdate = new JMOStaffEntity();
//            jmoUpdate.setMo_id(jmoStaffDao.ListCount()+1);
            jmoUpdate.setM_id(j_ZtreeEntity.getId());
            jmoUpdate.setJ_id(jid);
            jmoUpdate.setO_id(3);
            jmoStaffDao.addJ_MO(jmoUpdate);
        }
        if ("1".equals(j_ZtreeEntity.getQuery())) {
            jmoQuery = new JMOStaffEntity();
//            jmoQuery.setMo_id(jmoStaffDao.ListCount()+1);
            jmoQuery.setM_id(j_ZtreeEntity.getId());
            jmoQuery.setJ_id(jid);
            jmoQuery.setO_id(4);
            jmoStaffDao.addJ_MO(jmoQuery);
        }
    }

}
