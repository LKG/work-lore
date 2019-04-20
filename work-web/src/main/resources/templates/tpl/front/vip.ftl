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
        .tab-pane{
            padding-top:30px;
        }
        .panel-heading #btn-focus,.panel-heading #btn-cart,.panel-heading #btn-vip,.panel-heading #btn-down{
            margin-top: -27px;
        }
        .panel-heading  h4{
            border-left: 2px solid #189ae5;
            padding-left:15px;
            margin-left:-13px;
        }
        #row-main{
            box-shadow: 0 1px 5px rgba(4,0,0,.1);
        }
        #btn-praise{
            margin-top: -13px;
        }
        .isStuck{
            z-index: 99;
            /*min-width: 77%;*/
            left: 15px;
            right: 15px;
        }
        .originPrice{
            text-decoration: line-through;
        }
    </style>
    <#assign template="vip"/>
</head>
<body class="page-header-fixed">
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
<#include "/index-top-search.ftl" />
<!-- 搜索框 end-->
<!--轮播图上方导航栏  一栏-->
<#include "/index-nav-top.ftl" />
<div class="clearfix"></div>
<div class="container m-container" style="margin-top: 15px;" >
    <div class="row"  id="row-main">
        <div class="panel panel-default">
            <div class="panel-heading" id="doc-nav-top" >
                <h4>加入VIP</h4>

            </div>
            <div class="panel-body">
                <!---文档左侧begin--->
                <div class="col-md-9  col-xs-9">
                    <div class="row">

                    </div>
                </div>
                <!---文档左侧end--->
                <!---文档右侧begin--->
                <div class="col-md-3 col-xs-3"  >
                    <!---面板begin--->
                    <#include "/front/doc/template/doc-vips.ftl" />
                    <!---面板end--->
                </div>
                <!---文档右侧end--->
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
<#include "/includes/jquery.ftl" />
<#include "/includes/stickUp.ftl" />
<script type="text/javascript">
    //initiating jQuery
    jQuery(function($) {
        $(document).ready( function() {
            $('#doc-nav-top').stickUp();
        });
    });

</script>
<script>
    seajs.use(["js/doc/init.js?v="+Math.random(),"js/search.js?v="+Math.random()]);
</script>
</body>
</html>