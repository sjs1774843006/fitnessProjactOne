package com.shadow.controler;


import com.alibaba.fastjson.JSON;
import com.shadow.dao.CommissionDataDao;
import com.shadow.entity.CommissionDataEntity;
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
public class CommissionDataControler {

    @Resource
    private CommissionDataDao commissionDataDao;


//    数据查询分页
    @RequestMapping(value = "querycommissiondatalist")
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
        int count = commissionDataDao.selectCount(querymap);
        List<CommissionDataEntity>  list  =  commissionDataDao.selectAll(querymap);
        int n=0;
        for (CommissionDataEntity entity : list) {
            entity.setParentId(n);
            n++;
        }
        map.put("total",count);
        map.put("rows",list);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }


//    数据删除
    @RequestMapping(value = "delcommissiondata")
    public void  delete(@RequestParam(value = "id")int id, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            commissionDataDao.del(id);
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
    @RequestMapping(value = "updatecommissiondata")
    public void  update(CommissionDataEntity royaltyRateEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            commissionDataDao.update(royaltyRateEntity);
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
    @RequestMapping(value = "addcommissiondata")
    public void  add(CommissionDataEntity royaltyRateEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            royaltyRateEntity.setCommission_data_id(commissionDataDao.Count()+1);
            commissionDataDao.add(royaltyRateEntity);
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
