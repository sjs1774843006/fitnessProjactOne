package com.shadow.controler;


import com.alibaba.fastjson.JSON;
import com.shadow.dao.CommissionDataDao;
import com.shadow.dao.StaffDao;
import com.shadow.dao.TheLogDao;
import com.shadow.dto.TheLogDtoEntity;
import com.shadow.entity.CommissionDataEntity;
import com.shadow.entity.StaffEntity;
import com.shadow.entity.TheLogEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TheLogControler {

    @Resource
    private TheLogDao theLogDao;

    @Resource
    private StaffDao staffDao;


//    数据查询分页
    @RequestMapping(value = "queryTheLoglist")
    public void  query4List(HttpServletRequest request, HttpServletResponse  response) throws Exception{
        HttpSession session = request.getSession();
        StaffEntity staffEntity = (StaffEntity) session.getAttribute("staff");
        Map<String,Object> querymap = new HashMap<String,Object>();
        List<TheLogDtoEntity>  theLogDtoEntityArrayList = new ArrayList<TheLogDtoEntity>();
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
        int count = theLogDao.selectCount(querymap);
        List<TheLogEntity>  list  =  theLogDao.selectAll(querymap);
        int n=0;
        TheLogDtoEntity theLogDtoEntity;
        for (TheLogEntity entity : list) {
            theLogDtoEntity = new TheLogDtoEntity();
            theLogDtoEntity.setThelog_id(entity.getThelog_id());
            theLogDtoEntity.setThelog_contents(entity.getThelog_contents());
            theLogDtoEntity.setThelog_date(entity.getThelog_date());
            theLogDtoEntity.setStaffmember_name(staffDao.staffOnename(staffEntity.getStaff_id()));
            theLogDtoEntity.setParentId(n);
            theLogDtoEntityArrayList.add(theLogDtoEntity);
            n++;
        }
        map.put("total",count);
        map.put("rows",theLogDtoEntityArrayList);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }


//    数据删除
    @RequestMapping(value = "delTheLog")
    public void  delete(@RequestParam(value = "id")int id, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            theLogDao.del(id);
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
