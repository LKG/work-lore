define(function (require, exports, moudles) {
    require('jquery');
    $(function(){
        $('.panel .nav-tabs li').on("click mouseover",function () {
            $(this).addClass('active').siblings().removeClass('active');
            var index=$(this).index();
            $(this).parents('.panel').find('.tab-pane').eq(index).show().siblings().hide();
        });
    });
});