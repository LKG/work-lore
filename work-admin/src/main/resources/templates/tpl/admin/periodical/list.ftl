<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>期刊管理</title>
    <link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
  <#include "/includes/head.ftl" />
    <link rel="stylesheet" type="text/css" href="${appHost}/css/navbar-left-menu.css?v=${ver!'1'}" />
  <#include "/includes/laypage-css.ftl" />
   <style>
   .main-panel .panel-heading{
      padding: 0px 15px;
   }
   .main-panel{
      margin-top: -20px;
   }
  .table td{white-space:nowrap;overflow:hidden; text-overflow:ellipsis; } 
  .table  {table-layout:fixed;}  
  .table th{vertical-align:middle !important;text-align: center; font-size:14px;}
  .Wdate{
	  height: 32px !important;
	  padding: 3px 1px !important; 
	  border: 1px solid #ccc !important; 
	}
    .form-search select{
   	  padding: 6px 3px  !important; 
   }
	.form-inline .form-group,.form-inline .form-control{
		width: 100px;
		display: inline;
	}
	@media (max-width: 900px){
		.panel-heading{
			padding: 10px 0px !important; 
		}
	}
	.laypage_main{
		clear: none !important;
	}
  </style>
     <#assign template="periodical"/>
     <#assign submenu="index"/> 
</head> 
<body class="page-header-fixed">
    <#include "/admin/navbar-header.ftl" />
    <div class="container-fluid main-container" >
     <div  class="main-container-inner">
     	  <#include "/admin/navbar-left-menu.ftl" />
     	  <div class="main-content"> 
     	  	<!-- .breadcrumb  begin -->
     	 	 <div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i><a href="#"><@spring.message  code="label.system.index" /></a></li>
				<li><a href="#">期刊管理</a></li>
				<li class="active">期刊管理<small></small></li>
				</ul><!-- .breadcrumb -->
			 </div>
			<!-- .breadcrumb  end -->
			
			<div class="panel panel-info main-panel">
				<div class="panel-heading"> 
				  	<form class="form-search form-inline" id="search_form" >  
				  			<input type="hidden" name="size"  id="size"   value="10" />
				 		<input type="hidden" name="page" id="page" value="1" />
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
				    <label for="periodicalCode" >刊号</label>
				      <input type="text" name='periodicalCode_LIKES' style="width: 80px;" class="Wdate form-control"
							size='10' maxlength='6' id="periodicalCode" value="" onclick="WdatePicker({isShowClear:true,readOnly:false,dateFmt:'yyyyMM',maxDate:'%y-%M-%d'})"})"
						/>
				  </div>  
				  <div class="form-group">
				    <label for="periodicalName" >名称</label>
				    <input type="text" class="form-control" style="width: 300px;" id="periodicalName" name="periodicalName_LIKE" placeholder="名称">
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
				  <button type="button" id="seach-btn" class="btn btn-primary"><@spring.message code="label.default.button.search" /></button>
					</form>	
				</div>
				<div class="panel-body" style="min-height:523px;">
				<!---toolbar begin-->
					 <div id="toolbar">
					     <div id="table-pagination2"></div>
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
                             <button id="lock" class="btn btn-danger" >
                                 <i class="fa fa-lock"></i> 下架
                             </button>
					    	</div>
					     <#include "/pages/pagination-size.ftl" />
					</div>	
					<!---toolbar end-->
				 	<div  class="bs-table-tbody"  >
				 	<table style="margin-bottom:0px;"   class="table  table-striped table-bordered table-condensed table-hover"> 
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
					 <tbody id="table-tbody"  >
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
						<i id="remove-${model.id}"  title="删除" data="${model.id}" class="btn-remove fa fa-trash-o"></i>
					</td>
				</tr>
		   </#list >
	   </#if>

					 </tbody >
					</table>
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
				 	</div>
				 	<!-----分页-begin---->
				 	<div  id="table-pagination" style="margin-top: 5px;"  class="clearfix"></div>
				 	<!-----分页-end---->
				</div>
			 </div>
     	  </div>
     </div>
    </div>
   	<!------footer信息 begin----->
  	<#include "/includes/footer-js.ftl" />
  	<!------footer信息 end----->
  	<#include "/includes/datePicker.ftl" />
<!------seajs.config 引用信息 begin----->
<#include "/includes/seajs.config.ftl" />
<!------seajs.config   引用信息 end----->
 <script>
  	seajs.use(["js/admin/periodical/list.js?v="+Math.random(),"js/left-menu.js?v="+Math.random()]);
</script>
 </body>
</html>