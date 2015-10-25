<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;">
	<form method="post" enctype="multipart/form-data">
		<table class="tableForm">
					<tr>
				<th style="width:80px;">排序号</th>
				<td><input name="sort" class="easyui-validatebox"  style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;">标题</th>
				<td><input name="title" class="easyui-validatebox" style="width: 97%;" />
				</td>
			</tr>
			<tr>
				<th style="width:80px;">流程ID</th>
				<td><input name="processid" class="easyui-validatebox"  style="width: 97%;" />
				</td>
			</tr>
		</table>
		<input name="next_task_user_sysName" class="easyui-validatebox"  style="width: 20%;" /> 下一环节处理人
		<input name="createTime" class="easyui-validatebox"  style="width: 20%;" /> 创建时间
		<input name="createUser" class="easyui-validatebox"  style="width: 20%;" /> 创建人
		<input name="updateTime" class="easyui-validatebox"  style="width: 20%;" /> 更新时间
		<input name="updateUser" class="easyui-validatebox"  style="width: 20%;" /> 更新人
		
		<!-- 点击图片，打开文件选择器，确定，上传。(这是网络上的一个图片) -->
<img id="upload" alt="" style="width: 200px; height: 200px"
    src="http://d.pcs.baidu.com/thumbnail/e8119cd92364a9b2714ea0a92af15aec?fid=2399642819-250528-305026848845811&time=1405674000&sign=FDTAER-DCb740ccc5511e5e8fedcff06b081203-abo3xnZkLb7yMEPLDWiuaQI8kXM%3D&rt=sh&expires=2h&r=900585425&sharesign=unknown&size=c710_u500&quality=100">

<!-- 隐藏file标签 -->
<input id="filedata"  type="file" name="filedata"><br/>
	</form>
		
</div>

<script type="text/javascript">
$(function(){
    //点击打开文件选择器
    $("#upload").on('click', function() {
        $('#filedata').click();
    });
    
    //选择文件之后执行上传
    $('#filedata').on('change', function() {
    	parent.$.messager.progress({
					text : '数据上传中....'
				});
        $.ajaxFileUpload({
            url:'${pageContext.request.contextPath}/fileinAction!fileUpload.action',
            //secureuri:false,
            fileElementId:'filedata',//file标签的id
            dataType: 'json',//返回数据的类型
          //  data:{name:'logan'},//一同上传的数据
            success: function (data, status) {
                parent.$.messager.progress('close');
                //把图片替换
               // var obj = jQuery.parseJSON(data);
               // $("#upload").attr("src", "../image/"+obj.fileName);
				//alert(data.err);
                if(typeof(data.error) != 'undefined') {
                    if(data.error != '') {
                        alert(data.error);
                    } else {
                        alert(data.msg);
                    }
                }
            },
            error: function (data, status, e) {
                alert(e);
            }
        });
    });
    
});
</script>