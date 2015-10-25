<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
	<form method="post" enctype="multipart/form-data">
		<table class="tableForm">
					<tr>
				<th style="width:80px;">排序号</th>
				<td><input name="sort" class="easyui-validatebox" value="${editObject.sort}" style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;">流程名称</th>
				<td><input name="processName" class="easyui-validatebox" value="${editObject.processName}" style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;">流程ID</th>
				<td><input name="processId" class="easyui-validatebox"  value="${editObject.processId}" style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;">流程上传</th>
				<td><input name="file" class="easyui-validatebox"  type="file" value="${editObject.file}" style="width: 97%;" />
				</td>
			</tr>
		</table>
		<img alt="" width="100%" src="/SaxonOA/admin/process/${editObject.processId}.png">
	</form>
		
</div>