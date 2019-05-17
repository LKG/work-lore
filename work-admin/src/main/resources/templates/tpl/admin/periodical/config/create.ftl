<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>添加期刊</title>
  <#include "/includes/head.ftl" />
	<link rel="stylesheet" type="text/css" href="${appHost}/css/style.css?v=${ver!'1'}" />
    <#include "/includes/zTree-css.ftl" />
    <#assign template="desk"/>
    <style>
    .Wdate{
	  height: 32px !important;
	  padding: 3px 1px !important; 
	  border: 1px solid #ccc !important; 
	}
	.form-inline .form-group,.form-inline .form-control{
		width: 100px;
		display: inline;
	}
   .form-inline select{
   	    padding: 6px 6px  !important; 
   }
	.tab-pane{
		padding-top:30px;
	}
	.panel-heading {
		padding: 5px 13px;
	}
	.panel-heading  h4{
		border-left: 2px solid #f35d5d;
		padding-left:15px;
		margin-left:-13px;
	}
</style>
    
</head> 
<body class="page-header-fixed">
	<div class="clearfix" ></div>
	<!------页面header信息 end----->
	<div class="full-container" id="main-container" style="height: 531px;">
	   <form class="form-inline" id="main-form" >
	   	   <input type="hidden" name="type" value="1"  />
		    <input type="hidden" name="cityIds" id="cityIds" value="" />
			 <input type="hidden" name="cityNames" id="cityNames" value="" />
			<input type="hidden" name="citys" id="citys" value="" />
			 <input type="hidden" id="hideCityId" name="cityId" value="" />	
			<div class="form-group">
				<label for="cityId" >期刊地区:</label>
			 		<select class="form-control" style="width: 90px;" id="provinceId" name="cityId_LIKES">
					   	<option value="" >------</option>
					</select>
					<select class="form-control" style="width: 105px;" id="cityId" name="cityId_LIKES">
					   	<option value="" >------</option>
					</select>
				<select class="form-control" style="width: 105px;" id="areaId" name="cityId_LIKES">
					<option value="" >------</option>
			 </select>
		  </div>
	     <div class="form-group" >
			 <label for="year">年份:</label>
				<input type="text" name='year' id="year" style="width: 80px;" class="Wdate form-control"
							size='10' maxlength='10' 
						value="${.now?string('yyyy')}" 
				 onclick="WdatePicker({isShowClear:true,ychanged :ychanged ,readOnly:false,dateFmt:'yyyy',maxDate:'%y-%M-%d'})"/>
		</div>
		  <div class="row" >
		  	<div class="col-xs-6" style="padding-top: 20px;">
		  		<div class="form-group"  >
				 <label for="periodicalName" >期刊名称:</label>
			 	<input type="text" class="form-control" style="width: 200px;"  id="periodicalName" name="name" placeholder="名称"/>
				</div>
				<div class="form-group" >
				 <label for="periodicalDesc" style="display: block;margin-top: 20px;" >期刊描述:</label>
				 <textarea class="form-control" style="width: 360px;"  id="periodicalDesc" name="periodicalDesc" placeholder="期刊描述" rows="3"></textarea>

				</div>
		  	</div>
		  	<div class="col-xs-6" style="padding-top: 20px;">
		  		<div class="form-group" >
		  		<label for="areaTree">包含地市:</label>
				 <ul id="areaTree" class="ztree"  ></ul>
				</div>
		  	</div>
		  </div>
		  <button id="main-btn-save"  data-loading-text="提交中..." class="btn btn-danger"><i class="fa fa-save" ></i>保存</button>
	   </form>
	</div>
  	<!------seajs.config 引用信息 begin----->
	<#include "/includes/seajs.config.ftl" />
	<!------seajs.config   引用信息 end----->
	<script>
  	seajs.use("js/admin/periodical/config/create.js?v="+Math.random());
	</script>
	<#include "/includes/datePicker.ftl" />
 	<#include "/includes/jquery.ftl" />
  	<#include "/includes/zTree-js.ftl" />
  	<script type="text/javascript">
	  	function ychanged (){
	  		var desc=$("#periodicalDesc").val();
	  		$("#periodicalDesc").val(desc.replace(/\d.{3}/g, $dp.cal.getP('y')));
	  	}
  	
		$(document).ready(function(){
			$("#provinceId").on("change", function() {
				$.fn.zTree.destroy();
			});
			$("#main-btn-save").on("click", function() {
				var checkedNodes=$.fn.zTree.getZTreeObj("areaTree").getCheckedNodes();
				var cityIds=[];
				var cityNames=[];
				var citys=[];
	     		$.each(checkedNodes,function(index,node){
					cityIds.push(node.code);
					cityNames.push(node.name);
					citys.push({'cityId':node.code,'cityName':node.name});
				});
				$("#cityIds").val(cityIds);
				$("#cityNames").val(cityNames);
				$("#citys").val(JSON.stringify(citys));
			});
			$("#provinceId,#cityId,#areaId").on("change", function() {
				var parentCode = $(this).val();
				var setting = {
					check : {
						autoCheckTrigger : false,
						chkboxType : {"Y" : "ps", "N" : "ps" },
						chkStyle : "checkbox",
						enable : true,
						nocheckInherit : false,
						radioType : "level",
					},
					view: {
						selectedMulti: false
					},
					data: {
						key: {
							title:"name"
						},
						simpleData: {
							enable: true,
							idKey: "code",
							pIdKey: "parentId",
							rootPId: parentCode,
						}
					},
				};
				if(parentCode==null||parentCode==""){
					$.fn.zTree.destroy();
					return;
				}
				var name= $(this).find("option:selected").text();
				$("#periodicalName").val(name+"信息价");
				$("#periodicalDesc").val($("#year").val()+$("#periodicalName").val());
				var pid=$("#provinceId").val();
				var zNodes =[{code:parentCode, parentId:pid, name: name,checked:true}];

				$.ajax({
					url : "${appHost}/admin/areas.json?size=500&status=enabled&parentId=" + parentCode,
					type : "post",
					dataType : "json",
					success : function(data) {
						if (data.success) {
							$.each(data.result.content, function(index, item) {
								var $option = $("<option>").text(item.name).val(item.code);
								var parentCode = item.code;
								var name= item.name;
								var pid=item.parentId;
								zNodes.push({code:parentCode, parentId:pid, name: name});
							});
						}
						var $areaTree=$("#areaTree");
						var treeObj =$.fn.zTree.init($areaTree, setting, zNodes);
					}
				});
			});

		});
	</script>
</body>
</html>