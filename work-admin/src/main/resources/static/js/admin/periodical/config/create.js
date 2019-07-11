define(function(require, exports, moudles) {
	require('jquery');
	var template = require('arttemplate');
	require('art-dialog');
	require('validate');
	window.dialog = dialog;
	var httpUtil = require('js/common/httpUtil.js');
	var $baseRoot = $('#baseRoot');
	var baseRoot = $baseRoot.attr("href");
	var url = {
		api : baseRoot + "/admin/periodical/config",
	};
	var $msg= require('/js/common/alerts.js');
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
        if(null==cityId||cityId==""){
         	cityId=$("#provinceId").val();
        }
		$("#hideCityId").val(cityId);
		var dataHtml=$(this).html();
		var action=url.api+"/save.json";
		$btn.html($btn.attr("data-loading-text")).attr("disabled","disabled");
		var options = {
			url:action,
			type:"post",
		//	resetForm : true,    // 成功提交后，重置所有表单元素的值
			success: function(json) {
				$btn.html(dataHtml).removeAttr("disabled");
				var content="添加成功！";
				 if(json.success){
					$form.resetForm();
					 $msg.alert($btn,content,true);
					setTimeout(function(){
						parent.dialog.getCurrent().close().remove();
					}, 1000);
					
				 }else{
					 content=json.result.error_description;
					 $msg.alert($btn,content);
				 }
		}};
		$form.ajaxSubmit(options); 
	});
});