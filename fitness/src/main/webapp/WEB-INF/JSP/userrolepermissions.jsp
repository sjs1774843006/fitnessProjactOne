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
    <title>员工拥有权限</title>
    <link href="/statics/themes/insdep/easyui_full.css" rel="stylesheet" type="text/css">
    <link href="/statics/themes/insdep/icon.css" rel="stylesheet" type="text/css">
    <link href="/statics/themes/metroStyle/metroStyle.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/statics/themes/js/jquery.min.js"></script>
    <script type="text/javascript" src="/statics/themes/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/statics/js/toastr-defined.js"></script>
    <script type="text/javascript">
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
            var staff_id_all  = sessionStorage.getItem('staff_id');
                $('#tt').treegrid({
                    url : 'staffjurisdiction.do',
                    queryParams:{jid: staff_id_all},
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


    </script>
<body>

<div id='main'>
    <div id='ttdiv' style="width: 100%; height: 1000px">
        <table id="tt" style="width: 100%; height: 1000px"></table>
    </div>
</div>

</body>
</html>
