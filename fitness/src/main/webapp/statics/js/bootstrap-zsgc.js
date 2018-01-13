var f=true;
//状态
var state="";
//修改前的值
var Users={};

var IdName = "";
var IdNameValue = "";

var messageOpts = {
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

function update_front(row){
    var visibleColumns = $("#table").bootstrapTable('getVisibleColumns');
    $(Users).prop("parentId",row.parentId);
    $.each(visibleColumns,function(i,column){
        if(column.field==0){

        }
        else {
            var columnName = column.field;
            var rValue = eval("row."+column.field);
            if(rValue!=undefined) {
                $(Users).prop(columnName, rValue);
            }
            else{
                $(Users).prop(columnName, "-");
            }
        }
    });
}
//提交的数据
function submit_data(){
    var visibleColumns = $("#table").bootstrapTable('getVisibleColumns');
    var data={};
    $.each(visibleColumns,function(i,v){
       if(v.field==IdName){
         $(data).prop(v.field,IdNameValue);
       }
        $(data).prop(v.field,$('#'+v.field).val())
    });
    return data;
}
//增加
function add(){
    //取消启用
    $("#cancelBtn").attr('disabled',false);
    //保存启用
    $("#saveBtn").attr('disabled',false);
    if(f) {
        var visibleColumns = $("#table").bootstrapTable('getVisibleColumns');
        var strAppend='<tr id="save">';
        $.each(visibleColumns,function(i,column){
            var _edit=column.edit;
            if(column.field==0){
                strAppend += '<td></td>';
            }
            else if(_edit==undefined) {
                strAppend += '<td><input class="form-control" type="text" value="0" id="' + column.field + '" disabled/></td>';
            }
            else if(_edit.type=="text"){
                strAppend += '<td><input type="text" class="form-control" id="' + column.field + '" /></td>';
            }
            else if(_edit.type=="checkbox"){
                var _edit_type_options=_edit.options;
                strAppend+='<td><select style="width: 66px;height: 33px" class="form-control" id="'+column.field+'">';
                $.each(_edit_type_options,function(value,text){
                    strAppend+='<option value='+value+'>'+text+'</option>';
                });
                strAppend+='</select></td>';
            }
        });
        $("#table").append(strAppend+'</tr>');
        f=false;
        state="add";
    }
    else{
        parent.toastr.warning('请完成当前操作', '温馨提示',messageOpts);
    }
}

//修改
function update(){

    var index = $("#table").bootstrapTable('getSelections');
    if(index.length>1){
        parent.toastr.info('不支持批量修改', '温馨提示',messageOpts);
        return;
    }
    var row=index[0];
    if(!f){
        parent.toastr.warning('请完成当前操作', '温馨提示',messageOpts);
        return;
    }
    if(index.length<=0) {
        parent.toastr.warning('请选择需要修改的数据', '温馨提示', messageOpts);
        return;
    }
    //取消启用
    $("#cancelBtn").attr('disabled',false);
    //保存启用
    $("#saveBtn").attr('disabled',false);
    var visibleColumns = $("#table").bootstrapTable('getVisibleColumns');
    update_front(row);
    var columns = {};
    $.each(visibleColumns,function(i,column){
        if(column.field==0){
        }
        else {
            var _edit=column.edit;
            var columnName = column.field;
            var rValue = eval("row." + column.field);
            if(_edit==undefined) {
                IdName = column.field;
                IdNameValue = rValue;
                $(columns).prop(columnName, rValue);
            }
            else{
                var _edit_type=_edit.type;
                if(_edit_type=="text"){
                    $(columns).prop(columnName, "<input type='text' class=\"form-control\" value=\"" + rValue + "\" id=\"" + column.field + "\">");
                }
                else if(_edit_type=="checkbox"){
                    var _edit_type_options=_edit.options;
                    var _edit_type_options_value='<select style="width: 66px;height: 33px" class="form-control" id="'+column.field+'">';
                    $.each(_edit_type_options,function(value,text){
                        _edit_type_options_value+='<option value='+value+'>'+text+'</option>';
                    });
                    _edit_type_options_value+='</select>';
                    $(columns).prop(columnName,_edit_type_options_value)
                }
                else if(_edit_type=="combogrid"){
                    var combogrid_content_start='<div class="input-group"><input id="'+column.field+'" type="text" class="form-control" aria-label="..."> <div class="input-group-btn" id="div_'+column.field+'"> <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&nbsp; <span class="caret"></span></button></div></div>';
                    $(columns).prop(columnName,combogrid_content_start);
                    var _edit_utl=_edit.url;
                    var _edit_columns=_edit.columns;
                    var _combogrid_table_context='<table class="table table-bordered table-hover dropdown-menu dropdown-menu-right table-responsive"><thead><tr>';
                    $.each(_edit_columns,function(c,_edit_column){
                        $.each(_edit_column,function(index,content){
                            _combogrid_table_context+='<th class="active">'+content.title+'</th>';
                        });
                    });
                    _combogrid_table_context+='</thead><tbody>';
                    $.ajax({
                        url:_edit_utl,
                        type:'post',
                        dataType:'JSON',
                        success:function(data){
                            $.each(data,function(index,obj){
                                _combogrid_table_context+='<tr>';
                                $.each(_edit_columns,function(c,_edit_column){
                                    $.each(_edit_column,function(index,content){
                                        $.each(obj,function(name_space,value){
                                            if(content.field==name_space) {
                                                _combogrid_table_context += '<td>' + value + "</td>";
                                            }
                                        });
                                    });
                                });
                                _combogrid_table_context+='</tr>';
                            });
                            _combogrid_table_context+='</tbody></table>';
                            $("#div_"+column.field).append(_combogrid_table_context);
                        }
                    });
                }
            }
        }
    });
    var rows = $("#table").bootstrapTable("updateRow",{
        index: row.parentId,
        row: columns,

    });
    f=false;
    state="update";
}


function save(){
    //取消禁用
    $("#cancelBtn").attr('disabled',true);
    //保存禁用
    $("#saveBtn").attr('disabled',true);
    var datass = submit_data();
    if(!f) {
        if (state == "add") {
            save_add(datass);
            $("#save").remove();
            f = true;

        }
        else if(state=="update"){
            save_update(datass);
            var rows = $("#table").bootstrapTable("updateRow",{
                index: Users.parentId,
                row: datass
            });
            f = true;
            state="";

        }
    }
    else{
        parent.toastr.warning('请完成当前操作', '温馨提示',messageOpts);
    }
}
function cancel() {
    //取消禁用
    $("#cancelBtn").attr('disabled',true);
    //保存禁用
    $("#saveBtn").attr('disabled',true);
    if(state=="add") {
        $("#save").remove();
        f=true;
        state="";
    }
    else if(state=="update") {
        var rows = $("#table").bootstrapTable("updateRow", {
            index: Users.parentId,
            row: Users
        });
        f=true;
        state="";
    }
}


function  del(url,arrselections,numberId){
    if(!f){
        parent.toastr.info('请完成当前操作', '温馨提示',messageOpts);
    }else{
        if(arrselections.length>1){
            parent.toastr.warning('不支持批量删除', '温馨提示',messageOpts);
            return;
        }
        Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                url :url,
                data : 'id='+numberId,
                method : "post",
                dataType : 'JSON',
                success: function (data) {
                    if (data.success=="success") {
                        parent.toastr.success('删除数据成功','温馨提示',messageOpts);
                        $("#table").bootstrapTable('refresh');
                    }else if(data.success=="defeated"){
                        parent.toastr.error('删除数据失败', '温馨提示',messageOpts);
                    }
                },
                error: function () {
                    parent.toastr.error('Error', '温馨提示',messageOpts);
                },
                complete: function () {
                }
            });
        });
    }
}

$(function(){

    //取消禁用
    $("#cancelBtn").attr('disabled',true);
    //保存禁用
    $("#saveBtn").attr('disabled',true);

    //增加
    $("#addBtn").click(function(){
        add();
    })

    //修改
    $("#editBtn").click(function(){
        update()
    })
    //取消
    $("#cancelBtn").click(function(){
        cancel();
    })
    //保存
    $("#saveBtn").click(function(){
        save();
    })
    //数据export导出
    $("#exportBtn").click(function(){
        $('#table').tableExport({type:'excel',escape:false})
    })

})