<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>收文列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/inc.jsp" %>

        <script  type="text/javascript" charset="utf-8">
        var datagrid;
$(function(){
	datagrid = $("#tt").datagrid({
			height:$("#body").height()-$('#search_area').height()-5,
			width:$("#body").width(),
			url:"${pageContext.request.contextPath}/fileinAction!dataGrid.action",  
			idField : 'uuid',
		singleSelect:false, 
		nowrap:true,
		fitColumns:true,
		rownumbers:true,
		showPageList:false,
		pagination : true,
		sortName : 'sort',
		sortOrder : 'asc',
		//remoteSort: false,
		selectOnCheck:false,
		checkOnSelect:false,
		columns:[[
			{field:'uuid',title:'uuid',width:100,halign:"center", align:"left",checkbox:'true'},
			{field:'sort',title:'排序号',width:50,halign:"center", align:"center",sortable : 'true'},
			{field:'title', title : '标题',	width : 160},
			{field:'processid',title:'流程ID',width:100,halign:"center", align:"center"},
			{field:'createTime',title:'登记时间',width:150,halign:"center", align:"center"},
			{field:'createUser',title:'登记人',width:50,halign:"center", align:"center"},
			{field:'updateTime',title:'更新时间',width:150,halign:"center", align:"center"},
			{field:'updateUser',title:'更新人',width:50,halign:"center", align:"center"},
			{field:'opt',title:'操作',halign:"center", align:"center",width:170,
				 formatter:function(value,rec){  
               var btn ="<a id='delcls' class='delcls' onclick=\"javascript:del('"+rec.uuid+"')\"></a>";
              		  btn =btn + "<a class='editcls'  onclick=\"javascript:edit('"+rec.uuid+"')\"></a>";
              		  btn =btn + "<a class='showcls'  onclick=\"javascript:show('"+rec.uuid+"')\" ></a>";
               	 return btn;  
	       			   }  
	          }
		]],
		onLoadSuccess:function(data){  
        	$('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
        	$('.delcls').linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});  
        	$('.showcls').linkbutton({text:'查看',plain:true,iconCls:'icon-search'});  
        	parent.$.messager.progress('close');
		 },
		 onBeforeLoad : function(row, param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
		 
			toolbar:'#tt_btn'
	});

	//新增弹出框
	$("#save").on("click", function(){
			var p = parent.sl.dialog({
			title : '添加发文',
			href : '${pageContext.request.contextPath}/fileinAction!toAdd.action',
			width : 540,
			height : 300,
			buttons : [ {
				text : '保存',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/fileinAction!add.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								datagrid.datagrid('reload');
								p.dialog('close');

							}
							parent.sl.messagerAlert('提示',json.msg,'info');
						}
					});
				}
			} ,
			{
				text : '提交',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/fileinAction!submit.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								datagrid.datagrid('reload');
								p.dialog('close');

							}
							parent.sl.messagerAlert('提示',json.msg,'info');
						}
					});
				}
			}  ,
			{
				text : '查看流程',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/!add.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								datagrid.datagrid('reload');
								p.dialog('close');

							}
							parent.sl.messagerAlert('提示',json.msg,'info');
						}
					});
				}
			} ]
		});		
		
	});
	
	
	//删除功能
	$("#delete").on("click", function(){
		var rows = datagrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].uuid);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/fileinAction!remove.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							datagrid.datagrid('load');
							datagrid.datagrid('unselectAll');
							parent.sl.messagerAlert('提示',r.msg,'info');
						}
					});
				}
			});
		} else {
			parent.sl.messagerAlert('提示', '请勾选要删除的记录！','info');
		}
		});
		

});





	
	
function del(uuid){
				$.ajax({
						url : '${pageContext.request.contextPath}/deptAction!remove.action',
						data : {
							ids : uuid
						},
						dataType : 'json',
						success : function(d) {
							parent.sl.messagerAlert('提示',d.msg,'info');
							treegrid.treegrid('reload');
							
							}
							})
}



function edit(uuid){
				var p = parent.sl.dialog({
			title : '修改部门',
			href : '${pageContext.request.contextPath}/fileinAction!toEdit.action?uuid='+uuid,
			width : 540,
			height : 300,
			buttons : [ {
				text : '保存',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/fileinAction!add.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								datagrid.datagrid('reload');
								p.dialog('close');

							}
							parent.sl.messagerAlert('提示',json.msg,'info');
						}
					});
				}
			} ,
			{
				text : '提交',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/fileinAction!submit.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								datagrid.datagrid('reload');
								p.dialog('close');

							}
							parent.sl.messagerAlert('提示',json.msg,'info');
						}
					});
				}
			}  ,
			{
				text : '查看流程',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/!add.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								datagrid.datagrid('reload');
								p.dialog('close');

							}
							parent.sl.messagerAlert('提示',json.msg,'info');
						}
					});
				}
			} ]
		});		

}


function show(uuid){
		var p = parent.sl.dialog({
			title : '通知公共',
			href : '${pageContext.request.contextPath}/bulletinAction!toShow.action?uuid='+uuid,
			width : 540,
			height : 300
		});		

}
</script>
</head>
<body class="easyui-layout" >
<div id="body" region="center" > 
  <!-- 数据表格区域 -->
  <table id="tt" style="table-layout:fixed;" ></table>
      <!-- 表格顶部工具按钮 -->
  <div id="tt_btn">
      <a href="javascript:void(0)"  id="save" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
      <a href="javascript:void(0)"  id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
  </div>
</div>
</body>
</html>
