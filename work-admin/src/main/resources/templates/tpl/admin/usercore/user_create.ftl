<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>添加用户</title>

</head> 
<body class="page-header-fixed">
	<div class="clearfix" ></div>
	<!------页面header信息 end----->
	<div class="full-container" id="main-container" style="height: 531px;">
		<div id="J_RegisterBox" > 
		<script type="text/javascript">(function(x){x&&(x.className+=" loading")})(document.getElementById("J_RegisterBox"))</script>
		<form class="form-horizontal" id="J_RegisterForm"  action="${appHost}/admin/user/save" method="post" >
			<div class="form-group">
		     <label for="userType" class="col-sm-3 col-xs-3 control-label"><span class="text-danger" >*</span>用户类型</label>
		    <div class="col-sm-9 col-xs-9">
			    <div class="input-group ">
			      <div class="input-group-addon"><i class="fa fa-mobile"></i></div>
			      <select name="userType" class="form-control">
				 <option value="">---</option>
					<@custom.dict dictCode="user_type" >	
						<#list items as model>  
							<option value="${model.itemValue}" >${model.itemName}</option>
						</#list>
					</@custom.dict>
			</select>
			    </div>
			</div>
		  </div>
		  <div class="form-group">
		    <label for="userName" class="col-sm-3 col-xs-3 control-label"><span class="text-danger" >*</span><@spring.message  code="label.login.username" /></label>
		     <div class="col-sm-9 col-xs-9">
			    <div class="input-group">
			      <div class="input-group-addon"><i class="fa fa-user"></i></div>
			      <input type="text" class="form-control" id="userName" name="userName" placeholder="<@spring.message  code="label.user.account" />" />
			    </div>
		    </div>
		  </div>
		
		  <div class="form-group">
		     <label for="userPhone" class="col-sm-3 col-xs-3 control-label"><span class="text-danger" >*</span><@spring.message  code="label.user.phone" /></label>
		    <div class="col-sm-9 col-xs-9">
			    <div class="input-group ">
			      <div class="input-group-addon"><i class="fa fa-mobile"></i></div>
			      <input type="text" class="form-control" id="userPhone" name="userPhone" placeholder="<@spring.message  code="label.user.phone" />" >
			    </div>
			</div>
		  </div>
		  <div class="form-group">
		     <label for="password" class="col-sm-3 col-xs-3 control-label"><span class="text-danger" >*</span><@spring.message  code="label.login.password" /></label>
		    <div class="col-sm-9 col-xs-9">
			    <div class="input-group">
			      <div class="input-group-addon"><i class="fa fa-key"></i></div>
			      <input type="password" class="form-control" id="passWord" name="passWord" placeholder="<@spring.message  code="label.login.password" />" >
			    </div>
		 		<div class="progress" style="margin-bottom:-10px;margin-top:2px;">
		 			<div id="pass_progress" class="progress-bar  progress-bar-striped active" role="progressbar" 
						aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
		 				<span class=""  id="pass_progress_text" ></span>
		 			</div>
		 		</div>
		    </div>
		  </div>
		   <div class="form-group">
		     <label for="retryPassWord" class="col-sm-3 col-xs-3 control-label"><span class="text-danger" >*</span><@spring.message  code="label.user.password.confirm" /></label>
		    <div class="col-sm-9 col-xs-9">
			    <div class="input-group">
			      <div class="input-group-addon"><i class="fa fa-key"></i></div>
			      <input type="password" class="form-control" id="retryPassWord" name="retryPassWord" placeholder="<@spring.message  code="label.user.password.confirm" />" >
			    </div>
		    </div>
		  </div>
		 <!----  
		  <div class="form-group">
		     <label for="phoneCode" class="col-sm-3 col-xs-3 control-label"><span class="text-danger" >*</span>短信码：</label>
		    <div class="col-sm-9 col-xs-9">
			    <div class="input-group">
			      <div class="input-group-addon"><i class="fa fa-comment"></i></div>
			      <input type="text" class="form-control" maxlength="5" id="phoneCode" name="phoneCode" tabindex="5"  placeholder="短信码" />
				  <span class="input-group-btn">
			        <button class="btn btn-default" id="J_PhoneCodeBtn" type="button">获取短信验证码</button>
			      </span>
			    </div>
		    </div>
		  </div>
		 ----->
		  <div class="form-group">
		     <label for="nickName" class="col-sm-3 col-xs-3 control-label"><@spring.message  code="label.user.nickName" /></label>
		    <div class="col-sm-9 col-xs-9">
			    <div class="input-group">
			      <div class="input-group-addon"><i class="fa fa-envelope-o"></i></div>
			      <input type="text" class="form-control" id="nickName" name="nickName" placeholder="<@spring.message  code="label.user.nickName.help" />">
			    </div>
		    </div>
		  </div>
		  <div class="form-group">
		     <label for="userEmail" class="col-sm-3 col-xs-3 control-label"><@spring.message  code="label.user.email" /></label>
		    <div class="col-sm-9 col-xs-9">
			    <div class="input-group">
			      <div class="input-group-addon"><i class="fa fa-envelope-o"></i></div>
			      <input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="<@spring.message  code="label.user.email" />">
			    </div>
		    </div>
		  </div>
		<div class="form-group hide">
		     <label for="userIdcard" class="col-sm-3 col-xs-3 control-label">身份证号</label>
		    <div class="col-sm-9 col-xs-9">
			    <div class="input-group">
			      <div class="input-group-addon"><i class="fa fa-credit-card"></i></div>
			      <input type="text" class="form-control" id="userIdcard" name="userIdcard"  placeholder="身份证号"/>
			    </div>
		    </div>
		  </div>
		  <div class="form-group">
		     <label for="realName" class="col-sm-3 col-xs-3 control-label">真实姓名</label>
		    <div class="col-sm-9 col-xs-9">
			    <div class="input-group">
			      <div class="input-group-addon"><i class="fa fa-credit-card"></i></div>
			      <input type="text" class="form-control" id="userIdcard" name="realName"  placeholder="真实姓名">
			    </div>
		    </div>
		  </div>
		   <div class="form-group">
		     <label for="userQq" class="col-sm-3 col-xs-3 control-label"><@spring.message  code="label.user.qq" /></label>
		    <div class="col-sm-9 col-xs-9">
			    <div class="input-group">
			      <div class="input-group-addon"><i class="fa fa-qq"></i></div>
			      <input type="text" class="form-control" id="userQq" name="userQq" placeholder="<@spring.message  code="label.user.qq" />" />
			    </div>
		    </div>
		  </div>
		   <div class="form-group">
		     <div class="col-sm-3 col-xs-3">
		   	 <!--	<input type="hidden" name="format" value="json" />-->
		   	 <#if sub_token??>
		   	 <input type="hidden" name="sub_token" value="${sub_token}" />
		   	 </#if>
		   	 </div>
		   	  <div class="col-sm-9 col-xs-9">
		     <button  id="J_RegisterSubmit" class="btn btn-danger"  data-loading-text="注册中..."   tabindex="5" >
			   <@spring.message  code="label.default.button.save" />
			 </button> 
			 </div>
			 
		   </div>
		</form>
		</div>
	</div>
  	<!------seajs.config 引用信息 begin----->
	<#include "/includes/seajs.config.ftl" />
	<!------seajs.config   引用信息 end----->
	<script>
  	seajs.use(["${appHost}/js/admin/usercore/user_details.js?v="+Math.random(),"${appHost}/js/common/passwordCheck.js?v=${ver!'1'}"]);
	</script>
</body>
</html>