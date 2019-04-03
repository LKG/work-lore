<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>结果页面</title>
<link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
<!------head 引用信息 begin----->
<#include "/includes/head.ftl" />
<!------head   引用信息 end----->
</head>
<body>
  <!--[if lte IE 8]>
  <p class="browsehappy">你正在使用<strong>过时</strong>的浏览器， 暂不支持。 请 <a
	  href="http://browsehappy.com/" target="_blank">升级浏览器</a>
	  以获得更好的体验！</p>
  <![endif]-->
<!------页面header信息 begin----->
<#include "/index-header.ftl" />
	<div class="clearfix"></div>
    <div class="container main-container" tyle="min-height:500px;" >
    	<div class="row">
    	<div class="col-xs-12">
		  <!--用户面板 begin--->
		  	<div class="panel panel-default user-panel">
			  <div class="panel-body">
			     <!--userInfo begin---->
	 			    <div class="alert alert-danger alert-dismissible fade in" role="alert">
				      <p>
						  <#if result??>
						<div class="alert alert-warning alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<strong>Warning!</strong> ${result.error_description!''}
						</div>
						  </#if>
						  ${message!''}
					  </p>
				    </div>
	 	 		 <!--userInfo end---->
			  </div>
			  <div class="panel-footer">
			  	
			  </div>
			</div>
		  <!--用户面板 end--->
		  
		  </div>
    	</div>
    </div>
<!------页面header信息 end----->
<!------页面版权信息 begin----->
<#include "/includes/footer.ftl" />

<!------页面版权信息 end----->
</body>
