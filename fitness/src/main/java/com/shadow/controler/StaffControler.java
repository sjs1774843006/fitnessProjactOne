package com.shadow.controler;


import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import com.shadow.dao.*;
import com.shadow.dto.J_ZtreeEntity;
import com.shadow.dto.M_ODtoEntity;
import com.shadow.dto.StaffDtoEntity;
import com.shadow.dto.staff_typeJurEntity;
import com.shadow.entity.ButtonEntity;
import com.shadow.entity.ModuleEntity;
import com.shadow.entity.StaffEntity;
import com.shadow.entity.StaffTypeEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StaffControler {

    @Resource
    private StaffDao staffDao;

    @Resource
    private StaffTypeDao staffTypeDao;

    @Resource
    private ModuleDao moduleDao;

    @Resource
    private ButtonDao buttonDao;

    @Resource
    private JMOStaffDao jmoStaffDao;

    @Resource
    private J_MODao  j_moDao;

    private List<M_ODtoEntity> object;


    //查询
    @RequestMapping(value = "staffjurisdiction")
    public void jurisdiction(@RequestParam(value = "jid")int jid,HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<J_ZtreeEntity> jlist = new ArrayList<J_ZtreeEntity>();
        Map<Integer,List<Integer>> jmap = getJurisdiction(jid);
        if(jmap!=null){
            List<ModuleEntity> list=moduleDao.selectAll();

            if(list.size()!=0&&list!=null){
                J_ZtreeEntity entity;
                for (ModuleEntity moduleEntity : list) {
                    List<ButtonEntity> blist = buttonDao.getButton(moduleEntity.getModule_id());
                    entity = new J_ZtreeEntity();
                        if(moduleEntity.getModule_pid()!=0){
                            entity.set__parentId(moduleEntity.getModule_pid());
                        }
                        entity.setId(moduleEntity.getModule_id());
                        entity.setModule(moduleEntity.getModule_name());
                        int  s=1;
                        for (Integer key : jmap.keySet()) {
                            List<Integer> tempList = jmap.get(key);
                            for (Integer integer : tempList) {
                                if(key==moduleEntity.getModule_id()){
                                    if(integer==1){
                                        entity.setAdd("1");
                                    }
                                    if(integer==2){
                                        entity.setDel("1");
                                    }
                                    if(integer==3){
                                        entity.setUpdate("1");
                                    }
                                    if(integer==4){
                                        entity.setQuery("1");
                                    }
                                    if(integer==11){
                                        entity.setChecked(true);
                                    }
                                }
                            }
                        }
                    jlist.add(entity);
                }
            }
            map.put("total", jlist.size());
            map.put("rows", jlist);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(map));
        }else{
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("{\"success\":\"defeated\"}");
        }

    }
    public   Map<Integer, List<Integer>> getJurisdiction(int jid) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        List<M_ODtoEntity> object =  new ArrayList<M_ODtoEntity>();
        int j_typeid=0;
        List<staff_typeJurEntity> staff_typeJurEntities = staffDao.staff_id_type(jid);
        if(staff_typeJurEntities.size()>0){
            for (int i=0;i<staff_typeJurEntities.size();i++){
                if(staff_typeJurEntities.get(i).getStaff_id()==jid){
                    j_typeid=1;
                }
                if(j_typeid==1){
                    List<M_ODtoEntity> object1 = jmoStaffDao.JurisdictionList(staff_typeJurEntities.get(0).getStaff_id());
                    for (M_ODtoEntity Entity : object1) {
                        M_ODtoEntity entity = new M_ODtoEntity();
                        entity.setM_id(Entity.getM_id());
                        entity.setO_id(Entity.getO_id());
                        object.add(entity);
                    }
                }
                List<M_ODtoEntity>  object2 = j_moDao.JurisdictionList(staff_typeJurEntities.get(i).getType_id());
                for (M_ODtoEntity mODto : object2) {
                    M_ODtoEntity m_oDto = new M_ODtoEntity();
                    m_oDto.setM_id(mODto.getM_id());
                    m_oDto.setO_id(mODto.getO_id());
                    object.add(m_oDto);
                }
            }
            List<Integer> list = null;
            int mid = 0;
            int i=0;
            for (M_ODtoEntity objects : object) {
                if(mid!=object.get(i).getM_id()){
                    list = new ArrayList<Integer>();
                    mid = object.get(i).getM_id();
                }
                list.add(object.get(i).getO_id());
                map.put(mid, list);
                i++;
            }
        }else{
            return null;
        }
        return map;
    }


    //    数据查询分页
    @RequestMapping(value = "queryStafflist")
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
        int count = staffDao.selectCount(querymap);
        List<StaffEntity>  list  =  staffDao.staff_All(querymap);
        int n=0;
        String str="";
        List<StaffDtoEntity>  listdto = new ArrayList<StaffDtoEntity>();
        StaffDtoEntity  staffDtoEntity;
        for (StaffEntity entity : list) {
            staffDtoEntity = new StaffDtoEntity();
            staffDtoEntity.setStaff_id(entity.getStaff_id());
            staffDtoEntity.setStaff_name(entity.getStaff_name());
            List<StaffTypeEntity>  staffTypeEntities = staffTypeDao.selectstafftype(entity.getStaff_id());
            if(staffTypeEntities.size()>0){
                for (StaffTypeEntity staffTypeEntity : staffTypeEntities) {
                    str+=staffTypeEntity.getType_name()+",";
                    staffDtoEntity.setJurisdiction(str.substring(0,str.length()-1));
                }
                str="";
            }else{
                staffDtoEntity.setJurisdiction("无角色");
            }
            staffDtoEntity.setStaff_password(entity.getStaff_password());
            staffDtoEntity.setStaff_sex(entity.getStaff_sex());
            staffDtoEntity.setStaff_age(entity.getStaff_age());
            staffDtoEntity.setStaff_tel(entity.getStaff_tel());
            staffDtoEntity.setStaff_address(entity.getStaff_address());
            staffDtoEntity.setStaff_idcard(entity.getStaff_idcard());
            staffDtoEntity.setHeadportrait(entity.getHeadportrait());
            staffDtoEntity.setParentId(n);
            n++;
            listdto.add(staffDtoEntity);
        }
        map.put("total",count);
        map.put("rows",listdto);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(map));
    }


