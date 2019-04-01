define(function (require, exports, moudles) {
    var $ = require('jquery');
    var jQuery = require('jquery');
    var dialog = require('artDialog');
    var laypage = require('laypage');
    window.dialog = dialog;
    $("#xx,#xx a").on("click",function () {
        $("#top-banner").hide();
    });
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    };
    function replaceParamVal(paramName,replaceWith) {
        var oUrl = this.location.href.toString();
        var re=eval('/('+ paramName+'=)([^&]*)/gi');
        if(null==GetQueryString(paramName)){
            oUrl +="&page=";
        };
        var nUrl = oUrl.replace(re,paramName+'='+replaceWith);
        this.location = nUrl;
        window.location.href=nUrl
    }
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
                $("#page").val(curr);
                model = dialog({content: '<i class="fa fa-spinner fa-pulse fa-spin"></i>服务器正在处理中。。。',});
                model.showModal();
                replaceParamVal("page",curr);
            }
        }
    });
});