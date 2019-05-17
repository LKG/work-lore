<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title>文章列表-<@spring.message  code="label.system.name" /></title>
  <#include "/includes/head.ftl" />
<#include "/includes/laypage-css.ftl" />
    <style>
        .tab-pane{
            padding-top:30px;
        }
        .panel-heading {
            padding: 5px 13px;
        }
        .panel-heading  h4{
            border-left: 2px solid #189ae5;
            padding-left:15px;
            margin-left:-13px;
        }
        .main-panel .panel-heading{
            padding: 0px 15px;
        }
        .main-panel{
            margin-top: -20px;
        }
        .table td{white-space:nowrap;overflow:hidden; text-overflow:ellipsis; }
        .table  {table-layout:fixed;}
        .table th{vertical-align:middle !important;text-align: center; font-size:14px;}
        .Wdate{
            height: 32px !important;
            padding: 3px 1px !important;
            border: 1px solid #ccc !important;
        }
        .form-search select{
            padding: 6px 3px  !important;
        }
        .form-inline .form-group,.form-inline .form-control{
            width: 100px;
            display: inline;
        }
        @media (max-width: 900px){
            .panel-heading{
                padding: 10px 0px !important;
            }
        }
        .laypage_main{
            clear: none !important;
        }
    </style>
<#assign template="docs"/>
</head>
<body class="page-header-fixed">
<!-- toolbar begin-->
<#include "/index-toolbar.ftl"/>
<!-- toolbar end-->
<!-----------------------------------------顶部-------------->
<!-- header begin-->
<#include "/index-header.ftl" />
<!-- banner 广告 begin-->
  <#include "/index-top-banner.ftl" />
<!-- banner 广告 end-->
<!-- 搜索框 begin-->
<#include "/index-top-search.ftl" />
<!-- 搜索框 end-->
<!--轮播图上方导航栏  一栏-->
<#include "/index-nav-top.ftl" />
<div class="container" style="margin-top: 15px;">
    <ol class="breadcrumb">
        <li><a href="${appHost}/"><@spring.message  code="label.system.index" /></a></li>
        <li class="active">文章列表</li>
    </ol>
</div>
<div class="container"  style="margin-top: -10px;" >
    <div class="row">
        <div class="panel panel-default">
            <div class="">
                <ul id="my-tab-rule" class="myNavTab nav nav-tabs" role="tablist">
                    <li role="presentation" style="cursor:pointer" class="active">
                        <a id="my-tab-all" data-key="">
                            全部 (共<code id="paginationTotal" >${result.totalElements}</code> 篇)
                        </a>
                    </li>
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-economy"  data-key="economy"  >-->
                <#--经济-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-politics"  data-key="politics"  >-->
                <#--政治-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-tj"  data-key="tj"  >-->
                <#--图解-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-history"  data-key="history"  >-->
                <#--历史-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-keji"  data-key="keji"  >-->
                <#--科技-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-law"  data-key="law"  >-->
                <#--法律-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-ll"  data-key="ll"  >-->
                <#--理论-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-nationaldefense"  data-key="nationaldefense"  >-->
                <#--国防-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-kejiao"  data-key="kejiao"  >-->
                <#--科教-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-jiaoyu"  data-key="jiaoyu"  >-->
                <#--教育-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-international"  data-key="international"  >-->
                <#--国际-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-culture"  data-key="culture"  >-->
                <#--文化-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-dangjian"  data-key="dangjian"  >-->
                <#--党建-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-community"  data-key="community"  >-->
                <#--社会-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-ecology"  data-key="ecology"  >-->
                <#--生态-->
                <#--</a>-->
                <#--</li>-->
                <#--<li role="presentation" style="cursor:pointer" class="" >-->
                <#--<a id="my-tab-other"  data-key="other"  >-->
                <#--其他-->
                <#--</a>-->
                <#--</li>-->
                </ul>
            </div>
            <div class="panel-body">
                <!---文档begin--->
                <div class="col-md-12  col-xs-12">
                    <div class="row">
                        <form class="form-search form-inline" id="search_form">
                            <input type="hidden" name="page" id="page" value="1">
                        </form>
                    </div>
                    <div  class="bs-table-tbody"  >
                        <ul class="list-group" id="table-tbody"   >
                              <#if (result.content?size<=0) >
                                  <tr ><td class="text-center" scope="row" colspan='3' ><@spring.message  code="label.default.empty" /></td></tr>
                              <#else>
                                  <#list result.content as article>
                                      <li class="list-group-item">
                                          <span class="item-type" style="padding-right: 50px;">[${article.type!''}]</span>
                                          <a class="item-href"  href="${appHost}/article/${article.id!''}.jhtml"
                                             target="_blank" >${article.title!''}</a>
                                          <span >阅读量：<code>${article.hits!'0'}</code></span>
                                      <#--                        <span >点赞数：<code>${article.rateTimes!'0'}</code></span>-->
                                          <span class="pull-right">${article.pushTime!''}</span>
                                      </li>
                                  </#list >
                              </#if>
                        </ul>
                    </div>
                    <!-----分页-begin---->
                    <div  id="table-pagination" data-totalPages="${result.totalPages}" data-number="${result.number}" style="margin-top: -15px;"  class="clearfix"></div>
                    <!-----分页-end---->

                </div>
                <!---文档end--->
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
<!------footer信息 end----->
  	<#include "/includes/datePicker.ftl" />
<script>
    seajs.use(["js/cms/article.js?v="+Math.random(),"js/search.js?v="+Math.random()]);
</script>
</body>
</html>