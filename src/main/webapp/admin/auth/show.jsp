<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="padding: 5px;">
	<form method="post" class="form">
		<fieldset>
			<legend>权限基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<input type="hidden" name="uuid" class="easyui-validatebox" data-options="required:true" style="width: 150px;"  value="${editObject.uuid}"/>
					<th>排序号</th>
					<td>${editObject.sort}</td>
					<th>权限名称</th>
					<td>${editObject.name}</td>
				</tr>
				<tr>
					<th>权限类型</th>
					<td>
					${editObject.type}
					</td>
					<th>上级权限</th>
					<td>
					<input id="pid"  name="pid"  value="${editObject.pid}"  class="easyui-combobox"    data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/authAction!getAllAuth.action'" /> 
					</td>
				</tr>
				<tr>
					<th>权限图标</th>
					<td>${editObject.iconcls}</td>
					<th>权限路径</th>
					<td>${editObject.url}</td>
				</tr>
				<tr>
					<th>权限描述</th>
					<td colspan="3">${editObject.description}</td>
				</tr>
			</table>
		</fieldset>
	</form>
</div>