// window.onload=function(){
// 	var oxx=document.getElementById('xx');
// 	var obanner=document.getElementById('top-banner');
// 	var otext=document.getElementById('textt');
// 	oxx.onmousedown=function(){
// 		obanner.style.display='none';
// 	};
// 	otext.onmousedown=function(){
// 		otext.value='';
// 		otext.style.color='rgb(51, 51, 51)';
// 	};
// }
$(function(){
				//手动控制轮播图
				$('#one li').eq(0).show();

				$('#two li').mouseover(function(){
					$(this).addClass('on').siblings().removeClass('on');
					var index=$(this).index();
					i=index;
					$('#one li').eq(index).stop().fadeIn(300).siblings().stop().fadeOut(300);
				})
				//自动播放
				var i=0;
				var t=setInterval(move,1500);
				//自动播放核心函数
				function move(){
					i++;
					if(i==5){
						i=0;
					}
					$('#two li').eq(i).addClass('on').siblings().removeClass('on');
					$('#one li').eq(i).fadeIn(300).siblings().fadeOut(300);
				}

				//向右播放核心函数
				function moveL(){
					i--;
					if(i==-1){
						i=4;
					}
					$('#two li').eq(i).addClass('on').siblings().removeClass('on');
					$('#one li').eq(i).fadeIn(300).siblings().fadeOut(300);
				}
				$('#left').click(function(){
						moveL();
				})
				$('#right').click(function(){
					move();
				})
				//鼠标移入移除
				$('#lunbo').hover(function(){
					clearInterval(t);
				},function(){
					t=setInterval(move,1500);
				})
})
//**************服装鞋包选项卡*******************
$(function(){
		$('.tab li').mouseover(function(){
			 $(this).addClass('active').siblings().removeClass('active');
			 $('.tatab-item span').css('right','1px');
			 var index=$(this).index();
             $('.main').eq(index).show().siblings().hide();
             $('.side').css('display','block');
        });
  		  
});

$(function(){
	$('.panel .nav-tabs li').on("click mouseover",function () {
		$(this).addClass('active').siblings().removeClass('active');
		var index=$(this).index();
            debugger;
        $(this).parents('.panel').find('.tab-pane').eq(index).show().siblings().hide();
	});
});
 $(function () {  
	//当点击跳转链接后，回到页面顶部位置
    $("#jdm-tbar-tab-top").click(function(){
        $('body,html').animate({scrollTop:0},2000);
           return false;
    });
 });
;$(function(){
    $('.counter-value').each(function(){
        $(this).prop('Counter',0).animate({
            Counter: $(this).text()
        },{
            duration: 3500,
            easing: 'swing',
            step: function (now){
                $(this).text(Math.ceil(now));
            }
        });
    });
    var $menu = $(".dropdown-menu");
    $menu.menuAim({
        activate: activateSubmenu,
        deactivate: deactivateSubmenu
    });
    function activateSubmenu(row) {
        var $row = $(row),
            submenuId = $row.data("submenuId"),
            $submenu = $("#" + submenuId),
            height = $menu.outerHeight(),
            width = $menu.outerWidth();

        // Show the submenu
        $submenu.css({
            display: "block",
            top: -1,
            left: width - 3,  // main should overlay submenu
            height: height - 4  // padding for main dropdown's arrow
        });
        // Keep the currently activated row's highlighted look
        $row.find("a").addClass("maintainHover");
    }

    function deactivateSubmenu(row) {
        var $row = $(row),
            submenuId = $row.data("submenuId"),
            $submenu = $("#" + submenuId);
        $submenu.css("display", "none");
        $row.find("a").removeClass("maintainHover");
    }
    $(".dropdown-menu li").click(function(e) {
        e.stopPropagation();
    });
    $(document).click(function() {
        $(".popover").css("display", "none");
        $("a.maintainHover").removeClass("maintainHover");
    });
});

