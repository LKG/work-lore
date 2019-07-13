<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title>用户收藏</title>
  <#include "/includes/head.ftl" />
    <#assign template="follows"/>
    <style>
        .main-container{
            padding:0px 100px;
            margin-top: 40px;
            height:583px;
        }

        .user-img .thumbnail{
            width:100px;
            height:100px;
        }
        .user-img .thumbnail img{
            height:100%;
        }
        .follow-media .thumbnail{
            width:100px;
        }
    </style>
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
<div class="clearfix"></div>
<div class="full-container main-container">
    <div class="row">
        <div class="col-xs-3">
            <!--用户面板 begin--->
            <div class="panel panel-default user-panel">
                <div class="panel-body">
                    <!--userInfo begin---->
	 	 		 <#include "/userinfo/user-media.ftl" />
                    <!--userInfo end---->
                </div>
                <!-- List group begin-->
			  <#include "/userinfo/user-menu.ftl" />
                <!-- List group end-->
                <div class="panel-footer">

                </div>
            </div>
            <!--用户面板 end--->

        </div>
        <div class="col-xs-9">
            <div class="panel panel-info">
<#--                <div class="">
                    <ul id="my-tab-rule" class="myNavTab nav nav-tabs" role="tablist">
                        <li role="presentation" style="cursor:pointer" class="active" >
                            <a id="my-tab-all"  data-key="order-all"  >
                                全部
                            </a>
                        </li>
                        <li role="presentation" style="cursor:pointer" class="" >
                            <a id="my-tab-uppaid"  data-key="order-uppaid"  >
                                待付款
                            </a>
                        </li>
                    </ul>
                </div>-->
                <div class="panel-body" style="min-height:500px;">
                    <!--- 订单模块end--->
                    <div class="clearfix"></div>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-xs-12">
                                <div id="my-tab-content" class="myTabContent tab-content">
                                    <div role="tabpanel"  class="tab-pane fade  active in" id="tab-pane-related" style="min-height:360px;"  >
                                        <form class="form-search form-inline" id="order_search_form" >
                                            <div class="form-group" >
                                                <input type="hidden" name="page" id="order-page" value="1" />
                                                <input type="hidden" name="size" id="order-size" value="5" />
                                            </div>
                                        </form>
                                        <table style="margin-bottom:0px;"   class="table  table-striped table-bordered table-condensed table-hover">
                                            <thead>
                                            <tr>
                                                <th class="text-center" >关注信息</th>
                                                <th class="text-center" style="width: 100px;" >操作</th>
                                            </tr>
                                            </thead>
                                            <tbody  id="order-table-tbody" >
                                            <tr>
                                                  <#if (result.content?size<=0) >
	          		                                 <tr ><td class="text-center" scope="row" colspan='2' ><@spring.message  code="label.default.empty" /></td></tr>
                                                  <#else>
                                                      <#list result.content as model>
                                                        <tr >
                                                            <td scope="row"" >
                                                            <div class="media follow-media">
                                                                <div class="media-left">
                                                                    <div class="thumbnail">
                                                                        <a href="${appHost}/doc/${model.relateId}.jhtml" ><img class="media-object"  src="${appHost}/${model.itemImgUrl}" alt="${model.itemTitle}"></a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                      </#list >
                                                  </#if>
                                            </tr>
                                            </tbody >
                                            <script id="order-tr-template-js"  type="text/html">
                                                {{if (content.length>0) }}
                                                {{each content as model}}
                                                <tr >
                                                    <td scope="row"" >
                                                    <div class="media">
                                                        <div class="media-left">
                                                            <div class="thumbnail ">
                                                                <a href="{{contextPath}}/doc/{{model.id}}.jhtml" ><img class="media-object"  src="{{model.prodUrl}}" alt="{{model.prodName}}"></a>
                                                            </div>
                                                        </div>
                                                        <div class="media-body">
                                                            <span>￥<code>{{model.prodPrice}}</code></span>  <span>数量：<code>{{model.prodQuantity}}</code></span>
                                                        </div>
                                                    </div>
                                                    </td>
                                                    <td></td>
                                                </tr>
                                                {{/each}}
                                                {{else}}
                                                <tr id="ext_{{$index}}" class="text-center" >
                                                    <td colspan="2"><@spring.message  code="label.default.empty" /></td>
                                                </tr>
                                                {{/if}}
                                            </script>
                                        </table>
                                        <div  id="order-table-pagination" style="margin-top: 5px;"  class="clearfix"></div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!--- 订单模块end--->
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
    seajs.use(["/js/userinfo/user_follow.js?v="+Math.random(),"js/userinfo/user-sign.js?v="+Math.random()]);
</script>
</body>
</html>