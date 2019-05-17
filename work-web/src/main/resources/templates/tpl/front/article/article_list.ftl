<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title>专家专栏 - <@spring.message  code="label.system.name" /></title>
  <#include "/includes/head.ftl" />
    <link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
    <style>
        .hot-list .thumbnail {
            height: 265px;
        }
    </style>
<#assign template="topic"/>
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
<div class="container m-container"  >
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default hot-article" id="hot-article" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">
                    <i class="fa fa-th" style="margin-right: 10px"></i> 专家专栏
                </div>
                <div class="panel-body">
                    <div class="row">
                    <@cms.articleCategory parentId="2"  >
                       <#if cates??&&cates.content??>
                           <#list cates.content as model>

                                 <div class="col-md-3 hot-list">
                                     <div class="thumbnail">
                                         <a href="${appHost}/articles/c-${model.id!''}.jhtml" target="_blank" alt="专家专栏-${model.name!''}">
                                             <img src="${model.imageUrl!''}" alt="专家专栏-${model.name!''}">
                                         </a>
                                         <div class="caption">

                                             <h4><a href="${appHost}/articles/c-${model.id!''}.jhtml"  target="_blank" alt="专家专栏-${model.name!''}">${model.name!''}</a></h4>
                                         </div>
                                     </div>
                                 </div>
                           </#list>
                       </#if>
                    </@cms.articleCategory >
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
<script>
    seajs.use(["js/search.js?v="+Math.random()]);
</script>
</body>
</html>