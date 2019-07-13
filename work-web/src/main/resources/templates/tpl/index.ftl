<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <#include "/includes/head.ftl" />
    <meta name="baidu-site-verification" content="v9ouxuGJew" />
    <title>公文写作、公文分享、会议发言稿、工作总结、党政-<@spring.message  code="label.system.name" /></title>
    <meta name="description" content="<@spring.message  code="label.system.name" />,公文分享,为您提供愉悦的资料分享体验!" />
    <meta name="Keywords" content="公文、公文库、文秘、公文分享、ppt模板下载、简历模板、视频教程、ppt素材库、工作总结、工作计划、调研报告、竞聘演讲稿、会议发言稿、学习心得体会、入党申请书、应用公文、论文范文、范文写作技巧" />
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
        .m-container{
            width: 1210px;
            margin-top: 10px;
            padding: 10px 2px;
        }
        .hot-list{
            height: 285px;
        }
        .hot-list .thumbnail{
            height: 265px;
        }
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
<!-- banner 广告 begin-->
<#include "/index-top-banner.ftl" />
<!-- banner 广告 end-->
<!-- header begin-->
<#include "/index-header.ftl" />
<!-- header end-->
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
<!-- button banner 广告 begin-->
<#include "/index-button-banner.ftl" />
<!-- button banner 广告 end-->
</body>
<script>
    seajs.use("js/search.js?v="+Math.random());
</script>
<script src="js/index.js?v=${ver!'1'}"></script>
<#include "/includes/baidu-zn.ftl" />
</html>