$(function() {
	
	$.ajax({
		url : 'controlbutton.action',
		data : 'm_id=6',
		method : "post",
		dataType : 'JSON',
		success : controlButton,
	});
	
	$("#list").datagrid({
		pagination : true,
		idField : "a_id",
		pageList : [ 10, 20, 30 ],
		pageSize : 10,
		method : "post",
		singleSelect : true,
		toolBar : "#searchPanel",
		resizable : true,
		remoteSort : false,
		fitColumns : true,
		fit : true,
		onEndEdit : finishEdit,
		onBeginEdit : startEdit,
		url : 'queryApprove.action',
		columns : [ [ {
			title : "编号",
			field : "a_id",
			sortable : true,
			align : 'center',
		}, {
			title : "状态",
			field : "a_state",
			align : 'center',
			formatter:function(value){
				if(value == 1){
					return '待审核';
				}else if(value == 2){
					return '审核中';
				}else if(value == 3){
					return '审核通过';
				}else if(value == 4){
					return '审核未通过';
				}
			},
			editor : {
				type : 'combobox',
				options:{
					valueField:'id',
					textField: 'text',
					editable : false,
					url:'appdata.action',
					}
			},
			sortable : true,
			width : 150
		}, {
			title : "申请人ID",
			field : "r_id",
			align : 'center',
			editor : {
				type : 'combogrid',
				options : {
						 editable : false,
						 panelWidth:700,  
		                 idField:'r_id',  
		                 textField:'r_realname',
		                 url:'QueryUserList.action',
		     			columns : [ [ {
		    				title : "用户编号",
		    				field : "r_id",
		    				align : 'center',
		    				sortable : true,
		    				width : 50
		    			}, {
		    				title : "用户类型",
		    				field : "r_type",
		    				align : 'center',
		    				sortable : true,
		    				width : 100,
		    				formatter:function(value){
		    					if(value == 1){
		    						return '普通用户';
		    					}else{
		    						return '推广用户';
		    					}
		    				},
		    				editor : {
		    					type : 'combobox',
		    					options:{
		    						valueField:'id',
		    						textField: 'text',
		    						editable : false,
		    						url:'r_typedata.action',
		    						}
		    				}
		    			}, {
		    				title : "联系方式",
		    				field : "r_phone",
		    				align : 'center',
		    				sortable : true,
		    				width : 110,
		    			}, {
		    				title : "用户姓名",
		    				field : "r_realname",
		    				align : 'center',
		    				sortable : true,
		    				width : 110,
		    			}, {
		    				title : "账号状态",
		    				field : "r_state",
		    				align : 'center',
		    				sortable : true,
		    				width : 107,
		    			}, {
		    				title : "身份证号码",
		    				field : "r_card",
		    				align : 'center',
		    				sortable : true,
		    				width : 110,
		    			}, {
		    				title : "教育水平",
		    				field : "r_el",
		    				align : 'center',
		    				sortable : true,
		    				width : 110,
		    			} 
		    			] ]
				}
			},
			sortable : true,
			width : 150
		}, {
			title : "借款金额",
			field : "a_money",
			align : 'center',
			editor : {
				type : 'textbox',
				options : {
					required : true,
					missingMessage : '不能为空！'
				}
			},
			sortable : true,
			width : 150
		}, {
			title : "借款天数",
			field : "a_day",
			align : 'center',
			editor : {
				type : 'textbox',
				options : {
					required : true,
					missingMessage : '不能为空！'
				}
			},
			sortable : true,
			width : 150
		}, {
			title : "信审费",
			field : "a_camoney",
			align : 'center',
			editor : {
				type : 'textbox',
				options : {
					required : true,
					missingMessage : '不能为空！'
				}
			},
			sortable : true,
			width : 150
		}, {
			title : "管理费",
			field : "a_managemoney",
			align : 'center',
			editor : {
				type : 'textbox',
				options : {
					required : true,
					missingMessage : '不能为空！'
				}
			},
			sortable : true,
			width : 150
		}, {
			title : "利息",
			field : "a_interest",
			align : 'center',
			editor : {
				type : 'textbox',
				options : {
					required : true,
					missingMessage : '不能为空！'
				}
			},
			sortable : true,
			width : 150
		}, {
			title : "申请时间",
			field : "a_applytime",
			align : 'center',
			editor : {
				type : "datebox",
				options : {
					required : true,
					missingMessage : '不能为空！'
				}
			},
			sortable : true,
			width : 150
		}, {
			title : "生效时间",
			field : "a_takeTime",
			align : 'center',
			editor : {
				type : "datebox",
				options : {
					required : true,
					missingMessage : '不能为空！'
				}
			},
			sortable : true,
			width : 150
		}, {
			title : "还款时间",
			field : "a_repayTime",
			align : 'center',
			editor : {
				type : "datebox",
				options : {
					required : true,
					missingMessage : '不能为空！'
				}
			},
			sortable : true,
			width : 150
		},] ]
	})

})

