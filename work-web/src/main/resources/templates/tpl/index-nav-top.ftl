<div class="pull-container" style="position: relative;padding-top: 0px;margin-top: 7px;">
    <div class="m-container container" style="position: relative;padding-top: 15px;">
        <!-- 搜索框 begin-->
        <#include "index-top-search.ftl" />
        <!-- 搜索框 end-->
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
        </div>
        <div id="treasure" style="display: none;">
                <a id="J_promo_lk" class="promo_lk" href="${appHost}/group.jhtml" target="_blank" aria-label="推广位" style="background-image: url(&quot;https://img14.360buyimg.com/babel/jfs/t1/80462/17/6134/23893/5d43ad7eEdb75e03b/ae492f020abda4a5.png.webp&quot;);"></a>
        </div>
    </div>
    <div id="nav-v">
        <div class="nav-img" ></div>
        <div class="nav-imgs"></div>
    </div>
</div>


