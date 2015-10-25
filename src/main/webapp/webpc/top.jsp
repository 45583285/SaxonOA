<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="header">
  <span class="bg0">&nbsp;</span>
  <div class="wrap1100 clearfix">
    <div class="logo fl"><img src="${pageContext.request.contextPath}/style/images/webpc/images/logo1.png" alt="" /></div>
    <div class="header-r fr">
      <p class="oalink"><a href="${pageContext.request.contextPath}/login.jsp">后台管理</a></p>
      <p class="tel"><span>服务热线</span><span class="txt" id="txt">${sysInfo.serviceLine}</span></p>
    </div>
  </div>
</div>
<div class="navigation">
   <div id="main_nav" class="wrap1100">
      <ul class="nav_list clearfix">
         <li><a href="${pageContext.request.contextPath}/webHomeAction!toHome.action">首页</a></li>
         <li>
            <a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=3ac3ffcd-4bd9-44a8-9301-0a1833199168">走进本公司</a>
            <div id="apDiv1">
               <ul id="sub_nav">
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=3ac3ffcd-4bd9-44a8-9301-0a1833199168">公司简介</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=aa732d65-b803-44f6-bcf8-9e5d507ec4de">董事长风采</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=1b7af988-578b-4b1f-aad5-0d0dc391eafa">企业荣誉</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=8a64df97-956c-41d8-82d2-cf65fc29d684">组织机构</a></li>
               </ul>
            </div>
         </li>
         <li>
            <a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=b7620863-ef48-491f-bce2-e1f7d307a175">新闻中心</a>
            <div id="apDiv2">
               <ul id="sub_nav">
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=b7620863-ef48-491f-bce2-e1f7d307a175">公司新闻</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=f2094cfd-ae1b-4a61-9fd1-492bef1fdef3">行业动态</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=f0807332-2d65-494a-b461-1f4ed7393fec">政策法规</a></li>
               </ul>
            </div>
         </li>
         <li>
            <a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=0f4d0456-908d-4aa3-ab11-4fa020161148"> 工程业绩</a>
            <div id="apDiv3">
               <ul id="sub_nav">
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=0f4d0456-908d-4aa3-ab11-4fa020161148">经典工程</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=410f7b24-1dd0-46f9-b4c0-a44249506a79">在建项目</a></li>
               </ul>
            </div>
         </li>
         <li>
            <a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=a10e2eb0-fdb5-4c24-83d5-3fc4cf74a8ff">研发创新</a>
            <div id="apDiv4">
               <ul id="sub_nav">
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=a10e2eb0-fdb5-4c24-83d5-3fc4cf74a8ff">技术中心</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=78f8cb49-d7e8-404f-9401-5ca409f30c59">科技成果</a></li>
               </ul>
            </div>
         </li>
         <li>
            <a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=1cc99626-7cf4-4795-a087-23688ed353eb">企业文化</a>
            <div id="apDiv5">
               <ul id="sub_nav">
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=1cc99626-7cf4-4795-a087-23688ed353eb">企业形象</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=36507134-3236-42c5-9a2a-ae0c7e3a94c7">企业理念</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=a0cba1fa-94ad-4adb-9d11-4a5ec7a278da">社会责任</a></li>
               </ul>
            </div>
         </li>
         <li>
            <a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=79c5f68a-6dde-4a4f-8727-cdb9e92735c3">人力资源</a>
            <div id="apDiv6">
               <ul id="sub_nav">
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=79c5f68a-6dde-4a4f-8727-cdb9e92735c3">人才理念</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=1cc5783e-580e-4a35-98e5-a0cf81a5ffb6">培训发展</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=a936729d-6426-40d9-882b-cde86b04e1a9">工作环境</a></li>
                  <li><a href="${pageContext.request.contextPath}/webCommonAction!toCommon.action?uuid=0440f669-feb9-404c-9895-da2e8e50c2b1">招贤纳士</a></li>
               </ul>
            </div>
         </li>
         <li>
            <a href="${pageContext.request.contextPath}/webCommonAction!toCommonInfo.action?uuid=1bbc6abe-35c0-4f9a-81ba-29f93c85ae74">联系我们</a>
         </li>
      </ul>
   </div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/webpc/js/home.js"></script>

</div>
