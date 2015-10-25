<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
	<form method="post">
		<table class="tableForm">
						<input type="hidden" name="uuid" class="easyui-validatebox" data-options="required:true" style="width: 150px;"  value="${editObject.uuid}"/>
				
					<tr>
				<th style="width:80px;">排序号</th>
				<td><input name="sort" class="easyui-validatebox" value="${editObject.sort}"  style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;">标题</th>
				<td><input name="title" class="easyui-validatebox" value="${editObject.title}" style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;">流程ID</th>
				<td><input id="processid" name="processid" class="easyui-validatebox" value="${editObject.processid}" style="width: 97%;" />
				</td>
			</tr>
		</table>
		<div  style="display:none;">
		<input name="next_task_user_sysName" class="easyui-validatebox"  value="${editObject.next_task_user_sysName}"  style="width: 20%;" /> 下一环节处理人
		<input name="createTime" class="easyui-validatebox"  value="${editObject.createTime}"  style="width: 20%;" /> 创建时间
		<input name="createUser" class="easyui-validatebox"  value="${editObject.createUser}"  style="width: 20%;" /> 创建人
		<input name="updateTime" class="easyui-validatebox"  value="${editObject.updateTime}"  style="width: 20%;" /> 更新时间
		<input name="updateUser" class="easyui-validatebox"  value="${editObject.updateUser}"   style="width: 20%;" /> 更新人
		</div>
	</form>
		
	    <script  type="text/javascript" charset="utf-8">
        var datagrid;
        var uuid = $("#processid").val();
$(function(){

	datagrid = $("#tt").datagrid({
			height:200,
			width:500,
			url:"${pageContext.request.contextPath}/fileinAction!queryHistoricActivityInstance.action?uuid="+uuid,  
		singleSelect:false, 
		nowrap:true,
		fitColumns:true,
		rownumbers:true,
		sortName : 'sort',
		sortOrder : 'asc',
		columns:[[
			{field:'activityName',title:'环节名称',width:80,halign:"center", align:"left"},
			{field:'assignee',title:'处理人',width:80,halign:"center", align:"center"},
			{field:'startTime', title : '开始时间',halign:"center", align:"center",width : 100},
			{field:'endTime',title:'结束时间',width:100,halign:"center", align:"center"}
		]]
	});
	});
	</script>
	 <!-- 数据表格区域 -->
  <table id="tt" style="table-layout:fixed;height:200px" ></table>	
		
		
</div>