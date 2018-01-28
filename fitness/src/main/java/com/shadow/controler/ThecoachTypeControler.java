package com.shadow.controler;


import com.alibaba.fastjson.JSON;
import com.shadow.dao.ConsumptionTypeDao;
import com.shadow.dao.TheLogDao;
import com.shadow.dao.ThecoachTypeDao;
import com.shadow.entity.ConsumptionTypeEntity;
import com.shadow.entity.ThecoachTypeEntity;
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
public class ThecoachTypeControler {

    @Resource
    private ThecoachTypeDao thecoachTypeDao;

    @Resource
    private TheLogDao theLogDao;


    //    数据查询分页
    @RequestMapping(value = "querythecoachlist")
    public void  query4List(HttpServletRequest request, HttpServletResponse  response) throws Exception{

        InterfaceJumpControler.theLogAdd(request,theLogDao,"thecoach_type","查询及分页");

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
        int count = thecoachTypeDao.selectCount(querymap);
        List<ThecoachTypeEntity>  list  =  thecoachTypeDao.selectAll(querymap);
        int n=0;
        for (ThecoachTypeEntity entity : list) {
            entity.setParentId(n);
            n++;
        }
        map.put("total",count);
        map.put("rows",list);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }


//    数据删除
    @RequestMapping(value = "delthecoach")
    public void  delete(@RequestParam(value = "id")int id, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            ThecoachTypeEntity consumptionTypeEntity = thecoachTypeDao.selectOne(id);
            InterfaceJumpControler.theLogAdd(request,theLogDao,"thecoach_type","删除了《"+consumptionTypeEntity.toString()+"这条数据");
            thecoachTypeDao.del(id);
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
    @RequestMapping(value = "updatethecoach")
    public void  update(ThecoachTypeEntity thecoachTypeEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            ThecoachTypeEntity typeEntity = thecoachTypeDao.selectOne(thecoachTypeEntity.getThecoach_type_id());
            InterfaceJumpControler.theLogAdd(request,theLogDao,"thecoach_type","修改了数据，原数据为：《 "+typeEntity.toString()+" 》");
            thecoachTypeDao.update(thecoachTypeEntity);
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
    @RequestMapping(value = "addthecoach")
    public void  add(ThecoachTypeEntity thecoachTypeEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            thecoachTypeEntity.setThecoach_type_id(thecoachTypeDao.Count()+1);
            thecoachTypeDao.add(thecoachTypeEntity);
            ThecoachTypeEntity typeEntity = thecoachTypeDao.selectOne(thecoachTypeDao.Count());
            InterfaceJumpControler.theLogAdd(request,theLogDao,"thecoach_type","增加了一条新数据，新数据为：《 "+typeEntity.toString()+" 》");
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
