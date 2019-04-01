<#include "/includes/taglib.ftl" />
<!-- List group begin-->
<!-- user media begin-->
<div class="media">
	<div class="media-left user-img">
	 <a href="${appHost}/userinfo/showImg.jhtml" class="thumbnail" target="_top">
	   <img class="media-object img-circle" id="headImgUrl" src="<@shiro.principal property='headImgUrl' defaultValue='' />" alt="...">
	 </a>
	</div>
	<div class="media-body">
	 <h4 class="media-heading"><@shiro.principal property='nickName' defaultValue='匿名' /></h4>
	  <p >积分<code><@shiro.principal property='userPoint' defaultValue='0' /></code></p>
		<botton type="button"  style="display: none;" class="btn btn-default"  id="sign-btn" role="button">签到</botton>
    </div>
</div>
<!-- user media end-->