<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
<%@ include file="/inc.jsp" %>
<script>
$(function(){
		$('#tt').tree({   
			checkbox:true,
   			url:'${pageContext.request.contextPath}/authAction!treeGrid.action'
		}); 
})

</script>
	<form method="post" class="form">
		<fieldset>
			<legend>权限清单</legend>			
			<ul id="tt"></ul> 
		</fieldset>
	</form>
</div>