<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Saxon办公自动化系统V1.0</title>
<%@ include file="/inc.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/portal.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.portal.js"></script>

<script>
		$(function(){
			$('#pp').portal({
				border:false,
				fit:true
			});
			add();
		});
		function add(){
			for(var i=0; i<3; i++){
				var p = $('<div/>').appendTo('body');
				p.panel({
					title:'Title'+i,
					content:'<div style="padding:5px;">Content'+(i+1)+'</div>',
					height:100,
					closable:true,
					collapsible:true
				});
				$('#pp').portal('add', {
					panel:p,
					columnIndex:i
				});
			}
			$('#pp').portal('resize');
		}
		function remove(){
			$('#pp').portal('remove',$('#pgrid'));
			$('#pp').portal('resize');
		}
		
		
		function userInfo(){
			
		var p = parent.sl.dialog({
			title : '个人信息',
			href : '${pageContext.request.contextPath}/userInfo.jsp',
			width : 540,
			height : 300
		});		
		}
		
		
		
		
		function logout(){
		     $.ajax({
						url : '${pageContext.request.contextPath}/userAction!doNotNeedSession_logout.action',
						dataType : 'json',
						success : function(d) {
							parent.sl.messagerShow({
								title : '提示',
								msg : d.msg
							});
							window.location.href='${pageContext.request.contextPath}/login.jsp';
							
							}
							})
		}
		
	</script>
</head>

<body class="easyui-layout">
<!-- 头部标题 -->
<div data-options="region:'north',border:false" style="height:60px; padding:5px; background:#F3F3F3"> 
	<a href="#"><span class="northTitle">Saxon办公自动化系统V1.0</span></a>
    <span class="loginInfo">登录用户：<a href="#" onclick="userInfo()">${sessionInfo.loginName}</a>&nbsp;&nbsp;姓名：${sessionInfo.loginNames}&nbsp;&nbsp;登录IP：${sessionInfo.ip}&nbsp;&nbsp;<a href="#" onclick="logout()">退出</a></span>
</div>

<!-- 左侧导航 -->
<div data-options="region:'west',split:true,title:'导航菜单', fit:false" style="width:200px;"> 
  <ul id="menuTree" class="ztree">
  </ul>
</div>
<!-- 页脚信息 -->
<div data-options="region:'south',border:false" style="height:20px; background:#F3F3F3; padding:2px; vertical-align:middle;">
	<span id="sysVersion">系统版本：V1.0</span>
    <span id="nowTime"></span>
</div>

<!-- 内容tabs -->
<div id="center" data-options="region:'center'" border="false">
  <div id="tabs" class="easyui-tabs">
    <div title="首页" style="padding:5px;display:block;" >
     <!--  <p>模板说明：</p>
        <ul>
          <li>主界面使用 easyui1.3.5</li>
          <li>导航树使用 JQuery-zTree-v3.5.15</li>
        </ul>
      <p>特性说明：</p>
        <ul>
          <li>所有弹出框均显示在顶级父窗口</li>
          <li>修改easyui window拖动，移动时显示窗口而不显示虚线框，并限制拖动范围</li>
        </ul> -->
        <div id="pp" style="position:relative">
			<div style="width:30%;">
				<div title="Clock" style="text-align:center;background:#f3eeaf;height:150px;padding:5px;">
					
			    </div>
			   
			</div>
			<div style="width:40%;">
				<div id="pgrid" title="DataGrid" closable="true" style="height:200px;">
					<table class="easyui-datagrid" style="width:650px;height:auto"
							fit="true" border="false"
							singleSelect="true"
							idField="itemid" url="">
						<thead>
							<tr>
								<th field="itemid" width="60">Item ID</th>
								<th field="productid" width="60">Product ID</th>
								<th field="listprice" width="80" align="right">List 

Price</th>
								<th field="unitcost" width="80" align="right">Unit 

Cost</th>
								<th field="attr1" width="120">Attribute</th>
								<th field="status" width="50" 

align="center">Status</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div style="width:30%;">
				<div title="Searching" iconCls="icon-search" closable="true" 

style="height:80px;padding:10px;">
					<input class="easyui-searchbox">
				</div>
				<div title="Graph" closable="true" style="height:200px;text-align:center;">

				</div>
			</div>
		</div>
    </div>
  </div>
</div>
</body>
</html>
