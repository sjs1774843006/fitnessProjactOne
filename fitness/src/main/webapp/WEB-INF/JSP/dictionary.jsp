<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="icon" href="/statics/images/bitbug_favicon.ico" type="image/x-icon"/>
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
    <script src="/statics/js/bootstrap-crud.js"></script>



    <script>
        $(function(){

            $.ajax({
                url : 'controlbutton.do',
                data : 'm_id=15',
                method : "post",
                dataType : 'JSON',
                success : controlButton,
            });

            $('#table').bootstrapTable({
                method:'get',//提交方式
                url: 'queryDatalist.do',//提交地址
                striped: true,//设置为 true 会有隔行变色效果
                cache: false,//设置为 false 禁用 AJAX 数据缓存
                sortable: false,//设置为false 将禁止所有列的排序
                sortOrder: "asc",//定义排序方式 'asc' 或者 'desc'
                pageSize: 10,
                height:850,
                pageList: [10, 25, 50, 100],
                queryParams:function(params){
                    var subcompany = $('#subcompany option:selected').val();
                    return {
                        offset: params.offset,
                        limit: params.limit,
                        companyId:subcompany,
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
                    field: 'data_id',
                    title: 'ID',
                    align:'center',
                    sortable:'true',
                }, {
                    field: 'data_name',
                    title: '数据字典名称',
                    align:'center',
                    sortable:'true',
                    edit:{
                        type:'text'
                    }
                }, {
                    field: 'data_message',
                    title: '字典说明',
                    align:'center',
                    edit:{
                        type:'text'
                    }
                }, {
                    field: 'data_url',
                    title: '跳转URL',
                    align:'center',
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
                del("delData.do",arrselections,arrselections[0].data_id);
            });

//            数据项
             $("#dataBtn").click(function(){
                 if(!f){
                     parent.toastr.info('请完成当前操作', '温馨提示',messageOpts);
                 }else {
                     var arrselections = $("#table").bootstrapTable('getSelections');
                     if (arrselections.length <= 0) {
                         parent.toastr.warning('请选择需要查看的数据项', '温馨提示', messageOpts);
                         return;
                     }if(arrselections.length > 1){
                         parent.toastr.warning('选择查看的数据项过多', '温馨提示', messageOpts);
                         return;
                     }
                         $('#_modalDialog').modal('show');
                     if(arrselections[0].data_id=='1'){
                         openwindow(arrselections[0].data_url);
                     }else if(arrselections[0].data_id=='2'){
                         openwindow(arrselections[0].data_url);
                     }else if(arrselections[0].data_id=='3'){
                         openwindow(arrselections[0].data_url);
                     }else if(arrselections[0].data_id=='4'){
                         openwindow(arrselections[0].data_url);
                     }else if(arrselections[0].data_id=='5'){
                         openwindow(arrselections[0].data_url);
                     }else if(arrselections[0].data_id=='6'){
                         openwindow(arrselections[0].data_url);
                     }

                 }
            })

        })

        function openwindow(obj){
            $("#selectTree").html(" <iframe frameborder = '0' border = '0'  src="+obj+"  scrolling='no' width=99% height=99% ></iframe>")
        }

        //修改
        function save_update(datass){
            $.ajax({
                type: "post",
                url: "updateData.do",
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
                }
            });
        }


    </script>
</head>
<body style="width: auto;height: 99%">

<div id="searchPanel">
    &nbsp;
        <button type="button" class="btn btn-danger glyphicon glyphicon-minus "  id="delBtn"    >&nbsp;删&nbsp;除</button>
        <button type="button" class="btn btn-info glyphicon glyphicon-pencil" id="editBtn"   >&nbsp;修&nbsp;改</button>
        <button type="button" class="btn btn-warning glyphicon  glyphicon-remove" id="cancelBtn" >&nbsp;取&nbsp;消</button>
        <button type="button" class="btn btn-success glyphicon glyphicon-floppy-disk"    id="saveBtn"   >&nbsp;保&nbsp;存</button>
        <button type="button" class="btn btn-primary glyphicon  glyphicon-option-horizontal" id="dataBtn">&nbsp;数据项</button>
        <button type="button" class="btn btn-inverse glyphicon glyphicon-export" id="exportBtn"   >&nbsp;导出Excel</button>

</div>
    <table id="table"></table>

<%--弹出框开始--%>
<div class="modal fade in" id="_modalDialog" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document" style="width:100%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="modalLabel">详细数据项</h4></div>
            <div id="_modalDialog_body" class="modal-body">
                <!--  设置这个div的大小，超出部分显示滚动条 -->
                <div id="selectTree" class="ztree" style="height:800px;overflow:auto; ">

                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div><%--弹出框结束--%>

</body>
</html>
