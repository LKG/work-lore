define(function(require, exports, moudles) {
	require('jquery');
	var template = require('arttemplate');
	require('art-dialog');
	var fineUploader = require('fine-uploader');
	window.dialog = dialog;
    var $msg= require('/js/common/alerts.js');
	var httpUtil = require('js/common/httpUtil.js');
	var $baseRoot = $('#baseRoot');
	var baseRoot = $baseRoot.attr("href");
	var excelfineUploader= $('#fine-uploader-manual-trigger').fineUploader({
         template: 'qq-template-manual-trigger',
         request: {
             endpoint: baseRoot+'/upload/material.json',
             filenameParam: "filename",
         },
         thumbnails: {
             placeholders: {
                 waitingPath: 'https://static.gongwk.com/images/placeholders/waiting-generic.png',
                 notAvailablePath: 'https://static.gongwk.com/images/placeholders/not_available-generic.png'
             }
         },
         validation: {
             allowedExtensions: ['zip','doc','xlsx','xls','pdf','docx','ppt','pptx','txt'],
             itemLimit: 50,// 最多上传
             sizeLimit: 50*1024*1024*1024 // 50 kB = 50 * 1024 bytes
         },
          text: {
	            formatProgress: "{percent}% of {total_size}",
	            failUpload: "",
	            waitingForResponse: "处理中...",
	            paused: "暂停"
     	},
     	 messages: {
     		  typeError: "{file} 文件格式错误. 文件格式只能为(s): {extensions}.",
              sizeError: "{file} 文件过大,最大文件大小 为 {sizeLimit}.",
              minSizeError: "{file} 文件过小, 最小文件大小 为  {minSizeLimit}.",
              emptyError: "{file} 文件为空 , 请重新选择.",
              noFilesError: "请选择要上传的文件.",
              tooManyItemsError: "已有 ({netItems}) 正在处理中....  最大支持文件个数为：{itemLimit}.",
              maxHeightImageError: "Image is too tall.",
             maxWidthImageError: "Image is too wide.",
             minHeightImageError: "Image is not tall enough.",
             minWidthImageError: "Image is not wide enough.",
             retryFailTooManyItems: "Retry failed - you have reached your file limit.",
             onLeave: "The files are being uploaded, if you leave now the upload will be canceled.",
             unsupportedBrowserIos8Safari: "Unrecoverable error - this browser does not permit file uploading of any kind due to serious bugs in iOS8 Safari.  Please use iOS8 Chrome until Apple fixes these issues."
         },
         showMessage:function(content) {
         	//------------
        	// message($(this),content);
         },
         /***
         showConfirm:function(message) {
         	//------------
         }, 
         **/
         autoUpload: false ,
         callbacks: {
           onComplete: function(id, name, response) {
        	   var content="上传成功，解析任务已提交";
        	   if(response.success){
                   $msg.alert($(this),content);
        		   return ;
        	   }else{
        		   if(response.result &&response.result.error_description){
        			   content=response.result.error_description;
             	   }else{
             		  content="<i class='fa fa-exclamation-triangle text-danger'></i>上传失败";
             	   }
        	   }
               $msg.alert($(this),content);
             },
             onCancel: function(id, name, response) {
	             	//取消上传
             },
             onSubmit: function(id, name) {
            	// debugger;
             	//开始上传
             },
              onUpload: function(id, name, response) {
                  // var price=$("#price").val();
                  var finalPrice=$("#finalPrice").val();
                  var originPrice=$("#originPrice").val();
                  var periodicalCode=$("#periodicalCode").val();
                  var categoryId=$("#categoryId").val();
                  var categoryCode=$("#categoryCode").val();
                  var clearHeader=$("#clearHeader").is(':checked');
                  var params={
                      // price:price,
                      finalPrice:finalPrice,
                      originPrice:originPrice,
                      periodicalCode:periodicalCode,
                      categoryId:categoryId,
                      categoryCode:categoryCode,
                      clearHeader:clearHeader,
                  };
                  this.setParams(params);
             },
           
         },
     });
	
     $('#trigger-upload').click(function() {
         var periodicalCode=$("#periodicalCode").val();
         if(periodicalCode==null||periodicalCode.length!=6){
             $msg.alert($(this),"请选择期刊号");
             return;
         }

         var originPrice=$("#originPrice").val();
         if(originPrice==null||originPrice.length==0){
             $msg.alert($(this),"请设置标价");
             return;
         }
         var finalPrice=$("#finalPrice").val();
         if(finalPrice==null||finalPrice.length==0){
             $msg.alert($(this),"请设置售价");
             return;
         }
        $('#fine-uploader-manual-trigger').fineUploader('uploadStoredFiles');
     });
});