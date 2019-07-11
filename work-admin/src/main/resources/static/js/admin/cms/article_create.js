define(function(require, exports, moudles) {
	require('jquery');
	var $baseRoot = $('#baseRoot');
	var baseRoot = $baseRoot.attr("href");
	var template = require('arttemplate');
	var url = {
		api : baseRoot + "/admin/article",
		createApi:baseRoot + "/admin/article/save.json",
	};
	var $httpUtil = require('/js/common/httpUtil.js');
	var dialog = require('/js/common/dialog');
	 require('/js/common/validate');
	var $msg= require('/js/common/alerts.js');
	window.dialog = dialog;
	 var $mainForm=$("#J_articleForm");
	 $mainForm.validate({
			rules: {
				title: {
					 required: true,
				},
			},
			messages:{
				title: {
					 required: "标题不能为空",
				},
			
			},
			submitHandler: function(form) { 
			}
		});
	 $("#heading-btn-save,#footer-btn-save").on("click",function(){
		 if(!$mainForm.valid()){
			 return;
		 }
		 var md=testEditor.getMarkdown();       // 获取 Markdown 源码
		 if(md.length<=0){
			 $msg.alert($btn,"<i class='fa fa-exclamation-triangle text-danger'></i>文章内容不能为空");
			 return;
		 }
		 testEditor.getHTML();           // 获取 Textarea 保存的 HTML 源码
		 testEditor.getPreviewedHTML();  // 获取预览窗口里的 HTML，在开启 watch 且没有开启 saveHTMLToTextarea 时使用
		 var $btn=$(this);
			var dataHtml=$(this).html();
			$mainForm.attr("action",url.createApi);
			$btn.html($btn.attr("data-loading-text")).attr("disabled","disabled");
			$mainForm.ajaxSubmit({
				url:url.createApi,
				type:"post",
				success: function(data) {
					if(data.success){
						$msg.alert($btn,"<i class='fa  fa-check-circle text-success'></i>数据更新成功");
						setTimeout(function() {
							topdialog.close().remove();
						}, 500);
					}else{
						$msg.alert($btn,"<i class='fa fa-exclamation-triangle text-danger'></i>数据更新异常");
					}
					
				}
			}); 
			$btn.html(dataHtml).removeAttr("disabled");
		});
	
	
});