<#include "/includes/taglib.ftl" />
<!-- List group begin-->
<div class="list-group text-center user-menu" style="min-height: 368px;">
	 <a href="${appHost}/userinfo.jhtml" class="list-group-item <#if template=='userinfo'>active</#if>">
	 	<i class="fa fa-credit-card"></i>   账号主页
	 </a>
	  <a href="${appHost}/userinfo/safe.jhtml" class="list-group-item <#if template=='safe'>active</#if>">
		 <i class="fa fa-bullhorn"></i>   账户安全
	 </a>
	 <a href="${appHost}/userinfo/changePwd.jhtml" class="list-group-item <#if template=='changePwd'>active</#if>">
		 <i class="fa fa-key"></i>   修改密码
	 </a>
    <a href="${appHost}/userinfo/orders.jhtml" class="list-group-item <#if template=='orders'>active</#if>" >
        <i class="fa fa-shopping-bag"></i>我的订单
    </a>
    <!--
	 <a href="${appHost}/userinfo/receivers.jhtml" class="list-group-item <#if template=='receivers'>active</#if>" >
     	<i class="fa fa-fighter-jet"></i>收货地址
     </a>
     <a href="${appHost}/userinfo/levels.jhtml" class="list-group-item <#if template=='level'>active</#if>">
		 <i class="fa fa-legal"></i>   我的级别
	 </a>
		-->
	 <a href="${appHost}/userinfo/follows.jhtml" class="list-group-item <#if template=='follows'>active</#if>"">
		 <i class="fa fa-star"></i>   我的收藏
	 </a>
    <!--
	  <a href="${appHost}/userinfo/receivers.jhtml" class="list-group-item ">
		 <i class="fa fa-star"></i>   我的关注
	 </a>
	 <a href="${appHost}/userinfo/messages.jhtml" class="list-group-item <#if template=='message'>active</#if>">
	 	<i class="fa fa-comments"></i>  消息中心
	 </a> -->
</div>
<!-- List group end-->