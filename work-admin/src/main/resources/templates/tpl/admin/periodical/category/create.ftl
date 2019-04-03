<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>分类信息-明细</title>
<style>
</style>
</head> 
<body class="page-header-fixed">
	<div class="clearfix"></div>
	<div class="container-fluid">
	   <div class="row">
	   	 <div class="col-xs-12">
			<form class="form-horizontal" action="" id="J_CreateForm"  method="post"  >
			 <div class="form-group">
				<label for="code" class="col-sm-2 control-label">标识</label>
				<div class="col-sm-4">
					<#if result.categoryId??>
						<input type="hidden" class="form-control" id="categoryId"  value="${result.categoryId}" name="categoryId" >
					</#if>
				 	<input type="hidden" class="form-control" id="level"  value="${result.level}" name="level" >
					 <input type="text" class="form-control" id="categoryCode" value="${result.categoryCode}" name="categoryCode" placeholder="">
				</div>
				<label for="parentName" class="col-sm-2 control-label">上级</label>
				<div class="col-sm-4">
					<div class="input-group">
					 <input type="hidden" class="form-control" id="parentId" value="<#if result.parentId??>${result.parentId!'0'}</#if>" name="parentId" />
			         <input type="text" class="form-control" readonly id="parentName" value="${result.parentName!'无'}" name="parentName" placeholder="上级节点"/>
			        </div>
				</div>
			 </div>
			  <div class="form-group" >
				<label for="parentName" class="col-sm-2 control-label">名称</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="categoryName" value="<#if result.categoryName??>${result.categoryName}</#if>" name="categoryName" placeholder="名称"/>
				</div>
			 </div>
			  <div class="form-group">
				<label for="resourceDesc" class="col-sm-2 control-label" >描述</label>
				<div class="col-sm-10">
				<textarea class="form-control" id="remark" name="remark" placeholder="描述" rows="3"><#if result.remark??>${result.remark}</#if></textarea>
				</div>
			  </div>
			  <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				  <button type="button" id="J_Submit"  class="btn btn-primary"><@spring.message  code="label.default.button.save" /></button>
				</div>
			  </div>
			</form>
		 </div>
	   </div>
	  </div>
  <!------seajs.config 引用信息 begin----->
  	<#include "/includes/seajs.config.ftl" />
  	<!------seajs.config   引用信息 end----->
<script>
  	seajs.use("js/admin/periodical/category/create.js?v="+Math.random());
</script>
 </body>
</html>