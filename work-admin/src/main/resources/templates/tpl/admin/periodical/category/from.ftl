<form class="form-horizontal" action="" id="J_MainForm"  method="post"  >
 <div class="form-group">
	<label for="code" class="col-sm-2 control-label">标识</label>
	<div class="col-sm-4">
	 	<input type="hidden" class="form-control" id="categoryId"  value="${result.categoryId}" name="categoryId" >
	 	<input type="hidden" class="form-control" id="level"  value="${result.level}" name="level" >
	 	<input type="hidden" class="form-control" id="status"  value="${result.status}" name="status" >
		<input type="text" class="form-control" id="categoryCode" readonly value="${result.categoryCode}" name="categoryCode" placeholder="">
	</div>
	<label for="parentName" class="col-sm-2 control-label">上级节点</label>
	<div class="col-sm-4">
		<div class="input-group">
		 <input type="hidden" class="form-control" id="parentId" value="<#if result.parentId??>${result.parentId!'0'}</#if>" name="parentId" />
         <input type="text" class="form-control" readonly id="parentName" value="${result.parentName!'无'}" name="parentName" placeholder="上级节点"/>
          <div class="input-group-btn">
            <button type="button" class="btn btn-danger"><@spring.message  code="label.default.button.query" /></button>
          </div>
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
	  <button type="button" id="child-${result.categoryId}" data="${result.categoryId}"  class="<#if !result.categoryId??>hide</#if>  btn-add-child btn btn-primary">
	   <i class="fa fa-plus"></i>添加子节点
	  </button>
	 
	</div>
  </div>
</form>