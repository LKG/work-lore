define(function (require, exports, moudles) {
    var $ = require('jquery');
    var jQuery = require('jquery');
    // var dialog = require('art-dialog');
    var dialog = require('art-dialog');
    debugger;
    var $baseRoot=$("#baseRoot");
    window.dialog = dialog;
    var baseRoot=$baseRoot.attr("href");
    var $httpUtil = require('/js/common/httpUtil.js');
    var $msg= require('/js/common/alerts.js');
    $msg.alert("<i class='fa fa-exclamation-triangle text-danger'></i> 请输入关键词",false);
    $("#qt").val($("#qt").attr("data-val"));
    $("#qu").val($("#qu").attr("data-val"));
    $("#btn-search").on("click",function () {
        var param = $("#search-form").serialize();
        var qu= $("#search-form #qu").val();
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