<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
	<form method="post" class="form">
		<fieldset>
			<legend>部门基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>排序号</th>
					<td><input name="sort" class="easyui-validatebox" data-options="required:true" style="width: 150px;"/></td>
					<th>部门名称</th>
					<td><input name="name"  class="easyui-validatebox" data-options="required:true"  /></td>
				</tr>
				<tr>
					<th>上级部门</th>
					<td>
					<input id="pid"  name="pid"  class="easyui-combotree"    data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/deptAction!treeGrid.action'" /> 
					</td>
					<th>创建人员</th>
					<td><input name="createUser"  class="easyui-validatebox" readonly="readonly" value="${sessionInfo.loginNames}" /></td>
				</tr>
					<th>部门描述</th>
					<td colspan="3"><input name="description"  class="easyui-validatebox"  /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</div>