<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title>资料分类-<@spring.message  code="label.system.name" /></title>
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
<#include "/index-toolbar.ftl" />
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
<div class="container" style="margin-top: 30px;">
    <ol class="breadcrumb">
        <li><a href="${appHost}/"><@spring.message  code="label.system.index" /></a></li>
        <li class="active">文库列表</li>
    </ol>
</div>
<div class="container" >
    <div class="row">
        <div class="panel panel-default">
            <div class="">
                <ul id="my-tab-rule" class="myNavTab nav nav-tabs" role="tablist">
                    <li role="presentation" style="cursor:pointer" class="active" >
                        <a id="my-tab-all"  data-key=""  >
                            <h4>全部分类 (共<code id="paginationTotal" >${result.totalElements}</code> 份)</h4>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="panel-body">
                <!---文档begin--->
                <div class="col-md-12  col-xs-12">
                    <div class="row">
                        <form class="form-search form-inline" id="search_form">
                            <input type="hidden" name="page" id="page" value="1">
                            <div class="form-group">
                                <label for="periodicalName">名称</label>
                                <input type="text" class="form-control" style="width: 300px;"  value="${periodicalName!''}" id="periodicalName" name="periodicalName_LIKE" placeholder="名称">
                            </div>
                            <div class="form-group">
                                <select class="form-control" style="display: inline;width: 75px;"name="size" id="paginationSize">
                                    <option value="10">10</option>
                                    <option value="20">20</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                    <option value="200">200</option>
                                </select>
                            </div>
                            <button type="button" id="seach-btn" class="btn btn-primary">搜索</button>
                        </form>
                    </div>
                    <div  class="bs-table-tbody"  >
                        <table style="margin-bottom:0px;"   class="table  table-striped table-bordered table-condensed table-hover">
                            <thead>
                            <tr>
                                <th >文档信息</th>
                                <th style="width: 150px;">上架时间</th>
                            </tr>
                            </thead>
                            <tbody id="table-tbody"  >
                            <#if (result.content?size<=0) >
                                <tr ><td class="text-center" scope="row" colspan='2' ><@spring.message  code="label.default.empty" /></td></tr>
                            <#else>
                                <#list result.content as model>
                                    <tr  >
                                        <td><a href="${appHost}/doc/${model.id!''}.jhtml" ><i class="fa fa-file-excel-o"></i>${model.periodicalName}</a>
                                           (浏览量:  <code>${model.hits!'0'} </code> <code>大小：${model.dataSizeHuman}</code> ，共<code>${model.pageNum}</code>页 )
                                        </td>
                                        <td>${model.pushTime}</td>
                                    </tr>
                                </#list >
                            </#if>
                            </tbody >
                        </table>
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
    seajs.use(["js/doc/list.js?v="+Math.random(),"js/search.js?v="+Math.random()]);
</script>
</body>
</html>