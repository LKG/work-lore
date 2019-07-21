<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <#include "/includes/head.ftl" />
    <title>${result.categoryName!''}-${result.title!''}-<@spring.message  code="label.system.name" /></title>
    <link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
    <meta name="Keywords" content="${result.seoKeywords!''}" />
    <meta name="description" itemprop="description"  content="${result.seoDescription!''}" />
    <style>
        .tab-pane{
            padding-top:30px;
        }
        .panel-heading  h4{
            border-left: 2px solid #189ae5;
            padding-left:15px;
            margin-left:-13px;
        }
        .borline{
            border-bottom: 1px solid #e4e4e4;
            padding-bottom: 5px;
            padding-top: 10px;
        }
        .artinfo{
            padding-left: 2px;
        }
        .article-content p {
            margin-bottom: 5px;
            text-indent: 2em;
            color: #333333;
            line-height: 2;
        }
        #row-main{
            padding-bottom: 15px;padding-top: 15px;border: 1px solid #ddd;box-shadow: 0 1px 5px rgba(4,0,0,.1);
        }
        #article-content-dev{
            padding-top: 10px; font-size: 16px;
        }
         .describe {
            border: 1px solid #eee;
            background-color: #ffffff;
            padding: 9px;
            line-height: 24px;
            font-size: 14px;
            color: #555;
            text-indent: 2em;
             margin-top: 15px;
            margin-bottom: 15px;
        }
        .isStuck{
            z-index: 99;
            /*min-width: 77%;*/
            left: 15px;
            right: 15px;
        }
        @media print{
            a:after{
                content: "" !important;
            }
            a[href]:after {
                content: "" !important;
            }
        }
        .originPrice{
            text-decoration: line-through;
        }
        .keywords a{
            padding: 2px 2px;
        }
        .breadcrumb {
            margin-bottom: 0px;
        }
    </style>
    <#assign template="article"/>
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
    <li><a href="${appHost}/articles/c-${result.categoryId!''}.jhtml">${result.categoryName!''}</a></li>
    <li class="active">文章正文</li>
</ol>
</div>
<div class="container" >

    <div class="row" id="row-main" >
        <!---文章begin--->
        <div class="col-md-12  col-xs-12" >
            <i class="pull-right fa fa-lg fa-print" id="btn-print" >打印 </i>
            <div class="row" id="article-row"  style="padding: 5px 15px">
                <h2 class="newstitle text-center" title="${result.title}">${result.title!''}</h2>
                <div class="borline clearfix">
                    <p class="artinfo">　时间：${result.pushTime!''} |　<span class="author">
                       <#if result.author??>
                           <#list result.author?split(" ") as autho>
                               <a href="${appHost}/q?v=${ver!'1'}&q=${autho!''}&qt=4" title="${autho!''}" target="_blank">
                                    <code>${autho!''}</code>
                                </a>
                           </#list>
                        </#if>
                        </span>  |　字体：【<a class="cgray" data-font="16"  href="javascript:void(0);">大</a> <a class="cgray"  data-font="14"  href="javascript:void(0);" >中</a> <a class="cgray"  data-font="12" href="javascript:void(0);" >小</a>】|　阅读: ${result.hits!''}</p>
                    <!-- <p class="share">分享到：</p> -->
                </div>
                  ${result.describe!''}
                <div class="article-content-dev" id="article-content-dev" >
                    ${result.content!''}
                </div>
            </div>
            <div class="keywords text-right"><strong>本文关键词：</strong>
                <!-- 关键字 -->
                <#if result.seoKeywords??>
                    <#list result.seoKeywords?split(",") as keyword>
                           <a href="${appHost}/q?v=${ver!'1'}&q=${keyword!''}&qt=1" title="${keyword!''}" target="_blank">${keyword!''}</a>
                    </#list>
                </#if>
            </div>
        </div>
        <!---文章end--->
    </div>
    <#if near??>
        <nav aria-label="...">
            <ul class="pager">
                <#list near as page>
                    <#if page_index==0>
                        <li class="previous"><a href="${appHost}/article/${page.id!''}.jhtml"><span aria-hidden="true">上一篇：</span> ${page.title}</a></li>
                    </#if>
                    <#if page_index==1>
                        <li class="next"><a  href="${appHost}/article/${page.id!''}.jhtml"><span aria-hidden="true">下一篇：</span>${page.title}</a></li>
                    </#if>
                </#list>
            </ul>
        </nav>
    </#if>

</div>
<!------footer信息 begin----->
<#include "/includes/footer.ftl" />
<#include "/includes/footer-js.ftl" />
<!------footer信息 end----->
<!------seajs.config 引用信息 begin----->
<#include "/includes/seajs.config.ftl" />
<!------seajs.config   引用信息 end----->
<script>
    seajs.use(["js/cms/article-details.js?v="+Math.random(),"js/search.js?v="+Math.random()]);
</script>
</body>
</html>