<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/webpc/css/base.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/webpc/css/typography.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/webpc/pageGroup.css"/>
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
			
			
			
		

			
			$.ajax({
					url : '${pageContext.request.contextPath}/webCommonAction!searchCommon.action',
					data : {
						page:"1",
						rows:"10",
						sort:"createTime",
						order:"desc",
						uuid :uuid
					},
					dataType : 'json',
					success : function(d) {
							var obj = d;
							var html = "";
							$(obj).each(function(index) {
							
				var val = obj[index];
                if (typeof (val.type) == "object") {
                    $(val.type).each(function(ind) {
                        alert(val.title + " --------------------- " + val.author + " ------------- " + val.uuid);
                    });
                } else {
 							if(html==""){
 							html = "<li><a href=\"${pageContext.request.contextPath}/webCommonAction!toCommonInfo.action?uuid="+val.uuid+"\" class=\"clearfix\"><span>["+val.createTime+"]</span>"+val.title + "</a></li>";
 							}else{
 							html+="<li><a href=\"${pageContext.request.contextPath}/webCommonAction!toCommonInfo.action?uuid="+val.uuid+"\" class=\"clearfix\"><span>["+val.createTime+"]</span>"+val.title + "</a></li>";
 							}
 					}
							
					var testjs = $("#testjs");	
				     testjs.html(html);
               			
							
							
							});
					}
				});


var pageCount = 11;	

				$.ajax({
					url : '${pageContext.request.contextPath}/webCommonAction!getPages.action',
					data : {
						page:"1",
						rows:"10",
						sort:"createTime",
						order:"desc",
						uuid :uuid
					},
					dataType : 'json',
					success : function(d) {
						pageCount = parseInt(d.pages);//模拟后台总页数
								//根据总页数判断，如果小于5页，则显示所有页数，如果大于5页，则显示5页。根据当前点击的页数生成
						//生成分页按钮
		if(pageCount>5){
		page_icon(1,5,0);
	}else{
		page_icon(1,pageCount,0);
	}
	
	
	
	
	
	
					}
				});




	
$("#pageGro").on("click","li",function(){
		if(pageCount > 5){
			var pageNum = parseInt($(this).html());//获取当前页数
			pageGroup(pageNum,pageCount);
		}else{
			$(this).addClass("on");
			$(this).siblings("li").removeClass("on");
		}
		test(uuid)
});



	//点击上一页触发
	$("#pageGro .pageUp").click(function(){
		if(pageCount > 5){
			var pageNum = parseInt($("#pageGro li.on").html());//获取当前页
			pageUp(pageNum,pageCount);
			
		}else{
			var index = $("#pageGro ul li.on").index();//获取当前页
			if(index > 0){
				$("#pageGro li").removeClass("on");//清除所有选中
				$("#pageGro ul li").eq(index-1).addClass("on");//选中上一页
			}
		}
		
		test(uuid)
	});
	
	//点击下一页触发
	$("#pageGro .pageDown").click(function(){
		if(pageCount > 5){
			var pageNum = parseInt($("#pageGro li.on").html());//获取当前页
			pageDown(pageNum,pageCount);
			
		}else{
			var index = $("#pageGro ul li.on").index();//获取当前页
			if(index+1 < pageCount){
				$("#pageGro li").removeClass("on");//清除所有选中
				$("#pageGro ul li").eq(index+1).addClass("on");//选中上一页
			}
		}
		test(uuid)
	});











	});
	
	
	
	
	//点击跳转页面
function pageGroup(pageNum,pageCount){
	switch(pageNum){
		case 1:
			page_icon(1,5,0);
		break;
		case 2:
			page_icon(1,5,1);
		break;
		case pageCount-1:
			page_icon(pageCount-4,pageCount,3);
		break;
		case pageCount:
			page_icon(pageCount-4,pageCount,4);
		break;
		default:
			page_icon(pageNum-2,pageNum+2,2);
		break;
	}
}

