<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="userForm" method="post">
				<table>
			<tr>
				<input type="hidden" name="uuid" class="easyui-validatebox" data-options="required:true" style="width: 150px;"  value="${editObject.uuid}"/>
				<th>登录名称</th>
				<td>${editObject.sysName}
				</td>
				<th>姓名</th>
				<td>${editObject.name}
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td>${editObject.pwd}
				</td>
				<th>排序</th>
				<td>${editObject.sort}
				</td>
			</tr>
			<tr>
				<th>生日</th>
				<td>${editObject.birthdate}
				</td>
				<th>移动电话</th>
				<td>${editObject.mobilephone}
				</td>
			</tr>
			<tr>
				<th>办公电话</th>
				<td>${editObject.officephone}
				</td>
				<th>状态</th>
				<td>${editObject.state}
				</td>
			</tr>
			<tr>
				<th>家庭住址</th>
				<td>${editObject.homeAddress}
				</td>
				<th>邮箱地址</th>
				<td>${editObject.email}
				</td>
			</tr>
			<tr>
				<th>是否管理员</th>
				<td>${editObject.isAdmin}</td>
				<th>所属部门</th>
				<td>${editObject.pid} 
					
				</td>
			</tr>
		</table>
	</form>
</div>