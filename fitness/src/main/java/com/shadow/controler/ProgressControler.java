package com.shadow.controler;


import com.alibaba.fastjson.JSON;
import com.shadow.dao.ProgressDao;
import com.shadow.dao.StaffTypeDao;
import com.shadow.dao.TheLogDao;
import com.shadow.entity.ConsumptionTypeEntity;
import com.shadow.entity.ProgressEntity;
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
public class ProgressControler {

    @Resource
    private ProgressDao progressDao;

    @Resource
    private TheLogDao theLogDao;


    //    数据查询分页
    @RequestMapping(value = "queryProgresslist")
    public void  query4List(HttpServletRequest request, HttpServletResponse  response) throws Exception{


        InterfaceJumpControler.theLogAdd(request,theLogDao,"progress","查询及分页");


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
        int count = progressDao.selectCount(querymap);
        List<ProgressEntity>  list  =  progressDao.selectAll(querymap);
        int n=0;
        for (ProgressEntity entity : list) {
            entity.setParentId(n);
            n++;
        }
        map.put("total",count);
        map.put("rows",list);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }

    //    数据修改
    @RequestMapping(value = "updateProgress")
    public void  update(ProgressEntity progressEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            ProgressEntity typeEntity = progressDao.selectOne(progressEntity.getParentId());
            InterfaceJumpControler.theLogAdd(request,theLogDao,"progress","修改了数据，原数据为：《 "+typeEntity.toString()+" 》");

            progressDao.update(progressEntity);
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

//    数据删除
    @RequestMapping(value = "delProgress")
    public void  delete(@RequestParam(value = "id")int id, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            ProgressEntity entity = progressDao.selectOne(id);
            InterfaceJumpControler.theLogAdd(request,theLogDao,"progress","删除了《"+entity.toString()+"这条数据");
            progressDao.del(id);
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

    //    数据增加
    @RequestMapping(value = "addProgress")
    public void  add(ProgressEntity progressEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            progressEntity.setProgress_id(progressDao.Count()+1);
            progressDao.add(progressEntity);
            ProgressEntity typeEntity = progressDao.selectOne(progressDao.Count());
            InterfaceJumpControler.theLogAdd(request,theLogDao,"progress","增加了一条新数据，新数据为：《 "+typeEntity.toString()+" 》");

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
