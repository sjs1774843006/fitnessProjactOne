package com.shadow.controler;


import com.alibaba.fastjson.JSON;
import com.shadow.dao.DataDictionaryDao;
import com.shadow.dao.StaffTypeDao;
import com.shadow.entity.DataDictionaryEntity;
import com.shadow.entity.StaffTypeEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StaffTypeControler {

    @Resource
    private StaffTypeDao staffTypeDao;


//    数据查询分页
    @RequestMapping(value = "queryStaffTypelist")
    public void  query4List(HttpServletRequest request, HttpServletResponse  response) throws Exception{
        Map<String,Object> querymap = new HashMap<String,Object>();
        String  pageindex = request.getParameter("offset");
        String  pagesize = request.getParameter("limit");
        String  name = request.getParameter("searchText");
        String  data_name = null;
        if(!StringUtils.isEmpty(name)){
            data_name = " '%"+name+"%' ";
            querymap.put("name",data_name);
        }
        querymap.put("pageindex",Integer.parseInt(pageindex));
        querymap.put("pagesize",Integer.parseInt(pagesize));

        Map<String,Object> map = new HashMap<String,Object>();
        int count = staffTypeDao.selectCountStaffType(querymap);
        List<StaffTypeEntity>  list  =  staffTypeDao.selectAllStaffType(querymap);
        int n=0;
        for (StaffTypeEntity entity : list) {
            entity.setParentId(n);
            n++;
        }
        map.put("total",count);
        map.put("rows",list);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }


//    数据删除
    @RequestMapping(value = "delStaffType")
    public void  delete(@RequestParam(value = "id")int id, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            staffTypeDao.delStaffType(id);
        }catch (Exception e) {
            flag = true;
        }finally{
            response.setCharacterEncoding("utf-8");
            if(flag){ //定义boolean flag = false;   不发生改变就为false
                response.getWriter().write("{\"success\":\"defeated\"}");
            }else{
                response.getWriter().write("{\"success\":\"success\"}");
            }
        }

    }

//    数据修改
    @RequestMapping(value = "updateStaffType")
    public void  update(StaffTypeEntity staffTypeEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            staffTypeDao.updateStaffType(staffTypeEntity);
        } catch (Exception e) {
            flag = true;
        }finally {
            response.setCharacterEncoding("utf-8");
            if(flag){ //定义boolean flag = false;   不发生改变就为false
                response.getWriter().write("{\"success\":\"defeated\"}");
            }else{
                response.getWriter().write("{\"success\":\"success\"}");
            }
        }
    }
//    数据增加
    @RequestMapping(value = "addStaffType")
    public void  add(StaffTypeEntity staffTypeEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            staffTypeEntity.setType_id(staffTypeDao.CountStaffType()+1);
            staffTypeDao.addStaffType(staffTypeEntity);
        } catch (Exception e) {
            flag = true;
        }finally {
            response.setCharacterEncoding("utf-8");
            if(flag){ //定义boolean flag = false;   不发生改变就为false
                response.getWriter().write("{\"success\":\"defeated\"}");
            }else{
                response.getWriter().write("{\"success\":\"success\"}");
            }
        }
    }

    //    用户授角表的数据增加
    @RequestMapping(value = "addStaff_Type")
    public void  add(@RequestParam(value = "staff_id")int staff_id,@RequestParam( value = "type_id") int type_id, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        Map<String,Object>  map = new HashMap<String,Object>();
        map.put("staff_id",staff_id);
        map.put("type_id",type_id);
        boolean flag = false;
        try {
            //删除用户对应的角色
            staffTypeDao.delStaff_Type(staff_id);
            //重新保存
            staffTypeDao.addStaff_Type(map);

        } catch (Exception e) {
            flag = true;
        }finally {
            response.setCharacterEncoding("utf-8");
            if(flag){ //定义boolean flag = false;   不发生改变就为false
                response.getWriter().write("{\"success\":\"defeated\"}");
            }else{
                response.getWriter().write("{\"success\":\"success\"}");
            }
        }
    }
    @RequestMapping(value = "delStaff_Type")
    public void  deleteType(@RequestParam(value = "staff_id")int staff_id, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
                //删除用户角色表
                staffTypeDao.delStaff_Type(staff_id);
                //删除一个用户单独的权限
                staffTypeDao.deljmo_staff(staff_id);
        }catch (Exception e) {
            flag = true;
        }finally{
            response.setCharacterEncoding("utf-8");
            if(flag){ //定义boolean flag = false;   不发生改变就为false
                response.getWriter().write("{\"success\":\"defeated\"}");
            }else{
                response.getWriter().write("{\"success\":\"success\"}");
            }
        }

    }



}
