package com.shadow.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 此类只负责界面跳转，不做其他用途
 */
@Controller
public class InterfaceJumpControler {

//    跳转进入首页
    @RequestMapping(value = "jsp")
    public String  query4List() throws Exception{
        return "index";
    }

//    跳转进入模块管理界面
    @RequestMapping(value = "disjsp")
    public String  display() throws Exception{
        return "display";
    }

//    跳转进入数据字典界面
    @RequestMapping(value = "dictjsp")
    public String  dictionary() throws Exception{
        return "dictionary";
    }

//    跳转进入数据字典员工类型数据项界面
    @RequestMapping(value = "StaffTypeJsp")
    public String  StaffType() throws Exception{
        return "stafftype";
    }

//    跳转进入数据字典业务进度数据项界面
    @RequestMapping(value = "progressJsp")
    public String  Progress() throws Exception{
        return "progress";
    }

//    跳转进入数据字典会员卡类型数据项界面
    @RequestMapping(value = "membercardtypeJsp")
    public String  Membercardtype() throws Exception{
        return "membercardtype";
    }

//    跳转进入数据字典会员消费类型数据项界面
    @RequestMapping(value = "consumptiontypeJsp")
    public String  Consumptiontype() throws Exception{
        return "consumptiontype";
    }
//    跳转进入数据字典教练类型数据项界面
    @RequestMapping(value = "thecoachtypeJsp")
    public String  Thecoachtype() throws Exception{
        return "thecoachtype";
    }
//    跳转进入数据字典百分率数据项界面
    @RequestMapping(value = "commissiondataJsp")
    public String  Commissiondata() throws Exception{
        return "commissiondata";
    }

}
