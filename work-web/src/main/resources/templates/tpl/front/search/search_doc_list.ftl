<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title>文档列表-<@spring.message  code="label.system.name" /></title>
  <#include "/includes/head.ftl" />
<#include "/includes/laypage-css.ftl" />
    <style>
        .tab-pane{
            padding-top:30px;
        }
        .panel-heading {
            padding: 5px 13px;
        }
        .panel-heading  h4{
            border-left: 2px solid #189ae5;
            padding-left:15px;
            margin-left:-13px;
        }
        .main-panel .panel-heading{
            padding: 0px 15px;
        }
        .main-panel{
            margin-top: -20px;
        }
        .table td{white-space:nowrap;overflow:hidden; text-overflow:ellipsis; }
        .table  {table-layout:fixed;}
        .table th{vertical-align:middle !important;text-align: center; font-size:14px;}
        .Wdate{
            height: 32px !important;
            padding: 3px 1px !important;
            border: 1px solid #ccc !important;
        }
        .form-search select{
            padding: 6px 3px  !important;
        }
        .form-inline .form-group,.form-inline .form-control{
            width: 100px;
            display: inline;
        }
        @media (max-width: 900px){
            .panel-heading{
                padding: 10px 0px !important;
            }
        }
        .laypage_main{
            clear: none !important;
        }
        .box___33F08 {
            position: relative;
            z-index: 9;
            background: #fff;
            margin-bottom: 10px;
            -webkit-box-shadow: 0px 1px 5px 0 rgba(0, 0, 0, 0.15);
            box-shadow: 0px 1px 5px 0 rgba(0, 0, 0, 0.15);
            border: 1px solid #e5e5e5;
            border-bottom: none;
        }
        .line___1Xmcw {
            position: relative;
            line-height: 36px;
            border-bottom: 1px solid #e5e5e5;
        }
        .key___3ciqD {
            display: table-cell;
            vertical-align: middle;
            width: 125px;
            min-width: 125px;
            padding: 6px 0 6px 18px;
            white-space: nowrap;
            overflow: hidden;
            color: #333;
            border-right: 1px solid #e5e5e5;
        }
        .value___1RNtH {
            position: relative;
            display: table-cell;
            width: 100%;
            vertical-align: top;
            background-color: #fff;
        }
        .list___2n8rw:first-child {
            border-top: none;
        }
        .list___2n8rw {
            position: relative;
            padding: 8px 70px 8px 0;
            -webkit-box-sizing: content-box;
            box-sizing: content-box;
            height: 32px;
            overflow: hidden;
            border-top: 1px solid #e5e5e5;
        }
        .listLabel___updLg {
            float: left;
            margin: 5px 5px 5px 20px;
            height: 22px;
            line-height: 22px;
        }
        .listTm___R6Tqy a {
            margin-right: 5px;
        }
        .listCnt___1U2LQ {
            overflow: hidden;
        }
        .list___2n8rw a {
            float: left;
            margin: 5px 40px 5px 20px;
            height: 22px;
            line-height: 22px;
        }
    </style>
<#assign template="docs"/>
</head>
<body class="page-header-fixed">
<!-- toolbar begin-->
<#include "/index-toolbar.ftl"/>
<!-- toolbar end-->
<!-----------------------------------------顶部-------------->
<!-- header begin-->
<#include "/index-header.ftl" />
<!-- banner 广告 begin-->
  <#include "/index-top-banner.ftl" />
<!-- banner 广告 end-->
<!-- 搜索框 begin-->
<#include "/index-top-search.ftl" />
<!-- 搜索框 end-->
<!--轮播图上方导航栏  一栏-->
<#include "/index-nav-top.ftl" />
<div class="container" style="margin-top: 15px;">
    <ol class="breadcrumb">
        <li><a href="${appHost}/"><@spring.message  code="label.system.index" /></a></li>
        <li class="active">文档搜索列表</li>
    </ol>
