define(function(require, exports, moudles) {
	require('jquery');
	var template = require('template');
	require('art-dialog');
	var laypage = require('laypage');
	window.dialog = dialog;
	var httpUtil = require('js/common/httpUtil.js');
	var $baseRoot = $('#baseRoot');
	var baseRoot = $baseRoot.attr("href");
	var url = {
		api : baseRoot + "/admin/material/category",
	};
	var getAreas = require('js/common/areas.js');
	getAreas($("#provinceId"), "0");
	$("#provinceId").on("change", function() {
		var parentCode = $(this).val();
		getAreas($("#cityId"), parentCode, "");
		$("#areaId").empty();
	});
	getAreas($("#cityId"));// 
	$("#cityId").on("change", function() {
		var parentCode = $(this).val();
		getAreas($("#areaId"), parentCode, "");
	});
	var $msg= require('/js/common/alerts.js');
	// 新增事件
	var $add = $("#add");
	$add.on("click", function() {
		$msg.alert($(this));
	});
	var $remove = $("#remove");
	$remove.on("click", function() {
		$msg.alert($(this));
	});
	
	$("#refresh,#search-btn").on("click", function() {
		search(true);
	});
	setTimeout(function() {
		search(false);
	}, 10);
    var $tbody = $("#table-tbody");
    // 修改事件
    $tbody.on("click", ".operate .fa-chain",function() {
        var id = $(this).attr("data");
        var model = dialog({
            id : id,
            title : "添加规则",
            cancelValue : '取消',
            bodyClass : "ui-dialog-body-min",
            height : "650",
            width : "750",
            url : url.api  +"/"+ id+ "/rule.jhtml"
        });
        model.showModal();
    });

    // 全选事件
    $("#btSelectAll").on("change",function() {
        var $checkBox = $tbody.find("input[type='checkbox']:not(:disabled)");
        if ($(this).is(':checked')) {
            $checkBox.prop("checked",true);// 全选
            $("#remove").removeAttr("disabled");
        } else {
            $("#remove").attr("disabled","disabled");
            $checkBox.prop("checked",false);// 反选
        }
    });
	var search = function(loading) {
		$("#btSelectAll").prop("checked",false);
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
							laypage({
								cont : 'table-pagination2', // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div	// id="page1"></div>
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
});