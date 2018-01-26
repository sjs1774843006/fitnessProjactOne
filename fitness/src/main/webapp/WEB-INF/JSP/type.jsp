<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Title</title>
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

    <script>
        var che= new Array();
        $(function(){
            var   type = sessionStorage.getItem('staff_id');
            $('#table').bootstrapTable({
                method:'get',//提交方式
                url: 'queryStaffTypelist.do',//提交地址
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
                showRefresh:'true',//是否显示 刷新按钮
                showToggle:'true',//是否显示 切换试图（table/card）按钮
                silentSort:'false',//设置为 false 将在点击分页按钮时，自动记住排序项。仅在 sidePagination设置为 server时生效.
                clickToSelect:'true',//设置true 将在点击行时，自动选择rediobox 和 checkbox
                toolbar:'#searchPanel',//一个jQuery 选择器，指明自定义的toolbar
                columns:[{
                    checkbox: true,
                    visible: true,
                    formatter:stateFormatter,
                }, {
                    field: 'type_name',
                    title: '角色名称',
                    align:'center',
                    sortable:'true',
                    edit:{
                        type:'text'
                    }
                }
                ],

            })
            $.ajax({
                type: "post",
                ansyn:false,
                url: "queryStaff_Type.do?staff_id="+type,
                dataType: "JSON",
                success: function (data) {
                    $.each(data,function (k,v) {
                        che.push(v.type_id)
                    })
                }
            });

            $("#savaTypeBtn").click(function () {
                var arrselections = $("#table").bootstrapTable('getSelections');
                    Ewin.confirm({message: "确认要授予这些角色吗？"}).on(function (e) {
                        if (!e) {
                            return;
                        }
                        if(arrselections.length>0){
                            for (var i = 0; i < arrselections.length; i++) {
                                var mubId = arrselections[i].type_id;
                                $.ajax({
                                    type: "post",
                                    url: "addStaff_Type.do?staff_id="+type+"&type_id="+mubId,
                                    dataType: "text",
                                    success: function (data) {
                                        if (data.success=="success") {
                                            parent.$("#table").bootstrapTable('refresh');
                                        }
                                        else if(data.success=="defeated"){
                                        }
                                    }
                                });
                            }
                        }else if(arrselections.length<=0){
                            $.ajax({
                                type: "post",
                                url: "delStaff_Type.do?staff_id="+type,
                                dataType: "text",
                                success: function (data) {
                                    if (data.success=="success") {
                                        parent.$("#table").bootstrapTable('refresh');
                                    }
                                    else if(data.success=="defeated"){
                                    }
                                }
                            });
                        }
                    })
            })

        })

        function stateFormatter(value, row, index) {
            for(var i=0;i<che.length;i++){
                if (row.type_id ==che[i])
                    return {
                        checked : true//设置选中
                    };
            }
            return value;
        }


    </script>
</head>
<body style="width: auto;height: 99%">
<div id="searchPanel">
    <button type="button" class="btn btn-primary glyphicon  glyphicon-floppy-disk"     id="savaTypeBtn"    >&nbsp;确&nbsp;认</button>
</div>
<table id="table"></table>
</body>
</html>
