<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>地区管理</title>
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
	.operate i{
		cursor:pointer;
		font-size: 12px;
	}
  </style>
     <#assign template="sysedit"/>
     <#assign submenu="area"/> 
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
				<li ><i class="icon-home home-icon"></i><a href="#">系统维护</a></li>
				<li>地区管理</li>
				</ul><!-- .breadcrumb -->
			 </div>
			<!-- .breadcrumb  end -->
			<div class="container-fluid" >
			<!-- .row  begin -->
				<div class="row">
					<div class="col-xs-2" style="margin-top: -20px;" >
					<#include "/admin/frame/area_tree_page.ftl" />
					</div>
					<div class="col-xs-10">
						<#include "/admin/frame/area_list_page.ftl" />
					</div>
				</div> 
				<!-- .row  end -->
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
<#include "/includes/vue-js.ftl" />
<#include "/includes/jquery.ftl" />
<#include "/includes/zTree-js.ftl" />
<script type="text/javascript">
    $(document).ready(function(){
        var setting = {
            async: {
                enable: true,
                dataFilter: filter,
                autoParam:["code=parentId"],
                otherParam:{"size":"500"},
                url: getUrl
            },
            data: {
                key: {
                    title:"name"
                },
                simpleData: {
                    enable: true,
                    idKey: "code",
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
        var url = {
            api : "${appHost}/admin/area",
        };
        var zNodes =[
            {code:0,name:"中国",isParent:true,parentId:null,open:true}
        ];
        function filter(treeId, parentNode, data) {
            var nodes = [];
            if (data.success) {
                if (data.result.totalElements==0) return null;
                $.each(data.result.content,function(index,node){
                    var name=node.name;
                    var code=node.code;
                    var isParent=false;
                    if(node.hasChildren){
                        isParent=true;
                    }
                    var parentId=node.parentId;
                    var $node={code:code,name:name,isParent:isParent,parentId:parentId,open:true};
                    nodes.push($node);
                });
            }
            return nodes;
        }

        function getUrl(treeId, treeNode) {
            var apiUrl=url.api+"s.json";
            if(undefined===treeNode){
                apiUrl+="?parentId=0";
            }
            return apiUrl;
        }

        var treeObj=$.fn.zTree.init($("#areaTree"), setting,zNodes);
        $("#reset-btn").on("click",function () {
            // window.location.reload();
            treeObj.refresh();
            $("#search_form").find("#parentId").val("");
            $("#search_form").find("#search-btn").click();
        });
        function onAsyncSuccess(event, treeId, treeNode, msg) {
            $(".loading").hide();
        }
        function  onNodeClick(event, treeId, treeNode, clickFlag){
            $("#search_form").find("#parentId").val(treeNode.code);
            $("#search_form").find("#search-btn").click();
        };
        function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
            var zTree = $.fn.zTree.getZTreeObj("areaTree");
            alert("异步获取数据出现异常。");
            treeNode.icon = "";
            zTree.updateNode(treeNode);
        }
        setTimeout(function(){
            $(".loading").hide();
            var nodes = treeObj.getNodes();
            var node = treeObj.selectNode(nodes[0]);
            onNodeClick(null,treeObj.setting.treeId,nodes[0]);
        },200);//延迟加载
    });
</script>
<script>
   	seajs.use(["js/left-menu.js?v=${ver!'1'}","/js/admin/frame/area_list.js?v="+Math.random(),"/js/app.js?v=${ver!'1'}"]);
</script>
 </body>
</html>