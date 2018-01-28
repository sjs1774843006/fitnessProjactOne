<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="icon" href="/statics/images/bitbug_favicon.ico" type="image/x-icon"/>
    <title>员工界面</title>
    <link rel="stylesheet" href="/statics/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/css/toastr.min.css">
    <link rel="stylesheet" href="/statics/css/datetimepicker.css">
    <link rel="stylesheet" href="/statics/bootstrap-table/dist/bootstrap-table.min.css">
    <script src="/statics/js/jquery.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap.min.js"></script>
    <script src="/statics/bootstrap-table/dist/bootstrap-table.js"></script>
    <script src="/statics/js/bootstrap-datetimepicker.js"></script>
    <script src="/statics/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="/statics/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
    <script src="/statics/js/tableExport.min.js"></script>
    <script src="/statics/js/toastr.js"></script>
    <script src="/statics/js/toastr-min.js"></script>
    <script src="/statics/js/toastr-defined.js"></script>
    <script src="/statics/js/bootstrap-crud.js"></script>

    <script>
        $(function(){

            $.ajax({
                url : 'controlbutton.do',
                data : 'm_id=34',
                method : "post",
                dataType : 'JSON',
                success : controlButton,
            });

            $('#table').bootstrapTable({
                method:'get',//提交方式
                url: 'queryStafflist.do',//提交地址
                striped: true,//设置为 true 会有隔行变色效果
                cache: false,//设置为 false 禁用 AJAX 数据缓存
                sortable: false,//设置为false 将禁止所有列的排序
                sortOrder: "asc",//定义排序方式 'asc' 或者 'desc'
                pageSize: 10,
                height:850,
                pageList: [10, 25, 50, 100],
                queryParams:function(params){
                    return {
                        offset: params.offset,
                        limit: params.limit,
                        searchText:params.search,
                    };
                },
                pagination:true,//设置为 true 会在表格底部显示分页条
                sidePagination:"server",//设置为服务器分页
                search:true,//是否启用搜索框
                strictSearch:false,//设置为 true启用 全匹配搜索，否则为模糊搜索
                searchTimeOut:'500',//设置搜索超时时间
                showHeader:'true',//是否显示列头
                showFooter:'true',//是否显示列脚
                showColumns:'true',//是否显示 内容列下拉框
                showRefresh:'true',//是否显示 刷新按钮
                showToggle:'true',//是否显示 切换试图（table/card）按钮
                silentSort:'false',//设置为 false 将在点击分页按钮时，自动记住排序项。仅在 sidePagination设置为 server时生效.
                clickToSelect:'true',//设置true 将在点击行时，自动选择rediobox 和 checkbox
                toolbar:'#searchPanel',//一个jQuery 选择器，指明自定义的toolbar
                columns:[{
                    checkbox: true,
                    visible: true
                }, {
                    field: 'staff_id',
                    title: 'ID',
                    align:'center',
                    sortable:'true',
                }, {
                    field: 'staff_name',
                    title: '员工名称',
                    align:'center',
                    sortable:'true',
                    edit:{
                        type:'text'
                    }
                },  {
                    field: 'jurisdiction',
                    title: '拥有角色',
                    align:'center',
                    sortable:'true',
                }, {
                    field: 'staff_password',
                    title: '员工密码',
                    align:'center',
                    sortable:'true',
                    edit:{
                        type:'password'
                    }
                }, {
                    field: 'staff_sex',
                    title: '员工性别',
                    align:'center',
                    sortable:'true',
                    edit:{
                        type:'text'
                    }
                }, {
                    field: 'staff_age',
                    title: '员工年龄',
                    align:'center',
                    sortable:'true',
                    edit:{
                        type:'text'
                    }
                }, {
                    field: 'staff_tel',
                    title: '员工电话',
                    align:'center',
                    sortable:'true',
                    edit:{
                        type:'text'
                    }
                }, {
                    field: 'staff_address',
                    title: '员工地址',
                    align:'center',
                    sortable:'true',
                    edit:{
                        type:'text'
                    }
                },{
                    field: 'staff_idcard',
                    title: '员工证件号',
                    align:'center',
                    sortable:'true',
                    edit:{
                        type:'text'
                    }
                }, {
                    field: 'headportrait',
                    title: '员工头像',
                    align:'center',
                    sortable:'true',
                    edit:{
                        type:'img',
                    },
                    formatter: function (value, row, index) {
                            if (row.headportrait!=null) {
                                var html="<form  id=\"form"+row.staff_id+"\">";
                                html+="<input  type=\"file\" name=\"file\" id=\"file"+row.staff_id+"\" style=\"display: none\">";
                                html+="<div style=\"border: dashed 1px #ccc; width: 120px ;height: 80px;\" id=\"uploadBtn"+row.staff_id+"\">";
                                html+="<div style=\" text-align: center; width: 120px;  height: 80px; float: left; padding-left: 0px; margin-left: 0px;\"> ";
                                html+="<img id=\"uploadBtnImg"+row.staff_id+"\"   style=\"cursor: pointer;  width: 120px;  height: 80px;\" src=\""+row.headportrait+"\">";
                                html+="</div></div>";
                                html+="</form>";
                                return html;
                            }
                            else {
                                var html="<form  id=\"form"+row.staff_id+"\">";
                                html+="<input  type=\"file\" name=\"file\" id=\"file"+row.staff_id+"\" style=\"display: none\">";
                                html+="<div style=\"border: dashed 1px #ccc; width: 120px ;height: 80px;\" id=\"uploadBtn"+row.staff_id+"\">";
                                html+="<div style=\" text-align: center; width: 120px;  height: 80px; float: left; padding-left: 0px; margin-left: 0px;\"> ";
                                html+="<img id=\"uploadBtnImg"+row.staff_id+"\"   style=\"cursor: pointer;  width: 120px;  height: 80px;\" src=\"/statics/images/nwc.png\">";
                                html+="</div></div>";
                                html+="</form>";
                                return html;
                            }
                    }
                }
                ],

            })

//            删除
            $("#delBtn").click(function(){
                var arrselections = $("#table").bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    parent.toastr.warning('请选择需要删除的数据', '温馨提示',messageOpts);
                    return;
                }
                del("delStaff.do",arrselections,"staff_id");
            });

//            角色授予
            $("#addstafftypeBtn").click(function(){
                var arrselections = $("#table").bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    parent.toastr.warning('请选择需要授权用户 ', '温馨提示',messageOpts);
                    return;
                }
                if (arrselections.length > 1) {
                    parent.toastr.warning('请选择不要多选 ', '温馨提示',messageOpts);
                    return;
                }
                $('#_modalDialog').modal('show');
                openwindow_text(arrselections[0].staff_id,'typeJsp.do','','');

            });
            //授权限
            $("#jurisdictionBtn").click(function(){

                if(!f){
                    parent.toastr.info('请完成当前操作', '温馨提示',messageOpts);
                }else {
                    var arrselections = $("#table").bootstrapTable('getSelections');
                    if (arrselections.length <= 0) {
                        parent.toastr.warning('请选择需要授予权限的用户', '温馨提示', messageOpts);
                        return;
                    }if(arrselections.length > 1){
                        parent.toastr.warning('选择查看的权限的用户过多', '温馨提示', messageOpts);
                        return;
                    }
                    if(arrselections[0].jurisdiction=="无角色"){
                        parent.toastr.warning('当前用户不属于系统员工', '温馨提示', messageOpts);
                        return;
                    }
                    $('#_modalDialog').modal('show');
                    openwindow_text(arrselections[0].staff_id,'ztreeStaffJsp.do','staffjurisdiction.do','savestaff_jurisdiction.do');
                }
            })
