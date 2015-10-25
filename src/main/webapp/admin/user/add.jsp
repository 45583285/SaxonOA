<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="userForm" method="post">
				<table>
			<tr>
				<th>登录名称</th>
				<td><input name="sysName" class="easyui-validatebox" data-options="required:true" />
				</td>
				<th>姓名</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" />
				</td>
				<th>排序</th>
				<td><input name="sort" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>生日</th>
				<td><input name="birthdate"  class="easyui-datebox" />
				</td>
				<th>移动电话</th>
				<td><input name="mobilephone" class="easyui-validatebox"  />
				</td>
			</tr>
			<tr>
				<th>办公电话</th>
				<td><input name="officephone" class="easyui-validatebox" />
				</td>
				<th>状态</th>
				<td><select id="state" class="easyui-combobox" name="state"
					style="width:150px;">
						<option value="0">启用</option>
						<option value="1">关闭</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>家庭住址</th>
				<td><input name="homeAddress" class="easyui-validatebox"   />
				</td>
				<th>邮箱地址</th>
				<td><input name="email" class="easyui-validatebox"  />
				</td>
			</tr>
			<tr>
				<th>是否管理员</th>
				<td><select id="isAdmin" class="easyui-combobox" name="isAdmin"
					style="width:150px;">
						<option value="0">管理员</option>
						<option value="1">普通用户</option>
				</select></td>
				<th>所属部门</th>
				<td><input id="pid"  name="pid"  class="easyui-combotree"    data-options="valueField:'id',textField:'text',url:'${pageContext.request.contextPath}/deptAction!treeGrid.action'" /> 
					
				</td>
			</tr>
		</table>
	</form>
</div>