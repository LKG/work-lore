<form class="form-horizontal"  id="J_MainForm"  method="post"  >
 <div class="form-group">
	<label for="noticeName" class="col-sm-2 control-label">公告名称</label>
	<div class="col-sm-10">
	<input type="hidden" name="noticeId" id="noticeId"  value="<#if result.noticeId??>${result.noticeId}</#if>" /> 
	  <input type="text" class="form-control" id="noticeName"  value="<#if result.noticeName??>${result.noticeName}</#if>" name="resourceCode" placeholder="模块标识">
	</div>
 </div>
 <div class="form-group">
	<label for="resourceIcon" class="col-sm-2 control-label">公告关键词</label>
	<div class="col-sm-10">
	  <input type="text" class="form-control" id="noticeWord" value="<#if result.noticeWord??>${result.noticeWord}</#if>" name="noticeWord" placeholder="公告关键词">
	</div>
 </div>
 <div class="form-group">
	<label for="resourceUrl" class="col-sm-2 control-label">模块Url</label>
	<div class="col-sm-10">
	  <div class="input-group">
         <input type="text" class="form-control" id="noticeUrl" value="<#if result.noticeUrl??>${result.noticeUrl}</#if>" name="noticeUrl" placeholder="模块Url"/>
          <div class="input-group-btn">
           	<select name="resourceTarget" id="resourceTarget"  data="<#if result.resourceTarget??>${result.resourceTarget}</#if>" style="min-width:140px;"   class="form-control">
				<option value="_self">当前页面</option>
				<option value="_parent">父窗口</option>
				<option value="_blank">新窗口</option>
				<option value="_top">框架页</option>
			</select>
          </div>
        </div>
	</div>
  </div>
  <div class="form-group">
	<label for="resourceDesc" class="col-sm-2 control-label" >模块描述</label>
	<div class="col-sm-10">
	<textarea class="form-control" id="resourceDesc" name="resourceDesc" placeholder="模块描述" rows="3"></textarea>
	</div>
  </div>
  <div class="form-group">
	<div class="col-sm-offset-2 col-sm-10">
	  <button type="submit" class="btn btn-primary"><@spring.message  code="label.default.button.save" /></button>
	  <button type="button" class="btn btn-danger"><@spring.message  code="label.default.button.cancel" /></button>
	</div>
  </div>
</form>