define(function(require, exports, moudles) {
	var $ = require('jquery');
	var $baseRoot = $('#baseRoot');
	var baseRoot = $baseRoot.attr("href");
	var template = require('arttemplate');
	var url = {
		api : baseRoot + "/userinfo/order",
	};
	var $httpUtil = require('/js/common/httpUtil.js');
    var $msg= require('/js/common/alerts.js');
	var dialog = require('/js/common/dialog');
	window.dialog = dialog;
	var laypage = require('laypage');
	var $tbody = $("#order-table-tbody");
	$(".page-size-sel").on("change", function() {
		$("#size").val($(this).val());
		$("#seach-btn").click();
	});
	$("#refresh,#seach-btn").on("click", function() {
        $("#order-page").val(1);
		search(true);
	});

    $("#my-tab-rule").on("click", "li", function(h) {
        var g=$(h.target).closest("a");
        if(!g||g.length==0){
            return;
        }
        if($(this).hasClass("active")){
            return;
        }else{
            //移除同级展开节点
            $(this).siblings("li").removeClass("active").removeClass("open");
            $(this).addClass("active");
        }
        $("#order-page").val(1);
        var datakey=$(this).find("a").data("key");
        $("#orderStatus").val(datakey);
        search(true);
    });
    // 修改事件
    $tbody.on("click", ".operate .btn-cancel",function() {
        var orderId = $(this).attr("data-orderId");
        var id = $(this).attr("id");
        var d = dialog({
            id:id,
            title : '消息',
            content : '是否要取消该订单？',
            okValue : '确 定',
            ok : function() {
                var that = this;
                that.title('提交中..');
                $.httpUtil.curl({
                    url : url.api +"/"  +orderId+"/cancel.json",
                    type : "post",
                    loading : true,
                    dataType : "json",
                    data : null,
                    success : function(data) {
                        var content="取消成功";
                        if(data.success){
                            $msg.alert($(this),content);
                            search(false);
                            return ;
                        }else{
                            if(data.result &&data.result.error_description){
                                content=data.result.error_description;
                            }else{
                                content="取消失败";
                            }
                        }
                        $msg.alert($(this),content);
                    },
                });
                return true;
            },
            cancelValue : '取消',
            cancel : function() {
            }
        }).showModal();
    });
	var search = function(loading) {
		$("#btSelectAll").removeAttr("checked");
		var param = $("#order_search_form").serialize();
		$.httpUtil.curl({url : url.api + "s.json",
			type : "get",
			dataType : "json",
			loading : loading,
			data : param,
			success : function(data) {
				var html = template('order-tr-template-js', data.result);
				$("#pagination-total").html(data.result.totalElements);
				$tbody.empty();
				$tbody.append(html);
				var $pagination=$("#table-pagination");
				laypage({
					cont : $pagination, // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div	// id="page1"></div>
					pages : data.result.totalPages, // 通过后台拿到的总页数
					curr : Number(data.result.number) + 1, // 初始化当前页
					skip : true,
					skin : '#AF0000',
					jump : function(obj, first) { // 触发分页后的回调
						if (!first) {
							var curr = obj.curr;
							$("#order-page").val(obj.curr);
							search(false);
						}
					}
				});
			}
		});
	};
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
                $("#order-page").val(obj.curr);
				search(false);
			}
		}
	});
	
});