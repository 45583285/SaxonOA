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
			url:"${pageContext.request.contextPath}/processAction!dataGrid.action",  
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
			{field :'processName', title : '流程名称',	width : 160},
			{field:'processId',title:'流程ID',width:100,halign:"center", align:"center"},
			{field:'createTime',title:'登记时间',width:170,halign:"center", align:"center"},
			{field:'opt',title:'操作',halign:"center", align:"center",width:300,
				 formatter:function(value,rec){  
               var btn ="<a id='delcls' class='delcls' onclick=\"javascript:del('"+rec.uuid+"')\"></a>";
              		  btn =btn + "<a class='editcls'  onclick=\"javascript:edit('"+rec.uuid+"')\"></a>";
              		  btn =btn + "<a class='showcls'  onclick=\"javascript:show('"+rec.uuid+"')\" ></a>";
              		  btn =btn + "<a class='showcls1'  onclick=\"javascript:showflow('"+rec.uuid+"')\" ></a>";
              		  btn =btn + "<a class='wordcls'  onclick=\"javascript:editflow('"+rec.uuid+"')\" ></a>";
               	 return btn;  
	       			   }  
	          }
		]],
		onLoadSuccess:function(data){  
        	$('.editcls').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});  
        	$('.delcls').linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});  
        	$('.showcls').linkbutton({text:'查看',plain:true,iconCls:'icon-search'});  
        	$('.showcls1').linkbutton({text:'查看流程',plain:true,iconCls:'icon-search'});
        	$('.wordcls').linkbutton({text:'编辑流程',plain:true,iconCls:'icon-search'});  
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
			title : '添加流程',
			href : '${pageContext.request.contextPath}/processAction!toAdd.action',
			width : 540,
			height : 300,
			buttons : [ {
				text : '添加',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/processAction!add.action',
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
						url : '${pageContext.request.contextPath}/processAction!remove.action',
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
			title : '修改部门',
			href : '${pageContext.request.contextPath}/processAction!toEdit.action?uuid='+uuid,
			width : 540,
			height : 300,
			buttons : [ {
				text : '保存',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/processAction!update.action',
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
			title : '通知公共',
			href : '${pageContext.request.contextPath}/processAction!toShow.action?uuid='+uuid,
			width : 540,
			height : 300
		});		

}


function editflow(uuid){
    		var p = parent.sl.dialog({
			title : '流程配置',
			//href : '${pageContext.request.contextPath}/admin/process/demo4.jsp?uuid='+uuid,
			width : 740,
			height : 500,
            //注：使用iframe即可防止同一个页面出现js和css冲突的问题  
            content : '<iframe width="100%" id="flowIframe" src="${pageContext.request.contextPath}/processAction!editFlow.action?uuid='+uuid+'" height="100%" frameborder="0" scrolling="auto" ></iframe>', 
			buttons : [ {
				text : '保存',
				handler : function() {
				p.find("iframe").contents().find("#uuid").val(uuid);
				//p.find("iframe").contents().find("#processId").val();
				
				if(p.find("iframe").contents().find("#processInfo").val()==""){
						parent.sl.messagerAlert('提示',"请点击生成按钮",'info');
						return false;		
				}
				
				
				 var f = p.find("iframe").contents().find("form");
					f.form('submit', {
						url : '${pageContext.request.contextPath}/processAction!flowInfo.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								p.dialog('close');

							}
							parent.sl.messagerAlert('提示',json.msg,'info');
						}
					});
				} 
			} ]
		});	   

}



function showflow(uuid){
    		var p = parent.sl.dialog({
			title : '流程查看',
			width : 740,
			height : 500,
            //注：使用iframe即可防止同一个页面出现js和css冲突的问题  
            content : '<iframe width="100%" id="flowIframe" src="${pageContext.request.contextPath}/processAction!showFlow.action?uuid='+uuid+'" height="100%" frameborder="0" scrolling="auto" ></iframe>' 
			
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
