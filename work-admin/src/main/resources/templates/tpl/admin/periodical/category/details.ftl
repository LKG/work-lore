<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>分类信息-明细</title>
<style>
</style>
</head> 
<body class="page-header-fixed">
	<div class="clearfix"></div>
	<div class="container-fluid">
	   <div class="row">
	   	 <div class="col-xs-12">
			<#include "/admin/periodical/category/from.ftl" />
		 </div>
	   </div>
	  </div>
  <!------seajs.config 引用信息 begin----->
  	<#include "/includes/seajs.config.ftl" />
  	<!------seajs.config   引用信息 end----->
<script>
  	seajs.use("js/admin/periodical/category/details.js?v="+Math.random());
</script>
 </body>
</html>