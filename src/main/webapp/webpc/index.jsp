<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/webpc/css/base.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/webpc/css/typography.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/webpc/js/jquery-2.1.1.min.js"></script>

<script src="${pageContext.request.contextPath}/jslib/webpc/js/jcarousellite.js" type="text/javascript"></script>
<script type="text/javascript">


	$(function() {
		
			$.ajax({
					url : '${pageContext.request.contextPath}/webHomeAction!getSysInfo.action',
					dataType : 'json',
					success : function(d) {
	 
					}
				});
	
	
/* 		$.ajax({
					url : '${pageContext.request.contextPath}/webHomeAction!getAll.action',
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
 							html = "<li><a href=\"#\" class=\"clearfix\"><span class=\"time fr\">["+val.createTime+"]</span>"+val.title + "</a></li>";
 							}else{
 							html+="<li><a href=\"#\" class=\"clearfix\"><span class=\"time fr\">["+val.createTime+"]</span>"+val.title + "</a></li>";
 							}
 					}
							
					var testjs = $("#testjs");	
				     testjs.html(html);
               			
							
							
							});
					}
				}); */
				
				
				//获取新闻动态
				
				$.ajax({
					url : '${pageContext.request.contextPath}/webHomeAction!getContentsByMenusId.action',
					data : {
						page:"1",
						rows:"4",
						sort:"createTime",
						order:"desc",
						uuid :"b7620863-ef48-491f-bce2-e1f7d307a175"
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
 							html = "<li><a href=\"${pageContext.request.contextPath}/webCommonAction!toCommonInfo.action?uuid="+val.uuid+"\" class=\"clearfix\"><span  class=\"time fr\">["+val.createTime+"]</span>"+val.title + "</a></li>";
 							}else{
 							html+="<li><a href=\"${pageContext.request.contextPath}/webCommonAction!toCommonInfo.action?uuid="+val.uuid+"\" class=\"clearfix\"><span  class=\"time fr\">["+val.createTime+"]</span>"+val.title + "</a></li>";
 							}
 					}
							
					var newslist = $("#newslist");	
				     newslist.html(html);
               			
							
							
							});
					}
				});
				
				
				//获取行业动态
				
				$.ajax({
					url : '${pageContext.request.contextPath}/webHomeAction!getContentsByMenusId.action',
					data : {
						page:"1",
						rows:"4",
						sort:"createTime",
						order:"desc",
						uuid :"f2094cfd-ae1b-4a61-9fd1-492bef1fdef3"
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
 							html = "<li><a href=\"${pageContext.request.contextPath}/webCommonAction!toCommonInfo.action?uuid="+val.uuid+"\" class=\"clearfix\"><span class=\"time fr\">["+val.createTime+"]</span>"+val.title + "</a></li>";
 							
 							
 							}else{
 							html+="<li><a href=\"${pageContext.request.contextPath}/webCommonAction!toCommonInfo.action?uuid="+val.uuid+"\" class=\"clearfix\"><span class=\"time fr\">["+val.createTime+"]</span>"+val.title + "</a></li>";
 							}
 					}
							
					var hyslist = $("#hyslist");	
				     hyslist.html(html);
               			
							
							
							});
					}
				});

	});
</script>


</head>

<body>
	<%@ include file="/webpc/top.jsp" %>
<div class="banner wrap1100">
 <script src="${pageContext.request.contextPath}/jslib/webpc/js/pptBox.js" type="text/javascript"></script>
      <script>
        var box =new PPTBox();
        box.width = 1000; //宽度
        box.height = 330;//高度
        box.autoplayer = 100;//自动播放间隔时间
       // box.add({"url":"图片地址","title":"悬浮标题","href":"链接地址"})
        box.add({"url":"${pageContext.request.contextPath}/style/images/webpc/images/banner1.png","href":"###","title":"悬浮提示标题1"})
        box.add({"url":"${pageContext.request.contextPath}/style/images/webpc/images/banner2.png","href":"###","title":"悬浮提示标题2"})
        box.show();
      </script>
</div>
<div class="index-wrap1">
   <div class="wrap1100 clearfix">
      <div class="index-project fl">
         <ul id="tabli" class="tabli clearfix">
            <li>经典工程</li>
            <li>获得专利</li>
         </ul>
         <div id="changewrap" class="changewrap">

            <div class="pro">
               <div class="l-point l-point1"><a href=""><img src="${pageContext.request.contextPath}/style/images/webpc/images/l-point.png" /></a></div>
               <div class="scroll-wrap scroll-wrap1">
                  <ul class="clearfix">
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>宁波皎口生态湿地</span></a></li>
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>宁波皎口生态湿地</span></a></li>
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>宁波皎口生态湿地</span></a></li>
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>宁波皎口生态湿地</span></a></li>
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>宁波皎口生态湿地</span></a></li>
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>宁波皎口生态湿地</span></a></li>
                  </ul>
               </div>
               <div class="r-point r-point1"><a href=""><img src="${pageContext.request.contextPath}/style/images/webpc/images/r-point.png" /></a></div>
            </div>
            <div class="pat">
               <div class="l-point l-point2"><a href=""><img src="${pageContext.request.contextPath}/style/images/webpc/images/l-point.png" /></a></div>
               <div class="scroll-wrap scroll-wrap2">
                  <ul class="clearfix">
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>获得专利名称</span></a></li>
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>获得专利名称</span></a></li>
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>获得专利名称</span></a></li>
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>获得专利名称</span></a></li>
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>获得专利名称</span></a></li>
                     <li><a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" /><span>获得专利名称</span></a></li>
                  </ul>
               </div>
               <div class="r-point r-point2"><a href=""><img src="${pageContext.request.contextPath}/style/images/webpc/images/r-point.png" /></a></div>
            </div>
            <script>
               $(".scroll-wrap1").jCarouselLite({
               btnNext: ".r-point1",
               btnPrev: ".l-point1",
               auto: 40000,
               speed: 800, 
               visible:4,
               scroll:1
               });
               $(".scroll-wrap2").jCarouselLite({
               btnNext: ".r-point2",
               btnPrev: ".l-point2",
               auto: 40000,
               speed: 800, 
               visible:4,
               scroll:1
               });
            </script>
         </div>
