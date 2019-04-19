define(function (require, exports, moudles) {
    var $ = require('jquery');
    var jQuery = require('jquery');
    var dialog = require('artDialog');
    var $baseRoot=$("#baseRoot");
    window.dialog = dialog;
    var baseRoot=$baseRoot.attr("href");
    var $httpUtil = require('/js/common/httpUtil.js');
    var $msg= require('/js/common/alerts.js');
    $("#qt").val($("#qt").attr("data-val"));
    var qu= $("#qu").val();

    $("#btn-search").on("click",function () {
        var param = $("#search-form").serialize();
        var q=$("#search-form #q").val();
        var qt=$("#qt").val();
        if(!q||""==q){
            $msg.alert(q,"<i class='fa fa-exclamation-triangle text-danger'></i> 请输入关键词",false);
            return
        }
        $msg.alert(q,"<i class='fa fa-spinner fa-pulse fa-spin text-success'></i> 搜索中...",true,true);
        window.location.href="/"+qu+"?v=1&"+param;
    });
});