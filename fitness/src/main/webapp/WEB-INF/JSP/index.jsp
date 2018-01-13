<%--
  Created by IntelliJ IDEA.
  User: XIN
  Date: 2017/12/28
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>后台模板</title>
    <link rel="stylesheet" href="/statics/assets/css/amazeui.css" />
    <link rel="stylesheet" href="/statics/assets/css/core.css" />
    <link rel="stylesheet" href="/statics/assets/css/menu.css" />
    <link rel="stylesheet" href="/statics/assets/css/index.css" />
    <link rel="stylesheet" href="/statics/assets/css/admin.css" />
    <link rel="stylesheet" href="/statics/assets/css/page/typography.css" />
    <link rel="stylesheet" href="/statics/assets/css/page/form.css" />
    <link rel="stylesheet" href="/statics/assets/css/component.css" />
    <script type="text/javascript" src="/statics/assets/js/jquery-2.1.0.js" ></script>
    <script type="text/javascript" src="/statics/miniui/jquery.min.js" ></script>
    <script type="text/javascript" src="/statics/assets/js/amazeui.min.js"></script>
    <script type="text/javascript" src="/statics/assets/js/blockUI.js" ></script>

</head>
<body>
<div class="admin" style="margin-top: 0px">
    <div class="admin-sidebar am-offcanvas  am-padding-0" id="admin-offcanvas" style="margin-top: 0px">
        <div class="am-offcanvas-bar admin-offcanvas-bar" >
            <div class="user-box am-hide-sm-only">
                <div class="user-img">
                    <img src="/statics/assets/img/avatar-1.jpg" alt="user-img" title="Mat Helme" class="img-circle img-thumbnail img-responsive">
                    <div class="user-status offline"><i class="am-icon-dot-circle-o" aria-hidden="true"></i></div>
                </div>

                <ul class="list-inline">
                    <li>
                        <a href="#">
                            <i class="fa fa-cog" aria-hidden="true"></i>
                        </a>
                    </li>

                    <li>
                        <a href="#" class="text-custom">
                            <i class="fa fa-cog" aria-hidden="true"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- End User -->

            <script>

                $(function() {

                    $.ajax({
                        url: 'queryModulelist.do',
                        method: "post",
                        dataType: 'JSON',
                        success: callBack
                    })

                });

                function  callBack(data) {
                    var     html ="";
                    $.each(data,function(k,v){
                        if(v.module_pid==0) {
                            html += "<li class=\"admin-parent\">";
                            html += "<a class=\"am-cf\" data-am-collapse=\"{target: '#collapse-nav" + v.module_id + "'}\">";
                            html += "<span class=\"am-icon-th-list\"></span>";
                            html += v.module_name;
                            html += "<span class=\"am-icon-angle-right am-fr am-margin-right\"></span>";
                            html += "</a>";
                            html += "<ul class=\"am-list am-collapse admin-sidebar-sub \" id=\"collapse-nav" + v.module_id + "\">";

                            $.each(data,function(key,val){
                                if(v.module_id==val.module_pid){
                                    if(val.module_parent==true){
                                        html += "<li class=\"admin-parent\">";
                                        html += "<a class=\"am-cf\" data-am-collapse=\"{target: '#collapse-nav" + val.module_id + "'}\">";
                                        html += "<span class=\"am-icon-\"></span>";
                                        html += val.module_name;
                                        html += "<span class=\"am-icon-angle-right am-fr am-margin-right\"></span>";
                                        html += "</a>";
                                        html += "<ul class=\"am-list am-collapse admin-sidebar-sub \" id=\"collapse-nav" + val.module_id + "\">";
                                        $.each(data,function(x,y){
                                            if(val.module_id==y.module_pid){
                                                html += "<li  onclick=\"javascript:closeTab('"+y.module_jsp+"')\" ><a href=\"#\">";
                                                html += "<span class=\"am-icon-list-alt\"></span>";
                                                html +=y.module_name;
                                                html +="</a>";
                                                html +="</li>";
                                            }
                                        })
                                        html += "</ul>";


                                        html += "</li>";
                                    }else{
                                        html += "<li  onclick=\"javascript:closeTab('"+val.module_jsp+"')\" ><a href=\"#\">";
                                        html += "<span class=\"am-icon-list-alt\"></span>";
                                        html +=val.module_name;
                                        html +="</a>";
                                        html +="</li>";

                                    }
                                }
                            })
                            html += "</ul>";
                            html += "</li>";
                        }else if(v.module_pid=="-1"){
                            html += "<li  onclick=\"javascript:closeTab('"+v.module_jsp+"')\" ><a href=\"#\">";
                            html += "<span class=\"am-icon-list-alt\"></span>";
                            html +=v.module_name;
                            html +="</a>";
                            html +="</li>";
                        }
                    })
                    $("#ztree").html(html);

                }
                function closeTab(obj){
                    var rameHtml = "";
                    if(obj!=null&&obj!=""){
                        rameHtml +="<iframe width=99% height=99% marginwidth=0 marginheight=0 frameborder=\"no\" border=\"0\" src=\""+obj+"\" ></iframe>";
                    }
                    $("#rame").html(rameHtml)
                }

            </script>

            <ul class="am-list admin-sidebar-list" id="ztree" >

            </ul>


        </div>
    </div>

    <div  style="width:100%;" ><!-- start center -->
        <div title="首页" style="width:99%;height:99%;" id="rame"><!-- start homePage -->
            <img src="/statics/images/paragliding.jpg" width="99%" height="99%" />
        </div><!-- end homePage -->
    </div><!-- end center -->

</div>
</div>

</body>
</html>
