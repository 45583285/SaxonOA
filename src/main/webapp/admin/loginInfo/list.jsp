<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>用户列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/inc.jsp" %>
        
        <script>
var datagrid;
$(function(){
	   datagrid = $("#tt").datagrid({
		height:$("#body").height()-$('#search_area').height()-5,
		width:$("#body").width(),
		idField:'uuid',
		url:"${pageContext.request.contextPath}/loginInfoAction!dataGrid.action",  
		singleSelect:false, 
		nowrap:true,
		fitColumns:true,
		rownumbers:true,
		showPageList:false,
		pagination : true,
		sortName : 'sort',
		sortOrder : 'asc',
		columns:[[
			{field:'uuid',title:'uuid',width:50,halign:"center", align:"left",checkbox:'true'},
			{field:'userName',title:'IP',width:100,halign:"center", align:"center",sortable : 'true'},
			{field:'info',title:'操作内容',width:100,halign:"center", align:"center",sortable : 'true'},
			{field:'createTime',title:'操作时间',width:150,halign:"center", align:"center",sortable : 'true'},
			{field:'createUser',title : '操作人员',width : 100,halign:"center", align:"center"}
		]],
		onLoadSuccess:function(data){  
        	$('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
        	$('.delcls').linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});  
        	$('.showcls').linkbutton({text:'查看',plain:true,iconCls:'icon-save'}); 
        	$('.checkcls').linkbutton({text:'赋权',plain:true,iconCls:'icon-save'});  
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
			title : '添加角色',
			href : '${pageContext.request.contextPath}/roleAction!toAdd.action',
			width : 540,
			height : 300,
			buttons : [ {
				text : '添加',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/roleAction!add.action',
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
	});
	
	
	function del(uuid){
				$.ajax({
						url : '${pageContext.request.contextPath}/roleAction!remove.action',
						data : {
							ids : uuid
						},
						dataType : 'json',
						success : function(d) {
							parent.sl.messagerAlert('提示',d.msg,'info');
							datagrid.datagrid('reload');
							
							}
							})
}



function edit(uuid){
			var p = parent.sl.dialog({
			title : '修改角色信息',
			href : '${pageContext.request.contextPath}/roleAction!toEdit.action?uuid='+uuid,
			width : 540,
			height : 300,
			buttons : [ {
				text : '保存',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/roleAction!update.action',
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
			title : '查看角色信息',
			href : '${pageContext.request.contextPath}/roleAction!toShow.action?uuid='+uuid,
			width : 540,
			height : 300
		});		

}

		function checkAuth(uuid){
			var p = parent.sl.dialog({
			title : '角色赋权',
			href : '${pageContext.request.contextPath}/roleAction!checkAuth.action?uuid='+uuid,
			width : 540,
			height : 300,
			buttons : [ {
				text : '保存',
				handler : function() {
						var node = p.find('#authTree').tree('getChecked',['checked','indeterminate']);
						var ids = "";
						
							for(i=0;i<node.length;i++){
									if(ids==""){
										ids=node[i].id;
									}else{
										ids+=","+node[i].id;
									}
							}
							p.find('#ids').val(ids);
				
 					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/roleAction!roleAuth.action',
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
	
	$(function(){

		$("#search").on("click", function() {
				datagrid.datagrid('load', sl.serializeObject($('#searchForm')));
		})
		
		$("#cleanSearch").on("click", function() {
					datagrid.datagrid('load', {});
					$('#searchForm input').val('');
		})
		
})	
</script>
</head>
<body class="easyui-layout" >
<div id="body" region="center" > 
  <!-- 数据表格区域 -->
  <table id="tt" style="table-layout:fixed;" ></table>
      <!-- 表格顶部工具按钮 -->
  <div id="tt_btn">
     
  </div>
</div>
</body>
</html>
