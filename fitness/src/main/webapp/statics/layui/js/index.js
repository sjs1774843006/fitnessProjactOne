<!--获取title -->
var titles="";
var page = "首页";
function closeTab(obj){
	var length=$("#mainTab").tabs('tabs').length;
	<!--关闭当前 -->
	if(obj=='present'){
		$("#mainTab").tabs('close',titles)
	}<!--关闭所有 -->
	else if(obj=='all'){
	 var length=$("#mainTab").tabs('tabs').length;
		for(var i=1;i<length;i++){
			$("#mainTab").tabs('close',1);
		}

	}<!--关闭其他 -->
	else if(obj=='else'){
	var list = new Array();
	 var len=$("#mainTab").tabs('tabs');
		for(var i=0;i<len.length;i++){
			if(len[i].panel('options').title!=titles){
				list.push(len[i].panel('options').title);
			}
		}
		for(var i=0;i<list.length;i++){
				if(i!=0){
					$("#mainTab").tabs('close',list[i]);
				}
		}
		
	} <!--关闭左边 -->
	else if(obj=='left'){		
	 var list = new Array();
	 var p=0;
	 var len=$("#mainTab").tabs('tabs');
		for(var i=0;i<len.length;i++){
			if(len[i].panel('options').title==titles){
				p=i;
			}
			list.push(len[i].panel('options').title);
		}
			for(var i=1;i<list.length;+i++){
				if(i<p){
					$("#mainTab").tabs('close',1);
				}
			}
	
	} 
	<!--关闭右边 -->
	else if(obj=='right'){	
	var list=new Array();
	var n=0;
	var tt=$('#mainTab').tabs('tabs');
	for(var i=0;i<tt.length;i++){
	if(tt[i].panel('options').title==titles){
		n=i;
	}
	list.push(tt[i].panel('options').title);
}
			for(var i=1;i<list.length;i++){
				if(i>n){
					$('#mainTab').tabs('close',n+1);
				}
			}
	
	} 
}
var setting = {
		async: {
			enable: true,
			type: "post",
			autoParam: ["m_id"],
			url:'ztree.action',
		},
		data:{
			simpleData: {
				enable: true
			}
		},
		callback : {
			onClick:zTreeOnClick,
			beforeClick: beforeClick,
	    },	
	};


function filter(treeId, parentNode, childNodes) {
    var nodes =  JSON.parse(childNodes.data);
    if (!nodes)
        return null;
    for (var i = 0, l = nodes.length; i < l; i++) {
        nodes[i].name = nodes[i].name.replace(/\.n/g, '.');
    }
    return nodes;
}
function beforeRemove(treeId, treeNode) {
    if (confirm("确认删除此节点吗?")) {
        var param = "id=" + treeNode.m_id;
      	$.ajax({
			url : 'delModule.action',
			dataType : 'JSON',
			method : "post",
			data : param,
			success : function(data) {
				if(data.success=="success"){
				}
			}
		});
        
    } else {
        return false;
    }
}
function beforeRename(treeId, treeNode, newName) {
	var newname = treeNode.name;
    if (newName.length == 0) {
        $.messager.show({
			title : '提示信息',
			msg : '节点名称不能为空',
		});
        var param = "id=" + treeNode.m_id + "&name=" + newname;
    }
    else{
    	var param = "id=" + treeNode.m_id + "&name=" + newName;
     	$.ajax({
			url : 'updateModule.action',
			dataType : 'JSON',
			method : "post",
			data : param,
			success : function(data) {
				if(data.success=="success"){
				}
			}
		});
    }
    
  
    return true;
}

var newCount = 1;
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
        return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_" + treeNode.tId);
    if (btn)
        btn.bind("click", function() {
           var Ppname = prompt("请输入新节点名称");
            if (Ppname == null) {
                return;
            } else if (Ppname == "") {
                alert("节点名称不能为空");
            } else {
                var param ="&pId="+ treeNode.m_id + "&name=" + Ppname;
                var zTree = $.fn.zTree.getZTreeObj("power");
           
            	$.ajax({
					url : 'moduleAdd.action',
					dataType : 'JSON',
					method : "post",
					data : param,
					success : function(data) {
						if(data.success=="success"){
							zTree.refresh();
						}
					}
				});
                
            }

        });
};
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
};


function beforeClick(treeId, treeNode) {
	if (treeNode.isParent) {
		return false;
	} else {
		return true;
	}
}
	
	
function zTreeOnClick(event, treeId, treeNode) {
	var content = "";
	var title = treeNode.name;
	<!--浏览器属性禁用 并自定义一个右键事件 -->
	$(" .tabs li").bind('contextmenu',function(e){
					e.preventDefault();
					var  thisFchil = $(this).children(":first");
					titles=$(thisFchil).children(":first").text();     
					$("#menu").menu('show',{left:e.pageX,top:e.pageY});
	});
	

	if($("#mainTab").tabs('exists',title)){
			$("#mainTab").tabs('select',title);
		return;	
	}
	else{
		if(treeNode.jsp!=""){
			content = "<iframe frameborder=\"0\" src=\""+treeNode.jsp+"\" width=99% height=99% scrolling=\"no\"></iframe>"
		}
				<!--增加一个窗体 -->
				$("#mainTab").tabs('add',{"title":title,"closable":true,content:content});
				<!--将浏览器属性禁用 -->
				$(" .tabs-last").bind('contextmenu',function(e){
					e.preventDefault();
					
					var  thisFchil = $(this).children(":first");
					titles=$(thisFchil).children(":first").text();
					<!--自定义右键事件-->
				$("#menu").menu('show',{left:e.pageX,top:e.pageY});
					
					});
		}
};
	
$(function(){
	
	$(".tabs li").bind('contextmenu',function(e){
		e.preventDefault();
		$("#menu").menu('show',{left:e.pageX,top:e.pageY});
	})
	$.fn.zTree.init($("#power"),setting);
});


