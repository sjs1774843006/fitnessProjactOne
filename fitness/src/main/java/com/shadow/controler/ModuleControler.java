package com.shadow.controler;


import com.alibaba.fastjson.JSON;
import com.shadow.dao.ModuleDao;
import com.shadow.entity.ModuleEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ModuleControler {

    @Resource
    private ModuleDao  moduleDao;

    //手风琴.
    @RequestMapping(value = "queryModulelist")
    public void  query4List(HttpServletRequest request, HttpServletResponse  response) throws Exception{
        List<ModuleEntity>  list  =  moduleDao.selectAll();
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(list));
    }



}
