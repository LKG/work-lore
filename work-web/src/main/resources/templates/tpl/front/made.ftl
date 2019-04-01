<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>文章定制- <@spring.message  code="label.system.name" /></title>
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
<div class="container" style="margin-top: 30px;">
    <ol class="breadcrumb">
        <li><a href="${appHost}/"><@spring.message  code="label.system.index" /></a></li>
        <li class="active">文章定制</li>
    </ol>
</div>
<div class="container" style="margin-top: 35px" id="main-container">

    <div class="panel panel-info">
        <div class="">
            <ul id="my-tab-rule" class="myNavTab nav nav-tabs" role="tablist">
                <li role="presentation" style="cursor:pointer" class="active">
                    <a id="my-tab-all" data-key="">
                        全部
                    </a>
                </li>
                <li role="presentation" style="cursor:pointer" class="">
                    <a id="my-tab-1" data-key="">
                        党政
                    </a>
                </li>
                <#--<form class="form-inline">-->
                    <#--<div class="form-group">-->
                        <#--<input type="text" class="form-control" id="qqS" name="qqS" placeholder="关键词">-->
                    <#--</div>-->
                    <#--<button type="button" class="btn btn-default">搜索</button>-->
                <#--</form>-->
            </ul>
        </div>
        <div class="panel-body">
        <div class="row">
            <#if (result.content?size<=0) >
            <#else>
                <#list result.content as model>
                    <div class="col-sm-4 col-md-3">
                            <div class="thumbnail">
                                <img src="/images/qq_vip.jpg" style="width: 200px;" alt="...">
                                <div class="caption">
                                    <p>群号：<code>${model.qqNum!''}</code></p>
                                    <p>名称：${model.name!''} <code>lv${model.qqLevel!'1'}</code></p>
                                    <p>（<i class="fa fa-group"> </i> ${model.peopleTotal!'200'} / ${model.qqTotal!'1'}）</p>
                                </div>
                            </div>
                    </div>
                </#list >
            </#if>
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
<script>
    seajs.use(["js/search.js?v="+Math.random(),"js/group.js?v="+Math.random()]);
</script>
</body>
</html>