<%--
  Created by IntelliJ IDEA.
  User: XIN
  Date: 2017/12/30
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/statics/images/bitbug_favicon.ico" type="image/x-icon"/>
    <title>模块</title>
    <link rel="stylesheet" type="text/css" href="/statics/bootstrap/css/bootstrap.min.css" />
    <link href="/statics/themes/demo.css" rel="stylesheet" type="text/css" />
    <script src="/statics/miniui/boot.js" type="text/javascript"></script>
</head>
<body>
<div style="margin:10px 0;"></div>
<div id="treegrid" class="mini-treegrid" style="width:99%"
     url="queryModulelist.do"
     showTreeIcon="true"
     treeColumn="taskname"
     idField="module_id"
     parentField="module_pid"
     resultAsTree="false"
     allowResize="false"
     expandOnLoad="true"
     frozenEndColumn="1"
     allowSortColumn="false"
     sortMode="client">
    <div property="columns">
        <div type="indexcolumn" headerAlign="center"  width="10px" >菜单编号</div>
        <div name="taskname" field="module_name" headerAlign="center" property="editor" >菜单标题</div>
    </div>
</div>

</body>
</html>