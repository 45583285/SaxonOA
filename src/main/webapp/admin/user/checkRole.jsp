<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
<script>
$(function(){
		$('#roleTree').tree({   
			checkbox:true,
			animate:true,
			lines:true,
			cascadeCheck:false,
   			url:'${pageContext.request.contextPath}/roleAction!treeGrid.action'
		}); 
});

</script>
	<form method="post" class="form">
	<input type="hidden" name="uuid" class="easyui-validatebox" data-options="required:true" style="width: 150px;"  value="${editObject.uuid}"/>
	<input type="hidden"  name="ids"  id="ids" class="easyui-validatebox"  style="width: 150px;" />
		<fieldset>
			<legend>角色清单</legend>			
			<ul id="roleTree"></ul> 
		</fieldset>
	</form>
</div>