<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form method="post">
    <table width="280" border="0" align="center" cellpadding="3">
      <tr>
        <td width="80" align="right"><label for="loginName"><span class="x">*</span>用户名：</label></td>
        <td width="200">
        <input type="text" name="name" id="name" /></td>
      </tr>
      <tr>
        <td align="right"><label><span class="x">*</span>密码：</label></td>
        <td><input class="easyui-validatebox" type="password" name="pwd" id="pwd" /></td>
      </tr>
    </table>
    </form>
</div>