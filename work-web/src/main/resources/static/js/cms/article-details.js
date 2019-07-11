define(function (require, exports, moudles) {
    var $ = require('jquery');
    var jQuery = require('jquery');
    var template = require('arttemplate');
    var dialog = require('artDialog');
    var $baseRoot=$("#baseRoot");
    var $httpUtil = require('/js/common/httpUtil.js');
    var $docSupportContainer=$("#doc-support-container");
    require('printThis');
    alert(jQuery);
    debugger;
    window.dialog = dialog;
    var baseRoot=$baseRoot.attr("href");

    //TODO：后期考虑公用全局配置

    var url = {
        api : baseRoot + "/article",
    };
    $("#xx,#xx a").on("click",function () {
        $("#top-banner").hide();
    });
    $("#btn-print").on("click", function() {
        $("#article-row").printThis();
    });
    $(".artinfo .cgray").on("click", function() {
        var font=$(this).attr("data-font");
        $("#article-content-dev").css("font-size",font+"px");;
    });
});