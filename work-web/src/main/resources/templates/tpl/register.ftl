<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title><@spring.message  code="label.register.title" /> - <@spring.message  code="label.system.name" /></title>
  <#include "/includes/head.ftl" />
<style>
.text-danger{
	color: #a94442 !important;
}
  .main-container{
   	padding:0px 100px;
   	margin-top: 40px;
   	height:583px;
   }
    #quick-regist-btn{
		margin-top: -7px;
	}

</style>
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
<!------页面header信息 end----->
	<div class="container main-container" id="register-container">
		<div class="panel panel-default">
			<div class="panel-heading">	
				<p>
				<@spring.message  code="label.register.title" />
				<button type="button"  id="quick-regist-btn" class="btn btn-danger pull-right">
					<i class="fa fa-heart fa-lg"></i> 
					手机号码快速注册
				</button>
				</p>	
			</div>
	  		<div class="panel-body">
		  		<div class="row ">
				  <div class="col-sm-12 col-md-12 col-xs-12">
					<#include "/pages/register-in.ftl" />
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