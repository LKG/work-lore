<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title> <@spring.message  code="label.system.name" />-收银台</title>
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
        .pay-type {
            margin: 0 auto;
            width: 90%;
            padding-bottom: 30px;
        }
        .pay-type .pay-item label{
            margin-top: 20px;
            width: 175px;
            height: 50px;
            display: flex;
            border: 1px solid #E5E5E5;
            cursor: pointer;
            border-radius: 6px;
            margin-right: 10px;
            background: #FAFAFA;
        }
        .pay-type .pay-item label input{
            top:15px;
            left: 40px;
        }
        .pay-type .pay-item {
            display: flex;
            align-items: center;
            flex-wrap: wrap;
        }
        .pay-type .pay-item img {
            height: 34px;
            margin: 8px auto;
        }
    </style>
</head>
<body class="page-header-fixed">
<!------页面header信息 begin----->
<header class="navbar navbar-static-top bs-docs-nav" id="top" style="height: 30px;" role="banner">
    <nav class="navbar navbar-inverse" style="height: 30px;" >
        <div class="container" style="height: 30px;" >
            <ul class="nav navbar-nav">
                <li>
                    <a href="${appHost}/" >首页</a>
                </li>
            </ul>
            <p class="navbar-text" style="height: 30px;" >收银台</p>
        </div>
    </nav>
</header>
<!------页面header信息 end----->
<div class="container" id="user-container">
    <section id="features" class="features">
        <div class="container">
            <div class="row">
                <div class="main_features p-top-100">

                    <div class="gray-box">
                        <div class="title">
                            <h2>XPay收银台 </h2>
                        </div>
                        <!--内容-->
                        <div>
                            <div class="box-inner order-info">
                                <p class="payment-detail">扫一扫付款（元）</p>
                                <p class="payment-money" id="money1"></p>
                                <p id="showreamrk" class="payment-detail" style="display: none">
                                    支付时请在备注中输入您的订单标识号：<b class="payNum" style="color: #d44d44;"></b><br>
                                </p>
                                <div id="qr-pic" class="img-box" style="flex-direction: column;">
                                    <div class="explain">
                                        <img class="fn-left"
                                             src="https://t.alipayobjects.com/images/T1bdtfXfdiXXXXXXXX.png"
                                             alt="扫一扫标识">
                                        <div class="fn-right">打开手机支付宝<br>扫一扫继续付款</div>
                                    </div>
                                    <div class="timeout" style="display: none">二维码已过期</div>
                                </div>
                                <div class="download-box">
                                    <p id="qrmobile" style="display: none;margin: 10px 10px -10px 10px;text-align: center;">
                                        <span>请长按二维码保存至手机后，打开支付宝使用“扫一扫”，点击右上角“相册”选择刚保存的二维码进行支付</span>
                                    </p>
                                    <a id="startApp" class="main-btn" style="width: 205px;display: none;margin-top: 20px;">一键启动支付宝APP支付</a>
                                    <a id="startAppFromWechat" onclick="showTip()" class="main-btn" style="width: 205px;display: none;margin-top: 20px;">打开支付宝APP支付</a>
                                    <a class="download-alipay" href="https://mobile.alipay.com/index.htm" target="_blank">首次使用请下载手机支付宝</a>
                                </div>

                                <div class="count" id="time-box"></div>

                                <div class="qrguide-area">
                                    <img id="img1"
                                         src="https://t.alipayobjects.com/images/rmsweb/T13CpgXf8mXXXXXXXX.png"
                                         class="show" onclick="change()">
                                    <img id="img2"
                                         src="https://t.alipayobjects.com/images/rmsweb/T1ASFgXdtnXXXXXXXX.png"
                                         class="hide" onclick="change()">
                                </div>

                                <img src="assets/images/red.png" width="50px" height="50px" class="red" title="支付领红包"
                                     onclick="showRed()">
                            </div>
                        </div>

                        <div>
                            <div class="box-inner">
                                <div>
                                    <span id="other-way" style="position: absolute;left: 15px;font-size: 14px;">
                                        体验红包或转卡其他方式扫码后请勿立即支付，返回后再点击支付按钮
                                    </span>
                                    <em><span>¥</span><em id="money2"></em></em>
                                    <input type="text" class="disabled-btn" onclick="confirm()" id="confirm"
                                           value="等待支付..." readonly>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </div><!-- End off row -->
        </div><!-- End off container -->
    </section><!-- End off Featured Section-->
</div>
<!------footer信息 begin----->
  	<#include "/includes/footer.ftl" />
  	<#include "/includes/footer-js.ftl" />
<!------footer信息 end----->
<!------seajs.config 引用信息 begin----->
<#include "/includes/seajs.config.ftl" />
<script src="https://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
<script>
    function isAlipay() {
        var ua = window.navigator.userAgent.toLowerCase();
        if (ua.indexOf("alipay")>=0) {
            location.href = "https://xpay.gongwk.com";
        }
    }
    isAlipay();
</script>
<!------seajs.config   引用信息 end----->
</body>
</html>