<script>switchTab("#tabli","#changewrap","click","fadeIn","0");</script>
      </div>
      <div class="index-about fr">
         <h4 class="title clearfix"><a href="#" class="more fr"><img src="${pageContext.request.contextPath}/style/images/webpc/images/more1.png" alt="" /></a>关于禹顺</h4>
         <div class="wrap"><b class="arrow">&nbsp;</b>${sysInfo.contactPerson}</div>
      </div>
   </div>
</div>
<div class="index-wrap2">
   <div class="wrap1100 clearfix">
      <div class="index-news fl">
         <ul id="tabli2" class="tabli clearfix">
            <li>新闻动态</li>
            <li>行业动态</li>
         </ul>
         <div id="changewrap2" class="changewrap">
            <div class="news1">
               <a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=b7620863-ef48-491f-bce2-e1f7d307a175" class="more"><img src="${pageContext.request.contextPath}/style/images/webpc/images/more2.png" alt="" /></a>
               <div class="firstlist clearfix" id="newsfirst">
                  <img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" class="img0 fl" alt="" />
                  <div class="txt fr">
                     <p class="name"><a href="#">我公司被宁波资信评估委员会评定为</a></p>
                     <p class="memo">我公司被宁波资信评估委员会评定为“贷款企业资信等级AAA级企业”。我公司被宁波资信评估委员会评定为“贷款企业资信等级AAA级企业”。我公司被宁波资信评估委员会评定为“贷款企业资信等级AAA级企业”。</p>
                  </div>
               </div>
               <ul class="list" id="newslist">
                  <li><a href="#" class="clearfix"><span class="time fr">[ 2014-09-29 ]</span>我公司被宁波资信评估委员会评定为</a></li>
                  <li><a href="#" class="clearfix"><span class="time fr">[ 2014-09-29 ]</span>我公司被宁波资信评估委员会评定为</a></li>
                  <li><a href="#" class="clearfix"><span class="time fr">[ 2014-09-29 ]</span>我公司被宁波资信评估委员会评定为</a></li>
                  <li><a href="#" class="clearfix"><span class="time fr">[ 2014-09-29 ]</span>我公司被宁波资信评估委员会评定为</a></li>
               </ul>
            </div>
            <div class="news2">
               <a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=f2094cfd-ae1b-4a61-9fd1-492bef1fdef3" class="more"><img src="${pageContext.request.contextPath}/style/images/webpc/images/more2.png" alt="" /></a>
               <div class="firstlist clearfix">
                  <img src="${pageContext.request.contextPath}/style/images/webpc/images/img0.png" class="img0 fl" alt="" />
                  <div class="txt fr">
                     <p class="name"><a href="#">我公司被宁波资信评估委员会评定为</a></p>
                     <p class="memo">我公司被宁波资信评估委员会评定为“贷款企业资信等级AAA级企业”。我公司被宁波资信评估委员会评定为“贷款企业资信等级AAA级企业”。我公司被宁波资信评估委员会评定为“贷款企业资信等级AAA级企业”。</p>
                  </div>
               </div>
               <ul class="list" id="hyslist">
                  <li><a href="#" class="clearfix"><span class="time fr">[ 2014-09-29 ]</span>我公司被宁波资信评估委员会评定为</a></li>
                  <li><a href="#" class="clearfix"><span class="time fr">[ 2014-09-29 ]</span>我公司被宁波资信评估委员会评定为</a></li>
                  <li><a href="#" class="clearfix"><span class="time fr">[ 2014-09-29 ]</span>我公司被宁波资信评估委员会评定为</a></li>
                  <li><a href="#" class="clearfix"><span class="time fr">[ 2014-09-29 ]</span>我公司被宁波资信评估委员会评定为</a></li>
               </ul>
            </div>
         </div>
         <script>switchTab("#tabli2","#changewrap2","click","fadeIn","0");</script>
      </div>
      <div class="index-other fr">
         <div class="index-objective fl">
            <div class="wrap">“ 质量求生存、信誉为动力、安全促发展 ”<br />“ 求实、创新、进取 ”</div>
            <h4 class="title"><img src="${pageContext.request.contextPath}/style/images/webpc/images/objective-tit.png" alt="" /></h4>
         </div>
         <div class="index-link fr">
            <a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/link1.png" alt="" /></a>
            <a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/link2.png" alt="" /></a>
            <a href="#"><img src="${pageContext.request.contextPath}/style/images/webpc/images/link3.png" alt="" /></a>
         </div>
      </div>
   </div>
</div>
	<%@ include file="/webpc/feet.jsp" %>
</body>
</html>
