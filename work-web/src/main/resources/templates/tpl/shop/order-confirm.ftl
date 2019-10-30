<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title> <@spring.message  code="label.system.name" />-订单确认</title>
  <#include "/includes/head.ftl" />
    <style>
        .tab-pane{
            padding-top:30px;
        }
        .panel-heading {
            padding: 5px 13px;
        }
        .panel-heading  h4{
            border-left: 2px solid #f35d5d;
            padding-left:15px;
            margin-left:-13px;
        }
        #order_process span.active{
            background: #fda6a3;
            color: #fff;
        }
        #order_process{
            width: 100%  !important;
        }
        #order_process span{
            width: 25%  !important;
            border:1px solid transparent;
            border-color: #ddd;
        }
        .container , .form-group{
            font-size: 12px;
            margin-bottom: 0px;
        }
        .form-group .radio-inline{
            padding-top: 0px;
        }
        .form-group .radio span {
            padding: 10px 10px;
        }
        .form-group .radio{
            margin-bottom: 10px;
        }
    </style>
<#assign template="project"/>
</head>
<body class="page-header-fixed">
<!-- toolbar begin-->
<#include "/index-toolbar.ftl"/>
<!-- toolbar end-->
<!-----------------------------------------顶部-------------->
<!-- header begin-->
<#include "/index-header.ftl" />
<div class="container" style="margin-top: 35px;">
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading" id="doc-nav-top" ><h4>${result.periodicalName!''}</h4></div>
            <div class="panel-body">
                <!---项目右侧begin--->
                <div class="col-md-12  col-xs-12">
                        <div class="media">
                            <div class="media-left">
                                <a href="${appHost}/doc/${result.id}.jhtml" >
                                    <img class="media-object" data-src="holder.js/64x64" alt="64x64" src="${result.coverImgUrl!''}" data-holder-rendered="true" style="width: 180px;">
                                </a>
                            </div>
                            <div class="media-body">
                               <form class="form-horizontal" id="J_OrderItemForm"  method="post" >
                                   <div id="items" >
                                   <input type="hidden" name="prodId" value="${result.id}">
                                   <input type="hidden" name="prodQuantity" value="1">
                                   <input type="hidden" name="prodOriginPrice" value="${result.originPrice}">
                                   <input type="hidden" name="prodFinalPrice" value="${result.finalPrice}">
                                   </div>
                               </form>
                                <div class="form-group">
                                    <label class="col-sm-2 col-xs-2  control-label text-right">优惠方式：</label>
                                    <div class="col-sm-10 col-xs-10">
                                        <p class="form-control-static">
                                            <#if result.finalPrice==0>
                                                    限时优惠
                                            </#if>
                                             <#if result.finalPrice!=0>
                                                    无
                                             </#if>
                                        </p>
                                    </div>
                                </div>
                                <form class="form-horizontal" id="J_OrderForm"  method="post" >
                                <div class="form-group">
                                    <label class="col-sm-2 col-xs-2  control-label text-right">支付金额：</label>
                                    <div class="col-sm-10 col-xs-10">
                                        <p class="form-control-static"><code><font class="cOrange" style="font-size: 18px;font-weight:bold;color:#f60;">${result.finalPrice}</font></code>元</p>
                                    </div>
                                </div>
                                </form>

                            </div>
                        </div>
                    <div class="text-center" >
                        <botton type="button"  data-loading-text="订单提交中..."   class="btn btn-danger btn-lg" id="J_OrderSubmit" >提交订单</botton>
                    </div>

                </div>
                <!---项目右侧end--->
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
    seajs.use("js/shop/order_create");
</script>
</body>
</html>