</div>
<div class="container" >
    <div class="row">
        <div class="alert alert-info" style="margin-bottom: 10px;margin-top: -10px;" role="alert">
            为您找到 ${result.totalElements}条结果
        </div>
        <div class="box___33F08">
            <div class="line___1Xmcw clearfix">
                <div class="key___3ciqD"><span class="text"><i class="fa fa-tags"></i>分类</span></div>
                <div class="value___1RNtH">
                    <div class="list___2n8rw listTm___R6Tqy">
                        <div class="clearfix">
                            <a href="https://jiaoyi.zhifuzi.com/01.html"><span class="num">01<!-- --> 类</span>化工原料</a>
                            <a href="https://jiaoyi.zhifuzi.com/02.html"><span class="num">02<!-- --> 类</span>油漆涂料</a>
                            <a href="https://jiaoyi.zhifuzi.com/03.html"><span class="num">03<!-- --> 类</span>化妆清洁</a>
                            <a href="https://jiaoyi.zhifuzi.com/04.html"><span class="num">04<!-- --> 类</span>工业油脂</a>
                            <a href="https://jiaoyi.zhifuzi.com/05.html"><span class="num">05<!-- --> 类</span>药品制剂</a>
                            <a href="https://jiaoyi.zhifuzi.com/06.html"><span class="num">06<!-- --> 类</span>五金器具</a>
                            <a href="https://jiaoyi.zhifuzi.com/07.html"><span class="num">07<!-- --> 类</span>机械机器</a>
                            <a href="https://jiaoyi.zhifuzi.com/08.html"><span class="num">08<!-- --> 类</span>手工用具</a>
                            <a href="https://jiaoyi.zhifuzi.com/09.html"><span class="num">09<!-- --> 类</span>电子电器</a>
                            <a href="https://jiaoyi.zhifuzi.com/10.html"><span class="num">10<!-- --> 类</span>医疗器械</a>
                            <a href="https://jiaoyi.zhifuzi.com/11.html"><span class="num">11<!-- --> 类</span>家用电器</a>
                            <a href="https://jiaoyi.zhifuzi.com/12.html"><span class="num">12<!-- --> 类</span>车船配件</a>
                            <a href="https://jiaoyi.zhifuzi.com/13.html"><span class="num">13<!-- --> 类</span>火器烟花</a>
                            <a href="https://jiaoyi.zhifuzi.com/14.html"><span class="num">14<!-- --> 类</span>珠宝饰品</a>
                            <a href="https://jiaoyi.zhifuzi.com/15.html"><span class="num">15<!-- --> 类</span>乐器乐辅</a>
                            <a href="https://jiaoyi.zhifuzi.com/16.html"><span class="num">16<!-- --> 类</span>文具办公</a>
                            <a href="https://jiaoyi.zhifuzi.com/17.html"><span class="num">17<!-- --> 类</span>橡塑制品</a>
                            <a href="https://jiaoyi.zhifuzi.com/18.html"><span class="num">18<!-- --> 类</span>皮具制品</a>
                            <a href="https://jiaoyi.zhifuzi.com/19.html"><span class="num">19<!-- --> 类</span>建筑材料</a>
                            <a href="https://jiaoyi.zhifuzi.com/20.html"><span class="num">20<!-- --> 类</span>家具工艺</a>
                            <a href="https://jiaoyi.zhifuzi.com/21.html"><span class="num">21<!-- --> 类</span>厨具日用</a>
                            <a href="https://jiaoyi.zhifuzi.com/22.html"><span class="num">22<!-- --> 类</span>缆绳帐篷</a>
                            <a href="https://jiaoyi.zhifuzi.com/23.html"><span class="num">23<!-- --> 类</span>线纱丝纺</a>
                            <a href="https://jiaoyi.zhifuzi.com/24.html"><span class="num">24<!-- --> 类</span>家用纺品</a>
                            <a href="https://jiaoyi.zhifuzi.com/25.html"><span class="num">25<!-- --> 类</span>服装鞋帽</a>
                            <a href="https://jiaoyi.zhifuzi.com/26.html"><span class="num">26<!-- --> 类</span>缝纫用品</a>
                            <a href="https://jiaoyi.zhifuzi.com/27.html"><span class="num">27<!-- --> 类</span>地毯席垫</a>
                            <a href="https://jiaoyi.zhifuzi.com/28.html"><span class="num">28<!-- --> 类</span>运动器械</a>
                            <a href="https://jiaoyi.zhifuzi.com/29.html"><span class="num">29<!-- --> 类</span>食品鱼肉</a>
                            <a href="https://jiaoyi.zhifuzi.com/30.html"><span class="num">30<!-- --> 类</span>食品佐料</a>
                            <a href="https://jiaoyi.zhifuzi.com/31.html"><span class="num">31<!-- --> 类</span>生鲜农产</a>
                            <a href="https://jiaoyi.zhifuzi.com/32.html"><span class="num">32<!-- --> 类</span>啤酒饮料</a>
                            <a href="https://jiaoyi.zhifuzi.com/33.html"><span class="num">33<!-- --> 类</span>酒精饮料</a>
                            <a href="https://jiaoyi.zhifuzi.com/34.html"><span class="num">34<!-- --> 类</span>烟草制品</a>
                            <a href="https://jiaoyi.zhifuzi.com/35.html"><span class="num">35<!-- --> 类</span>广告商业</a>
                            <a href="https://jiaoyi.zhifuzi.com/36.html"><span class="num">36<!-- --> 类</span>金融经纪</a>
                            <a href="https://jiaoyi.zhifuzi.com/37.html"><span class="num">37<!-- --> 类</span>修理安装</a>
                            <a href="https://jiaoyi.zhifuzi.com/38.html"><span class="num">38<!-- --> 类</span>通讯服务</a>
                            <a href="https://jiaoyi.zhifuzi.com/39.html"><span class="num">39<!-- --> 类</span>运输旅行</a>
                            <a href="https://jiaoyi.zhifuzi.com/40.html"><span class="num">40<!-- --> 类</span>加工服务</a>
                            <a href="https://jiaoyi.zhifuzi.com/41.html"><span class="num">41<!-- --> 类</span>教育娱乐</a>
                            <a href="https://jiaoyi.zhifuzi.com/42.html"><span class="num">42<!-- --> 类</span>科技研究</a>
                            <a href="https://jiaoyi.zhifuzi.com/43.html"><span class="num">43<!-- --> 类</span>餐饮住宿</a>
                            <a href="https://jiaoyi.zhifuzi.com/44.html"><span class="num">44<!-- --> 类</span>医疗美容</a>
                            <a href="https://jiaoyi.zhifuzi.com/45.html"><span class="num">45<!-- --> 类</span>法律安全</a>
                            <div class="ext___nLVLX"><span class="more___9ECHY">更多<i></i></span></div>
                        </div>
                        <div class="list___2n8rw">
                            <div class="clearfix">
                                <span class="listLabel___updLg">涉及群组<!-- -->:</span>
                                <div class="listCnt___1U2LQ">
                                    <a href="/1401.html">1401贵重金属及其合金</a>
                                    <a href="/1402.html">1402贵重金属盒</a>
                                    <a href="/1403.html">1403珠宝，首饰，宝石及贵重金属制纪念品</a>
                                    <a href="/1404.html">1404钟，表，计时器及其零部件</a>
                                </div>
                                <div class="ext___nLVLX"><span class="more___9ECHY">更多<i></i></span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="line___1Xmcw clearfix">
                <div class="key___3ciqD"><span class="text"><i class="fa fa-group"></i>资源格式</span></div>
                <div class="value___1RNtH">
                    <div class="list___2n8rw">
                        <div class="clearfix">
                           <#list ["doc","ppt","pdf","图片"] as model>
                               <a href="#">${model!''}</a>
                           </#list>
                        </div>
                    </div>
                </div>
            </div>
            <div class="line___1Xmcw clearfix">
                <div class="key___3ciqD"><span class="text"><i class="fa fa-briefcase"></i>上架时间</span></div>
                <div class="value___1RNtH">
                    <div class="list___2n8rw">
                        <div class="clearfix">
                            <#list ["三天内","一周内","一个月内","最近半年","最近一年","最近三年"] as model>
                                <a href="#">${model!''}</a>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="">
                <ul id="my-tab-rule" class="myNavTab nav nav-tabs" role="tablist">
                    <li role="presentation" style="cursor:pointer" class="active">
                        <a id="my-tab-all" data-key="">
                            全部 (共<code id="paginationTotal" >${result.totalElements}</code> 篇)
                        </a>
                    </li>
                </ul>
            </div>
            <div class="panel-body">
                <!---文档begin--->
                <div class="col-md-12  col-xs-12">
                    <div class="row">
                        <form class="form-search form-inline" id="search_form">
                            <input type="hidden" name="page" id="page" value="1">
                        </form>
                    </div>
                    <div  class="bs-table-tbody"  >
                        <ul class="list-group" id="table-tbody"   >
                              <#if (result.content?size<=0) >
	          		               <tr ><td class="text-center" scope="row" colspan='3' ><@spring.message  code="label.default.empty" /></td></tr>
                              <#else>
                                  <#list result.content as doc>
                                      <li class="list-group-item">
                                          <i class="fa fa-${doc.fileHeader!''}" style="margin-right: 10px;"></i>
                                          <a class="item-href"  href="${appHost}/doc/${doc.id!''}.jhtml"
                                             target="_blank" >${doc.shortTitle!''}</a>
                                          <span >阅读量：<code>${doc.hits!'0'}</code></span>
                                          <span class="pull-right">${doc.pushTime!''}</span>
                                      </li>
                                  </#list >
                              </#if>
                        </ul>
                    </div>
                    <!-----分页-begin---->
                    <div  id="table-pagination" data-totalPages="${result.totalPages}" data-number="${result.number}" style="margin-top: -15px;"  class="clearfix"></div>
                    <!-----分页-end---->

                </div>
                <!---文档end--->
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
<!------footer信息 end----->
  	<#include "/includes/datePicker.ftl" />
<script>
    seajs.use(["js/cms/doc-search.js?v="+Math.random(),"js/search.js?v="+Math.random()]);
</script>
</body>
</html>