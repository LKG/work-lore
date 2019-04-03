<form class="form-horizontal" action="${appHost}/api/area/subGeneral.json" id="J_MainForm"  method="post"  >
 <div class="form-group">
	<label for="resourceCode" class="col-sm-2 control-label">标识</label>
	<div class="col-sm-4">
		<#if result.code??>
			 <p class="form-control-static"><code>${result.code}</code></p>
		<#else>
		 <input type="text" class="form-control" id="areaCode"  value="${result.code}" name="code" placeholder="">
		</#if>
	</div>
	<label for="resourceName" class="col-sm-2 control-label">名称</label>
	<div class="col-sm-4">
	  <input type="text" class="form-control" id="areaName" value="<#if result.name??>${result.name}</#if>" name="name" placeholder="模块名称">
	</div>
 </div>
  <div class="form-group" >
	<label for="parentName" class="col-sm-2 control-label">上级节点</label>
	<div class="col-sm-10">
		<div class="input-group">
		 <input type="hidden" class="form-control" id="parentCode" value="0" name="parentCode" />
         <input type="text" class="form-control" id="parentName" value="" name="parentName" placeholder="上级节点"/>
          <div class="input-group-btn">
            <button type="button" class="btn btn-danger"><@spring.message  code="label.default.button.query" /></button>
          </div>
        </div>
	</div>
 </div>
  <div class="form-group">
	<label for="resourceDesc" class="col-sm-2 control-label" >描述</label>
	<div class="col-sm-10">
	<textarea class="form-control" id="resourceDesc" name="resourceDesc" placeholder="描述" rows="3"><#if result.resourceDesc??>${result.resourceDesc}</#if></textarea>
	</div>
  </div>
  <div class="form-group">
	<div class="col-sm-offset-2 col-sm-10">
	  <button type="submit" class="btn btn-primary"><@spring.message  code="label.default.button.save" /></button>
	  <button type="button" class="btn btn-danger"><@spring.message  code="label.default.button.cancel" /></button>
	</div>
  </div>
</form>