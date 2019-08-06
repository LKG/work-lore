 <div class="panel panel-info main-panel" id="app"  >
   <div class="panel-heading">
	   <form class="form-search form-inline" id="search_form" >
		   <input type="hidden" name="page" id="page" value="1" v-model="page"/>
		   <input type="hidden" name="order" id="order" value="" />
		   <input type="hidden" name="sort" id="sort" value="" v-model="sort" />
		   <input type="hidden" name="size" id="size" value="" v-model="size"  />
		   <!----
			         <div class="form-group" >

					  <label for="type">时间:</label>
					  <input type="text" name='createTime_GTE' style="width: 100px;" class="Wdate form-control"
							size='10' maxlength='10' id="startYear"
						value="${.now?string('yyyy-MM-01')}" readonly="readonly"
						 onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endYear\')}'})"})"/>
						 <input type="text" name='createTime_LTE' id="endYear" style="width: 100px;" class="Wdate form-control"
							size='10' maxlength='10'
						value="${.now?string('yyyy-MM-dd')}" readonly="readonly"
						 onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startYear\')}',maxDate:'%y-%M-%d'})"/>
					 </div>
					 ---->
		   <div class="form-group">
               <label for="periodicalName" >期号</label>
               <input type="text" name='periodicalCode_LIKES' style="width: 80px;" class="Wdate form-control"
                      size='10' maxlength='6' id="periodicalCode" value="" onclick="WdatePicker({isShowClear:true,readOnly:false,dateFmt:'yyyyMM',maxDate:'%y-%M-%d'})"})"
               />
		   </div>
		   <div class="form-group">
			   <label for="periodicalName" >名称</label>
			   <input type="text" class="form-control" style="width: 300px;" id="periodicalName" name="periodicalName_LIKE" placeholder="名称">
		   </div>
           <div class="form-group">
               <label for="createTime_GTE" >日期</label>
               <input type="text" name='createTime_GTE' style="width: 120px;" class="Wdate form-control"
                      size='10' maxlength='6' id="beginTime" value="" onclick="WdatePicker({isShowClear:true,readOnly:false,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"})"
               />
               <input type="text" name='createTime_LTE' style="width: 120px;" class="Wdate form-control"
                      size='10' maxlength='6' id="endTime" value="" onclick="WdatePicker({isShowClear:true,readOnly:false,dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"})"
               />
		   </div>
		   <div class="form-group">
			   <label for="rptCriType">状态</label>
			   <select class="form-control" id="status" name="status">
				   <option value="" >------</option>
				   <option value="initial" >未处理</option>
				   <option value="waiting" >处理中</option>
				   <option value="processed" >已处理</option>
				   <option value="fail" >处理失败</option>

			   </select>
		   </div>
		   <div class="form-group">
			   <label for="rptCriType">审核状态</label>
			   <select class="form-control" id="checkStatus" name="checkStatus">
				   <option value="" >------</option>
				   <option value="pending" > 待发布</option>
				   <option value="enabled" >已发布</option>
				   <option value="disabled" >已下架</option>
			   </select>
		   </div>
		   <div class="form-group">
			   <label for="periodicalType">文档格式</label>
			   <select class="form-control" id="fileHeader" name="fileHeader">
				   <option value="" >------</option>
				   <option value="doc" >doc</option>
				   <option value="ppt" >ppt</option>
			   </select>
		   </div>
		   <div class="form-group">
			   <label for="periodicalType">文档类型</label>
			   <select class="form-control" id="periodicalType" name="periodicalType">
				   <option value="" >------</option>
				   <option value="sharing" >共享</option>
			   </select>
		   </div>
		   <button type="button" id="search-btn" class="btn btn-primary"><@spring.message code="label.default.button.search" /></button>
	   </form>
   </div>
 	<div class="panel-body" >
		<!---toolbar begin-->
		<div id="toolbar">
			<div class="bars pull-left" >
				<button id="upload" class="hide btn btn-danger">
					<i class="fa fa-cloud-upload"></i>导入
				</button>
				<button id="remove" class="btn btn-danger">
					<i class="fa fa-trash-o"></i>删除
				</button>
				<button id="publish" class="btn btn-danger">
					<i class="fa fa-key"></i> 发布
				</button>
                <button id="parse" class="btn btn-danger" >
                    <i class="fa fa-repeat"></i> 重新生成
                </button>
				<button id="lock" class="btn btn-danger" >
					<i class="fa fa-lock"></i> 下架
				</button>
			</div>
			<#include "/includes/pagination-total.ftl" />
		</div>
		<!---toolbar end-->
	 	<div class="table-responsive">
		 <table class="table table-responsive table-striped table-bordered table-condensed table-hover">
			 <thead>
				 <tr>
					 <th style="width: 50px;" class="bs-checkbox" >
						 <input type="checkbox" id="btSelectAll" />
					 </th>
					 <th  style="width: 60px;">期刊号</th>
					 <th style="width: 65px;">类型</th>
					 <th style="width: 65px;" >格式</th>
					 <th >名称</th>
					 <th style="width: 75px;">大小</th>
					 <th style="width: 65px;">总页数</th>
					 <th style="width: 70px;">状态</th>
					 <th style="width: 70px;">审核状态</th>
					 <th style="width: 140px;">创建时间</th>
					 <th style="width: 120px;">操作</th>
				 </tr>
			 </thead>
	        <tbody  id="table-tbody" >
			<#if (result.content?size<=0) >
				<tr ><td class="text-center" scope="row" colspan='11' ><@spring.message  code="label.default.empty" /></td></tr>
			<#else>
				<#list result.content as model>
					<tr  data-num=""
						 class="<#if model.status=='fail'>text-danger</#if>">
						<td class="bs-checkbox text-center" >
							<div >
								<input id="${model.id!''}" name="id" type="checkbox" value="${model.id!''}"/>
							</div>
						</td>
						<td title="${model.periodicalCode!''}" class="text-center">${model.periodicalCode!''}</td>
						<td title="${model.periodicalType!''}" class="text-center">${model.periodicalType!''}</td>
						<td title="${model.fileHeader!''}" class="text-center">${model.fileHeader!''}</td>
						<td><a href="${appHost}${model.pathUrl!''}" >${model.periodicalName!''}</a>
						</td>
						<td title="${model.dataSizeHuman}" class="text-right" >${model.dataSizeHuman!''}</td>
						<td title="${model.pageNum}" class="text-right" >${model.pageNum!''}</td>
						<td class="text-center">
							<#if model.status=='initial'>
								待处理
							</#if>
							<#if model.status=='processed'>
								已处理
							</#if>
							<#if model.status=='waiting'>
								处理中
							</#if>
							<#if model.status=='fail'>
								<i class="fa fa-warning"></i>失败
							</#if>
						</td>
						<td class="text-center">
							<#if model.checkStatus=='pending'>
								待发布
							</#if>
							<#if model.checkStatus=='enabled'>
								已发布
							</#if>
							<#if model.checkStatus=='disabled'>
								已下架
							</#if>
						</td>
						<td>${model.createTime}</td>
						<td class="operate">
							<#if model.checkStatus!='enabled'>
								<i id="publish-${model.id}" title="发布"  data="${model.id}" class="btn-publish fa fa-key"></i>
							</#if>
							<#if model.checkStatus=='enabled'>
								<i id="lock-${model.id}}" title="下架"  data="${model.id}" class="btn-lock fa fa-lock"></i>
							</#if>
							<a   href="${appHost}/admin/periodical/${model.id}.jhtml"   title="修改" > <i id="edit-${model.id}" title="修改"  data="${model.id}" class="btn-edit fa fa-edit"></i></a>
							<i id="view-${model.id}}"   title="查看日志" data="${model.id}" class="btn-view fa fa-eye"></i>
                            <i id="parse-${model.id}}"   title="重新生成" data="${model.id}" class="btn-parse fa fa-repeat"></i>
							<i id="remove-${model.id}"  title="删除" data="${model.id}" class="btn-remove fa fa-trash-o"></i>
						</td>
					</tr>
				</#list >
			</#if>
	        </tbody>
	      </table>  		
	  	</div>
	  	<!-----分页-begin---->
			<div  id="table-pagination" data-totalPages="${result.totalPages}" data-number="${result.number}" style="margin-top: -15px;"  class="clearfix"></div>
		<!-----分页-end---->
 	</div>
 </div>
 <script id="tr-template-js"  type="text/html">
	 {{if (content.length>0) }}
	 {{each content as model}}
	 <tr  data-num="{{$index}}"
		  class="{{if model.status=='fail'}}text-danger{{/if}} ">
		 <td class="bs-checkbox text-center" >
			 <div >
				 <input id="{{model.id}}" name="id" type="checkbox" value="{{model.id}}"/>
			 </div>
		 </td>
		 <td title="{{model.periodicalCode}}" class="text-center">{{model.periodicalCode}}</td>
		 <td title="{{model.periodicalType}}" class="text-center">{{model.periodicalType}}</td>
		 <td title="{{model.fileHeader}}" class="text-center">{{model.fileHeader}}</td>
		 <td><a href="${appHost}{{model.pathUrl}}" >{{model.periodicalName}}</a>
		 </td>
		 <td title="{{model.dataSizeHuman}}" class="text-right" >{{model.dataSizeHuman}}</td>
		 <td title="{{model.pageNum}}" class="text-right" >{{model.pageNum}}</td>
		 <td class="text-center">
			 {{if model.status=='initial' }}
			 待处理
			 {{else if model.status=='processed'}}
			 已处理
			 {{else if model.status=='waiting'}}
			 处理中
			 {{else if model.status=='fail'}}
			 <i class="fa fa-warning"></i>失败
			 {{/if}}
		 </td>
		 <td class="text-center">
			 {{if model.checkStatus=='pending'}}
			 待发布
			 {{/if}}
			 {{if model.checkStatus=='enabled'}}
			 已发布
			 {{/if}}
			 {{if model.checkStatus=='disabled'}}
			 已下架
			 {{/if}}
		 </td>
		 <td>{{model.createTime}}</td>
		 <td class="operate">
			 {{if model.checkStatus !='enabled'}}
			 <i id="publish-{{model.id}}" title="发布"  data="{{model.id}}" class="btn-publish fa fa-key"></i>
			 {{/if}}
			 {{if model.checkStatus=='enabled'}}
			 <i id="lock-{{model.id}}" title="下架"  data="{{model.id}}" class="btn-lock fa fa-lock"></i>
			 {{/if}}
			 <a   href="${appHost}/admin/periodical/{{model.id}}.jhtml"   title="修改" > <i id="edit-{{model.id}}" title="修改"  data="{{model.id}}" class="btn-edit fa fa-edit"></i></a>

			 <i id="view-{{model.id}}"   title="查看日志" data="{{model.id}}" class="btn-view fa fa-eye"></i>
             <i id="parse-{{model.id}}" title="下架"  data="{{model.id}}" class="btn-parse fa fa-repeat"></i>
			 <i id="remove-{{model.id}}"  title="删除" data="{{model.id}}" class="btn-remove fa fa-trash-o"></i>
		 </td>
	 </tr>
	 {{/each}}
	 {{else}}
	 <tr id="ext_{{$index}}" class="text-center" >
		 <td colspan="11"><@spring.message  code="label.default.empty" /></td>
	 </tr>
	 {{/if}}
 </script>