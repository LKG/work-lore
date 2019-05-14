<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>专题 - <@spring.message  code="label.system.name" /></title>
  <#include "/includes/head.ftl" />
    <link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
    <style>
        .hot-list .thumbnail {
            height: 265px;
        }
    </style>
<#assign template="topic"/>
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
<div class="clear"></div>
<!--轮播图上方导航栏  一栏-->
<div class="clearfix" ></div>
<div class="container m-container"  >
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default hot-article" id="hot-article" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">
                    <i class="fa fa-th" style="margin-right: 10px"></i> 热门专栏
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 hot-list">
                            <div class="thumbnail">
                                <a href="${appHost}/topic.jhtml" target="_blank" alt="工作总结专区">
                                    <img src="https://static.gongwk.com/images/20190426001.png" alt="党建工作、办公室工作、专项工作、行政工作等总结范文。">
                                </a>
                                <div class="caption">
                                    <h4><a href="${appHost}/topic.jhtml"  target="_blank" alt="工作总结专区">工作总结专区</a></h4>
                                    <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="工作总结专区">党建工作、办公室工作、专项工作、行政工作等总结范文。</a></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 hot-list">
                            <div class="thumbnail">
                                <a href="${appHost}/topic.jhtml" target="_blank" alt="述职报告范文">
                                    <img src="https://static.gongwk.com/images/20190426002.png" alt="各个岗位年终述职、述廉、述责、述学等报告及相关范文合集。">
                                </a>
                                <div class="caption">
                                    <h4><a href="${appHost}/topic.jhtml"  target="_blank" alt="述职报告范文">述职报告范文</a></h4>
                                    <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="述职报告范文">各个岗位年终述职、述廉、述责、述学等报告及相关范文合集。</a></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 hot-list">
                            <div class="thumbnail">
                                <a href="${appHost}/topic.jhtml" target="_blank" alt="2019两会专区">
                                    <img src="https://static.gongwk.com/images/20190426003.jpg" alt="2019年两会精神传达提纲、展板、学习资料等">
                                </a>
                                <div class="caption">
                                    <h4><a href="${appHost}/topic.jhtml"  target="_blank" alt="2019两会专区">2019两会专区</a></h4>
                                    <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="2019两会专区">2019年两会精神传达提纲、展板、学习资料等</a></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 hot-list">
                            <div class="thumbnail">
                                <a href="${appHost}/topic.jhtml" target="_blank" alt="党建文秘素材">
                                    <img src="https://static.gongwk.com/images/20190426004.png" alt="范文、表格、模板、PPT等资料。">
                                </a>
                                <div class="caption">
                                    <h4><a href="${appHost}/topic.jhtml" target="_blank" alt="党建文秘素材">党建文秘素材</a></h4>
                                    <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="党建文秘素材">范文、表格、模板、PPT等资料。</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>
<div class="clearfix" ></div>
<div class="container m-container"  >
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default hot-article" id="hot-article" style="box-shadow: 0 2px 8px 0 rgba(0,0,0,0.1);">
                <div class="panel-heading" style="border-left: 3px solid #FF970D;">
                    <i class="fa fa-th" style="margin-right: 10px"></i>工作务实
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 hot-list">
                            <div class="thumbnail">
                                <a href="${appHost}/topic.jhtml" target="_blank" alt="纪检人必备监督执纪规则流程">
                                    <img src="https://static.gongwk.com/images/2019051301.jpg" alt="纪检人必备监督执纪规则流程">
                                </a>
                                <div class="caption">
                                    <h4><a href="${appHost}/topic.jhtml"  target="_blank" alt="纪检人必备监督执纪规则流程">纪检人必备监督执纪规则流程</a></h4>
                                    <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="详解监督执纪规则流程，初核、谈话、函询、立案、审理、问题线索处置超详细流程图，纪检人必备。">详解监督执纪规则流程，初核、谈话、函询、立案、审理、问题线索处置超详细流程图，纪检人必备。</a></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 hot-list">
                            <div class="thumbnail">
                                <a href="${appHost}/topic.jhtml" target="_blank" alt="述职报告范文">
                                    <img src="https://static.gongwk.com/images/2019051302.jpg" alt="案件检查工作流程全套模板">
                                </a>
                                <div class="caption">
                                    <h4><a href="${appHost}/topic.jhtml"  target="_blank" alt="案件检查工作流程全套模板">案件检查工作流程全套模板</a></h4>
                                    <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="案件检查工作流程全套模板">案件检查工作流程全套模板</a></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 hot-list">
                            <div class="thumbnail">
                                <a href="${appHost}/topic.jhtml" target="_blank" alt="初核工作流程记录全套模板">
                                    <img src="https://static.gongwk.com/images/2019051303.jpg" alt="初核工作流程记录全套模板">
                                </a>
                                <div class="caption">
                                    <h4><a href="${appHost}/topic.jhtml"  target="_blank" alt="2019两会专区">初核工作流程记录全套模板</a></h4>
                                    <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="2019两会专区">2初核工作流程记录全套模板</a></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 hot-list">
                            <div class="thumbnail">
                                <a href="${appHost}/topic.jhtml" target="_blank" alt="信访举报工作流程全套模板">
                                    <img src="https://static.gongwk.com/images/2019051304.jpg" alt="信访举报工作流程全套模板">
                                </a>
                                <div class="caption">
                                    <h4><a href="${appHost}/topic.jhtml"  target="_blank" alt="党建文秘素材">信访举报工作流程全套模板</a></h4>
                                    <p><a href="${appHost}/topic.jhtml"  target="_blank" alt="党建文秘素材">信访举报工作流程全套模板</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
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
    seajs.use(["js/search.js?v="+Math.random()]);
</script>
</body>
</html>