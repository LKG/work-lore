<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>版权申诉 - <@spring.message  code="label.system.name" /></title>
  <#include "/includes/head.ftl" />
    <link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
<#assign template="contact"/>
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
<div class="container" style="margin-top: 35px" id="main-container">
    <div class="panel panel-info">
        <div class="panel-body">
            <form class="form-horizontal">
                <div class="form-group">
                    <div class="col-xs-4">
                        <div class="panel panel-danger">
                            <div class="panel-heading">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1"/>
                                        1、要求网站立即删除被侵权的文档
                                    </label>
                                </div>
                            </div>
                            <div class="panel-body"  style="height: 170px" >
                                <p>
                                    一键提交删除申请，我们收到后会尽快安排删除，无需复杂的流程和资质证明，您的心声就是最好的证明。网站承诺，只要侵权，一定删除该文档，保护原创作者合法权益是我们应尽的义务！
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked />
                                        2、认领被侵权文档，获取既往收益 （网站推荐）<i class="fa fa-fire text-danger"></i>
                                    </label>
                                </div>
                            </div>
                            <div class="panel-body" style="height: 170px;"  >
                                <p>
                                    认领文档的好处：认领后，被认领文档的历史下载收益和未来的下载收益均归您所有;如果有人提出悬赏提问或者相关付费咨询、合作等信息，我们优先短信通知您，让您第一时间获得潜在商机！
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" />
                                        3、将被侵权文档设为免费下载分享分档
                                    </label>
                                </div>
                            </div>
                            <div class="panel-body" style="height: 170px;" >
                                <p>
                                    将您自己的原创文档设置成免费分享、免费下载文档后，所有用户可免费下载您的文档，实现知识免费共享。

                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-1"> </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">申诉资源信息：</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">标题：${result.periodicalName!''}</p>
                        <p class="form-control-static"> 网址：${appHost}/doc/${result.id!''}.jhtml</p>
                    </div>
                </div>
               <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">版权申诉理由：<b class="text-danger">*</b></label>
                    <div class="col-sm-10">
                        <textarea   placeholder="请详细阐述您的作品情况及申诉理由，务必真实有效，谢谢！"  class="form-control" rows="3"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">申诉证据文件：</label>
                    <div class="col-sm-10">

                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary"><@spring.message  code="label.default.button.confirm" /></button>
                        <button type="submit" class="btn btn-default"><@spring.message  code="label.default.button.cancel" /></button>
                    </div>
                </div>

            </form>
            <div class="alert alert-danger" role="alert">
                如您有疑问，可以直接咨询QQ:
                <a href="tencent://message/?uin=790944904&site=${appHost}&menu=yes" title="点击交谈" >
                    790944904 <i class="fa fa-qq"></i>
                </a>
                在线时间：09:00-18:00
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
    seajs.use(["js/search.js?v="+Math.random(),"js/group.js?v="+Math.random()]);
</script>
</body>
</html>