<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <#include "/includes/head.ftl" />
    <title>${result.periodicalName!''}-<@spring.message  code="label.system.name" /></title>
    <meta name="title" content="${result.periodicalName!''}" />
    <meta content="${result.periodicalName!''}" itemprop="name" />
    <meta name="keywords" content="${result.seoKeywords!''}" />
    <meta name="description" itemprop="description" content="${result.seoDesc!''}" />
    <style>
        .tab-pane{
            padding-top:30px;
        }
        .panel-heading #btn-focus,.panel-heading #btn-cart,.panel-heading #btn-vip,.panel-heading #btn-down{
            margin-top: -27px;
        }
        .panel-heading  h4{
            border-left: 2px solid #189ae5;
            padding-left:15px;
            margin-left:-13px;
        }
        #row-main{
           box-shadow: 0 1px 5px rgba(4,0,0,.1);
        }
        #btn-praise{
            margin-top: -13px;
        }
        .isStuck{
            z-index: 99;
            /*min-width: 77%;*/
            left: 15px;
            right: 15px;
        }
        .originPrice{
            text-decoration: line-through;
        }
    </style>
<#assign template="docs"/>
</head>
<body class="page-header-fixed">
<!-- toolbar begin-->
<#include "/index-toolbar.ftl" />
<!-- toolbar end-->
<!-----------------------------------------顶部-------------->
<!-- banner 广告 begin-->
<#include "/index-top-banner.ftl" />
<!-- banner 广告 end-->
<!-- header begin-->
<#include "/index-header.ftl" />

<div class="container" style="margin-top: 15px;">
    <ol class="breadcrumb">
        <li><a href="${appHost}/"><@spring.message  code="label.system.index" /></a></li>
        <li><a href="${appHost}/docs.jhtml">${result.periodicalTypeName!''}</a></li>
        <li class="active">文档</li>
    </ol>
</div>
<div class="container" >
    <div class="row"  id="row-main">
        <div class="panel panel-default">
            <div class="panel-heading" id="doc-nav-top" ><h4>${result.periodicalName!''}</h4>
                <i class="fa fa-thumbs-o-up fa-lg pull-right" id="btn-praise"  data-url="${appHost}/doc/${result.id!''}/praise.json" ></i>
                <a type="button" id="btn-vip" class="btn pull-right" target="_blank" href="${appHost}/vip.jttml" ><i class="fa fa-free-code-camp">加入Vip免费下载</i></a>
                <a type="button" id="btn-down" class="btn btn-danger pull-right"
                            <#if result.allowDown>
                                href="${appHost}/fd/${result.id!''}.jhtml"
                            <#else>
                                href="${appHost}/order/getOrderInfo.jhtml?id=${result.id!''}"
                            </#if>
                        ><i class="fa  fa-cloud-download">立即下载</i></a>
                <button type="button" id="btn-focus"  class="btn btn-danger pull-right" data-url="${appHost}/doc/${result.id!''}/collect.json">
                    <i class="fa  <#if result.isCollect>fa-heart<#else>fa-heart-o</#if> fa-lg"></i>
                    <span id="collect-txt"><#if result.isCollect>已收藏<#else>收藏</#if></span>
                </button>

                <button type="button" id="cart-btn" class="btn btn-primary pull-right hide" data-url="${appHost}/doc/${result.id!''}/praise.json" ><i class="fa fa-cart-plus">加入购物车</i></button>

            </div>
            <div class="panel-body">
                <!---文档左侧begin--->
                <div class="col-md-9  col-xs-9">
                    <div class="row">
				       <#include "/front/doc/template/doc_carousel.ftl" />
                    </div>
                </div>
                <!---文档左侧end--->
                <!---文档右侧begin--->
                <div class="col-md-3 col-xs-3"  >
                    <!---详情面板begin--->
                        <#include "/front/doc/template/doc-infos.ftl" />
                        <!---详情面板end--->
                        <!---面板begin--->
                        <#include "/front/doc/template/doc-author-works.ftl" />
                        <!---面板end--->
                        <!---面板begin--->
                        <#include "/front/doc/template/doc-hot-keywords.ftl" />
                        <!---面板end--->
                        <!---面板begin--->
                          <#include "/pages/customer.ftl" />
                        <!---面板end--->
                </div>
                <!---文档右侧end--->
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
<#include "/includes/jquery.ftl" />
<#include "/includes/stickUp.ftl" />
<script type="text/javascript">
    //initiating jQuery
    jQuery(function($) {
        $(document).ready( function() {
            $('#doc-nav-top').stickUp();
        });
    });

</script>
<script>
    seajs.use(["js/doc/init.js?v="+Math.random(),"js/search.js?v="+Math.random()]);
</script>
</body>
</html>