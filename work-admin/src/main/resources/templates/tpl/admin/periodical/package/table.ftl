<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>基础材料库</title>
  <link rel="stylesheet" type="text/css" href="${appHost}/static/css/style.css" />
  <#include "/includes/head.ftl" />

   <style>
  .table td{white-space:nowrap;overflow:hidden; text-overflow:ellipsis; } 
  .table  {table-layout:fixed;}  
  .table th{vertical-align:middle !important;text-align: center; font-size:14px;}
  .Wdate{
	  height: 32px !important;
	  padding: 3px 1px !important; 
	  border: 1px solid #ccc !important; 
	}
   .form-search{
   	margin-top: -8px;
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
     <#assign template="level"/>
</head> 
<body class="page-header-fixed">
	<div class="clearfix"></div>
    <div class="container-fluid main-container">
   	   	<div class="row">
		  <div class="col-xs-12">
			<div class="panel panel-info">
			  <div class="panel-body" style="min-height:523px;">
				<!-- Table -->
				 <table class="table table-bordered" style="width:100%;" >
			      <thead>
			        <tr>
			          <th style="width: 50px;">
			          	<input type="checkbox" id="btSelectAll" />
			          </th>
			           <th style="width: 100px;">区域</th>
			          <th style="width: 250px;">编码</th>
			          <th>名称</th>
			          <th>规格/型号</th>
			          <th>单位</th>
			          <th style="width: 140px;">创建时间</th>
			          <th style="width: 120px;">操作</th>
			        </tr>
			      </thead>
			      <tbody>
			          <#if (result.content?size<=0) >
				      	 <tr >
				      	 	<td class="text-center" scope="row" colspan='7' > 无记录</td>
				      	 </tr>
				      <#else>
				       <#list result.content as model>	
				       <tr <#if (model_index%2)=0 >class="info"</#if>>
				         <th scope="row">
				        	 <input type="checkbox" id="check_${model.id}" name="id" value="${model.id}" />
				         </th>
				          <td>${model.cityName}</td>
				      	<td><code>${model.materialCode}</code></td>
				         <td>${model.materialName}</td>
				         <td>${model.materialFormat}</td>
				         <td>${model.materialUnit}</td>
				         <td>${model.createTime}</td>
				         <td class="operate">
							<i id="edit-${model.id}" data="${model.id}" class="fa fa-pencil fa-fw"></i>
							<i id="disabled-${model.id}" data="${model.id}" class="fa fa-trash-o fa-fw"></i>
						</td>
				       </tr>
				 	 </#list >			       	 
				      </#if>
			      </tbody>
			    </table>
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
 </body>
</html>