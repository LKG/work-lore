<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>关于我们 - <@spring.message  code="label.system.name" /></title>
  <link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
  <#include "/includes/head.ftl" />
<#assign template="contact"/>
</head> 
<body class="page-header-fixed">
<!-- toolbar begin-->
<#include "/index-toolbar.ftl"/>
<!-- toolbar end-->
<!-----------------------------------------顶部-------------->
<!-- header begin-->
<#include "/index-header.ftl" />
<!-- header end-->
<div class="clear"></div>
<!--轮播图上方导航栏  一栏-->
<div class="clearfix" ></div>
	<div class="container" style="margin-top: 35px" id="main-container">
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
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h2>联系我们</h2>
                    </div>
                    <div class="panel-body">
                        <p>
                            如您有网站相关问题，可以采取以下方式反馈：
                            1、发送邮件至service_reply@gongwk.com；
                            为了更好地帮助您解决问题，请您将问题清楚描述，并附上账户名及相关截图，谢谢。
                        </p>
                        <div class="alert alert-info" role="alert">
                            注：
                            1. 邮箱回复时间：我们将于七个工作日内回复相关问题，高级会员的问题将于两个工作日内回复。
                        </div>
                    </div>
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