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
					<th>导航名称</th>
					<td><input name="menuName"  class="easyui-validatebox" data-options="required:true"  /></td>
				</tr>
				<tr>
					<th>导航路径</th>
					<td><input name="menuUrl" class="easyui-validatebox" data-options="required:true" style="width: 150px;"/></td>
					<th>上级权限</th>
					<td>
					<input id="pid"  name="pid"  class="easyui-combotree"    data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/menusAction!treeGrid.action'" /> 
					</td>
				</tr>
				<tr>
					<th>创建人</th>
					<td><input id="iconcls" name="createUser"  class="easyui-validatebox" />
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

<script>
function showIcons(){
		var p = parent.sl.dialog({
			title : '浏览小图标',
			href : '${pageContext.request.contextPath}/style/icons.jsp',
			width : 540,
			height : 300,
			buttons : [ {
				text : '确定',
				handler : function() {
					$("#iconcls").val(p.find(':radio:checked').val());
					p.dialog('close');
				}
			} ]
		});		

}

</script>