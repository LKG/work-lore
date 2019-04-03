<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>菜单资源-新增</title>
  <#include "/includes/head.ftl" />
  <link rel="stylesheet" type="text/css" href="${appHost}/static/css/style.css" />
  <#include "/includes/zTree-css.ftl" />
<style>
</style>
</head> 
<body class="page-header-fixed">
	<div class="clearfix"></div>
	<div class="container-fluid">
	   <div class="row">
	   	 <div class="col-xs-12">
	   	 <@shiro.principal property='nickName'/>
			<#include "/admin/frameNotice/pages/from.ftl" />
		 </div>
	   </div>
	  </div>
  <!------seajs.config 引用信息 begin----->
  	<#include "/includes/seajs.config.ftl" />
  	<!------seajs.config   引用信息 end----->
<script>
  	seajs.use("js/notice/details");
</script>
 </body>
</html>