var f=true;
//状态
var state="";
//修改前的值
var Users={};

var IdName = "";
var IdNameValue = "";

var  images_upload_fileid = "";
var  images_upload_value = "";

//修改前得到原始数据并设置入新增的input中
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
            }else{
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
       }else if(v.field==images_upload_fileid){
           if (state == "add") {
               $(data).prop(v.field,$("#uploadBtnImgw").attr("src"));
           }else{
            $(data).prop(v.field,$("#uploadBtnImg"+IdNameValue).attr("src"));
           }
       }
           $(data).prop(v.field,$('#'+v.field).val())
    });

    return data;
}
//增加一行
function add(){
    //取消启用
    $("#cancelBtn").attr('disabled',false);
    //保存启用
    $("#saveBtn").attr('disabled',false);

    if(f) {
        var visibleColumns = $("#table").bootstrapTable('getVisibleColumns');
        var strAppend='<tr id="save">';
        var form_datetime_ids=new Array();
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
            else if(_edit.type=="password"){
                strAppend += '<td><input type="password" class="form-control" id="' + column.field + '" /></td>';
            }
            else if(_edit.type=="img"){
                images_upload_fileid==column.field;
                strAppend +='<td><form  id="formw" >';
                strAppend += '<input  type="file" name="file" id="filew" style="display: none">';
                strAppend += '<div style="border: dashed 1px #ccc; width: 100px ;height: 80px;" id="uploadBtnw"  >';
                strAppend += '<div style=" text-align: center; width: 100px;  height: 80px; float: left; padding-left: 0px; margin-left: 0px;">';
                strAppend += '<img id="uploadBtnImgw"   style="cursor: pointer;  width: 100px;  height: 80px;" src="/statics/images/nwc.png">';
                strAppend += '</div></div>';
                strAppend += '</form></td>';
            }
            else if(_edit.type=="checkbox"){
                var _edit_type_options=_edit.options;
                strAppend+='<td><select style="width: 100%;height: 33px" class="form-control" id="'+column.field+'">';
                $.each(_edit_type_options,function(value,text){
                    strAppend+='<option value='+value+'>'+text+'</option>';
                });
                strAppend+='</select></td>';
            }else if(_edit.type=="combogrid"){
                var idf = column.edit.idField;
                strAppend +='<td><div class="input-group" ><input  id="ming_'+column.field+'" type="text" class="form-control" aria-label="..."> ' +
                    '<input  id="'+column.field+'" type="hidden" class="form-control" aria-label="..."><div class="input-group-btn" id="div_'+column.field+'"> <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&nbsp; <span class="caret"></span></button></div></div></td>';
                $(strAppend).prop(column.field ,strAppend);
                var _edit_utl=_edit.url;
                var _edit_columns=_edit.columns;
                var _combogrid_table_context='<table style="min-width: 700px;"  class="table table-bordered table-hover dropdown-menu dropdown-menu-right table-responsive"><thead><tr>';
                $.each(_edit_columns,function(c,_edit_column){
                    $.each(_edit_column,function(index,content){
                        if(idf==content.field){
                            _combogrid_table_context+='<th class="active" style="width:auto;">'+content.title+'</th>';
                        }else{
                            _combogrid_table_context+='<th class="active" style="width:100%">'+content.title+'</th>';

                        }
                    });
                });
                _combogrid_table_context+='</thead><tbody>';
                $.ajax({
                    url:_edit_utl,
                    type:'post',
                    dataType:'JSON',
                    success:function(data){
                        $.each(data,function(index,obj){
                            var id_field = eval("obj."+idf);
                            _combogrid_table_context+='<tr  onclick=\"getval('+id_field+','+"'"+column.field +"'"+')\">';
                            $.each(_edit_columns,function(c,_edit_column){
                                $.each(_edit_column,function(index,content){
                                    $.each(obj,function(name_space,value){
                                        if(content.field==name_space) {
                                            if(idf==name_space){
                                                _combogrid_table_context += '<td id=\"td_id_'+id_field+'\">' + value + "</td>";
                                            }else{
                                                _combogrid_table_context += '<td  id=\"td_name_'+id_field+'\">' + value + "</td>";
                                            }
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
            } else if(_edit.type=="date"){
                var _date_context="<td><div class=\"input-group\">\n" +
                    "    <input  type=\"text\" id=\""+column.field+"\" onfocus=\"update_class('"+column.field+"')\"  class=\"form-control\" aria-label=\"...\">\n" +
                    "    <div class=\"input-group-btn\" id=\"date_div_class_"+column.field+"\">\n" +
                    "        <button class=\"btn btn-default dropdown-toggle\"  id=\"date_btn_"+column.field+"\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">&nbsp;<span class=\"caret\"></span></button>\n" +
                    "        <div class=\"input-append date dropdown-menu dropdown-menu-right\" id=\"form_datetime_"+column.field+"\">\n" +
                    "            <span class=\"add-on\"><i class=\"icon-th\"></i></span>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div></td>";
                strAppend+=_date_context;
                form_datetime_ids.push(column.field);
            }

        });
        $("#table").append(strAppend+'</tr>');
        $.each(form_datetime_ids,function(i,columnName){
            form_datetime(columnName);
        })
        init('w');
        f=false;
        state="add";
    }
    else{
        parent.toastr.warning('请完成当前操作', '温馨提示',messageOpts);
    }
}
//修改模式开启编辑框
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
    var One = 0;
    var form_datetime_ids=new Array();
    $.each(visibleColumns,function(i,column){
        if(column.field==0){
        }
        else {

            var _edit=column.edit;
            var columnName = column.field;
            var rValue = eval("row." + column.field);
            if(_edit==undefined) {
                if(One==0){
                    IdName = column.field;
                    IdNameValue = rValue;
                    One=1;
                }
                $(columns).prop(columnName, rValue);

            }
            else{
                var _edit_type=_edit.type;
                if(_edit_type=="text"){
                    $(columns).prop(columnName, "<input type='text' class=\"form-control\" value=\"" + rValue + "\" id=\"" + column.field + "\">");
                }
                else if(_edit_type=="password"){
                    $(columns).prop(columnName, "<input type='password' class=\"form-control\" value=\"" + rValue + "\" id=\"" + column.field + "\">");
                }
                else if(_edit_type=="img"){
                    ifuploadimg(column.field)
                    images_upload_fileid=column.field;
                }
                else if(_edit_type=="checkbox"){
                    var _edit_type_options=_edit.options;
                    var _edit_type_options_value='<select style="width: 100%;height: 33px" class="form-control" id="'+column.field+'">';
                    $.each(_edit_type_options,function(value,text){
                        _edit_type_options_value+='<option value='+value+'>'+text+'</option>';
                    });
                    _edit_type_options_value+='</select>';
                    $(columns).prop(columnName,_edit_type_options_value)
                }
                else if(_edit_type=="combogrid"){
                    var idf = column.edit.idField;
                    var combogrid_content_start='<div class="input-group" ><input  id="ming_'+column.field+'" type="text" class="form-control" aria-label="..."> ' +
                        '<input value="'+rValue+'" id="'+column.field+'" type="hidden" class="form-control" aria-label="..."><div class="input-group-btn" id="div_'+column.field+'" > <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&nbsp; <span class="caret"></span></button></div></div>';
                    $(columns).prop(columnName,combogrid_content_start);
                    var _edit_utl=_edit.url;
                    var _edit_columns=_edit.columns;
                    var _combogrid_table_context='<table  style="min-width: 700px;" class="table table-bordered table-hover dropdown-menu dropdown-menu-right table-responsive"  ><thead><tr>';
                    $.each(_edit_columns,function(c,_edit_column){
                        $.each(_edit_column,function(index,content){
                            if(idf==content.field){
                                _combogrid_table_context+='<th class="active" style="width:auto;">'+content.title+'</th>';
                            }else{
                                _combogrid_table_context+='<th class="active" style="width:100%">'+content.title+'</th>';

                            }
                        });
                    });
                    _combogrid_table_context+='</thead><tbody>';
                    $.ajax({
                        url:_edit_utl,
                        type:'post',
                        dataType:'JSON',
                        success:function(data){
                            $.each(data,function(index,obj){
                                var id_field = eval("obj."+idf);
                                _combogrid_table_context+='<tr  id="td_id_td_name_'+id_field+'" onclick=\"getval('+id_field+','+"'"+columnName+"'"+')\">';
                                $.each(_edit_columns,function(c,_edit_column){
                                    $.each(_edit_column,function(index,content){
                                        $.each(obj,function(name_space,value){
                                            if(content.field==name_space) {
                                                if(idf==name_space){
                                                    _combogrid_table_context += '<td id=\"td_id_'+id_field+'\">' + value + "</td>";
                                                }else{
                                                    _combogrid_table_context += '<td  id=\"td_name_'+id_field+'\">' + value + "</td>";
                                                }
                                            }
                                        });
                                    });
                                });
                                _combogrid_table_context+='</tr>';
                            });
                            _combogrid_table_context+='</tbody></table>';
                            $("#div_"+column.field).append(_combogrid_table_context);
                            value(rValue,columnName);
                        }
                    });
                }else if(_edit_type=="date"){
                    var _date_context="<div class=\"input-group\">\n" +
                        "    <input value='"+rValue+"' type=\"text\" id=\""+columnName+"\" onfocus=\"update_class('"+columnName+"')\" class=\"form-control\" aria-label=\"...\">\n" +
                        "    <div class=\"input-group-btn\" id=\"date_div_class_"+columnName+"\">\n" +
                        "        <button class=\"btn btn-default dropdown-toggle\"  id=\"date_btn_"+columnName+"\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">&nbsp;<span class=\"caret\"></span></button>\n" +
                        "        <div class=\"input-append date dropdown-menu dropdown-menu-right\"   id=\"form_datetime_"+columnName+"\">\n" +
                        "            <span class=\"add-on\"><i class=\"icon-th\"></i></span>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</div>";
                    $(columns).prop(columnName,_date_context);
                    form_datetime_ids.push(columnName);
                }

            }
        }
    });
    var rows = $("#table").bootstrapTable("updateRow",{
        index: row.parentId,
        row: columns,
    });

    $.each(form_datetime_ids,function(i,columnName){
        form_datetime(columnName);
    })
    f=false;
    state="update";
    init(IdNameValue);
}

function  ifuploadimg(idFiled){

    return ;
}
//文本框聚焦事件
var update_class = function(columnName){
    $("#date_div_class_"+columnName).hasClass('input-group-btn open');
}

//下拉时间选择器初始化
function form_datetime(id){
    $("#form_datetime_"+id).datetimepicker({
        format: "yyyy-mm-dd hh:ii",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN',
        pickerPosition:"bottom-left"
    });
}
//编辑模式设置选中样式
function value(id,text_name){
    var value = $("#td_name_"+id ).html()
    $("#ming_"+text_name).val(value);
    $("#td_id_td_name_"+id).addClass('success');
}

//保存数据
function save(){
    if($("#uploadBtnImgw").attr("src")=="/statics/images/nwc.png"){
        parent.toastr.warning('请上传头像', '温馨提示', messageOpts);
        return ;
    }
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

//取消任何操作
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

//删除判断
function  del(url,arrselections,numberId){
    if(!f){
        parent.toastr.info('请完成当前操作', '温馨提示',messageOpts);
    }else{
        if(arrselections.length>1){
            Ewin.confirm({ message: "确认要批量删除吗？" }).on(function (e) {
                if (!e) {
                    return;
                }
                for(var i=0;i<arrselections.length;i++){
                    var mubId = eval("arrselections[i]."+numberId);
                    sql_delect(url,mubId)
                }

            })
        }else{
            Ewin.confirm({ message: "确认要删除该条数据吗？" }).on(function (e) {
                if (!e) {
                    return;
                }
                var mubId = eval("arrselections[0]."+numberId);
                sql_delect(url,mubId)
            });
        }
        $("#table").bootstrapTable('refresh');
    }
}

//删除的ajax
function  sql_delect(url,numberId){
    $.ajax({
        url :url,
        data : 'id='+numberId,
        method : "post",
        dataType : 'JSON',
        success: function (data) {
            if (data.success=="success") {
                parent.toastr.success('删除数据成功','温馨提示',messageOpts);
            }else if(data.success=="defeated"){
                parent.toastr.error('删除数据失败', '温馨提示',messageOpts);
                $("#table").bootstrapTable('refresh');
            }
            else if(data.success=="insufficient"){
                parent.toastr.error('无法删除除自己以外他人的信息', '温馨提示',messageOpts);
                $("#table").bootstrapTable('refresh');
            }
        },
        error: function () {
            parent.toastr.error('Error', '温馨提示',messageOpts);
        },
        complete: function () {
        }
    });
}

//编辑模式得到选中的值并设置文本框的值
function getval(id,text_name){
    var value = $("#td_name_"+id ).html()
    var value_id = $("#td_id_"+id ).html()
    $("#ming_"+text_name).val(value);
    $("#"+text_name).val(value_id);
}

//文件图片上传
function  init(columid){
    $("#uploadBtn"+columid).click(function() {
        $("#file"+columid).trigger('click');
    });
    $("#form"+columid).on("change","#file"+columid,function(e){
        if(this.value.length>0){
            var formData = new FormData($( "#form"+columid )[0]);
            $.ajax({
                url: "fileupload.do",
                type: "POST",
                data: formData,
                dataType: "JSON",
                contentType: false,
                processData: false,
                success: function (data) {
                    $("#uploadBtnImg"+columid).attr("src",data.success);
                    images_upload_value = data.success;
                },
                error: function () {
                    parent.toastr.error('上传失败!!!', '温馨提示',messageOpts);
                }
            });
        }
    })

}

function  controlButton(data){
    $.each(data,function(k,v){
        $.each(v,function(key,val){
            if(val=="add"){
                $("#addBtn").show();
                $("#cancelBtn").show();
                $("#saveBtn").show();
            }else if(val=="del"){
                $("#delBtn").show();
            }else if(val=="update"){
                $("#editBtn").show();
                $("#cancelBtn").show();
                $("#saveBtn").show();
            }else if(val=="all"){
                $("#exportBtn").show();
                // $("#dataBtn").show();
            }else if(key=="staff"&val=="admin"){
                $("#addstafftypeBtn").show();
                $("#jurisdictionBtn").show();
            }
        })
    })
}


$(function(){
    //取消禁用
    $("#cancelBtn").attr('disabled',true);
    //保存禁用
    $("#saveBtn").attr('disabled',true);

    $("#addBtn").hide();
    $("#delBtn").hide();
    $("#editBtn").hide();
    $("#cancelBtn").hide();
    $("#saveBtn").hide();
    $("#exportBtn").hide();

    //角色
    $("#addstafftypeBtn").hide();
    $("#jurisdictionBtn").hide();
    // $("#dataBtn").hide();


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