<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <#include "/includes/head.ftl" />
    <meta name="baidu-site-verification" content="v9ouxuGJew" />
    <title>公文分享、公文写作、会议发言稿、工作总结、党政-<@spring.message  code="label.system.name" /></title>
    <meta name="description" content="公文分享,<@spring.message  code="label.system.name" />,为您提供愉悦的资料分享体验!" />
    <meta name="Keywords" content="公文分享、公文库、文秘、公文、ppt模板下载、简历模板、视频教程、ppt素材库、工作总结、工作计划、调研报告、竞聘演讲稿、会议发言稿、学习心得体会、入党申请书、应用公文、论文范文、范文写作技巧" />
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
        .m-account__head {
            width: 80px;
            height: 80px;
            left: 50%;
            top: -12px;
            margin-left: -40px;
            text-align: center;
            position: absolute;
        }
        .m-account__head__default{
            width: 80px;
            height: 80px;
            background: #fff;
            border-radius: 50%;
            -webkit-box-shadow: 0 -3px 5px 1px rgba(0,0,0,.2);
            box-shadow: 0 -3px 5px 1px rgba(0,0,0,.2);
            position: relative;
        }
        m-account__head__default .svg-default-avatar {
            color: #f1eae1;
            font-size: 80px;
            margin-left: -2px;
        }
        m-account__banner {
            margin-top: 90px;
            font-weight: 700;
        }
        .svg-icon {
            width: 1em;
            height: 1em;
            fill: currentColor;
            overflow: hidden;
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
<!--轮播图上方导航栏  一栏-->
<#include "index-nav-top.ftl" />
<div class="container m-container"  style="position: relative;padding-top: 0px;margin-top: 10px;height: 350px;">
    <div class="row">
        <div class="col-sm-8 col-md-8">
            <#include "index-nav-menu.ftl" />
            <#include "index-nav-carousel.ftl" />
        </div>
        <div class="col-sm-4 col-md-4">
            <div class="panel panel-default hot-article" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">
                    <i class="fa fa-th" style="margin-right: 10px"></i> 热门文章
                    <a href="${appHost}/article.jhtml" class="pull-right" >更多<i class="fa fa-angle-double-right"></i></a>
                </div>
                <div class="panel-body">
                    <div class="row">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
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