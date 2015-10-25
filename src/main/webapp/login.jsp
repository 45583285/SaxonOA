<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Saxon办公自动化系统V1.0</title>
<%@ include file="/inc.jsp" %>

<script>
$(function(){
	logins();
	$("#addUser_save").on("click", function(){
		var formData = $("form").serialize();
		$.ajax( {
			type : "POST",
			data : formData,
			dataType : 'json',
			url : "${pageContext.request.contextPath}/userAction!doNotNeedSession_login.action",
			success : function(d) {
				if(d.success){
					window.location.href="${pageContext.request.contextPath}/admin/home/index.jsp";
				}else{
					$.messager.alert('错误', d.msg);
				}
		}
		});
			
	});
	
	
		if (sl.isLessThanIe8()) {
			$.messager.show({
				title : '警告',
				msg : '您使用的浏览器版本太低！<br/>建议您使用谷歌浏览器来获得更快的页面响应效果！',
				timeout : 1000 * 60
			});
		}

});

function logins(){
		var p = parent.sl.dialog({
			title : '登入',
			href : '${pageContext.request.contextPath}/logins.jsp',
			width : 340,
			height : 200,
			buttons : [ {
				text : '登录',
				handler : function() {
					var f = p.find('form');
					f.form('submit', {
						url : '${pageContext.request.contextPath}/userAction!doNotNeedSession_login.action',
						success : function(d) {
							var json = $.parseJSON(d);
							if (json.success) {
								p.dialog('close');
								parent.sl.messagerAlert('提示',json.msg,'info');
								window.location.href="${pageContext.request.contextPath}/admin/home/index.jsp";
							}else{
								parent.sl.messagerAlert('错误', json.msg);
							}
							
						}
					});
				}
			} ]
		});		

}

</script>

<style type="text/css">
	body{

   background:url(${pageContext.request.contextPath}/style/images/login.jpg);

   background-attachment: fixed;
   background-position: center;
background-repeat: no-repeat;
background-attachment: fixed;}

}
	
</style>
</head>
<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 class=PageBody>
</body>
</html>

