<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>系统信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/inc.jsp" %>
</head>
<body class="easyui-layout" >
<div id="body" region="center" > 
	<form id="form" method="post">
		<table>
			<tr>
				<input type="hidden" name="uuid" class="easyui-validatebox" data-options="required:true" style="width: 150px;"  value="${editObject.uuid}"/>
				<th>网站名称</th>
				<td><input name="name" value="${editObject.name}" class="easyui-validatebox" data-options="required:true" />
				</td>
				<th>Logo</th>
				<td><input name="logo" value="${editObject.logo}" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>联系人</th>
				<td><input name="contactPerson" value="${editObject.contactPerson}"  class="easyui-validatebox" data-options="required:true" />
				</td>
				<th>服务热线</th>
				<td><input name="serviceLine" value="${editObject.serviceLine}" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>电话</th>
				<td><input name="telephone" value="${editObject.telephone}" class="easyui-datebox" />
				</td>
				<th>传真</th>
				<td><input name="fax" value="${editObject.fax}"  class="easyui-validatebox"  />
				</td>
			</tr>
			<tr>
				<th>QQ</th>
				<td><input name="qq" value="${editObject.qq}" class="easyui-validatebox"  />
				</td>
				<th>邮编</th>
				<td><input name="zipCode" value="${editObject.zipCode}" class="easyui-validatebox" />
				</td>
			</tr>
			<tr>
				<th>电子邮件</th>
				<td><input name="email" value="${editObject.email}" class="easyui-validatebox"   />
				</td>
				<th>网址</th>
				<td><input name="website" value="${editObject.website}" class="easyui-validatebox"  />
				</td>
			</tr>
			<tr>
				<th>地址</th>
				<td><input name="address" value="${editObject.address}" class="easyui-validatebox"  />
				</td>
				<th>网站关键词</th>
				<td><input name="keyWord" value="${editObject.keyWord}" class="easyui-validatebox"  />
				</td>
			</tr>
			<tr>
				<th>本案信息</th>
				<td><input name="archivalInfo" value="${editObject.archivalInfo}" class="easyui-validatebox"   />
				</td>
				<th>访问统计</th>
				<td><input name="accessQuantity" value="${editObject.accessQuantity}" class="easyui-validatebox" />
				</td>
			</tr>
			<tr>
				<th>版权信息</th>
				<td><input name="copyrightInfo" value="${editObject.copyrightInfo}" class="easyui-validatebox"  />
				</td>
				<th>备注</th>
				<td><input name="remarks" value="${editObject.remarks}" class="easyui-validatebox"  />
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
