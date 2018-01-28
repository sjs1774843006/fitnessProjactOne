<%--
  Created by IntelliJ IDEA.
  User: XIN
  Date: 2018/1/16
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="/statics/images/bitbug_favicon.ico" type="image/x-icon"/>
    <title>赋权</title>
    <link href="/statics/themes/insdep/easyui_full.css" rel="stylesheet" type="text/css">
    <link href="/statics/themes/insdep/icon.css" rel="stylesheet" type="text/css">
    <link href="/statics/themes/metroStyle/metroStyle.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/statics/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="/statics/themes/js/jquery.min.js"></script>
    <script type="text/javascript" src="/statics/themes/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/statics/js/toastr-defined.js"></script>
    <script type="text/javascript">
        var staff_id;
        function colseAndOpen(obj, index, id, field) {
            var nodes = $("#tt").treegrid("getChildren", index);
            var state = $(obj).attr('checked') == undefined ? false : true;
            if (state == true) {
                for (var i = 0; i < nodes.length; i++) {
                    if (nodes[i].id == id) {
                        if (field == 'add') {
                            nodes[i].add = '2';
                        } else if (field == 'del') {
                            nodes[i].del = '2';
                        } else if (field == 'update') {
                            nodes[i].update = '2';
                        } else if (field == 'query') {
                            nodes[i].query = '2';
                        }
                    }
                }
                $(obj).attr('checked', false);
            } else {
                for (var i = 0; i < nodes.length; i++) {
                    if (nodes[i].id == id) {
                        if (field == 'add') {
                            nodes[i].add = '1';
                        } else if (field == 'del') {
                            nodes[i].del = '1';
                        } else if (field == 'update') {
                            nodes[i].update = '1';
                        } else if (field == 'query') {
                            nodes[i].query = '1';
                        }
                    }
                }
                $(obj).attr('checked', true);
            }
        }
        $(function() {

            $('#save').click(function () {
                save();
            });
            $('#choseAll').click(function () {
                choseAll();
            });
            $('#forbidAll').click(function () {
                forbidAll();
            });

            staff_id  = sessionStorage.getItem('staff_id');
            var url  = sessionStorage.getItem('query');
                $('#tt').treegrid({
                    url : url,
                    queryParams:{jid: staff_id},
                    idField : 'id',
                    treeField : 'module',
                    fitColumns : true,
                    checkbox :true,
                    fit : true,
                    columns : [ [
                        {
                            field : 'module',
                            title : '模块',
                            width : 60,
                            align : 'center'
                        },
                        {
                            field : 'add',
                            title : '新增',
                            width : 80,
                            align : 'center',
                            formatter : function(value, rec, index) {
                                if (value == 1) {
                                    return "<input type='checkbox' checked='checked' onClick='colseAndOpen(this,"
                                        + rec._parentId
                                        + ","
                                        + rec.id
                                        + ",\"add\")'/>";
                                } else if (value == 2) {
                                    return "<input type='checkbox' onClick='colseAndOpen(this,"
                                        + rec._parentId
                                        + ","
                                        + rec.id
                                        + ",\"add\")'/>";
                                }

                            }
                        },
                        {
                            field : 'del',
                            title : '删除',
                            width : 80,
                            align : 'center',
                            formatter : function(value, rec, index) {
                                if (value == 1) {
                                    return "<input type='checkbox' checked='checked' onClick='colseAndOpen(this,"
                                        + rec._parentId
                                        + ","
                                        + rec.id
                                        + ",\"del\")'/>";
                                } else if (value == 2) {
                                    return "<input type='checkbox' onClick='colseAndOpen(this,"
                                        + rec._parentId
                                        + ","
                                        + rec.id
                                        + ",\"del\")'/>";
                                }

                            }
                        },
                        {
                            field : 'update',
                            title : '修改',
                            width : 80,
                            align : 'center',
                            formatter : function(value, rec, index) {
                                if (value == 1) {
                                    return "<input type='checkbox' checked='checked' onClick='colseAndOpen(this,"
                                        + rec._parentId
                                        + ","
                                        + rec.id
                                        + ",\"update\")'/>";
                                } else if (value == 2) {
                                    return "<input type='checkbox' onClick='colseAndOpen(this,"
                                        + rec._parentId
                                        + ","
                                        + rec.id
                                        + ",\"update\")'/>";
                                }

                            }
                        },
                        {
                            field : 'query',
                            title : '其他',
                            width : 80,
                            align : 'center',
                            formatter : function(value, rec, index) {
                                if (value == 1) {
                                    return "<input type='checkbox' checked='checked' onClick='colseAndOpen(this,"
                                        + rec._parentId
                                        + ","
                                        + rec.id
                                        + ",\"query\")'/>";
                                } else if (value == 2) {
                                    return "<input type='checkbox' onClick='colseAndOpen(this,"
                                        + rec._parentId
                                        + ","
                                        + rec.id
                                        + ",\"query\")'/>";
                                }

                            }
                        } ] ]
                });


        })
        function save() {
            var saveurl  = sessionStorage.getItem('save');
            if(staff_id!=null){
                var nodes = $("#tt").treegrid("getRoots");
                var str = JSON.stringify(nodes);
                $.ajax({
                    url : saveurl,
                    method : 'post',
                    data : 'content=' + str+"&&jid="+staff_id,
                    dataType : 'text',
                    success : function(data) {
                        parent.parent.toastr.success('保存成功', '温馨提示',messageOpts);
                        $('#tt').treegrid("load",{
                            jid: staff_id,
                        });
                    }
                });
            }
        };
        function choseAll(){
            var roots = $("[type='checkbox']");
            $.each(roots,function(i,v){
                $(v).prop("checked",true);
            });
            var nodes = $("#tt").treegrid("getChildren");
            for (var i = 0; i < nodes.length; i++) {
                nodes[i].checked=true;
                if(nodes[i]._parentId!=null){
                    if(nodes[i].add!=null)
                        nodes[i].add = '1';
                    if(nodes[i].del!=null)
                        nodes[i].del = '1';
                    if(nodes[i].update!=null)
                        nodes[i].update = '1';
                    if(nodes[i].query!=null)
                        nodes[i].query = '1';
                }
            }
            var c1 = $("[class='tree-checkbox tree-checkbox0']");
            var c2 = $("[class='tree-checkbox tree-checkbox2']");
            $.each(c1,function(i,v){
                $(v).attr("class","tree-checkbox tree-checkbox1")
            })
            $.each(c2,function(i,v){
                $(v).attr("class","tree-checkbox tree-checkbox1")
            })
        }

        function forbidAll(){
            var roots = $("[type='checkbox']");
            $.each(roots,function(i,v){
                $(v).attr("checked",false);
            })
            var nodes = $("#tt").treegrid("getChildren");
            $('#tt').treegrid('clearChecked');
            for (var i = 0; i < nodes.length; i++) {
                if(nodes[i]._parentId!=null){
                    if(nodes[i].add!=null)
                        nodes[i].add = '2';
                    if(nodes[i].del!=null)
                        nodes[i].del = '2';
                    if(nodes[i].update!=null)
                        nodes[i].update = '2';
                    if(nodes[i].query!=null)
                        nodes[i].query = '2';
                }
            }
            var c1 = $("[class='tree-checkbox tree-checkbox1']");
            var c2 = $("[class='tree-checkbox tree-checkbox2']");
            $.each(c1,function(i,v){
                $(v).attr("class","tree-checkbox tree-checkbox0")
            })
            $.each(c2,function(i,v){
                $(v).attr("class","tree-checkbox tree-checkbox0")
            })
        }
    </script>
<body>
&nbsp;
<button type="button" class="btn btn-primary glyphicon  glyphicon-floppy-disk"  id="save"      >&nbsp;保存权限</button>&nbsp;
<button type="button" class="btn btn-info    glyphicon  glyphicon-ok"           id="choseAll"  >&nbsp;全部启用</button>&nbsp;
<button type="button" class="btn btn-danger  glyphicon  glyphicon-remove"       id="forbidAll" >&nbsp;全部禁用</button>

<div id='main'>
    <div id='ttdiv' style="width: 100%; height: 1000px">
        <table id="tt" style="width: 100%; height: 1000px"></table>
    </div>
</div>

</body>
</html>
