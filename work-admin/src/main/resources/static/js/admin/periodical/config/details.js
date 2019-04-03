define(function(require, exports, moudles) {
	var $ = require('jquery');
	var template = require('arttemplate');
	var dialog = require('artDialog');
	require('validate');
	window.dialog = dialog;
	var httpUtil = require('js/common/httpUtil.js');
	var $baseRoot = $('#baseRoot');
	var baseRoot = $baseRoot.attr("href");
	var url = {
		api : baseRoot + "/admin/periodical/config",
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
	var validator = $("#main-form").validate({
			rules: {
				year: {
					 required: true,
					 minlength: 4
				},
				name: {
					 required: true,
				},
				cityIds: {
					 required: true,
				},
				
			},
			messages:{
				year: {
					 required: "期刊年份不能为空",
				},
				periodicalName: {
					 required: "期刊名称不能为空",
				},
				cityIds: {
					 required: "期刊包含地市不能为空",
				},
			},
		});
	$("#main-btn-save").on("click",function(){
		var $form=$('#main-form');
		var $btn=$(this);
		var valid=$form.valid();
		if(!valid){
			return;
		}
		var cityId=$("#areaId").val();
        if(null==cityId||cityId==""){
         	cityId=$("#cityId").val();
        }
		$("#hideCityId").val(cityId);
		var dataHtml=$(this).html();
		var action=url.api+"/save";
		$form.attr("action",action);
		$btn.html($btn.attr("data-loading-text")).attr("disabled","disabled");
		var options = {
			url:action,
			type:"post",
		//	resetForm : true,    // 成功提交后，重置所有表单元素的值
			success: function(data) {
				$btn.html(dataHtml).removeAttr("disabled");
				var mdoel=dialog.getCurrent();
				mdoel.content(data);
		}};
		$form.ajaxSubmit(options); 
	});
});