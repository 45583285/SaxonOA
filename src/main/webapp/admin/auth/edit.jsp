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
					<td><input name="sort"  value="${editObject.sort}" class="easyui-validatebox" data-options="required:true" style="width: 150px;"/></td>
					<th>权限名称</th>
					<td><input name="name"   value="${editObject.name}" class="easyui-validatebox" data-options="required:true"  /></td>
				</tr>
				<tr>
					<th>权限类型</th>
					<td>
					<select id="type" name="type" class="easyui-combobox"   value="${editObject.type}" style="width: 155px;">
					<option value="0">导航</option>  
   					<option value="1">功能</option>  
					</select> 
					</td>
					<th>上级权限</th>
					<td>
					<input id="pid"  name="pid"  value="${editObject.pid}"  class="easyui-combotree"    data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/authAction!treeGrid.action'" /> 
					</td>
				</tr>
				<tr>
					<th>权限图标</th>
					<td><input id="iconcls" name="iconcls"  class="easyui-validatebox"  value="${editObject.iconcls}" data-options="required:true" />
					<img class="iconImg ext-icon-zoom" onclick="showIcons();" title="浏览图标" />&nbsp;<img class="iconImg ext-icon-cross" onclick="$('#iconcls').val('');$('#iconcls').attr('class','');" title="清空" />
					</td>
					<th>权限路径</th>
					<td><input name="url"  class="easyui-validatebox"  value="${editObject.url}" data-options="required:true"  /></td>
				</tr>
				<tr>
					<th>权限描述</th>
					<td colspan="3"><input name="description"   value="${editObject.description}" class="easyui-validatebox"  /></td>
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