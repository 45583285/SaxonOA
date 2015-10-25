<%@ page language="java"  import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="padding: 5px;">
	<form method="post" class="form">
		<fieldset>
			<legend>部门基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
				<input type="hidden" name="uuid" class="easyui-validatebox" data-options="required:true" style="width: 150px;"  value="${editObject.uuid}"/>
					<th>排序号</th>
					<td><input name="sort" class="easyui-validatebox" data-options="required:true" style="width: 150px;"  value="${editObject.sort}"/></td>
					<th>部门名称</th>
					<td><input name="name"  class="easyui-validatebox" data-options="required:true"  value="${editObject.name}"/></td>
				</tr>
				<tr>
					<th>上级部门</th>
					<td colspan="3">
					<input id="pid"  name="pid"  class="easyui-combotree"    value="${editObject.pid}" data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/deptAction!treeGrid.action'" /> 
					</td>
				</tr>
					<th>部门描述</th>
					<td colspan="3"><textarea style="width:100%;height: 100px;" name="description"  class="easyui-validatebox"  >${editObject.description}</textarea></td>
				</tr>
			</table>
		</fieldset>
		
		<input name="createUser"  class="easyui-validatebox"  value="${editObject.createUser}"/>
		<input name="createTime"  class="easyui-validatebox"  value="${editObject.createTime}"/>
		<input name="updateUser"  class="easyui-validatebox"  value="${sessionInfo.loginNames}"/>
		<input name="updateTime"  class="easyui-validatebox"  value="${editObject.updateTime}"/>

		
	</form>
</div>