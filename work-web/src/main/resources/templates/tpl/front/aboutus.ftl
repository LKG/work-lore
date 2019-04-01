<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title> 关于我们 - <@spring.message  code="label.system.name" /></title>
  <link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
  <#include "/includes/head.ftl" />
<#assign template="aboutus"/>
</head> 
<body class="page-header-fixed">
<!-- toolbar begin-->
<#include "/index-toolbar.ftl"/>
<!-- toolbar end-->
<!-----------------------------------------顶部-------------->
<!-- header begin-->
<#include "/index-header.ftl" />
	<div class="container" style="margin-top: 35px"  id="main-container">
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
			   <div class="row" style="height:360px;padding: 10px 10px;">
				    <object id="myFlash" name="myFlash" width="100%" height="100%" align="top" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" classid="clsid:001">
	                    <param value="http://yuntv.letv.com/bcloud.swf?uu=82abd5491f&amp;vu=676313e793&amp;pu=ae87902567&amp;auto_play=0&amp;width=600&amp;height=660" name="movie">
	                    <param value="high" name="quality">
	                    <param value="transparent" name="wmode">
	                    <param value="true" name="allowFullScreen">
	                    <param value="never" name="allowScriptAccess">
	                    <embed width="100%" height="100%" align="top" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" allowscriptaccess="never" allowfullscreen="true" wmode="transparent" name="myFlash" swliveconnect="true" quality="high" src="http://i7.imgs.letv.com/player/swfPlayer.swf?id=23178926&amp;autoplay=0&amp;width=640&amp;height=660" id="myFlash" allownetworking="all" title="Adobe Flash Player">
	       			</object>
				</div>
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