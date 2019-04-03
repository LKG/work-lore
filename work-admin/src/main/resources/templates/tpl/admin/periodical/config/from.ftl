<form class="form-horizontal" action="" id="main-form" method="post"  >
	 <input type="hidden" name="id" value="<#if result.id??>${result.id}</#if>"  />
	 <input type="hidden" name="cityIds" id="cityIds" value="<#if result.id??>${result.cityIds}</#if>" />
 <div class="form-group">
      <div class=" col-xs-6">
      	<div class="input-group">
	    <span class="input-group-addon">地区</span>
	     <p class="form-control form-control-static"" ><#if result.cityName??>${result.cityName}</#if></p>
	  </div>
    </div>
    <div class=" col-xs-6">
      	<div class="input-group">
	    <span class="input-group-addon">年份</span>
	     <p class="form-control form-control-static"" ><#if result.year??>${result.year}</#if></p>
	  </div>
    </div>
  </div>
 <div class="form-group">
      <div class=" col-xs-12">
      	<div class="input-group">
	    <span class="input-group-addon">名称</span>
	    <input type="text" class="form-control" id="periodicalName"   value="<#if result.name??>${result.name}</#if>"
	     name="name" placeholder="名称"/>
	  </div>
    </div>
  </div>
   <div class="form-group">
	<label for="remark" class="col-xs-1 control-label">含:</label>
	<div class="col-xs-11">
	<p class="form-control form-control-static"" ><#if result.cityNames??>${result.cityNames}</#if></p>	
	</div>
  </div>
   <div class="form-group">
	<label for="remark" class="col-xs-1 control-label">描述</label>
	<div class="col-xs-10">
		<textarea class="form-control" style="width: 400px;" id="periodicalDesc" name="periodicalDesc" placeholder="期刊描述" rows="3"><#if result.periodicalDesc??>${result.periodicalDesc}</#if></textarea>
	</div>
  </div>
  
  <div class="form-group">
	<div class="col-sm-offset-2 col-sm-10">
	  <button type="button" id="main-btn-save"  data-loading-text="提交中..."  class="btn btn-primary"><@spring.message  code="label.default.button.save" /></button>
	</div>
  </div>
</form>