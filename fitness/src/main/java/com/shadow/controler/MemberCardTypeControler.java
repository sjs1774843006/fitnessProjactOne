package com.shadow.controler;


import com.alibaba.fastjson.JSON;
import com.shadow.dao.MemberCardTypeDao;
import com.shadow.dao.StaffTypeDao;
import com.shadow.entity.MemberCardTypeEntity;
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
public class MemberCardTypeControler {

    @Resource
    private MemberCardTypeDao memberCardTypeDao;


//    数据查询分页
    @RequestMapping(value = "queryMemberCardTypelist")
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
        int count = memberCardTypeDao.selectCount(querymap);
        List<MemberCardTypeEntity>  list  =  memberCardTypeDao.selectAll(querymap);
        int n=0;
        for (MemberCardTypeEntity entity : list) {
            entity.setParentId(n);
            n++;
        }
        map.put("total",count);
        map.put("rows",list);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }


//    数据删除
    @RequestMapping(value = "delMemberCardType")
    public void  delete(@RequestParam(value = "id")int id, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            memberCardTypeDao.del(id);
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
    @RequestMapping(value = "updateMemberCardType")
    public void  update(MemberCardTypeEntity memberCardTypeEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            memberCardTypeDao.update(memberCardTypeEntity);
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
    @RequestMapping(value = "addMemberCardType")
    public void  add(MemberCardTypeEntity memberCardTypeEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            memberCardTypeEntity.setMember_card_type_id(memberCardTypeDao.Count()+1);
            memberCardTypeDao.add(memberCardTypeEntity);
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


}
