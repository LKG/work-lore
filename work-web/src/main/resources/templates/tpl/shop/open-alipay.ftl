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
<div class="container" id="user-container">
    <div class="panel panel-warning">
        <div class="panel-body">

        </div>
    </div>
</div>
<!------footer信息 begin----->
  	<#include "/includes/footer.ftl" />
  	<#include "/includes/footer-js.ftl" />
<!------footer信息 end----->
<!------seajs.config 引用信息 begin----->
<#include "/includes/seajs.config.ftl" />
<script>
    // 判断是否支付宝内打开
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