//            看权限
            $("#ownjurisdictionBtn").click(function(){
                if(!f){
                    parent.toastr.info('请完成当前操作', '温馨提示',messageOpts);
                }else {
                    var arrselections = $("#table").bootstrapTable('getSelections');
                    if (arrselections.length <= 0) {
                        parent.toastr.warning('请选择需要查看的用户', '温馨提示', messageOpts);
                        return;
                    }if(arrselections.length > 1){
                        parent.toastr.warning('选择查看的用户过多', '温馨提示', messageOpts);
                        return;
                    }
                    $.ajax({
                        type: "post",
                        url: "staffjurisdiction.do?jid="+arrselections[0].staff_id,
                        dataType: "Json",
                        success: function (data) {
                            if(data.success=="defeated") {
                                parent.toastr.error('该用户当前无任何权限', '温馨提示',messageOpts);
                            }else {
                                $('#_modalDialog').modal('show');
                                openwindow_text(arrselections[0].staff_id,'userrolepermissionsJsp.do','','');
                            }
                        }
                    });

                }
            })

        })

        function openwindow_text(type_id,url,jsp,saveurl){
            sessionStorage.setItem('staff_id', type_id);
            sessionStorage.setItem('query', jsp);
            sessionStorage.setItem('save', saveurl);
            $("#selectTree").html(" <iframe frameborder = '0' border = '0'  src='"+url+"'  scrolling='no' width=99% height=99% ></iframe>")
        }



        //增加
        function save_add(datass){
            $.ajax({
                type: "post",
                url: "addStaff.do",
                ansyn:false,
                data: datass,
                dataType: "JSON",
                success: function (data) {
                    if (data.success=="success") {
                        parent.toastr.success('数据增加成功','温馨提示',messageOpts);
                        $("#table").bootstrapTable('refresh');
                    }
                    else if(data.success=="defeated"){
                        parent.toastr.error('数据增加失败', '温馨提示',messageOpts);
                        $("#table").bootstrapTable('refresh');
                    }
                }
            });
        }

        //修改
        function save_update(datass){
            $.ajax({
                type: "post",
                url: "updateStaff.do",
                data: datass,
                dataType: "JSON",
                success: function (data) {
                    if (data.success=="success") {
                        parent.toastr.success('修改数据成功','温馨提示',messageOpts);
                        $("#table").bootstrapTable('refresh');
                    }
                    else if(data.success=="defeated"){
                        parent.toastr.error('修改数据失败', '温馨提示',messageOpts);
                        $("#table").bootstrapTable('refresh');
                    }
                    else if(data.success=="insufficient"){
                        parent.toastr.error('无法修改除自己以外他人的信息', '温馨提示',messageOpts);
                        $("#table").bootstrapTable('refresh');
                    }
                }
            });
        }


    </script>
