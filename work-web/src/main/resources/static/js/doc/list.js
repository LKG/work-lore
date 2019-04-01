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
	//TODO：后期考虑公用全局配置
	 var $docSupportContainer=$("#doc-support-container");
    var url = {
        api : baseRoot + "/doc",
    };
    $("#refresh,#seach-btn").on("click", function() {
        $("#page").val(1);
        search(true);
    });
    var search = function(loading) {
        $("#btSelectAll").removeAttr("checked");
        var param = $("#search_form").serialize();
        $.httpUtil.curl({
            url : url.api + "s.json",
            type : "get",
            dataType : "json",
            loading : loading,
            data : param,
            success : function(data) {
                if (data.success) {
                    var html = template('tr-template-js', data.result);
                    $("#paginationTotal").html(data.result.totalElements);
                    var $tbody = $("#table-tbody");
                    $tbody.empty();
                    $tbody.append(html);
                    // 加载分页组件
                    laypage({
                        cont : 'table-pagination', // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div	// id="page1"></div>
                        pages : data.result.totalPages, // 通过后台拿到的总页数
                        curr : Number(data.result.number) + 1, // 初始化当前页
                        skip : true,
                        skin : '#AF0000',
                        jump : function(obj, first) { // 触发分页后的回调
                            if (!first) {
                                var curr = obj.curr;
                                $("#page").val(obj.curr);
                                search(false);
                            }
                        }
                    });
                }
            }
        });
    }
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
                search(false);
            }
        }
    });
});