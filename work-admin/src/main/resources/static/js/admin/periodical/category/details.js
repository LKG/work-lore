define(function (require, exports, moudles) {
	require('jquery');
	var template = require('template');
	 require('validate');
	 require('js/common/from');
	 require('art-dialog');
	 var $baseRoot=$("#baseRoot");
	window.dialog = dialog;
	var $msg= require('/js/common/alerts.js');
	 var baseRoot=$baseRoot.attr("href");
	 var url={
		checkCode: baseRoot+"/admin/material/category/checkCode.json",
		create: baseRoot+"/admin/material/category/subGeneral.json",
		api: baseRoot+"/admin/material/category",
	 };
	 var $mainForm=$("#J_MainForm");
	 $mainForm.validate({
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
	                    	 customCode: function () {
	                             return $mainForm.find("#categoryCode").val();
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
			
			},
			submitHandler: function(form) { 
//				form.action=url.createArea ;
//				form.submit();
			}
		});
	 $mainForm.on("click", ".btn-add-child",function() {
			var dataId = $(this).attr("data");
			var id = $(this).attr("id");
			$.httpUtil.curl({
				url : url.api  +"/"+dataId+"/add.jhtml",
				loading : false,
				success : function(data) {
					dialog({
						id : id,
						title : "添加子节点",
						cancelValue : '取消',
						bodyClass : "ui-dialog-body-min",
						height : "400",
						width : "550",
						content:data,
						onclose: function () {
						
						},
					}).showModal();
				}
			});
		 });
	 $mainForm.on("click","#J_Submit",function(){
			var $btn=$(this);
			var dataHtml=$(this).html();
			$mainForm.attr("action",url.create);
			$btn.html($btn.attr("data-loading-text")).attr("disabled","disabled");
			$mainForm.ajaxSubmit({
				url:url.create,
				type:"post",
				success: function(data) {
					if(data.success){
						$msg.alert(($btn,"<i class='fa  fa-check-circle text-success'></i>数据更新成功");
					}else{
						$msg.alert(($btn,"<i class='fa fa-exclamation-triangle text-danger'></i>数据更新异常");
					}
					
				}
			}); 
			$btn.html(dataHtml).removeAttr("disabled");
		});
});