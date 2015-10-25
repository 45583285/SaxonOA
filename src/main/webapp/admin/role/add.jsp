<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="userForm" method="post">
				<table>
			<tr>
				<th>角色名称</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true" />
				</td>
				<th>排序号</th>
				<td><input name="sort" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
		</table>
	</form>
</div>