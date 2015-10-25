<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="userForm" method="post">
				<table>
			<tr>
				<input type="hidden" name="uuid" class="easyui-validatebox" data-options="required:true" style="width: 150px;"  value="${editObject.uuid}"/>
				<th>角色名称</th>
				<td><input name="name"   value="${editObject.name}" class="easyui-validatebox" data-options="required:true" />
				</td>
				<th>排序号</th>
				<td><input name="sort"  value="${editObject.sort}" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
		</table>
	</form>
</div>