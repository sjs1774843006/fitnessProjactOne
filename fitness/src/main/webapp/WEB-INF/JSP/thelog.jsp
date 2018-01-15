<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/statics/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/statics/css/toastr.min.css">
    <link rel="stylesheet" href="/statics/bootstrap-table/dist/bootstrap-table.min.css">
    <script src="/statics/js/jquery.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap.min.js"></script>
    <script src="/statics/bootstrap-table/dist/bootstrap-table.js"></script>
    <script src="/statics/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
    <script src="/statics/js/toastr.js"></script>
    <script src="/statics/js/tableExport.min.js"></script>
    <script src="/statics/js/toastr-min.js"></script>
    <script src="/statics/js/bootstrap-zsgc.js"></script>



    <script>
        $(function(){
            $('#table').bootstrapTable({
                method:'get',//提交方式
                url: 'queryTheLoglist.do',//提交地址
                striped: true,//设置为 true 会有隔行变色效果
                cache: false,//设置为 false 禁用 AJAX 数据缓存
                sortable: false,//设置为false 将禁止所有列的排序
                sortOrder: "asc",//定义排序方式 'asc' 或者 'desc'
                pageSize: 10,
                pageList: [10, 25, 50, 100],
                queryParams:function(params){
//                    var subcompany = $('#subcompany option:selected').val();
                    return {
                        offset: params.offset,
                        limit: params.limit,
//                        companyId:subcompany,
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
                    field: 'thelog_id',
                    title: 'ID',
                    align:'center',
                    sortable:'true',
                }, {
                    field: 'thelog_contents',
                    title: '日志内容',
                    align:'center',
                    sortable:'true',

                }, {
                    field: 'thelog_date',
                    title: '操作时间',
                    align:'center',
                    sortable:'true',

                }, {
                    field: 'staffmember_id',
                    title: '操作人',
                    align:'center',
                    sortable:'true',

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
                del("delcommissiondata.do",arrselections,arrselections[0].thelog_id);
            });

        })
    </script>
</head>
<body style="width: auto;height: 99%">

<div id="searchPanel">

        <%--<button type="button" class="btn btn-danger  glyphicon  glyphicon-minus "        id="delBtn"    >&nbsp;删&nbsp;除</button>--%>
        <button type="button" class="btn btn-inverse glyphicon  glyphicon-export"        id="exportBtn" >&nbsp;导出Excel</button>
</div>
    <table id="table"></table>
</body>
</html>
