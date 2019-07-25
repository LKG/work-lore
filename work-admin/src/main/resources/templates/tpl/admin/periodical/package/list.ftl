<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>数据包列表</title>
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
  </style>
     <#assign template="package"/>
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
				<li><a href="#">数据包管理</a></li>
				<li class="active">数据包列表<small></small></li>
				</ul><!-- .breadcrumb -->
			 </div>
			<!-- .breadcrumb  end -->

			<div class="panel panel-info main-panel">
				<div class="panel-heading">
				 <form class="form-search form-inline" id="search_form" >
				 	 	<input type="hidden" name="size" id="size" value="10" />
				 		<input type="hidden" name="page" id="page" value="1" />
				 		<input type="hidden" name="materialCode" id="materialCode" value="" />
			  	<div class="form-group">
			 			<select class="form-control" style="width: 75px;" id="provinceId" name="cityId_LIKES">
					     	<option value="" >------</option>
						</select>
							<select class="form-control" style="width: 95px;" id="cityId" name="cityId_LIKES">
					     	<option value="" >------</option>
						</select>
						<select class="form-control" style="width: 95px;" id="areaId" name="cityId_LIKES">
					     	<option value="" >------</option>
						</select>
				  </div>
			        <div class="form-group">
				   	 <label for="packageCode" >刊号</label>
				     <input type="text" name='packageCode_LIKES' style="width: 70px;" class="Wdate form-control"
							size='10' maxlength='6' id="packageCode"
						value="" />
				 	 </div>
				  <div class="form-group hide">
				    <label for="packageName" >名称</label>
				    <input type="text" class="form-control" style="width: 300px;" id="packageName" name="packageName_LIKE" placeholder="数据包名称">
				  </div>
				  <div class="form-group">
				     <label for="packageType" >类型</label>
				    <select class="form-control" id="packageType" name="packageType">
					     	<option value="" >------</option>
					     	<option value="1" >正刊</option>
					     	<option value="2" >副刊</option>
					     	<option value="5" >正刊规则库</option>
					     	<option value="6" >副刊规则库</option>
					     	<option value="9" >基础库</option>
					     	<option value="0" >城市库</option>
					</select>
					 <label for="packageType" >处理状态</label>
					<select class="form-control" id="status" name="status">
					      <option value="" >------</option>
					      <option value="initial" >未处理</option>
					       <option value="waiting" >处理中</option>
						  <option value="processed" >已处理</option>
						  <option value="fail" >失败</option>
					</select>
					<label for="packageType" >数据包状态</label>
					 <select class="form-control" id="checkStatus" name="checkStatus">
					     	<option value="" >------</option>
					     	<option value="pending" >待发布</option>
					     	<option value="enabled" >已发布</option>
					     	<option value="disabled" >已下架</option>
					</select>
				  </div>
				  <button type="button" id="search-btn" class="btn btn-primary"><@spring.message code="label.default.button.search" /></button>
					</form>
				</div>
				<div class="panel-body" style="min-height:523px;">
				 <!---toolbar begin-->
				 <div id="toolbar">
				 	 <div id="table-pagination2"></div>
				    	<div class="bars pull-left" >
					        <button id="publish" class="btn btn-danger" >
					            <i class="fa fa-trash-o"></i> 发布
					        </button>
					        <button id="disabled" class="btn btn-danger" >
					            <i class="fa fa-trash-o"></i> 下架
					        </button>
					         <button id="generate" class="btn btn-danger" >
					            <i class="fa fa-trash-o"></i> 重新生成
					        </button>
				    	</div>
					 <#include "/includes/pagination-total.ftl" />
				    </div>
				<!---toolbar end-->
				 	<div  class="bs-table-tbody"  >
				 		<table style="margin-bottom:0px;"   class="table table-responsive table-striped table-bordered table-condensed table-hover">
				     	 <thead>
				        <tr>
				          <th style="width: 50px;" class="bs-checkbox" >
				          	<input type="checkbox" id="btSelectAll" />
				          </th>
				          <th style="width: 220px;">名称</th>
				          <th >数据包描述</th>
				           <th style="width: 80px;">处理状态</th>
				          <th style="width: 80px;">发布状态</th>
				          <th style="width: 150px;">更新时间</th>
				          <th style="width: 100px;">操作</th>
				        </tr>
				      </thead>
						 <tbody id="table-tbody"  >
						 <tr>
						 	<td colspan="7"><@spring.message  code="label.default.data.loading" /></td>
						 </tr>
					<script id="tr-template-js"  type="text/html">
						{{if (content.length>0) }}
					    {{each content as model}}
						  <tr  data-num="{{$index}}"
						  	class="{{if model.status=='fail'||model.checkStatus=='disabled'  }}warning text-danger{{/if}} ">
							<td class="bs-checkbox text-center" >
							 <div >
							  	<input id="{{model.id}}"  {{if model.status=='fail'||model.status=='initial'|| (model.dataTotal=='0') }}disabled='disabled'{{/if}} name="id" type="checkbox" value="{{model.id}}"/>
							  </div>
							</td>

							<td> {{model.packageName}}<code>{{model.packageCode}}</code>  <code>v{{model.packageVersion}}</code> </td>
							<td>
								共:<i class='fa fa-bar-chart-o'>[{{model.dataTotal}}]</i>条
								{{if model.status!='initial'&&(model.dataTotal!='0') }}
									数据包：<a href="http://${bucketName!''}.oss.aliyuncs.com/{{model.dataUrl}}"><i class='fa fa-database'>{{model.dataSizeHuman}}</i></a>
									压缩包：<a href="http://${bucketName!''}.oss.aliyuncs.com/{{model.zipUrl}}"><i class='fa fa-file-zip-o'>{{model.zipSizeHuman}}</i></a>
								{{/if}}
							</td>
							<td>
							{{if model.status=='initial'}}
								待处理
							{{/if}}
							{{if model.status=='processed'}}
								已处理
							{{/if}}
							{{if model.status=='waiting'}}
								处理中
							{{/if}}
							{{if model.status=='fail'}}
								<i class="fa fa-warning"></i>失败
							{{/if}}
							</td>
							<td>
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
							<td>{{model.modiTime}}</td>
							<td class="operate">
								{{if  model.status=='processed' }}
									{{if  (model.checkStatus!='enabled')&& (model.dataTotal!='0')&& (model.status=='processed') }}
										<i id="publish-{{model.id}}" title="发布"  data="{{model.id}}" class="fa fa-key"></i>
									{{/if}}
									{{if  model.checkStatus=='enabled' }}
									<i id="disabled-{{model.id}}" title="下架"  data="{{model.id}}" class="fa fa-lock"></i>
									{{/if}}
								{{/if}}
								{{if  model.status!='initial' }}
									<i id="generate-{{model.id}}" title="重新生成"  data="{{model.id}}" class="fa fa-bell"></i>
								{{/if}}
							</td>
							</tr>
						{{/each}}
					  {{else}}
						<tr id="ext_{{$index}}" class="text-center" >
							<td colspan="7"><@spring.message  code="label.default.empty" /></td>
						</tr>
					  {{/if}}
						</script>
						 </tbody >
						</table>
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
  	seajs.use(["js/admin/periodical/package/list.js?v="+Math.random(),"js/left-menu.js?v="+Math.random()]);
</script>
 </body>
</html>