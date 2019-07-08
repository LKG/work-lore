<div class="panel panel-default" style="" >
	  <div class="panel-body">
          <button type="button" id="reset-btn" class="btn btn-primary">刷新</button>
	   <div class="loading"> <@spring.message  code="label.default.data.loading" /></div>
	    <ul id="areaTree" class="ztree">
	   	
	    </ul>
	  </div>
</div>
<#include "/includes/jquery.ftl" />
<#include "/includes/zTree-js.ftl" />
<script>
		$(document).ready(function(){
			var url = {
				api : "${appHost}/admin/area",
			};
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
				var listurl=url.api+"s.json";
				if(undefined===treeNode){
					listurl+="?parentId=0";
				}
				return listurl;
			}
            var zNodes =[
                {code:0,name:"中国",isParent:true,parentId:null,open:true}
            ];
            $(".loading").hide();
            var $areaTree=$("#areaTree");
            alert($areaTree):
            var treeObj=$.fn.zTree.init($areaTree, setting,zNodes);
            function onAsyncSuccess(event, treeId, treeNode, msg) {
                $(".loading").hide();
            }
			function  onNodeClick(event, treeId, treeNode, clickFlag){
                $("#search_form").find("#parentId").val(treeNode.code);
                $("#search_form").find("#seach-btn").click();
			};
			function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
				var zTree = $.fn.zTree.getZTreeObj("areaTree");
				alert("异步获取数据出现异常。");
				treeNode.icon = "";
				zTree.updateNode(treeNode);
			}
            $("#reset-btn").on("click",function () {
                // window.location.reload();
                treeObj.refresh();
                $("#search_form").find("#parentId").val("");
                $("#search_form").find("#seach-btn").click();
            });
		});
	
</script>