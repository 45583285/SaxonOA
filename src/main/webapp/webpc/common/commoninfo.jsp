<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/webpc/css/base.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/webpc/css/typography.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/webpc/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(function() {
	var uuid1 = "${editObject.pid}";
	var uuid = "${editObject.uuid}";		
			$.ajax({
					url : '${pageContext.request.contextPath}/webCommonAction!getCommonById.action',
					data : {
						uuid :uuid1
					},
					dataType : 'json',
					success : function(d) {
							var obj = d;
							var html = "";
							$(obj).each(function(index) {
							var val = obj[index];
							var temp="<li>";
							if(val.uuid==uuid){
								temp = "<li class=\"current\">";
							}
							
							
 							if(html==""){
 								html = temp+"<a href=\"${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid="+val.uuid+"\">"+val.menuName+"</a></li>";
 							}else{
 								html+=temp+"<a href=\"${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid="+val.uuid+"\">"+val.menuName+"</a></li>";
 							}	
							var sidelist = $("#sidelist");	
				     		sidelist.html(html);
               			
							
							
							});
					}
				});
		});	
			
</script>
</head>
<body>
	<%@ include file="/webpc/top.jsp" %>
<div class="sub-banner wrap1100"><img src="${pageContext.request.contextPath}/style/images/webpc/images/banner-about.png" alt="" /></div>
<div class="sub-page wrap1100 clearfix">
      <div class="sidebar fl">
         <h4 class="title"><span class="cn">${editObject.pname}</span><span class="en">ABOUT US</span></h4>
         <ul class="sidelist" id="sidelist">
            <li class="current"><a href="#">公司简介</a></li>
            <li><a href="#">董事长风采</a></li>
            <li><a href="#">企业荣誉</a></li>
            <li><a href="#">组织机构</a></li>
         </ul>
         <div class="links">
            <a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/link1.png" alt="" /></a>
            <a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/link2.png" alt="" /></a>
            <a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/link3.png" alt="" /></a>
         </div>
      </div>
      <div class="subbox fr">
         <h4 class="subtitle">${contents.title}</h4>
         <div class="contents">
         ${contents.details}
         </div>
      </div>
</div>
	<%@ include file="/webpc/feet.jsp" %>
</body>
</html>
