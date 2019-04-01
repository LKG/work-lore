<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title> <@spring.message  code="label.system.name" />-选择支付方式页面</title>
  <#include "/includes/head.ftl" />
    <link rel="stylesheet" type="text/css" href="${appHost}/css/bank.css" />
    <style>
        .bank_list h6 {
            float: left;
            width: 938px;
            height: 25px;
            margin-bottom: 15px;
        }
        .bank_list h6 label {
            margin-right: 10px;
            font-size: 14px;
            font-weight: bold;
        }
        .bank_list h6 label, .bank_list h6 span {
            float: left;
            color: #666;
            line-height: 25px;
        }
    </style>
</head>
<body class="page-header-fixed">
<!------页面header信息 begin----->
<header class="navbar navbar-static-top bs-docs-nav" id="top" style="height: 30px;" role="banner">
    <nav class="navbar navbar-inverse" style="height: 30px;" >
        <div class="container" style="height: 30px;" >
            <p class="navbar-text" style="height: 30px;" >收银台</p>
        </div>
    </nav>
</header>
<!------页面header信息 end----->
<div class="container" id="user-container">
    <div class="panel panel-warning">
        <div class="panel-body">
            <div class="alert alert-warning" role="alert">
                <div>订单提交成功，请尽快付款！订单号：<code style="font-weight:bold;color:#f60;">${result.orderId}</code></div>
                <div>请您在24小时内完成支付，否则订单会被自动取消(库存紧俏订单请参见详情页时限) </div>
                <label class="pull-right" style="margin-top: -30px;margin-right: 30px;">您应付金额：<font class="cOrange" style="font-size: 18px;font-weight:bold;color:#f60;">${result.totalAmount}</font>&nbsp;&nbsp;元
                    <a href="javascript:void(0)">查看详单</a>
                </label>
            </div>
            <div class="infor_box">
                <span class="helpInfo">
				   <u style="display: none;" class="helpDelimiterInfo">
				   <b class="arrow"><b class="arrow-one">◆</b>
				   <b class="arrow-two">◆</b></b>

			   </span>

                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <div class="panel panel-success">
        <div class="panel-heading">
            <div class="container-full"  >
                <div class="row"  >
                    <div class="col-xs-4 "  >
                        <h4 class="text-left"  >请选择支付方式：</h4>
                    </div>
                    <div class="col-xs-8" >

                    </div>
                </div>
            </div>
        </div>
        <div class="panel-body bank_list">
            <!----网银列表end--->
            <div class="page-header hide">
                <h6><label>网银支付</label><span>需开通网银</span> </h6>
                <div class="clearfix"></div>
                <div class="bank_list hide">
                    <label class="radio-inline " style="padding-left: 30px;">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_ICBC" value="option1">
                        <p class="ICBC" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_ABC" value="option1">
                        <p class="ABC" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_CMBCHINA" value="option1">
                        <p class="CMBCHINA" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_CCB" value="option1">
                        <p class="CCB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_BOC" value="option1">
                        <p class="BOC" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_BOCO" value="option1">
                        <p class="BOCO" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_CIB" value="option1">
                        <p class="CIB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_SPDB" value="option1">
                        <p class="SPDB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_HXB" value="option1">
                        <p class="HXB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_CMBC" value="option1">
                        <p class="CMBC" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_ECITIC" value="option1">
                        <p class="ECITIC" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_POST" value="option1">
                        <p class="POST" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_PAB" value="option1">
                        <p class="PAB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_GDB" value="option1">
                        <p class="GDB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_CBHB" value="option1">
                        <p class="CBHB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_NJCB" value="option1">
                        <p class="NJCB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_BCCB" value="option1">
                        <p class="BCCB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_NBCB" value="option1">
                        <p class="NBCB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_HZB" value="option1">
                        <p class="HZB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_SHB" value="option1">
                        <p class="SHB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_BJRCB" value="option1">
                        <p class="BJRCB" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_HKBEA" value="option1">
                        <p class="HKBEA" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_CZB" value="option1">
                        <p class="CZB" ></p>
                    </label>
                    <a>更多银行</a>
                    </label>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="page-header">
                <h6><label>快捷支付</label><span>一步验证，无需网银！</span> </h6>
                <div class="clearfix"></div>
                <div class="bank_list">
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_DEBITCARD_ALIPAY" value="option1">
                        <p class="DEBITCARD_ALIPAY" ></p>
                    </label>
                    <label class="radio-inline hide">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_YeePay01" value="option1">
                        <p class="YeePay01" ></p>
                    </label>
                    <label class="radio-inline hide">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_DEBITCARD_UNIONPAY" value="option1">
                        <p class="DEBITCARD_UNIONPAY" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_DEBITCARD_TENPAY" value="option1">
                        <p class="DEBITCARD_TENPAY" ></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_weixinScanCode" value="">
                        <p class="weixinScanCode"></p>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="paymentInfo.bankSelect" id="bank_weixinScanCode" value="">
                        <p class="text-center" style="padding-top: 14px;">线下支付</p>
                    </label>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="text-center">
                <button class="btn btn-danger  btn-lg" id="focus-btn" type="button" >
                    确认支付
                </button>
            </div>
            <!----网银列表end--->
        </div>
    </div>
    <!--支付提示--->
    <div class="row">
        <div class="page-header">
            <h5> 支付遇到的问题</h5>
        </div>
        <div class="">
            <dl>
                <dt>网银支付支持哪些银行？</dt>
                <dd>答：网银支付需要提前开通网银，可以选择个人网上银行（支持工商银行、农业银行、招商银行、建设银行、中国银行、交通银行、兴业银行、光大银行等在内的20多家银行）
                    和企业网上银行（工商银行、农业银行、招商银行、建设银行、光大银行、中国银行等）进行支付。</dd>
            </dl>
            <dl>
                <dt>没有开通网银，还能支付吗？</dt>
                <dd>答：没有网银您也可以支付，提供多种支付方式（快捷、信用卡无密码和账户支付）供您选择。</dd>
            </dl>
            <dl>
                <dt>账户支付支持哪些账户？</dt>
                <dd>答：账户支付可以选择微信、支付宝、财付通多种账户进行支付。</dd>
            </dl>
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
</body>
</html>