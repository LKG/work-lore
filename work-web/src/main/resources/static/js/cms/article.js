define(function (require, exports, moudles) {
    require('jquery');
    ;
    var template = require('arttemplate');
    require('art-dialog');
    var $baseRoot=$("#baseRoot");
    var laypage = require('laypage');
    window.dialog = dialog;
    var baseRoot=$baseRoot.attr("href");
    var $httpUtil = require('/js/common/httpUtil.js');
    //TODO：后期考虑公用全局配置
    var $docSupportContainer=$("#doc-support-container");
    var url = {
        api : baseRoot + "/article",
    };
    $("#xx,#xx a").on("click",function () {
        $("#top-banner").hide();
    });
    $("#refresh,#seach-btn").on("click", function() {
        $("#page").val(1);
        var categoryId=$("#main-container").attr("data-categoryId");
        window.location.href=url.api+"s/c-"+categoryId+"-1.jhtml";
    });
    var $pagination=$("#table-pagination");
    laypage({
        cont : $pagination, // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div	// id="page1"></div>
        pages : $pagination.attr('data-totalPages'), // 通过后台拿到的总页数
        curr : Number($pagination.attr('data-number')) + 1, // 初始化当前页
        last:false,
        skip : true,
        skin : '#AF0000',
        jump : function(obj, first) { // 触发分页后的回调
            if (!first) {
                var curr = obj.curr;
                $("#page").val(obj.curr);
                var categoryId=$("#main-container").attr("data-categoryId");
                window.location.href=url.api+"s/c-"+categoryId+"-"+obj.curr+".jhtml";
            }
        }
    });
});