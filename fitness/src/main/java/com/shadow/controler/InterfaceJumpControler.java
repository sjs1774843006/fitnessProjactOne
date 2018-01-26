package com.shadow.controler;

import com.shadow.dao.StaffDao;
import com.shadow.dao.StaffTypeDao;
import com.shadow.entity.StaffEntity;
import com.shadow.entity.StaffTypeEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此类只负责界面跳转，不做其他用途
 */
@Controller
public class InterfaceJumpControler {

    @Resource
    private StaffDao staffDao;

    @Resource
    private StaffTypeDao staffTypeDao;

    public static boolean flag;

    private  String  loginflag;
    /**
     * 此方法描述的是：登录验证
     * @author: 1774843006qq.com
     * @version: 2017年11月4日 下午4:54:04
     */
    @RequestMapping(value="login")
    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
        StaffEntity staff;
        Map<String,Object> map;
        String  account = request.getParameter("account");
        String  pwd = request.getParameter("passwrod");
        if(!"".equals(account)&&!"".equals(pwd)){
            map = new HashMap<String, Object>();
            map.put("staff_name",account);
            map.put("staff_password",pwd);
            staff = staffDao.selectOne(map);
            if(staff!=null){
                    List<StaffTypeEntity>  staffTypeEntities = staffTypeDao.selectloginjurisdiction(map);
                    if(staffTypeEntities.size()>1){
                        for (StaffTypeEntity staffTypeEntity : staffTypeEntities) {
                            if("true".equals(staffTypeEntity.getLoginflag())){
                                loginflag = "true";
                            }
                        }
                    }else{
                        loginflag = staffTypeEntities.get(0).getLoginflag();
                    }
                    if("false".equals(loginflag)){
                        flag=false;
                        response.getWriter().write("{\"success\":\"system\"}");
                    }else{
                        HttpSession session = request.getSession();
                        session.setAttribute("staff", staff);
                        flag = true;
                        response.getWriter().write("{\"success\":\"success\"}");
                    }

            }else{
                flag=false;
                response.getWriter().write("{\"success\":\"defeated\"}");
            }

        }else{
            flag=false;
            response.getWriter().write("{\"success\":\"defeated\"}");
        }
    }



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
    //    跳转进入日志记录界面
    @RequestMapping(value = "thelogJsp")
    public String  Thelog() throws Exception{
        return "thelog";
    }

    //    跳转进入授权员工界面
    @RequestMapping(value = "ztreeStaffJsp")
    public String  ztreeStaffJsp() throws Exception{
        return "ztreestaff";
    }
   //    跳转进入授权界面
    @RequestMapping(value = "jurisdictionstafftypeJsp")
    public String  JurisdictionStafftype() throws Exception{
        return "jurisdictionstafftype";
    }
    //    跳转进入查看所有权限界面
    @RequestMapping(value = "userrolepermissionsJsp")
    public String  UserrolepermissionsJsp() throws Exception{
        return "userrolepermissions";
    }



    //    跳转进入员工界面
    @RequestMapping(value = "staffJsp")
    public String  Staff() throws Exception{
        return "staff";
    }
    //    跳转进入员工界面
    @RequestMapping(value = "typeJsp")
    public String  Type() throws Exception{
        return "type";
    }

}
