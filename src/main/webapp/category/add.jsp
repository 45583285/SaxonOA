<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="padding: 5px;">
	<form method="post" class="form">
		<fieldset>
			<legend>基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>排序号</th>
					<td><input name="sort" class="easyui-validatebox" data-options="required:true" style="width: 150px;"/></td>
					<th>类别名称</th>
					<td><input name="name"  class="easyui-validatebox" data-options="required:true"  /></td>
				</tr>
				<tr>
					
					<th>上级类别</th>
					<td>
					<input id="pid"  name="pid"  class="easyui-combotree"    data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/categoryAction!treeGrid.action'" /> 
					</td>
					<th> </th>
					<td> </td>
				</tr>
				<tr>
					<th>创建人</th>
					<td><input name="createUser"  class="easyui-validatebox" />
					</td>
					<th>创建时间</th>
					<td><input name="createTime"  class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<th>更新人</th>
					<td><input name="updateUser"  class="easyui-validatebox"  /></td>
					<th>更新时间</th>
					<td><input name="updateTime"  class="easyui-validatebox"  /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</div>