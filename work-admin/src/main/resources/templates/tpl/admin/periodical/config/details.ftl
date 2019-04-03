<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>材料信息价期刊配置-明细</title>
   <style>
  .table td{white-space:nowrap;overflow:hidden; text-overflow:ellipsis; } 
  .table  {table-layout:fixed;}  
  .table th{vertical-align:middle !important;text-align: center; font-size:14px;}
  .Wdate{
	  height: 32px !important;
	  padding: 3px 1px !important; 
	  border: 1px solid #ccc !important; 
	}
   .form-search{
   	margin-top: -8px;
   }
  .form-search select{
   	  padding: 6px 3px  !important; 
   }
	.form-inline .form-group,.form-inline .form-control{
		width: 100px;
		display: inline;
	}
	@media (max-width: 900px){
		.panel-heading{
			padding: 10px 0px !important; 
		}
	}
  </style>
</head> 
<body class="page-header-fixed">
	<div class="clearfix"></div>
	<div class="container-fluid">
	   <div class="row">
	   	 <div class="col-xs-12">
			<#include "/admin/periodical/config/from.ftl" />
		 </div>
	   </div>
	  </div>
  <!------seajs.config 引用信息 begin----->
  	<#include "/includes/seajs.config.ftl" />
  	<!------seajs.config   引用信息 end----->
<script>
  		seajs.use("js/admin/periodical/config/details.js?v="+Math.random());
</script>
 </body>
</html>