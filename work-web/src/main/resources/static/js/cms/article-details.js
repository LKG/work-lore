define(function (require, exports, moudles) {
    var $ = require('jquery');
    var jQuery = require('jquery');
    var template = require('arttemplate');
    var dialog = require('artDialog');
    var $baseRoot=$("#baseRoot");
    var laypage = require('laypage');
    var printThis = require('printThis');
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
        search(true);
    });
    $("#btn-print").on("click", function() {
        $("#article-row").printThis();
    });
    $(".artinfo .cgray").on("click", function() {
        var font=$(this).attr("data-font");
        $("#article-content-dev").css("font-size",font+"px");;
    });
});