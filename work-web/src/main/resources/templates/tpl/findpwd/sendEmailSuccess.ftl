<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>找回密码 - <@spring.message  code="label.system.name" /></title>
  <link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
  <#include "/includes/head.ftl" />
<style>
 #forgot_process span.active{
  	  background: #fda6a3;
	  color: #fff;
  }
   #forgot_process{
   	width: 100%  !important;
   	margin-bottom: 50px;
   }
  .main-container{
   	padding:0px 100px;
   	margin-top: 40px;
   	height:583px;
   }
  #forgot_process span{
  	width: 25%  !important;
  	border:1px solid transparent;
  	border-color: #ddd;
  }
</style>
</head> 
<body class="page-header-fixed">
	<!------页面header信息 begin----->
	<#include "/index-header.ftl" />
	<!------页面header信息 end----->
	<div class="container main-container" id="register-container">
		<div class="panel panel-default">
			<div class="panel-heading">找回密码</div>
	  		<div class="panel-body">
	  			<div class="btn-group" id="forgot_process">
					 <span class="btn " ><i class="fa fa-pencil-square-o"></i>填写账户名</span>
					 <span class="btn active" ><i class="fa fa-anchor"></i>验证身份</span>
					 <span class="btn " ><i class="fa fa-paypal"></i>设置新密码</span>
					 <span class="btn " ><i class="fa fa-check"></i>完成</span>
				</div>
		  		<div class="row ">
				  <div class="col-sm-12 col-md-12 col-xs-12">
					  <span class="fa-stack fa-lg">
					  <i class="fa fa-circle fa-stack-2x"></i>
					  <i class="fa fa-check fa-stack-1x fa-inverse "></i>
					</span>
					验证邮件已发送成功！:<code>  （请立即完成验证，邮箱验证不通过则修改失败） </code>，验证邮件24小时内有效，请尽快登录您的邮箱点击验证链接完成验证。 
					 <a class="btn btn-success btn-lg btn-block " <#if emailServer??> href="http://mail.${emailServer}"  </#if> id="" target="_blank" type="button">查看验证邮件</a>
				 </div>
				</div>
	  		</div>
  		</div>
	</div>
   	<!------footer信息 begin----->
  	<#include "/includes/footer.ftl" />
  	<#include "/includes/footer-js.ftl" />
  	<!------footer信息 end----->
 </body>
</html>