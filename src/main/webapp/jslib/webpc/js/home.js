// navigation
var liList= $("ul.nav_list>li:not('.none')"); 
 var currentLink = document.location.href;
 var linkss=$("ul.nav_list>li:not('.one')>a");
  for(var i=0;i<liList.length;i++){
     if(currentLink.indexOf(linkss[i])!=-1) {
        $(liList[i]).addClass("current");
        }
     $(liList[i]).bind("mouseover", {inum:i,othis:liList[i]},function(params){
    var mm = params.data.inum;
    var othiSrc = params.data.othis;
    $(othiSrc).addClass("current"); 
    $("#apDiv"+mm).show();  
});
$(liList[i]).bind("mouseout", {inum:i,othis:liList[i]},function(params){ 
    var mm = params.data.inum;
    var othiSrc = params.data.othis;
    if(currentLink.indexOf(linkss[mm])!=-1) {
      $(othiSrc).addClass("current");
       }
       else {
    $(othiSrc).removeClass("current"); 
    }
   $("#apDiv"+mm).hide();
   })
 }





 // banner

$(document).ready(function() {
    var m = false;
    var i = "";
    var e = 500;
    var j = document.body.offsetWidth;
    var d = $("#actor li").length;
    var b = d * 30;
    var o = (j - (b + 26)) / 2;
    var h = 0;
   $("#imgPlay").width(j);
   $("#imgPlay li").width(j);
    $("#actor").width(j * d);
    $("#actor li").each(function(c) {
        i += "<span>" + (c + 1) + "</span>"
    });
    //$("#numInner").width(b).html(i);
    $("#imgPlay .mc").width(b);
    $("#numInner span:first").addClass("on");
    function l(n, c) {
        n = $(n) ? $(n) : n;
        n.addClass(c).siblings().removeClass(c)
    }
    $("#imgPlay .next").click(function() {
        g(1)
    });
    $("#imgPlay .prev").click(function() {
        g( - 1)
    });
    function g(c) {
        if ($("#actor").is(":animated") == false) {
            h += c;
            if (h != -1 && h != d) {
                $("#actor").animate({
                    marginLeft: -h * j + "px"
                },
                e)
            } else {
                if (h == -1) {
                    h = d - 1;
                    $("#actor").css({
                        marginLeft: -(j * (h - 1)) + "px"
                    });
                    $("#actor").animate({
                        marginLeft: -(j * h) + "px"
                    },
                    e)
                } else {
                    if (h == d) {
                        h = 0;
                        $("#actor").css({
                            marginLeft: -j + "px"
                        });
                        $("#actor").animate({
                            marginLeft: 0 + "px"
                        },
                        e)
                    }
                }
            }
            l($("#numInner span").eq(h), "on")
        }
    }
    $("#numInner span").click(function() {
        h = $(this).index();
        f(h);
        l($("#numInner span").eq(h), "on")
    });
    function f(c) {
        if ($("#actor").css("marginLeft") != -c * j + "px") {
            $("#actor").css("marginLeft", -c * j + "px");
            $("#actor").fadeOut(0,
            function() {
                $("#actor").fadeIn(500)
            })
        }
    }
    function a() {
        m = setInterval(function() {
            g(1)
        },
        5000)
    }
    function k() {
        if (m) {
            clearInterval(m)
        }
    }
    $("#imgPlay").hover(function() {
        k()
    },
    function() {
        a()
    });
    a()
});





// tabchange

/*switchTab(选项卡标题ID,选项内容总ID,事件【mouseover/click】,出现状态【show/slideDown/fadeIn】,默认显示第几个【0/1/2】)*/
function switchTab(tabid,tabbox,events,effect,num){
var n=num;
$(tabid+">li").eq(n).attr("class","current").siblings(":not(.none)").attr("class","normal");
$(tabbox+">div").eq(n).show().siblings().hide();
$(tabid+" li:not(.none)").bind(events,function(){
$(this).attr("class","current").siblings(":not(.none)").attr("class","normal");
$n=$(tabid+">li").index($(this)[0]);    
switch(effect){
case "slide":
$(tabbox+">div").eq($n).slideDown().siblings().hide();
case "show":
$(tabbox+">div").eq($n).show().siblings().hide();
case "fadeIn":
$(tabbox+">div").eq($n).fadeIn().siblings().hide();
default:
$(tabbox+">div").eq($n).show().siblings().hide();
}
})
}


//scroll


