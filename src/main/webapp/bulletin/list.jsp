<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>通知公告列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/inc.jsp" %>

        <script  type="text/javascript" charset="utf-8">
        var datagrid;
$(function(){
	datagrid = $("#tt").datagrid({
			height:$("#body").height()-$('#search_area').height()-5,
			width:$("#body").width(),
			url:"${pageContext.request.contextPath}/bulletinAction!dataGrid.action",  
			idField : 'uuid',
		singleSelect:false, 
		nowrap:true,
		fitColumns:true,
		rownumbers:true,
		showPageList:false,
		pagination : true,
		sortName : 'sort',
		sortOrder : 'asc',
		selectOnCheck:false,
		checkOnSelect:false,
		columns:[[
			{field:'uuid',title:'uuid',width:100,halign:"center", align:"left",checkbox:'true'},
			{field:'sort',title:'排序号',width:100,halign:"center", align:"center",sortable : 'true'},
			{field : 'createUser', title : '登记人',	width : 160},
			{field:'createUserDept',title:'部门',width:100,halign:"center", align:"center"},
			{field:'title',title:'标题',width:300,halign:"center", align:"center"},
			{field:'createTime',title:'登记时间',width:100,halign:"center", align:"center"},
			{field:'opt',title:'操作',halign:"center", align:"center",width:170,
				 formatter:function(value,rec){  
               var btn ="<a class='editcls'  onclick=\"javascript:edit('"+rec.uuid+"')\"></a>";
              		  btn =btn + "<a class='showcls'  onclick=\"javascript:show('"+rec.uuid+"')\" ></a>";
               	 return btn;  
	       			   }  
	          }
		]],
		onLoadSuccess:function(data){  
        	$('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
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
			title : '添加通知公共',
			href : '${pageContext.request.contextPath}/bulletinAction!toAdd.action',
			width : 640,
			height : 580,
			buttons : [ {
				text : '添加',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/bulletinAction!add.action',
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
	
	
	
	$("#excel").on("click", function(){
            //获取Datagride的列
            var rows = $('#tt').datagrid('getRows');
            var columns = $("#tt").datagrid("options").columns[0];
            var oXL = new ActiveXObject("Excel.Application"); //创建AX对象excel 
            var oWB = oXL.Workbooks.Add(); //获取workbook对象 
            var oSheet = oWB.ActiveSheet; //激活当前sheet
            //设置工作薄名称
            oSheet.name = "导出Excel报表";
            //设置表头
            for (var i = 0; i < columns.length; i++) {
                oSheet.Cells(1, i+1).value = columns[i].title;
            }
            //设置内容部分
            for (var i = 0; i < rows.length; i++) {
                //动态获取每一行每一列的数据值
                for (var j = 0; j < columns.length; j++) {               
                    oSheet.Cells(i + 2, j+1).value = rows[i][columns[j].field];
                }   
            }              
            oXL.Visible = true; //设置excel可见属性	

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
						url : '${pageContext.request.contextPath}/bulletinAction!remove.action',
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
	
	



function edit(uuid){
				var p = parent.sl.dialog({
			title : '修改通知公告',
			href : '${pageContext.request.contextPath}/bulletinAction!toEdit.action?uuid='+uuid,
			width : 640,
			height : 580,
			buttons : [ {
				text : '保存',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/bulletinAction!update.action',
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
      <a href="javascript:void(0)"  id="excel" class="easyui-linkbutton" iconCls="icon-excel" plain="true">导出</a>
  </div>
</div>
</body>
</html>