// 增加
function addWin() {
	if (flag2 == 2) {
		$.messager.show({
			title : '温馨提示',
			msg : '请完成当前操作',
		});
	} else if (flag2 = 1) {
		flags = 2;
		$('#cancelBtn').linkbutton('enable');
		$('#saveBtn').linkbutton('enable');
		$('#list').datagrid('appendRow', {
			a_id : "",
			a_state : "",
			r_id : "",
			a_money : "",
			a_day : "",
			a_camoney : "",
			a_managemoney : "",
			a_interest : "",
			a_applytime : "",
			a_takeTime : "",
			a_repayTime : "",
		});
		indexRow = $("#list").datagrid("getRowIndex", 0);
		$("#list").datagrid("beginEdit", indexRow);
	}
}

// 删除
function del() {
	if (flag2 == 2) {
		$.messager.show({
			title : '温馨提示',
			msg : '请完成当前操作',
		});
	} else {
		sid = $("#list").datagrid("getSelected");
		if (sid == null) {
			$.messager.show({
				title : '温馨提示',
				msg : '请选择需要删除的行',
			})
		} else if (id == sid) {
			$.messager.show({
				title : '温馨提示',
				msg : '请选择需要删除的行',
			})
		} else {
			id = sid;
			parent.$.messager.confirm('温馨提示', '你确定删除此行记录?', function(r) {
				if (r) {
					$.ajax({
						url : 'delApprove.action',
						data : 'a_id=' + sid.a_id,
						method : "post",
						dataType : 'JSON',
						success : function(data) {
							if (data.success == 'success') {
								$("#list").datagrid("reload");
								flag2 = 1;
								$.messager.show({
									title : '温馨提示',
									msg : '删除成功',
								})
							}
						}
					});
				}
			});
		}
	}
}

// 保存到数据库
function finishEdit(index, row, changes) {
	if (row.a_id == '') {
		$.ajax({
			url : 'addApprove.action',
			data : row,
			method : "post",
			dataType : 'JSON',
			success : function(data) {
				if (data.success == 'success') {
					$("#list").datagrid("reload");
					$('#saveBtn').linkbutton('disable');
					$('#cancelBtn').linkbutton('disable');
					$.messager.show({
						title : '温馨提示',
						msg : '添加成功',
					})
					flag2 = 1;
				}
			}
		});
	} else {
		$.ajax({
			url : 'updateApprove.action',
			data : row,
			method : "post",
			dataType : 'JSON',
			success : function(data) {
				if (data.success == 'success') {
					$("#list").datagrid("reload");
					$('#saveBtn').linkbutton('disable');
					$('#cancelBtn').linkbutton('disable');
					flag2 = 1;
					flag = 1;
					$.messager.show({
						title : '温馨提示',
						msg : '修改成功',
					})
				}
			}
		});
	}
}
