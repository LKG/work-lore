<header id="header">
    <div class="header-box">
        <ul class="header-left" style="display: none;">
            <li class="bj">
                北京
                <i class="ci-leftll">
                    <s class="jt">◇</s>
                </i>
                <div class="bj-1">
                    <a href="">北京</a><a href="">上海</a><a href="">天津</a><a href="">重庆</a><a href="">河北</a><a href="">山西</a><a href="">河南</a><a href="">辽宁</a><a href="">吉林</a><a href="">黑龙江</a><a href="">浙江</a><a href="">江苏</a><a href="">山东</a><a href="">安徽</a><a href="">内蒙古</a><a href="">湖北</a><a href="">湖南</a><a href="">广东</a><a href="">广西</a><a href="">江西</a><a href="">四川</a><a href="">海南</a><a href="">贵州</a><a href="">云南</a><a href="">西藏</a><a href="">陕西</a><a href="">甘肃</a><a href="">青海</a><a href="">宁夏</a><a href="">新疆</a><a href="">台湾</a><a href="">香港</a><a href="">澳门</a><a href="">海外</a><a href="">钓鱼岛</a>
                </div>
            </li>
        </ul>
        <ul class="navbar-menu pull-left" >
            <li class="dorpdown" >
                <p class="sn-back-home"><i class="fa fa-home"></i><a href="${appHost}/">首页</a></p>
            </li>
        </ul>
        <ul class="header-right">
            <@shiro.guest>
            <li class="denglu"> <a href="${appHost}/login.jhtml">您好，请登录</a>
                <a id="J_RegisterLink1" class="red" href="${appHost}/regist.jhtml" target="_blank" tabindex="8"><@spring.message  code="label.register.button" /></a>
            </li>
            </@shiro.guest>
            <@shiro.user ><!--有用户登录&ndash;&gt;-->
             <li class="denglu">
                 <a class="header-logo-invertocat" href="${appHost}/userinfo.jhtml" aria-label="Homepage" >
                     您好， <@shiro.authenticated><@shiro.principal property="nickName" /> </@shiro.authenticated>
                 </a>
                 <a class="sn-login" href="${appHost}/logout.jhtml" target="_top"><@spring.message  code="label.default.logout" /></a>
             </li>
           <li class="shu"></li>
            <@shiro.hasRole name="admin" >
				<li class="sn-sitemap">
                    <a class="header-logo-invertocat" href="${appHost}/admin.jhtml" >
                        管理后台
                    </a>
                </li>
             <li class="shu"></li>
            </@shiro.hasRole >
            </@shiro.user>
            <li class="shu"></li>
            <li class="shu"></li>
            <li class="my bj">
                <a href="#">我的</a>
                <i class="ci-right ">
                    <s class="jt">◇</s>
                </i>
                <div class="my1">
                    <div class="my2">
                        <img src="${appHost}/images/no-img_mid_.jpg">
                        <div class="my3">
                            <@shiro.guest>  <h3 class="neirong2"><a href="${appHost}/login.jhtml">您好，请登录</a></h3>  </@shiro.guest>
                            <p class="neirong2"><a href="">优惠卷 丨 消息</a></p>
                        </div>
                    </div>
                    <ul class="neirong2">
                        <li><a href="${appHost}/userinfo/orders.jhtml">带处理订单</a></li>
                        <li><a href="${appHost}/userinfo/orders.jhtml">我的订单</a></li>
                    </ul>
                </div>
            </li>
            <li class="shu"></li>
            <li class="guanzhu bj">
                <a href="#">关注</a>
                <i class="ci-right ">
                    <s class="jt">◇</s>
                </i>
                <div class="guanzhu1"><img style="width: 140px;" src="${appHost}/images/qq_vip.jpg"></div>
            </li>
            <li class="shu"></li>
            <li class="kehu bj">
                <a href="#">客户服务</a>
                <i class="ci-right ">
                    <s class="jt">◇</s>
                </i>
                <div class="kehu1">
                    <h3 class="neirong2">客户</h3>
                    <ul class="kehu2">
                        <li><a href="${appHost}/helps.jhtml">帮助中心</a></li>
                        <li><a  href="${appHost}/helps.jhtml">售后服务</a></li>
                        <li><a  href="${appHost}/helps.jhtml">在线客服</a></li>
                    </ul>
                </div>
            </li>
            <li class="shu"></li>
            <li class="daohang">
                <a href="#">网站导航</a>
                <i class="ci-right ">
                    <s class="jt">◇</s>
                </i>
                <div class="aa" >
                    <div class="neirong">
                        <h4 class="neirong2">特色主题</h4>
                    </div>
                    <div class="neirong neirong3">
                        <h4 class="neirong2">更多精选</h4>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</header>