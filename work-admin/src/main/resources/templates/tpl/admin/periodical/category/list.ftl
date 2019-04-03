<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>材料分类库</title>
<#--  <link rel="stylesheet" type="text/css" href="${appHost}/static/css/navbar-left-menu.css" />-->
    <link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
  <#include "/includes/head.ftl" />
    <link rel="stylesheet" type="text/css" href="${appHost}/css/navbar-left-menu.css?v=${ver!'1'}" />
  <#include "/includes/laypage-css.ftl" />
    <#include "/includes/zTree-css.ftl" />
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
   .form-search{
   	
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
  </style>.0
       <#assign template="periodical"/>
     <#assign submenu="category"/>
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
				<li class="active">期刊分类<small></small></li>
				</ul><!-- .breadcrumb -->
			 </div>
			<!-- .breadcrumb  end -->
			 <div class="container-fluid" >
				<div class="row" > 
					 <div class="col-xs-4">
					    <!-----面板begin----->
						<div class="panel panel-primary">
						  <div class="panel-heading">分类列表</div>
						  <div class="panel-body" style="min-height: 750px;"> 
						     <ul id="categoryTree" class="ztree">
						      	
						     </ul>
						  </div>
						</div>
						 <!-----面板 end----->
					 </div>
					 <div class="col-xs-8">
 						<!-----面板begin----->
						<div class="panel panel-primary">
						 <div class="panel-heading">明细</div>
						  <div class="panel-body" id="main-body" style="min-height: 330px;">
							<span id="loading"><i class="fa fa-spinner fa-spin fa-pulse"></i><@spring.message  code="label.default.data.loading" /></span>
									    
						  </div>
						</div>
						 <!-----面板 end----->
					 </div>
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
  	<#include "/includes/jquery.ftl" />
 <script>
  	seajs.use(["js/left-menu.js?v="+Math.random()]);
</script>
  	<#include "/includes/zTree-js.ftl" />
	<script type="text/javascript">

		var setting = {
			async: {
				enable: true,
				dataFilter: filter,
				autoParam:["categoryId=parentId"],
				otherParam:{"size":"2000"},
				url: getUrl
			},
			data: {
				key: {
					title:"name"
				},
				simpleData: {
					enable: true,
					idKey: "categoryId",
					pIdKey: "parentId",
					rootPId: 0,
				}
			},
			callback: {
				onAsyncSuccess: onAsyncSuccess,
				onClick: onNodeClick,
				onAsyncError: onAsyncError
			}
		};
	 	function filter(treeId, parentNode, data) {
			var nodes = [];
			if (data.success) {
				if (data.result.totalElements==0) return null;
				$.each(data.result.content,function(index,node){
					var name=node.categoryName;
					var categoryId=node.categoryId;
					var isParent=false;
					if(node.hasChildren){
						isParent=true;
					}
					var parentId=node.parentId;
					var $node={categoryId:categoryId,name:name,isParent:isParent,parentId:parentId};
					nodes.push($node);
				});
			}
			return nodes;
		}
		function getUrl(treeId, treeNode) {
			var url="${appHost}/admin/periodical/categorys.json";
			if(undefined===treeNode){
				url+="?parentId=0";
			}
			return url;
		}
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			//$("#loading").hide();
		}
		function  onNodeClick(event, treeId, treeNode, clickFlag){
			$("#main-body").removeClass("loading").load("${appHost}/admin/periodical/category/"+treeNode.categoryId+".jhtml");
		};
		function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
			var zTree = $.fn.zTree.getZTreeObj("categoryTree");
			alert("异步获取数据出现异常。");
			treeNode.icon = "";
			zTree.updateNode(treeNode);
		}
		$(document).ready(function(){
			var treeObj=$.fn.zTree.init($("#categoryTree"), setting);
			 setTimeout(function(){  
                var nodes = treeObj.getNodes();
			 	var node = treeObj.selectNode(nodes[0]);
				onNodeClick(null,treeObj.setting.treeId,nodes[0]); 
            },200);//延迟加载  
		});
	</script>



 </body>
</html>