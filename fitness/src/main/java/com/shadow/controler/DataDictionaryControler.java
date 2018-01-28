package com.shadow.controler;


import com.alibaba.fastjson.JSON;
import com.shadow.dao.DataDictionaryDao;
import com.shadow.dao.TheLogDao;
import com.shadow.entity.ConsumptionTypeEntity;
import com.shadow.entity.DataDictionaryEntity;
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
public class DataDictionaryControler {

    @Resource
    private DataDictionaryDao dataDictionaryDao;

    @Resource
    private TheLogDao theLogDao;


    //    数据查询分页
    @RequestMapping(value = "queryDatalist")
    public void  query4List(HttpServletRequest request, HttpServletResponse  response) throws Exception{

        InterfaceJumpControler.theLogAdd(request,theLogDao,"data_dictionary","查询及分页");
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
        int count = dataDictionaryDao.selectCount(querymap);
        List<DataDictionaryEntity>  list  =  dataDictionaryDao.selectAll(querymap);
        int n=0;
        for (DataDictionaryEntity entity : list) {
            entity.setParentId(n);
            n++;
        }
        map.put("total",count);
        map.put("rows",list);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }


//    数据删除
    @RequestMapping(value = "delData")
    public void  delete(@RequestParam(value = "id")int id, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            DataDictionaryEntity dataDictionaryEntity = dataDictionaryDao.selectOne(id);
            InterfaceJumpControler.theLogAdd(request,theLogDao,"data_dictionary","删除了《"+dataDictionaryEntity.toString()+"这条数据");
            dataDictionaryDao.del(id);
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

//    数据修改
    @RequestMapping(value = "updateData")
    public void  update(DataDictionaryEntity dataDictionaryEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            DataDictionaryEntity dictionaryEntity = dataDictionaryDao.selectOne(dataDictionaryEntity.getData_id());
            InterfaceJumpControler.theLogAdd(request,theLogDao,"data_dictionary","修改了数据，原数据为：《 "+dictionaryEntity.toString()+" 》");
            dataDictionaryDao.update(dataDictionaryEntity);
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
