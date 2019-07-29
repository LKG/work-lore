<#include "/includes/taglib.ftl" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title>文件上传 - <@spring.message  code="label.system.name" /></title>
    <#include "/includes/head.ftl" />
    <#include "/includes/fine-uploader-css.ftl" />
    <#assign template="desk"/>
    <style>
        .tab-pane{
            padding-top:30px;
        }
        .Wdate{
            height: 32px !important;
            padding: 3px 1px !important;
            border: 1px solid #ccc !important;
        }
        .form-inline .form-group,.form-inline .form-control{
            width: 100px;
            display: inline;
        }
        .panel-heading {
            padding: 5px 13px;
        }
        .panel-heading  h4{
            border-left: 2px solid #f35d5d;
            padding-left:15px;
            margin-left:-13px;
        }
        .qq-upload-button{
            border-radius: 4px;
            color: #fff;
            background-color: #337ab7;
            border-color: #2e6da4;
            box-shadow:none;
            margin-right: 8px;
        }
        .qq-edit-filename-icon{
            background:none;
        }
        .qq-upload-list li{
            margin-bottom: 2px;
        }
        .qq-thumbnail-selector{
            height: 50px;
        }
    </style>
    <#include "/includes/zTree-css.ftl" />
</head>
<body class="page-header-fixed">
<!-- toolbar begin-->
<#include "/index-toolbar.ftl"/>
<!-- toolbar end-->
<!-----------------------------------------顶部-------------->
<!-- header begin-->
<#include "/index-header.ftl" />
<!-- header end-->
<div class="clear"></div>
<!--轮播图上方导航栏  一栏-->
<div class="clearfix" ></div>
<!------页面header信息 end----->
<div class="full-container " id="main-container" style="height: 531px;">
<div class="alert alert-warning" style="padding: 5px;margin-bottom: 0px;"  role="alert">
    <p class="form-control-static">
        1、请勿上传已设置加密或只读的资源，不支持此类资源在线阅读。
        2、 目前支持文件格式(ppt,pptx,xls,xlsx,doc,docx,txt)
    </p>
</div>
<div class="container-full" >
        <div class="row" style="margin-right: 0px;" >
            <div class="col-xs-2">
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
            <div class="col-xs-10">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="periodicalCode" >期刊</label>
                        <input type="text" name='periodicalCode' style="width: 80px;" class="Wdate form-control"
                               size='10' maxlength='10' id="periodicalCode"
                               value="${(.now?string("yyyyMM"))!}"
                               onclick="WdatePicker({isShowClear:true,readOnly:false,dateFmt:'yyyyMM',maxDate:'%y-%M-%d'})"})"/>
                    </div>
               <#--     <div class="form-group">
                        <label for="price" >单价</label>
                        <input type="number" name='price' style="width: 80px;" class="form-control"
                               size='10' maxlength='10' id="price"  min="0"
                               value="0"/>
                    </div>-->
                    <div class="form-group">
                        <label for="type" >文档类型</label>
                     <select>

                     </select>
                    </div>
                    <div class="form-group">
                        <label for="originPrice" >标价</label>
                        <input type="number" name='originPrice' style="width: 80px;" class="form-control"
                               size='10' maxlength='10' id="originPrice"  min="0"
                               value="9.9"/>
                    </div>
                    <div class="form-group">
                        <label for="finalPrice" >售价</label>
                        <input type="number" name='finalPrice' style="width: 80px;" class="form-control"
                               size='10' maxlength='10' id="finalPrice"  min="0"
                               value="0.00"/>
                    </div>
                    <div class="form-group">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" checked name="clearHeader" id="clearHeader" > 清空页眉页脚
                            </label>
                        </div>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label for="finalPrice" >标题</label>
                        <textarea name="ctl00$Content$TextBoxKeyWords" rows="2" cols="20" id="Content_TextBoxKeyWords" class="textbox" onchange="this.value=this.value.substring(0, 150)" onkeydown="this.value=this.value.substring(0, 150)" onkeyup="this.value=this.value.substring(0, 150)" style="width: 592px; height:40px;"></textarea>
                    </div>
                    <input type="hidden" name='categoryId' id="categoryId" style="width: 80px;" class="form-control"/>
                    <input type="hidden" name='categoryCode' id="categoryCode"  style="width: 80px;" class="form-control"/>
                </form>
                <div id="fine-uploader-manual-trigger"  >

                </div>
            </div>
     </div>
    </div>
</div>
    <#include "/includes/footer.ftl" />
<!------seajs.config 引用信息 begin----->
	<#include "/includes/seajs.config.ftl" />
<!------seajs.config   引用信息 end----->
	<#include "/includes/datePicker.ftl" />
	<#include "/includes/jquery.ftl" />
	<#include "/includes/zTree-js.ftl" />
<script>
    seajs.use("js/desk/bath_import.js?v="+Math.random());
</script>
	 <#include "/pages/qq-template-manual-trigger.ftl" />

<script type="text/javascript">
    var setting = {
        async: {
            enable: true,
            dataFilter: filter,
            autoParam:["categoryId=parentId"],
            otherParam:{"size":"2000"},
            url: getUrl
        },
 /*       check: {
            enable: true,
            autoCheckTrigger: true,
            chkStyle: "radio",
            radioType: "all"
        },*/
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
                var categoryCode=node.categoryCode;
                var isParent=false;
                if(node.hasChildren){
                    isParent=true;
                }
                var parentId=node.parentId;
                var $node={categoryId:categoryId,categoryCode:categoryCode,name:name,isParent:isParent,parentId:parentId};
                nodes.push($node);
            });
        }
        return nodes;
    }
    function getUrl(treeId, treeNode) {
        var url="${appHost}/api/periodical/categorys.json";
        if(undefined===treeNode){
            url+="?parentId=0";
        }
        return url;
    }
    function onAsyncSuccess(event, treeId, treeNode, msg) {
        //$("#loading").hide();
    }
    function  onNodeClick(event, treeId, treeNode, clickFlag){
        $("#categoryId").val(treeNode.categoryId);
        $("#categoryCode").val(treeNode.categoryCode);
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