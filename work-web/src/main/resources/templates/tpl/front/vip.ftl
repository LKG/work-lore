<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title>加入Vip - <@spring.message  code="label.system.name" /></title>
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
<main class="bs-docs-masthead" id="content" tabindex="-1">
    <div class="container">

    </div>
</main>
<div class="container" style="margin-top: 35px"  id="main-container">
    <div class="row">
        <div class="col-xs-12" >
            <div class="row" style="height:360px;padding: 10px 10px;">
                <div class="panel panel-info">
                    <div class="panel-heading">加入Vip</div>
                    <div class="panel-body">

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