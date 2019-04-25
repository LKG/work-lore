<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>版权申诉 - <@spring.message  code="label.system.name" /></title>
  <#include "/includes/head.ftl" />
    <link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
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
<!-- banner 广告 begin-->
<#include "/index-top-banner.ftl" />
<!-- banner 广告 end-->
<!-- 搜索框 begin-->
<#include "/index-top-search.ftl" />
<!-- 搜索框 end-->
<!--轮播图上方导航栏  一栏-->
<#include "/index-nav-top.ftl" />
<div class="clear"></div>
<!--轮播图上方导航栏  一栏-->
<div class="clearfix" ></div>
<div class="container" style="margin-top: 35px" id="main-container">
    <div class="panel panel-info">
        <div class="panel-body">
            <form>
                <div class="form-group">
                    <label for="exampleInputEmail1">申诉资源信息：</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">版权申诉理由：</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="exampleInputFile">申诉证据文件：</label>
                    <input type="file" id="exampleInputFile">
                    <p class="help-block">Example block-level help text here.</p>
                </div>
                <button type="submit" class="btn btn-default"><@spring.message  code="label.default.button.confirm" /></button>
                <button type="submit" class="btn btn-default"><@spring.message  code="label.default.button.cancel" /></button>
            </form>
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
<script>
    seajs.use(["js/search.js?v="+Math.random(),"js/group.js?v="+Math.random()]);
</script>
</body>
</html>