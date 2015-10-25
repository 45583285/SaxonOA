<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<%String version = "20150706";%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/default.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.5/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.5/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/JQuery-zTree-v3.5.15/css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/slCss.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.5/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/JQuery-zTree-v3.5.15/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/extends.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/tools.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/ueditor/ueditor.all.min.js"></script>
<!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/ueditor/lang/zh-cn/zh-cn.js"></script>
<!-- 引用ajaxfileupload.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/ajaxfileupload.js"></script>
<%-- 引入扩展图标 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/syExtIcon.css?version=<%=version%>" type="text/css">
<%-- 引入自定义样式 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/syExtCss.css?version=<%=version%>" type="text/css">

