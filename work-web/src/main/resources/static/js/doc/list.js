define(function (require, exports, moudles) {
	var $ = require('jquery');
	var jQuery = require('jquery');
    var template = require('arttemplate');
    var dialog = require('artDialog');
	var $baseRoot=$("#baseRoot");
    var laypage = require('laypage');
    window.dialog = dialog;
	var baseRoot=$baseRoot.attr("href");
    var $httpUtil = require('/js/common/httpUtil.js');
    var lazyload = require('../../modules/lazyload/lazyload.min.js');
	//TODO：后期考虑公用全局配置
	 var $docSupportContainer=$("#doc-support-container");
    var url = {
        api : baseRoot + "/doc",
    };
    $("#refresh,#seach-btn").on("click", function() {
        $("#page").val(1);
        window.location.href=url.api+"s/p-1.jhtml";
    });
    var $pagination=$("#table-pagination");
    laypage({
        cont : $pagination, // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div	// id="page1"></div>
        pages : $pagination.attr('data-totalPages'), // 通过后台拿到的总页数
        curr : Number($pagination.attr('data-number')) + 1, // 初始化当前页
        skip : true,
        skin : '#AF0000',
        jump : function(obj, first) { // 触发分页后的回调
            if (!first) {
                var curr = obj.curr;
                $("#page").val(obj.curr);
                window.location.href=url.api+"s/p-"+obj.curr+".jhtml";
            }
        }
    });
    $("img.lazy").lazyload({
        effect : "fadeIn"
    });
    $("img.lazy").lazyload({
        // placeholder,值为某一图片路径.此图片用来占据将要加载的图片的位置,待图片加载时,占位图则会隐藏
        effect: "fadeIn", // 载入使用何种效果
        // effect(特效),值有show(直接显示),fadeIn(淡入),slideDown(下拉)等,常用fadeIn
        threshold: 200, // 提前开始加载
        // threshold,值为数字,代表页面高度.如设置为200,表示滚动条在离目标位置还有200的高度时就开始加载图片,可以做到不让用户察觉
        // event,值有click(点击),mouseover(鼠标划过),sporty(运动的),foobar(…).可以实现鼠标莫过或点击图片才开始加载,后两个值未测试…
        failurelimit : 10 // 图片排序混乱时
        // failurelimit,值为数字.lazyload默认在找到第一张不在可见区域里的图片时则不再继续加载,但当HTML容器混乱的时候可能出现可见区域内图片并没加载出来的情况,failurelimit意在加载N张可见区域外的图片,以避免出现这个问题.
    });
});