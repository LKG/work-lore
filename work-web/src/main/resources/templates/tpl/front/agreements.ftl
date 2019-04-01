<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>服务协议 - <@spring.message  code="label.system.name" /></title>
  <link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
  <#include "/includes/head.ftl" />
<#assign template="agreements"/>
</head> 
<body class="page-header-fixed">
<!-- toolbar begin-->
<#include "/index-toolbar.ftl"/>
<!-- toolbar end-->
<!-----------------------------------------顶部-------------->
<!-- header begin-->
<#include "/index-header.ftl" />
	<div class="container"  style="margin-top: 35px" id="main-container">
		<div class="row">
			<div class="col-xs-3">
				<div class="panel panel-default" id="user-info-panel" >
			  <div class="panel-heading" id="user_<@shiro.principal property='userId' />">
			  	
			  </div>
			  <div class="panel-body">
			   	
			  </div>
			<#include "/front/left-menu.ftl" />
			</div>
			</div>
			<div class="col-xs-9" >
					<#include "/front/agreement-reg.ftl" />
			</div>
		</div>
		
	
	</div>
   	<!------footer信息 begin----->
  	<#include "/includes/footer.ftl" />
  	<#include "/includes/footer-js.ftl" />
  	<!------footer信息 end----->
<!------seajs.config 引用信息 begin----->
<#include "/includes/seajs.config.ftl" />
<!------seajs.config   引用信息 end----->

</body>
</html>