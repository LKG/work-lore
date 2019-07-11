define(function (require, exports, moudles) {
     require('jquery');
    var template = require('arttemplate');
    require('art-dialog');
    debugger;
    var $baseRoot=$("#baseRoot");
    var $httpUtil = require('/js/common/httpUtil.js');
    var $docSupportContainer=$("#doc-support-container");
    require('printThis');
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
        var $row=$('#article-row');
        $('#article-row').printThis({
            importCSS: true,
        });
    });
    $(".artinfo .cgray").on("click", function() {
        var font=$(this).attr("data-font");
        $("#article-content-dev").css("font-size",font+"px");;
    });
});