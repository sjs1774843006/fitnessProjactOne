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

    <link rel="stylesheet" href="/statics/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/statics/css/toastr.css" />
    <link rel="stylesheet" href="/statics/css/index.css" />



    <script type="text/javascript" src="/statics/assets/js/jquery-2.1.0.js" ></script>
    <script type="text/javascript" src="/statics/js/jquery.min.js" ></script>
    <script type="text/javascript" src="/statics/assets/js/amazeui.min.js"></script>
    <script type="text/javascript" src="/statics/assets/js/blockUI.js" ></script>

    <script type="text/javascript" src="/statics/bootstrap/js/bootstrap.min.js" ></script>
    <script type="text/javascript" src="/statics/js/toastr.js" ></script>

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
                                                html += "<li  onclick=\"javascript:openTab('"+y.module_name+"','"+y.module_id+"','"+y.module_jsp+"')\" ><a href=\"#\">";
                                                html += "<span class=\"am-icon-list-alt\"></span>";
                                                html +=y.module_name;
                                                html +="</a>";
                                                html +="</li>";
                                            }
                                        })
                                        html += "</ul>";


                                        html += "</li>";
                                    }else{
                                        html += "<li  onclick=\"javascript:openTab('"+val.module_name+"','"+val.module_id+"','"+val.module_jsp+"')\" ><a href=\"#\">";
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
                            html += "<li  onclick=\"javascript:openTab('"+v.module_name+"','"+v.module_id+"','"+v.module_jsp+"')\" ><a href=\"#\">";
                            html += "<span class=\"am-icon-list-alt\"></span>";
                            html +=v.module_name;
                            html +="</a>";
                            html +="</li>";
                        }
                    })
                    $("#ztree").html(html);


                }
                var img = 0;
                function  openTab(title,TitleId,module_jsp) {
                    if(img==0){
                        $("#div").html("");
                        img=1;
                    }
                    var content = "";
                    var f = true;
                    $("#myTab li a").each(function(i){
                        if($(this).text()==title+"×"){
                            f=false;
                            tabSwitch(TitleId);
                            return;
                        }
                    });
                    if(f){

                        content = "<iframe id=\"content_"+TitleId+"\" name=\""+TitleId+"\" frameborder=\"0\" src=\""+module_jsp+"\" width=85% height=99% scrolling=\"no\" ></iframe>";
                        var msg = "<li class=\"active\" id=\"li_"+TitleId+"\" data-toggle=\”tab\”>" +
                            "<a href=javascript:tabSwitch("+TitleId+")>"+title+"<button type=\"button\" id=\"a_"+TitleId+"\" class=\"close\" aria-label=\"Close\" >" +
                            "<span aria-hidden=\"true\" style='margin-left: 10px' >&times;</span></button></a></li>";
                        $("#myTab").append(msg);
                        $("#div").append(content);
                        $("#a_"+TitleId).click(
                            function(){
                                var obj = this;
                                var c=0;
                                $("#div iframe").each(function(i){
                                    if(this.id=="content_"+TitleId){
                                        c=i;
                                        if($("#div").html().length<=160){
                                            img=0;
                                            $("#div").html("<img src=\"/statics/images/paragliding.jpg\"  id=\"homeImg\"  style=\"width: 99%; height: 100%\">");
                                        }
                                        $("#content_"+TitleId).remove();
                                        $(obj).parent().parent().remove();
                                    }
                                });
                                $("#div iframe").each(function(n){
                                    if(n==(c-1)){
                                        var ida = this.id;
                                        ida = ida.substring(8);
                                        tabSwitch(ida);
                                    }
                                });
                                return false;
                            });
                        tabSwitch(TitleId);
                    }
                }

                function tabSwitch(id){
                    $("#myTab li").each(function(i){
                        if(this.id!="li_"+id){
                            $(this).attr("class","tab-page")
                        }
                        else{
                            $(this).attr("class","active")
                        }
                    });
                    $("#div iframe").each(function(i){
                        if(this.id=="content_"+id){
                            $(this).show();

                        }
                        else{
                            $(this).hide()
                        }
                    });
                }

            </script>

            <ul class="am-list admin-sidebar-list" id="ztree" >

            </ul>


        </div>
    </div>

    <div style="width: 100%; height: 100%">
        <ul class="nav nav-tabs" id="myTab">
            <li class="active" data-toggle=”tab” id="Homepage"><a>首页</a></li>
        </ul>
        <div class="tab" id="div">
                <img src="/statics/images/paragliding.jpg" style="width: 99%; height: 100%" id="homeImg" >
        </div>
    </div>

    <%--弹出框开始--%>
    <div class="modal fade in" id="_modalDialog" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
        <div class="modal-dialog" role="document" style="width:60%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="modalLabel">详细数据项</h4></div>
                <div id="_modalDialog_body" class="modal-body">
                    <!--  设置这个div的大小，超出部分显示滚动条 -->
                    <div id="selectTree" class="ztree" style="height:600px;overflow:auto; ">

                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div><%--弹出框结束--%>


</div>
</div>




</body>
</html>
