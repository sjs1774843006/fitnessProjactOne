package com.shadow.controler;

import com.shadow.dao.StaffDao;
import com.shadow.dao.StaffTypeDao;
import com.shadow.dao.TheLogDao;
import com.shadow.entity.StaffEntity;
import com.shadow.entity.StaffTypeEntity;
import com.shadow.entity.TheLogEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
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
    private TheLogDao theLogDao;

    @Resource
    private StaffTypeDao staffTypeDao;

    public static boolean flag = false;

    // 跳转进入登录界面
    @RequestMapping(value = "intologin")
    public String  htmllogin(HttpServletRequest request) throws Exception{
        return "login";
    }

    //退出登录
    @RequestMapping(value = "loginout")
    public  String  loginout(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        StaffEntity entity = (StaffEntity) session.getAttribute("staff");
        theLogLoginAdd(request, theLogDao, "{[----" + entity.getStaff_name() + "----]}退出了登录,并注销了");
        session.removeAttribute("staff");
        flag = false;
        return "login";
    }

    private  String  loginflag;
    /**
     * 此方法描述的是：登录验证
     * @author: 1774843006qq.com
     * @version: 2017年11月4日 下午4:54:04
     */
    @RequestMapping(value="loginsubmit")
    public void login(HttpServletRequest request,HttpServletResponse response){
        try {
        StaffEntity staff;
        Map<String,Object> map;
        String  account = request.getParameter("account");
        String  pwd = request.getParameter("passwrod");
        String md5pad = StaffControler.MD5_password(pwd);
        if(!"".equals(account)&&!"".equals(pwd)){
            map = new HashMap<String, Object>();
            map.put("staff_name",account);
            map.put("staff_password",md5pad);
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
                     theLogLoginAdd(request,theLogDao,"登录了系统");
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
        }catch (Exception e) {
            flag=false;
            try {
                response.getWriter().write("{\"success\":\"datatime\"}");
            } catch (IOException e1) {
            }
        }
    }



//    跳转进入首页
    @RequestMapping(value = "jsp")
    public String  query4List(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else{
            theLogLoginAdd(request,theLogDao,"进入首页");
            return "index";
        }
    }

//    跳转进入模块管理界面
    @RequestMapping(value = "disjsp")
    public String  display(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入模块管理界面");
            return "display";
        }
    }

//    跳转进入数据字典界面
    @RequestMapping(value = "dictjsp")
    public String  dictionary(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入数据字典界面");
            return "dictionary";
        }
    }

//    跳转进入数据字典员工类型数据项界面
    @RequestMapping(value = "StaffTypeJsp")
    public String  StaffType(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入数据字典员工类型数据项界面");
            return "stafftype";
        }
    }

//    跳转进入数据字典业务进度数据项界面
    @RequestMapping(value = "progressJsp")
    public String  Progress(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入数据字典业务进度数据项界面");
            return "progress";
        }
    }

//    跳转进入数据字典会员卡类型数据项界面
    @RequestMapping(value = "membercardtypeJsp")
    public String  Membercardtype(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入数据字典会员卡类型数据项界面");
            return "membercardtype";
        }
    }

//    跳转进入数据字典会员消费类型数据项界面
    @RequestMapping(value = "consumptiontypeJsp")
    public String  Consumptiontype(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入数据字典会员消费类型数据项界面");
            return "consumptiontype";
        }
    }
//    跳转进入数据字典教练类型数据项界面
    @RequestMapping(value = "thecoachtypeJsp")
    public String  Thecoachtype(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入数据字典教练类型数据项界面");
            return "thecoachtype";
        }
    }
//    跳转进入数据字典百分率数据项界面
    @RequestMapping(value = "commissiondataJsp")
    public String  Commissiondata(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入数据字典百分率数据项界面");
            return "commissiondata";
        }
    }
    //    跳转进入日志记录界面
    @RequestMapping(value = "thelogJsp")
    public String  Thelog(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入日志记录界面");
            return "thelog";
        }
    }

    //    跳转进入授权员工界面
    @RequestMapping(value = "ztreeStaffJsp")
    public String  ztreeStaffJsp(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入授权员工界面");
            return "ztreestaff";
        }
    }
   //    跳转进入授权界面
    @RequestMapping(value = "jurisdictionstafftypeJsp")
    public String  JurisdictionStafftype(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入授权界面");
            return "jurisdictionstafftype";
        }
    }
    //    跳转进入查看所有权限界面
    @RequestMapping(value = "userrolepermissionsJsp")
    public String  UserrolepermissionsJsp(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入查看所有权限界面");
            return "userrolepermissions";
        }
    }


    //    跳转进入员工界面
    @RequestMapping(value = "staffJsp")
    public String  Staff(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入员工界面");
            return "staff";
        }
    }
    //    跳转进入员工界面
    @RequestMapping(value = "typeJsp")
    public String  Type(HttpServletRequest request) throws Exception{
        if(!flag){
            return "login";
        }else {
            theLogLoginAdd(request,theLogDao,"进入授权员工界面");
            return "type";
        }
    }


    public static  void  theLogAdd(HttpServletRequest request, TheLogDao theLogDao,String table,String message){
        HttpSession session = request.getSession();
        StaffEntity staffEntity = (StaffEntity) session.getAttribute("staff");
        TheLogEntity theLogEntity = new TheLogEntity();
        theLogEntity.setThelog_contents("{[--"+staffEntity.getStaff_name()+"--]} : 员工在*---["+new Date().toLocaleString()+"]---*这个时间，对[---"+table+"---]这张数据表"+message);
        theLogEntity.setThelog_date(new Date().toLocaleString());
        theLogEntity.setStaffmember_id(staffEntity.getStaff_id());
        theLogDao.add(theLogEntity);
    }

    public static  void  theLogLoginAdd(HttpServletRequest request, TheLogDao theLogDao,String message){
        HttpSession session = request.getSession();
        StaffEntity staffEntity = (StaffEntity) session.getAttribute("staff");
        TheLogEntity theLogEntity = new TheLogEntity();
        theLogEntity.setThelog_contents("{[--"+staffEntity.getStaff_name()+"--]} : 员工在*---["+new Date().toLocaleString()+"]---*这个时间"+message);
        theLogEntity.setThelog_date(new Date().toLocaleString());
        theLogEntity.setStaffmember_id(staffEntity.getStaff_id());
        theLogDao.add(theLogEntity);
    }

}
