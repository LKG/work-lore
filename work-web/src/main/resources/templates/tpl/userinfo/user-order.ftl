<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title>用户订单</title>
  <#include "/includes/head.ftl" />
      <#include "/includes/laypage-css.ftl" />
    <#assign template="orders"/>
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
        .order-panel{
            border: 0px;
            margin-bottom: 0px;
        }
        .order-panel .thumbnail{
            width:100px;
        }
        .order-panel .panel-heading{
            padding: 8px 10px;
            background-color:#ffffff;
        }
         .order-panel .panel-body{
             padding: 0px;
             padding-left:  10px;
        }
        .order-panel .media-body{
            width: 100%;
            padding: 15px 15px;
        }
        .order-panel .media-body p{
            padding: 15px 15px;
        }
        .order-panel .media-body span, .order-heading span{
            margin-right: 30px;
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
                <div class="">
                    <ul id="my-tab-rule" class="myNavTab nav nav-tabs" role="tablist">
                        <li role="presentation" style="cursor:pointer" class="active" >
                            <a id="my-tab-all"  data-key=""  >
                                全部
                            </a>
                        </li>
                        <li role="presentation" style="cursor:pointer" class="" >
                            <a id="my-tab-uppaid"  data-key="unprocessed"  >
                                待付款
                            </a>
                        </li>
                    </ul>
                </div>
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
                                                <input type="hidden" name="size" id="order-size" value="" />
                                                <input type="hidden" name="orderStatus" id="orderStatus" value="" />
                                            </div>
                                        </form>
                                        <table style="margin-bottom:0px;"   class="table  table-striped table-bordered table-condensed table-hover">
                                            <thead>
                                            <tr>
                                                <th class="text-center" >订单信息</th>
                                                <th class="text-center" >成交价</th>
                                                <th class="text-center" style="width: 100px;" >操作</th>
                                            </tr>
                                            </thead>
                                            <tbody  id="order-table-tbody" >
                                            <tr>
                                                  <#if (result.content?size<=0) >
	          		                                 <tr ><td class="text-center" scope="row" colspan='3' ><@spring.message  code="label.default.empty" /></td></tr>
                                                  <#else>
                                                      <#list result.content as model>
                                                        <tr >
                                                            <td scope="row"" >
                                                            <div class="panel panel-default order-panel">
                                                                <!-- Default panel contents -->
                                                                <div class="panel-heading order-heading"><span>订单号：<code>${model.orderId!''}</code></span><span>创建时间：<code>${model.createTime}</code></span></div>
                                                                <div class="panel-body">
                                                                      <#list model.items as item>
                                                                          <div class="media">
                                                                              <div class="media-left">
                                                                                  <div class="thumbnail ">
                                                                                      <a href="${appHost}/doc/${item.prodId}.jhtml" ><img class="media-object"  src="${item.prodUrl}" alt="${item.prodName}"></a>
                                                                                  </div>
                                                                              </div>
                                                                              <div class="media-body">
                                                                                  <h4 class="media-heading"><code>${item.prodName!''}</code></h4>
                                                                                 <p><span>￥：<code>${item.prodFinalPrice!'0'}</code></span><span>数量：<code>${item.prodQuantity!'1'}</code></span></p>
                                                                              </div>
                                                                          </div>
                                                                      </#list>
                                                                </div>
                                                            </div>


                                                            </td>
                                                            <td scope="row" class="text-center" style="vertical-align: middle;">
                                                                ￥：<code>${model.paymentFee!''}</code>
                                                            </td>
                                                            <td class="operate text-center" style="vertical-align: middle;">
                                                                <#if model.orderStatus=='unprocessed' >
                                                                    <button class="btn btn-danger btn-block"  href="${appHost}/order/confirmOrderInfo.html?orderId=${model.orderId}"    type="button">支付</button>
                                                                    <a id="btn-cancel_${model.orderId!''}" class="btn btn-cancel"  data-orderId="${model.orderId!''}" >取消订单</a>
                                                                </#if>
                                                                 <#if model.orderStatus=='completed' >
                                                                已完成
                                                                 </#if>
                                                                <#if model.orderStatus=='invalid' >
                                                                已关闭
                                                                 </#if>
                                                            </td>
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
                                                  <div class="panel panel-default order-panel">
                                                                <div class="panel-heading order-heading"><span>订单号：<code>{{model.orderId}}</code></span><span>创建时间：<code>{{model.createTime}}</code></span></div>
                                                                <div class="panel-body">
                                                                  {{each model.items as item}}
                                                                          <div class="media">
                                                                              <div class="media-left">
                                                                                  <div class="thumbnail ">
                                                                                      <a href="${appHost}/doc/{{item.prodId}}.jhtml" ><img class="media-object"  src="{{item.prodUrl}}" alt="{{item.prodName}}"></a>
                                                                                  </div>
                                                                              </div>
                                                                              <div class="media-body">
                                                                                  <h4 class="media-heading"><code>{{item.prodName}}</code></h4>
                                                                                  <p><span>￥<code>{{item.prodPrice|'0'}}</code></span><span>数量：<code>{{item.prodQuantity|'0'}}</code></span> </p>
                                                                              </div>
                                                                          </div>
                                                                    {{/each}}
                                                           </div>
                                                            </div>

                                                            </td>
                                                           <td scope="row" class="text-center" style="vertical-align: middle;">
                                                                <code>{{model.paymentFee}}</code>
                                                            </td>
                                                            <td class="operate text-center" style="vertical-align: middle;">
                                                              {{ if (model.orderStatus=='unprocessed') }}
                                                                  <button class="btn btn-danger btn-block"     href="${appHost}/order/confirmOrderInfo.html?orderId={{model.orderId}}"     type="button">支付</button>
                                                                  <a id="btn-cancel_{{model.orderId}}" class="btn btn-cancel"  data-orderId="{{model.orderId}}" >取消订单</a>
                                                              {{/if}}
                                                                {{ if (model.orderStatus=='completed') }}
                                                                    已完成
                                                                {{/if}}
                                                                {{ if (model.orderStatus=='invalid') }}
                                                                     已关闭
                                                                {{/if}}
                                                            </td>
                                                        </tr>
                                                {{/each}}
                                                {{else}}
                                                <tr id="ext_{{$index}}" class="text-center" >
                                                    <td colspan="3"><@spring.message  code="label.default.empty" /></td>
                                                </tr>
                                                {{/if}}
                                            </script>
                                        </table>
                                        <!-----分页-begin---->
                                        <div  id="table-pagination" data-totalPages="${result.totalPages}" data-number="${result.number}"  class="clearfix"></div>
                                        <!-----分页-end---->
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
    seajs.use(["/js/userinfo/user_order.js?v="+Math.random(),"js/userinfo/user-sign.js?v="+Math.random()]);
</script>
</body>
</html>