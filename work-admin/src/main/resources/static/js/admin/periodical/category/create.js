define(function (require, exports, moudles) {
	var $ = require('jquery');
	var template = require('template');
	 require('validate');
	 require('js/common/from');
	 var dialog = require('artDialog');
		window.dialog = dialog;
	 var $baseRoot=$("#baseRoot");
	 var baseRoot=$baseRoot.attr("href");
	 var url={
		checkCode: baseRoot+"/admin/material/category/checkCode.json",
		create: baseRoot+"/admin/material/category/subGeneral",
		api: baseRoot+"/admin/material/category",
	 };
	 var $J_CreateForm=$("#J_CreateForm");
	 $J_CreateForm.validate({
			rules: {
				categoryCode: {
					 required: true,
					 minlength: 3,
					 maxlength: 30,
					 isUid:true,//检验特殊字符
					 remote: {
						 url: url.checkCode,
	                     type: "get",               //数据发送方式
	                     dataType: "json",
	                     data: {
	                    	 categoryCode: function () {
	                             return $J_CreateForm.find("#categoryCode").val();
	                         }
	                     },dataFilter: function (data) {
	                    	 var json=$.parseJSON(data);
	                    	return ""+json.result;
	                     }
					 }
				},
				categoryName: {
					 required: true,
				},
				parentId: {
					 required: true,
				},
				parentName: {
					 required: true,
				}
			},
			messages:{
				categoryCode: {
					 required: "标识不能为空",
					 remote: "标识已经被使用",
				},
				categoryName: {
					 required: "名称不能为空",
				},
			},
			submitHandler: function(form) { 
//				form.action=url.createArea ;
//				form.submit();
			}
		});
	 
	 $J_CreateForm.on("click","#J_Submit",function(){
			var $btn=$(this);
			var dataHtml=$(this).html();
			$J_CreateForm.attr("action",url.create);
			$btn.html($btn.attr("data-loading-text")).attr("disabled","disabled");
			$J_CreateForm.ajaxSubmit({
				url:url.create,
				type:"post",
				success: function(data) {
					var mdoel=dialog.getCurrent();
					mdoel.content(data);
				}
			}); 
			$btn.html(dataHtml).removeAttr("disabled");
		});
	 
});