//根据当前选中页生成页面点击按钮
function page_icon(page,count,eq){
	var ul_html = "";
	for(var i=page; i<=count; i++){
		ul_html += "<li>"+i+"</li>";
	}
	$("#pageGro ul").html(ul_html);
	$("#pageGro ul li").eq(eq).addClass("on");
}

//上一页
function pageUp(pageNum,pageCount){
	switch(pageNum){
		case 1:
		break;
		case 2:
			page_icon(1,5,0);
		break;
		case pageCount-1:
			page_icon(pageCount-4,pageCount,2);
		break;
		case pageCount:
			page_icon(pageCount-4,pageCount,3);
		break;
		default:
			page_icon(pageNum-2,pageNum+2,1);
		break;
	}
}

//下一页
function pageDown(pageNum,pageCount){
	switch(pageNum){
		case 1:
			page_icon(1,5,1);
		break;
		case 2:
			page_icon(1,5,2);
		break;
		case pageCount-1:
			page_icon(pageCount-4,pageCount,4);
		break;
		case pageCount:
		break;
		default:
			page_icon(pageNum-2,pageNum+2,3);
		break;
	}
}





function test(uuid){
	
	$.ajax({
		url : '${pageContext.request.contextPath}/webCommonAction!searchCommon.action',
		data : {
			page:$("#pageGro li.on").html(),
			rows:"10",
			sort:"createTime",
			order:"desc",
			uuid :uuid
		},
		dataType : 'json',
		success : function(d) {
				var obj = d;
				var html = "";
				$(obj).each(function(index) {
				
	var val = obj[index];
    if (typeof (val.type) == "object") {
        $(val.type).each(function(ind) {
            alert(val.title + " --------------------- " + val.author + " ------------- " + val.uuid);
        });
    } else {
					if(html==""){
					html = "<li><a href=\"${pageContext.request.contextPath}/webCommonAction!toCommonInfo.action?uuid="+val.uuid+"\" class=\"clearfix\"><span>["+val.createTime+"]</span>"+val.title + "</a></li>";
					}else{
					html+="<li><a href=\"${pageContext.request.contextPath}/webCommonAction!toCommonInfo.action?uuid="+val.uuid+"\" class=\"clearfix\"><span>["+val.createTime+"]</span>"+val.title + "</a></li>";
					}
			}
		
		var testjs = $("#testjs");	
	     testjs.html(html);
   			
				
				
				});
		}
	});
	
	
}
	
	
	
	
	
</script>

</head>

<body>
	<%@ include file="/webpc/top.jsp" %>
<div class="sub-banner wrap1100"><img src="${pageContext.request.contextPath}/style/images/webpc/images/banner-about.png" alt="" /></div>
<div class="sub-page wrap1100 clearfix">
		<div class="sidebar fl">
			<h4 class="title"><span class="cn">${editObject.pname}</span><span class="en">NEWS</span></h4>
			<ul class="sidelist" id="sidelist">
				<li class="current"><a href="#">${editObject.menuName}</a></li>
				<li><a href="#">行业动态</a></li>
				<li><a href="#">政策法规</a></li>
			</ul>
			<div class="links">
				<a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/link1.png" alt="" /></a>
				<a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/link2.png" alt="" /></a>
				<a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/link3.png" alt="" /></a>
			</div>
		</div>
		<div class="subbox fr">
			<h4 class="subtitle">${editObject.menuName}</h4>
			<div class="contents">
				<ul class="newslist" id="testjs">
					
				</ul>
	<div id="pageGro" class="cb">
	<div class="pageUp">上一页</div>
    <div class="pageList">
        <ul>
            <li>1</li>
            <li>2</li>
            <li>3</li>
            <li>4</li>
            <li>5</li>
        </ul>
    </div>
    <div id="pageDown" class="pageDown">下一页</div>
</div>
			
			</div>
		</div>
</div>
	<%@ include file="/webpc/feet.jsp" %>
</body>
</html>
