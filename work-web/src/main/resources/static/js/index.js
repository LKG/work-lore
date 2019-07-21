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
//**************服装鞋包选项卡*******************
$(function(){
    $('.tab li').on({
            mouseover: function(){
                $(this).addClass('active').siblings().removeClass('active');
                $('.tatab-item span').css('right','1px');
                var index=$(this).index();
                $('.main').eq(index).show().siblings().hide();
                $('.side').css('display','block');
            }
        }
    );
});
$(function(){
    $('.panel .nav-tabs li').on("click mouseover",function () {
        $(this).addClass('active').siblings().removeClass('active');
        var index=$(this).index();
        $(this).parents('.panel').find('.tab-pane').eq(index).show().siblings().hide();
    });
});
;$(function(){
    var $menu = $(".dropdown-menu");
    $menu.menuAim({
        activate: activateSubmenu,
        deactivate: deactivateSubmenu,
        exitMenu: hideSubmenu ,
    });

    function hideSubmenu() {
        $(".popover").css("display", "none");
        $("a.maintainHover").removeClass("maintainHover");
    }

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
        hideSubmenu();
    });
});
