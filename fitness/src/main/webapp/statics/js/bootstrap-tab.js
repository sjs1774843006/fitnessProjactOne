// 当前ID
var presentID="";
$(function() {

    $.ajax({
        url: 'queryModulelist.do',
        method: "post",
        dataType: 'JSON',
        success: callBack
    })

    $(".nav-tabs").on("click", "[tabclose]", function (e) {
        id = $(this).attr("tabclose");
        closeTab(id);
    });

    window.onresize = function () {
        var target = $(".tab-content .active iframe");
        changeFrameHeight(target);
    }

    var menu = new BootstrapMenu('.nav-tabs li', {
        actions: [{
            iconClass: 'glyphicon glyphicon-remove-circle',
            name: '关闭所有',
            onClick: function() {
                for(var i=0;i<list.length;i++){
                    $("#tab_" + list[i]).remove();
                    $("#" + list[i]).remove();
                    $("#home").addClass('active');
                    $("#Index").addClass('active');
                }
            }

        }
        ]
    });

});

var message = {
    "closeButton" : true,//是否显示关闭按钮
    "debug" : false,//是否使用debug模式
    "positionClass" : "toast-bottom-right",//弹出窗的位置
    "onclick" : null,
    "showDuration" : "300",//显示的动画时间
    "hideDuration" : "1000",//消失的动画时间
    "timeOut" : "5000",//展现时间
    "extendedTimeOut" : "1000",//加长展示时间
    "showEasing" : "swing",//显示时的动画缓冲方式
    "hideEasing" : "linear",//消失时的动画缓冲方式
    "showMethod" : "fadeIn",//显示时的动画方式
    "hideMethod" : "fadeOut" //消失时的动画方式
};
var list = new Array();

var titles = "";

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
                                html += "<li  onclick=\"javascript:addTabs('"+y.module_name+"','"+y.module_id+"','"+y.module_jsp+"','true')\" ><a href=\"#\">";
                                html += "<span class=\"am-icon-list-alt\"></span>";
                                html +=y.module_name;
                                html +="</a>";
                                html +="</li>";
                            }
                        })
                        html += "</ul>";


                        html += "</li>";
                    }else{
                        html += "<li  onclick=\"javascript:addTabs('"+val.module_name+"','"+val.module_id+"','"+val.module_jsp+"','true')\" ><a href=\"#\">";
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
            html += "<li  onclick=\"javascript:addTabs('"+v.module_name+"','"+v.module_id+"','"+v.module_jsp+"','true')\" ><a href=\"#\">";
            html += "<span class=\"am-icon-list-alt\"></span>";
            html +=v.module_name;
            html +="</a>";
            html +="</li>";
        }
    })
    $("#ztree").html(html);


}

function  addTabs(moduletitle,moduId,moduleurl,optionsclose) {
    //可以在此处验证session
    id = "tab_" + moduId;
    var active_flag = false;
    if($("#" + id)){
        active_flag = $("#" + id).hasClass('active');
    }
    $(".active").removeClass("active");
    //如果TAB不存在，创建一个新的TAB
    if (!$("#" + id)[0]) {
        //固定TAB中IFRAME高度
        mainHeight = $(document.body).height();
        //创建新TAB的title
        title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">' + moduletitle;

        list.push(id);

        //是否允许关闭
        if (optionsclose) {
            title += ' <i class="glyphicon glyphicon-remove" tabclose="' + id + '"></i>';
        }
        title += '</a></li>';
        //是否指定TAB内容
       if (moduleurl==""||moduId==null) {
           content = '<div role="tabpanel" class="tab-pane" id="' + id + '">' + moduletitle + '</div>';
       } else {//没有内容，使用IFRAME打开链接
            content = '<div role="tabpanel" class="tab-pane" id="' + id + '"><iframe id="iframe_'+id+'" src="' + moduleurl +
            '" width="100%" height="100%" onload="changeFrameHeight(this)" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe></div>';
       }
       if($("#tt").html().length<=2300){
           //加入TABS
           $(".nav-tabs").append(title);
           $(".tab-content").append(content);
       }else{
           toastr.warning('打开界面已达上限', '温馨提示',message);
       }

    }else{
        if(active_flag){
            $("#iframe_" + id).attr('src', $("#iframe_" + id).attr('src'));
        }
    }
    //激活TAB
    $("#tab_" + id).addClass('active');
    $("#" + id).addClass("active");

    $(" .nav-tabs  li ").bind('contextmenu',function(e){
        presentID = e.currentTarget.id
        // console.log(e.currentTarget.innerText)
    });

};

var changeFrameHeight = function (that) {
    $(that).height(document.documentElement.clientHeight - 115);
    $(that).parent(".tab-pane").height(document.documentElement.clientHeight - 130);
}

var closeTab = function (id) {
    //如果关闭的是当前激活的TAB，激活他的前一个TAB
    if ($("li.active").attr('id') == "tab_" + id) {
        $("#tab_" + id).prev().addClass('active');
        $("#" + id).prev().addClass('active');
    }
    //关闭TAB
    $("#tab_" + id).remove();
    $("#" + id).remove();
};
