<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
<script>
$(function(){
		var uuid = $("#uuid").val();
		$('#authTree').tree({   
			checkbox:true,//定义是否显示checkbox在所有节点之前.
			animate:true,//定义当展开/折叠节点的时候是否显示效果
			lines:true,//定义是否显示树线
			cascadeCheck:false,//是否级联选择
   			url:'${pageContext.request.contextPath}/authAction!treeGridForRole.action?uuid='+uuid
   			
		}); 
});
</script>
	<form method="post" class="form">
	<input  type="hidden" id="uuid" name="uuid" class="easyui-validatebox"  style="width: 150px;"  value="${editObject.uuid}"/>
	<input  type="hidden"   name="ids"  id="ids" class="easyui-validatebox"  style="width: 150px;" />
		<fieldset>
			<legend>权限清单</legend>			
			<ul id="authTree"></ul> 
		</fieldset>
	</form>
</div>