//    数据删除
    @RequestMapping(value = "delStaff")
    public void  delete(@RequestParam(value = "id")int id, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            staffDao.staff_Del(id);
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
    @RequestMapping(value = "updateStaff")
    public void  update(StaffEntity staffEntity, HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            String password = MD5_password(staffEntity.getStaff_password());
            staffEntity.setStaff_password(password);
            staffDao.staff_Upd(staffEntity);
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

    //文件上传
    @RequestMapping( value = "fileupload")
    public  void   fileupload(@RequestParam MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException {
            String path = request.getServletContext().getRealPath("upload");
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(path+"/"+file.getOriginalFilename()));
            String filePath = "/upload/"+file.getOriginalFilename();
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("{\"success\":\""+filePath+"\"}");
    }



//    数据增加
    @RequestMapping(value = "addStaff")
    public void  add(StaffEntity staffEntity,HttpServletRequest request, HttpServletResponse  response) throws Exception{
        boolean flag = false;
        try {
            staffEntity.setStaff_id(staffDao.Count()+1);
            String password = MD5_password(staffEntity.getStaff_password());
            staffEntity.setStaff_password(password);
            staffDao.staff_Add(staffEntity);
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


//    查询
    @RequestMapping(value = "queryStaff_Type")
    public void  query(@RequestParam(value = "staff_id")int staff_id, HttpServletResponse  response) throws Exception{
        List<StaffTypeEntity>  staffTypeEntities = staffTypeDao.selectstafftype(staff_id);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(staffTypeEntities));
    }

    public  static String MD5_password(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
