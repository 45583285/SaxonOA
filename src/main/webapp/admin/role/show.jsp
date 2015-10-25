<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="userForm" method="post">
				<table>
			<tr>
				<input type="hidden" name="uuid" class="easyui-validatebox" data-options="required:true" style="width: 150px;"  value="${editObject.uuid}"/>
				<th>角色名称</th>
				<td>${editObject.name}
				</td>
				<th>排序号</th>
				<td>${editObject.sort}
				</td>
			</tr>
		</table>
	</form>
</div>