</head>
<body style="width: auto;height: 99%">

<div id="searchPanel">

    <button type="button" class="btn btn-primary glyphicon  glyphicon-plus"         id="addBtn"    >&nbsp;增&nbsp;加</button>
    <button type="button" class="btn btn-danger  glyphicon  glyphicon-minus "        id="delBtn"    >&nbsp;删&nbsp;除</button>
    <button type="button" class="btn btn-info    glyphicon  glyphicon-pencil"        id="editBtn"   >&nbsp;修&nbsp;改</button>
    <button type="button" class="btn btn-warning glyphicon  glyphicon-remove"        id="cancelBtn" >&nbsp;取&nbsp;消</button>
    <button type="button" class="btn btn-success glyphicon  glyphicon-floppy-disk"   id="saveBtn"   >&nbsp;保&nbsp;存</button>
    <button type="button" class="btn btn-inverse glyphicon  glyphicon-export"        id="exportBtn" >&nbsp;导出Excel</button>
    <button type="button" class="btn btn-inverse glyphicon glyphicon-user"        id="addstafftypeBtn" >&nbsp;授予角色</button>
    <button type="button" class="btn btn-inverse glyphicon glyphicon-lock"        id="jurisdictionBtn" >&nbsp;权限绑定</button>
    <button type="button" class="btn btn-inverse glyphicon glyphicon-cog"        id="ownjurisdictionBtn" >&nbsp;拥有权限</button>

</div>
<table id="table"></table>

<%--弹出框开始--%>
<div class="modal fade in" id="_modalDialog" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document" style="width:100%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="modalLabel">授予权限</h4></div>
            <div id="_modalDialog_body" class="modal-body" >
                <!--  设置这个div的大小，超出部分显示滚动条 -->
                <div id="selectTree" class="ztree" style="height:1070px;overflow:auto; ">

                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div><%--弹出框结束--%>

<iframe id="testFrame"  name="testFrame"  width="0" height="0">
</iframe>

</body>
</html>
