<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>权限列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/inc.jsp" %>
        <script  type="text/javascript" charset="utf-8">
        var datagrid;
$(function(){
	datagrid = $("#tt").datagrid({
			height:$("#body").height()-$('#search_area').height()-5,
			width:$("#body").width(),
			url:"${pageContext.request.contextPath}/dbsyAction!dataGrid.action",  
			idField : 'uuid',
			sortName : 'sort',
			sortOrder : 'desc',
			treeField: 'name',
		    rownumbers : true,
			pagination : false,
			frozenColumns : [ [ 
			{field : 'id', title : '任务ID',	width : 100, align:"center"}  
			] ],
		columns:[[
			{field:'name',title:'任务名称',width:100,halign:"center", align:"center"},
			{field:'createTime',title:'任务日期',width:100,halign:"center", align:"center"},
			{field:'assignee',title:'处理人系统名',width:100,halign:"center", align:"center"},
			{field:'dealingPeople',title:'处理人',width:100,halign:"center", align:"center"},
			{field:'title',title:'标题',width:300,halign:"center", align:"left"},
			{field:'opt',title:'操作',halign:"center", align:"center",
				 formatter:function(value,rec){  
               var btn ="<a id='delcls' class='delcls' onclick=\"javascript:del('"+rec.uuid+"')\"></a>";
              		  btn =btn + "<a class='editcls'  onclick=\"javascript:showSbmint('"+rec.id+"')\"></a>";
              		  btn =btn + "<a class='showcls'  onclick=\"javascript:show('"+rec.uuid+"')\" ></a>";
               	 return btn;  
	       			   }  
	          }
		]],
		onLoadSuccess:function(data){  
        	$('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
        	$('.delcls').linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});  
        	$('.showcls').linkbutton({text:'查看',plain:true,iconCls:'icon-save'});  
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
			title : '添加权限',
			href : '${pageContext.request.contextPath}/authAction!toAdd.action',
			width : 540,
			height : 300,
			buttons : [ {
				text : '添加',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/authAction!add.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								treegrid.treegrid('reload');
								p.dialog('close');
							}
							parent.sl.messagerAlert('提示',json.msg,'info');
						}
					});
				}
			} ]
		});		
		
	});
});
	
	
	
function del(uuid){
				$.ajax({
						url : '${pageContext.request.contextPath}/authAction!remove.action',
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
			title : '修改权限',
			href : '${pageContext.request.contextPath}/authAction!toEdit.action?uuid='+uuid,
			width : 540,
			height : 300,
			buttons : [ {
				text : '保存',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/authAction!update.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								treegrid.treegrid('reload');
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
			title : '查看权限',
			href : '${pageContext.request.contextPath}/authAction!toShow.action?uuid='+uuid,
			width : 540,
			height : 300
		});	
}
		
function showSbmint(uuid){
			var p = parent.sl.dialog({
			title : '修改部门',
			href : '${pageContext.request.contextPath}/fileinAction!getByTaskId.action?uuid='+uuid,
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
								treegrid.datagrid('reload');
								p.dialog('close');

							}
							parent.sl.messagerAlert('提示',json.msg,'info');
						}
					});
				}
			} ]
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
  </div>
</div>
</body>
</html>
