<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title>文档列表-<@spring.message  code="label.system.name" /></title>
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
<!-- banner 广告 begin-->
<#include "/index-top-banner.ftl" />
<!-- banner 广告 end-->
<!-- header begin-->
<#include "/index-header.ftl" />
<!-- 搜索框 begin-->
<#include "/index-top-search.ftl" />
<!-- 搜索框 end-->
<!--轮播图上方导航栏  一栏-->
<#include "/index-nav-top.ftl" />
<div class="container" style="margin-top: 15px;">
    <ol class="breadcrumb">
        <li><a href="${appHost}/"><@spring.message  code="label.system.index" /></a></li>
        <li class="active">文档搜索列表</li>
    </ol>
</div>
<div class="container" >
    <div class="row">
        <#include "/front/doc/doc_cate.ftl" />
        <div class="col-md-9  col-xs-9" style="padding: 0px 0px;">
            <div class="panel panel-default">
            <div class="">
                <ul id="my-tab-rule" class="myNavTab nav nav-tabs" role="tablist">
                    <li role="presentation" style="cursor:pointer" class="active">
                        <a id="my-tab-all" data-key="">
                            全部 (共<code id="paginationTotal" >${result.totalElements}</code> 篇)
                        </a>
                    </li>
                </ul>
            </div>
            <div class="panel-body">
                <!---文档begin--->
                <form class="form-search form-inline" id="search_form">
                    <input type="hidden" name="page" id="page" value="1">
                </form>
                    <div  class="bs-table-tbody"  >
                        <ul class="list-group" id="table-tbody"   >
                              <#if (result.content?size<=0) >
	          		               <tr ><td class="text-center" scope="row" colspan='3' ><@spring.message  code="label.default.empty" /></td></tr>
                              <#else>
                                  <#list result.content as doc>
                                      <li class="list-group-item">
                                          <i class="fa fa-${doc.fileHeader!''}" style="margin-right: 10px;"></i>
                                          <a class="item-href"  href="${appHost}/doc/${doc.id!''}.jhtml"
                                             target="_blank" >${doc.shortTitle!''}</a>
                                          <span >阅读量：<code>${doc.hits!'0'}</code></span>
                                          <span class="pull-right">${doc.pushTime!''}</span>
                                      </li>
                                  </#list >
                              </#if>
                        </ul>
                    </div>
                    <!-----分页-begin---->
                    <div  id="table-pagination" data-totalPages="${result.totalPages}" data-number="${result.number}" style="margin-top: -15px;"  class="clearfix"></div>
                    <!-----分页-end---->
                <!---文档end--->
            </div>
        </div>
        </div>
        <div class="col-md-3  col-xs-3" style="padding: 0px 0px 0px 10px;">
            <#include "/pages/customer.ftl" />
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
    seajs.use(["js/cms/doc-search.js?v="+Math.random(),"js/search.js?v="+Math.random()]);
</script>
</body>
</html>