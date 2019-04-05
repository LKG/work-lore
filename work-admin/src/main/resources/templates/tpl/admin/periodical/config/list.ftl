<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>期刊配置管理</title>
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
     <#assign submenu="config"/> 
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
				<li><a href="#">正刊管理</a></li>
				<li class="active">期刊配置<small></small></li>
				</ul><!-- .breadcrumb -->
			 </div>
			<!-- .breadcrumb  end -->
			
			<div class="panel panel-info main-panel">
				<div class="panel-heading"> 
				  	<form class="form-search form-inline" id="search_form" >
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
			         <div class="form-group" >
				 		<input type="hidden" name="size"  id="size"   value="10" />
				 		<input type="hidden" name="page" id="page" value="1" />
				 		<input type="hidden" name="type" id="type" value="1" />
					  <label for="year">年份:</label>
						 <input type="text" name='year' id="endYear" style="width: 80px;" class="Wdate form-control"
							size='10' maxlength='4' 
						value="" 
						 onclick="WdatePicker({isShowClear:true,readOnly:false,dateFmt:'yyyy',maxDate:'%y-%M-%d'})"/>
					 </div>
			        
				  <div class="form-group">
				    <label for="name" >名称</label>
				    <input type="text" class="form-control" style="width: 300px;" id="name" name="name_LIKE" placeholder="名称">
				  </div>
				  <button type="button" id="seach-btn" class="btn btn-primary"><@spring.message code="label.default.button.search" /></button>
					</form>	
				</div>
				<div class="panel-body" style="min-height:523px;">
				<!---toolbar begin-->
					 <div id="toolbar">
					     <div id="table-pagination2"></div>
					     <div class="bars pull-left" >
					     		<button id="add" class="btn btn-danger">
						            <i class="fa fa-cloud-upload"></i>新增
						        </button>
						         <button id="generate" class="btn btn-danger">
						       <i class="fa fa-building"></i> 生成规则库
						    </button>
						          <button id="remove" class="btn btn-danger hide" disabled>
						            <i class="fa fa-trash-o"></i>删除
						        </button>
					    	</div>
						 <#include "/includes/pagination-total.ftl" />
					</div>	
					<!---toolbar end-->
				 	<div  class="bs-table-tbody"  >
				 	<table style="margin-bottom:0px;"   class="table  table-striped table-bordered table-condensed table-hover"> 
			     	 <thead>
			        <tr>
			          <th style="width: 50px;" class="bs-checkbox" >
			          	<input type="checkbox" id="btSelectAll" />
			          </th>
			           <th style="width: 100px;">区域</th>
			          <th  style="width: 50px;">年份</th>
			           <th>名称</th>
			          <th style="width: 135px;">创建时间</th>
			          <th style="width: 120px;">操作</th>
			        </tr>
			      </thead>
					 <tbody id="table-tbody"  >
					 <tr>
					 	<td colspan="6"><@spring.message  code="label.default.data.loading" /></td>
					 </tr>
				<script id="tr-template-js"  type="text/html">
					{{if (content.length>0)}}
				    {{each content as model}}
					  <tr  data-num="{{$index}}" 
					  	class="{{if model.status=='fail'}}text-danger{{/if}} ">
						<td class="bs-checkbox text-center" >
						 <div >
						  	<input id="checkbox-{{model.id}}" name="id" type="checkbox" value="{{model.id}}"/>
						  </div>
						</td>
						<td title="{{model.cityName}}" class="text-center showcity" class="" data-citynames="{{model.cityNames}}" >{{model.cityName}}</td>
						<td title="{{model.year}}" class="text-center">{{model.year}}</td>
						<td title="{{model.name}}">{{model.name}}</td>
						<td>{{model.createTime}}</td>
						<td class="operate">
							<i id="rule-{{model.id}}"  title="生成规则库" data="{{model.id}}" class="btn-generate fa fa-building"></i>
							<i id="pageage-{{model.id}}" data-title="{{model.name}}{{model.year}}" title="生成数据包" data="{{model.id}}" class="btn-pageage fa fa-database"></i>
							<i id="edit-{{model.id}}"  title="修改" data="{{model.id}}" class="btn-edit fa fa-pencil"></i>
							{{if !model.hasPackage&&!model.hasUpload}}
								<i id="remove-{{model.id}}"  title="删除" data="{{model.id}}" class="btn-del fa fa-trash-o"></i>	
							{{/if}} 
						</td>
						</tr>
					{{/each}}
				  {{else}}
					<tr id="ext_{{$index}}" class="text-center" >
						<td colspan="6"><@spring.message  code="label.default.empty" /></td>
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
<div id="dialog-template" style="display:none;" >
<form class="form-search form-inline" >
		<div class="form-group">	
			 <p class="form-control-static"><i class="fa fa-exclamation-triangle text-danger"></i>
			 		生成
			 	 <span id="data-title" style="padding-left:0px;" ></span>
				 <select class="form-control" id="periodicalCode"  name="periodicalCode" style="display: inline;width: 50px;" >
				  <option value="01">01</option>
				  <option value="02">02</option>
				  <option value="03">03</option>
				  <option value="04">04</option>
				  <option value="05">05</option>
				  <option value="06">06</option>
				  <option value="07">07</option>
				  <option value="08">08</option>
				  <option value="09">09</option>
				  <option value="10">10</option>
				  <option value="11">11</option>
				  <option value="12">12</option>
				</select>
				期数据包?
			 </p>
		</div>
	</from>
</div>
 <script>
  	seajs.use(["js/admin/periodical/config/list.js?v="+Math.random(),"js/left-menu.js?v="+Math.random()]);
</script>
 </body>
</html>