// JavaScript Document
$(function(){
	//根据总页数判断，如果小于5页，则显示所有页数，如果大于5页，则显示5页。根据当前点击的页数生成
	
	var pageCount = 11;//模拟后台总页数
	//生成分页按钮
	if(pageCount>5){
		page_icon(1,5,0);
	}else{
		page_icon(1,pageCount,0);
	}
	//点击分页按钮触发
/*	$("#pageGro li").live("click",function(){
		if(pageCount > 5){
			var pageNum = parseInt($(this).html());//获取当前页数
			pageGroup(pageNum,pageCount);
		}else{
			$(this).addClass("on");
			$(this).siblings("li").removeClass("on");
		}
	});*/

$("#pageGro").on("click","li",function(){
		if(pageCount > 5){
			var pageNum = parseInt($(this).html());//获取当前页数
			pageGroup(pageNum,pageCount);
			test()
		}else{
			$(this).addClass("on");
			$(this).siblings("li").removeClass("on");
		}
});



	//点击上一页触发
	$("#pageGro .pageUp").click(function(){

		if(pageCount > 5){
			var pageNum = parseInt($("#pageGro li.on").html());//获取当前页
			pageUp(pageNum,pageCount);
			test()
		}else{
			var index = $("#pageGro ul li.on").index();//获取当前页
			if(index > 0){
				$("#pageGro li").removeClass("on");//清除所有选中
				$("#pageGro ul li").eq(index-1).addClass("on");//选中上一页
			}
		}
	});
	
	//点击下一页触发
	$("#pageGro .pageDown").click(function(){
		if(pageCount > 5){
			var pageNum = parseInt($("#pageGro li.on").html());//获取当前页
			pageDown(pageNum,pageCount);
			test()
		}else{
			var index = $("#pageGro ul li.on").index();//获取当前页
			if(index+1 < pageCount){
				$("#pageGro li").removeClass("on");//清除所有选中
				$("#pageGro ul li").eq(index+1).addClass("on");//选中上一页
			}
		}
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





function test(){
	
	$.ajax({
		url : '/SaxonOA/webCommonAction!searchCommon.action',
		data : {
			page:$("#pageGro li.on").html(),
			rows:"5",
			sort:"createTime",
			order:"desc",
			uuid :'b7620863-ef48-491f-bce2-e1f7d307a175'
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
					html = "<li><a href=\"/SaxonOA/webCommonAction!toCommonInfo.action?uuid="+val.uuid+"\" class=\"clearfix\"><span>["+val.createTime+"]</span>"+val.title + "</a></li>";
					}else{
					html+="<li><a href=\"/SaxonOA/webCommonAction!toCommonInfo.action?uuid="+val.uuid+"\" class=\"clearfix\"><span>["+val.createTime+"]</span>"+val.title + "</a></li>";
					}
			}
		
		var testjs = $("#testjs");	
	     testjs.html(html);
   			
				
				
				});
		}
	});
	
	
}



