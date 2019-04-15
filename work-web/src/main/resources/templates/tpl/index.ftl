<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <#include "/includes/head.ftl" />
    <meta name="baidu-site-verification" content="v9ouxuGJew" />
    <title><@spring.message  code="label.system.name" />-会议发言稿-工作总结-党政- 十九大</title>
    <meta name="description" content="<@spring.message  code="label.system.name" />,为您提供愉悦的资料分享体验!" />
    <meta name="Keywords" content="公文、公文库、文秘、ppt模板下载、简历模板、视频教程、ppt素材库、工作总结、工作计划、调研报告、竞聘演讲稿、会议发言稿、学习心得体会、入党申请书、应用公文、论文范文、范文写作技巧" />
    <link href="${appHost}/css/main.css?v=${ver!'1'}" type="text/css" rel="stylesheet">
    <style>
        a {
            color: #666;
            text-decoration: none;
        }
        a:hover {
            cursor:pointer;
            text-decoration: none;
        }
    </style>
    <style type="text/css">
        .counter{
            padding: 20px 0;
            text-align: center;
            position: relative;
        }
        .counter .counter-content{
            width: 100px;
            height: 100px;
            border-radius: 50%;
            background: #fff;
            z-index: 1;
            position: relative;
            transition: all 0.3s ease 0s;
        }
        .counter .counter-content:before{
            content: "";
            width: 122%;
            height: 122%;
            border-radius: 50%;
            border: 15px solid #f19f48;
            border-bottom: 20px solid transparent;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
        }
        .counter .counter-value{
            font-size: 16px;
            font-weight: 700;
            color: rgba(0,0,0,0.7);
            line-height: 80px;
        }
        .m-container{
            width: 1210px;
            margin-top: 10px;
            padding: 10px 2px;
        }
        .counter.red .counter-content:before{
            border-color: #ef5f61;
            border-bottom-color: transparent;
        }
        .counter.red .counter-content:after{ border-top-color: #ef5f61; }
        .counter.red .counter-icon{ color: #ef5f61; }
        .counter.red .title{ background: #ef5f61; }
        .counter.blue .counter-content:before{
            border-color: #4d9fcf;
            border-bottom-color: transparent;
        }
        .counter.blue .counter-content:after{ border-top-color: #4d9fcf; }
        .counter.blue .counter-icon{ color: #4d9fcf; }
        .counter.blue .title{ background: #4d9fcf; }
        .counter.purple .counter-content:before{
            border-color: #a98ceb;
            border-bottom-color: transparent;
        }
        .counter.purple .counter-content:after{ border-top-color: #a98ceb; }
        .counter.purple .counter-icon{ color: #a98ceb; }
        .counter.purple .title{ background: #a98ceb; }
        .dd-inner{
            display: block;
        }
    </style>
  <#assign template="index"/>
</head>
<body id="top">
<!-- toolbar begin-->
<#include "/index-toolbar.ftl"/>
<!-- toolbar end-->
<!-----------------------------------------顶部-------------->
<!-- header begin-->
<#include "/index-header.ftl" />
<!-- header end-->
<!-- banner 广告 begin-->
<#include "/index-top-banner.ftl" />
<!-- banner 广告 end-->
<!-- 搜索框 begin-->
<#include "index-top-search.ftl" />
<!-- 搜索框 end-->
<!--轮播图上方导航栏  一栏-->
<#include "index-nav-top.ftl" />
<#include "index-nav-left.ftl" />

<#include "index-main.ftl" />
<!------footer信息 begin----->


<#include "/includes/footer.ftl" />
 <#include "/includes/footer-js.ftl" />
<!------footer信息 end----->
<!------seajs.config 引用信息 begin----->
<#include "/includes/seajs.config.ftl" />
<!------seajs.config   引用信息 end----->
<div class="clearfix" ></div>
<!------页面header信息 end----->
</body>
<script>
    seajs.use("js/search.js?v="+Math.random());
</script>
<script src="js/index.js?v=${ver!'1'}"></script>
<#include "/includes/baidu-zn.ftl" />
</html>