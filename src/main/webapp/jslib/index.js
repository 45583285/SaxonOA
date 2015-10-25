/*
	主页加载方法
	@eric
*/
//系统时间显示
setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	view: {
		selectedMulti: false
	},
	callback: {
		onClick:function(e, id, node){
			var zTree = $.fn.zTree.getZTreeObj("menuTree");
			if(node.isParent) {
				zTree.expandNode();
			} else {
				addTabs(node.name, node.file);
			}
		}
	}
};

var zNodes =[
	{ id:1, pId:0, name:"组织结构", open:true},
	{ id:11, pId:1, name:"部门管理", file:"/SaxonOA/deptAction!toList.action"},
	{ id:12, pId:1, name:"用户管理", file:"/SaxonOA/userAction!toList.action"},
	{ id:13, pId:1, name:"权限管理", file:"/SaxonOA/authAction!toList.action"},
	{ id:14, pId:1, name:"角色管理", file:"/SaxonOA/roleAction!toList.action"},	
	{ id:2, pId:0, name:"系统信息", open:true},
	{ id:21, pId:2, name:"基本信息", file:"/SaxonOA/infoAction!toEdit.action"},
	{ id:22, pId:2, name:"模块信息", file:"/SaxonOA/admin/menu/list.jsp"},
	{ id:23, pId:2, name:"登录日志", file:"/SaxonOA/loginInfoAction!toList.action"},
	{ id:24, pId:2, name:"常用短语", file:""},
	{ id:25, pId:2, name:"流程管理", file:"/SaxonOA/processAction!toList.action"},
	{ id:26, pId:2, name:"申请模板管理", file:"applicationTemplateAction_list.action"},
	{ id:3, pId:0, name:"审批流转", open:true},
	{ id:31, pId:3, name:"起草申请", file:"flowAction_list.action"},
	{ id:31, pId:3, name:"待我审核", file:"/SaxonOA/dbsyAction!toList.action"},
	{ id:31, pId:3, name:"我的申请查询", file:""},
	{ id:4, pId:0, name:"其他功能", open:true},
	{ id:41, pId:4, name:"通知公告", file:"/SaxonOA/bulletinAction!toList.action"},
	{ id:5, pId:0, name:"公文管理", open:true},
	{ id:51, pId:5, name:"收文管理", file:"/SaxonOA/fileinAction!toList.action"},
	{ id:6, pId:0, name:"发布系统", open:true},
	{ id:61, pId:6, name:"栏目管理", file:"/SaxonOA/menusAction!toList.action"},
	{ id:62, pId:6, name:"信息管理", file:"/SaxonOA/contentsAction!toList.action"},
	{ id:7, pId:0, name:"商城管理", open:true},
	{ id:71, pId:7, name:"商品分类", file:"/SaxonOA/categoryAction!toList.action"},
	{ id:72, pId:7, name:"商品信息", file:"/SaxonOA/ordersAction!toList.action"},
	{ id:73, pId:7, name:"订单管理", file:"/SaxonOA/productAction!toList.action"}
];

$(function() {
	$.fn.zTree.init($("#menuTree"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("menuTree");
	
	//中间部分tab
	$('#tabs').tabs({  
		border:false,
		fit:true,
		onSelect: function(title, index){
			var treeNode = zTree.getNodeByParam("name", title, null);
			zTree.selectNode(treeNode);
		}
	}); 
	
	$('.index_panel').panel({  
	  width:300,  
	  height:200,  
	  closable:true,
	  minimizable:true,
	  title: '主面板'
	});
	
});

//添加一个选项卡面板 
function addTabs(title, url, icon){
	if(!$('#tabs').tabs('exists', title)){
		$('#tabs').tabs('add',{  
			title:title,  
			content:'<iframe src="'+url+'" frameBorder="0" border="0" scrolling="no" style="width: 100%; height: 100%;"/>',  
			closable:true
		});
	} else {
		$('#tabs').tabs('select', title);
	}
}