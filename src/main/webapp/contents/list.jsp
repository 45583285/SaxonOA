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
		url:"${pageContext.request.contextPath}/contentsAction!dataGrid.action",  
		singleSelect:false, 
		nowrap:true,
		fitColumns:true,
		rownumbers:true,
		showPageList:false,
		pagination : true,
		sortName : 'sort',
		sortOrder : 'asc',
		columns:[[
			{field:'uuid',title:'uuid',width:100,halign:"center", align:"left",checkbox:'true'},
			{field:'title',title:'名称',width:200,halign:"center", align:"left",sortable : 'true'},
			{field:'origin',title:'来源',width:100,halign:"center", align:"center",sortable : 'true'},
			{field:'type',title:'所属栏目',width:100,halign:"center", align:"center",sortable : 'true'},
			{field:'author',title:'作者',width:100,halign:"center", align:"center"},
			{field:'abstract_',title:'内容简介',width:100,halign:"center", align:"center"},
			{field:'opt',title:'操作',halign:"center", align:"center",width:250,
				 formatter:function(value,rec){  
               var btn ="<a id='delcls' class='delcls' onclick=\"javascript:del('"+rec.uuid+"')\"></a>";
              		  btn =btn + "<a class='editcls'  onclick=\"javascript:edit('"+rec.uuid+"')\"></a>";
              		  btn =btn + "<a class='showcls'  onclick=\"javascript:show('"+rec.uuid+"')\" ></a>";
              		  btn =btn + "<a class='checkRole'  onclick=\"javascript:checkRole('"+rec.uuid+"')\" ></a>";
               	 return btn;  
	          }  
				
			
			}
		]],
		onLoadSuccess:function(data){  
        	$('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
        	$('.delcls').linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});  
        	$('.showcls').linkbutton({text:'查看',plain:true,iconCls:'icon-save'}); 
        	$('.checkRole').linkbutton({text:'角色',plain:true,iconCls:'icon-save'}); 
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
			title : '添加内容',
			href : '${pageContext.request.contextPath}/contentsAction!toAdd.action',
			width : 640,
			height : 400,
			buttons : [ {
				text : '添加',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/contentsAction!add.action',
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
						url : '${pageContext.request.contextPath}/contentsAction!remove.action',
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
		})
		
	
	
	});
	
	
	function del(uuid){
				$.ajax({
						url : '${pageContext.request.contextPath}/contentsAction!remove.action',
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
			title : '修改人员信息',
			href : '${pageContext.request.contextPath}/contentsAction!toEdit.action?uuid='+uuid,
			width : 540,
			height : 300,
			buttons : [ {
				text : '保存',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/contentsAction!update.action',
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
			title : '查看人员信息',
			href : '${pageContext.request.contextPath}/contentsAction!toShow.action?uuid='+uuid,
			width : 540,
			height : 300
		});		

}
		function checkRole(uuid){
			var p = parent.sl.dialog({
			title : '角色选择',
			href : '${pageContext.request.contextPath}/contentsAction!checkRole.action?uuid='+uuid,
			width : 540,
			height : 300,
			buttons : [ {
				text : '保存',
				handler : function() {
						var node = p.find('#roleTree').tree('getChecked',['checked','indeterminate']);
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
						url : '${pageContext.request.contextPath}/contentsAction!userRole.action',
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
		$('#dept').tree({   
			//checkbox:true,
   			url:'${pageContext.request.contextPath}/menusAction!treeGrid.action',
   			onClick: function(node){
						$("#uuid").val(node.id);
						datagrid.datagrid('load', sl.serializeObject($('#searchForm')));
						datagrid.datagrid('unselectAll');
					}
		}); 
		
		$("#search").on("click", function() {
				//alert(sl.serializeObject($('#searchForm')))
				datagrid.datagrid('load', sl.serializeObject($('#searchForm')));
		});
		
		$("#cleanSearch").on("click", function() {
					datagrid.datagrid('load', {});
					$('#searchForm input').val('');
		});
		
})	
	
</script>
</head>
<body class="easyui-layout" >
<div data-options="region:'west',split:true, fit:false" style="width:150px;"> 
		<fieldset>
			<legend>栏目</legend>			
			<ul id="dept"></ul> 
		</fieldset>
</div>
<div id="body" region="center" > 
  <!-- 查询条件区域 -->
  <div id="search_area" >
    <div id="conditon">
    <form id="searchForm">
      <table border="0">
        <tr>
		<input type="hidden"  name="uuid" id="uuid"   />
          <td>&nbsp;名称：</td>
          <td><input  name="title" id="title"   />
           </td>
          <td>
              <a  href="javascript:void(0)" id="search" class="easyui-linkbutton my-search-button" iconCls="icon-search" plain="true">查询</a> 
              <a  href="javascript:void(0)"  id="cleanSearch" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
          </td>
        </tr>
      </table>
      	</form>
    </div>
    <span id="openOrClose">111</span> 
  </div>
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
