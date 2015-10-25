<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>部门列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/inc.jsp" %>
        <script>
$(function(){
	$("#tt").datagrid({
		height:$("#body").height()-$('#search_area').height()-5,
		width:$("#body").width(),
		pagination : true,
		idField : 'uuid',
		sortName : 'sort',
		sortOrder : 'asc',
		url:"${pageContext.request.contextPath}/deptAction!datagrid.action",  
		singleSelect:false, 
		nowrap:true,
		fitColumns:true,
		rownumbers:true,
		showPageList:false,
		checkOnSelect : false,
		selectOnCheck : false,
		columns:[[
			{field:'uuid',title:'uuid',width:100,halign:"center", align:"left",checkbox:'true'},
			{field:'sort',title:'排序号',width:50,halign:"center", align:"center"},
			{field:'deptName',title:'部门名称',width:50,halign:"center", align:"center"},
			{field:'deptId',title:'部门编号',width:100,halign:"center", align:"center"},
			{field:'paren_deptId',title:'上级部门',width:100,halign:"center", align:"center"},
			{field:'createUser',title:'创建人',width:100,halign:"center", align:"center"},
			{field:'createTime',title:'创建时间',width:100,halign:"center", align:"center"},
			{field:'updataUser',title:'修改人',width:100,halign:"center", align:"center"},
			{field:'updataTime',title:'更新时间',width:100,halign:"center", align:"center"},
			{field:'opt',title:'操作',halign:"center", align:"center",
			formatter:function(value,rec){  
               		var btn = "<a class='editcls' onclick='update()'  href='javascript:void(0)'>编辑</a>";  
               		return btn;  
	          }  
				
			
			}
		]],
		onLoadSuccess:function(data){  
        	$('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
		 },
		toolbar:'#tt_btn',  
        pagination:true,
		onDblClickRow:function(rowIndex, rowData){
			viewDetail(rowData.userId);
		}
	});
	
	
	
	//新增弹出框
	$("#save").on("click", function(){
		addUI(274,325,'departmentAction_addUI.action','新增部门')
		
	});
		//修改弹出框
	$("#update").on("click", function(){
		editUI(274,325,'departmentAction_editUI.action','修改部门')
	});

		//删除
		$("#delete").on("click", function() {
		//	del('departmentAction_delete.action')
			var rows = $('#tt').datagrid('getChecked');
		//var rows = $('#dept_datagrid').datagrid('getSelected');
		//var rows = $('#dept_datagrid').datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].uuid);
					}
					
					$.ajax({
						url : '${pageContext.request.contextPath}/deptAction!remove.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							$('#tt').datagrid('load');
							$('#tt').datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : r.msg
							});
						}
					});
				}
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
		});
		
		
		//查询
		$("#search").on("click", function() {
			//QueryData()
			var p1 = $("#deptName").val();  
			var parm = {"deptName":p1}; 
			var url="json/departmentAction_search_json.action";
			$.post(url, parm, function(data){
				$("#tt").datagrid('loadData', data)
			});
		});
			

	})
        function update(){
        	editUI(274,325,'departmentAction_editUI.action','修改部门')
        }
</script>
</head>
<body class="easyui-layout" >
<div id="body" region="center" > 
  <!-- 查询条件区域 -->
  <div id="search_area" >
    <div id="conditon">
      <table border="0">
        <tr>
          <td>部门名称：</td>
          <td ><input  name="deptName" id="deptName"   /></td>
          <td>
              <a  href="javascript:void(0)" id="search" class="easyui-linkbutton my-search-button" iconCls="icon-search" plain="true">查询</a> 
          </td>
        </tr>
      </table>
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

<!-- 用于弹出框 -->
<div id="parent_win"></div>
</body>
</html>
