<div id="navv">
    <div class="nav-img" style="background:url(https://static.gongwk.com/images/navv.jpg) repeat-x"></div>
</div>
<div class="focus" >
    <div class="focus-a">
        <div class="fouc-font"><a href="">全部分类</a> </div>
    </div>
    <div class="focus-b">
        <ul>
 <#--           <li><a href="${appHost}/vip.jhtml"">VIP会员</a></li>-->
            <li><a href="${appHost}/" class="active"><i class="fa fa-home"></i><@spring.message  code="label.system.index" /></a></li>
            <li><a href="${appHost}/article.jhtml">免费资料</a></li>
            <li><a href="${appHost}/docs.jhtml">文库精选</a></li>
            <li><a href="${appHost}/made.jhtml" class="hide"> 定制</a></li>
            <li><a href="${appHost}/topic.jhtml">专栏</a></li>
            <li><a href="${appHost}/group.jhtml">QQ群交流<i class="fa fa-fire text-danger"></i></a></li>
        </ul>
    </div>
        <#include "index-nav-menu.ftl" />
</div>