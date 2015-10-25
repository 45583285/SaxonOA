<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
	<form method="post" class="form">
		<fieldset>
			<legend>部门基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>排序号</th>
					<td>${editObject.sort}</td>
					<th>部门名称</th>
					<td>${editObject.name}</td>
				</tr>
				<tr>
					<th>上级部门</th>
					<td colspan="3">
					<input id="pid"  name="pid"  class="easyui-combotree"  value="${editObject.pid}"   data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/deptAction!treeGrid.action'" /> 
					</td>
				</tr>
					<th>部门描述</th>
					<td colspan="3">${editObject.description}</td>
				</tr>
			</table>
		</fieldset>
	</form>
</div>