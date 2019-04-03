define(function(require, exports, moudles) {
	var $ = require('jquery');
	var template = require('arttemplate');
	var dialog = require('artDialog');
	var laypage = require('laypage');
	window.dialog = dialog;
	var httpUtil = require('js/common/httpUtil.js');
	var $baseRoot = $('#baseRoot');
	var baseRoot = $baseRoot.attr("href");
	var url = {
		api : baseRoot + "/admin/periodical/config",
		materialPrice : baseRoot + "/admin/periodical/material/price",
	};
	var getAreas = require('js/common/areas.js');
	getAreas($("#provinceId"), "0");
	$("#provinceId").on("change", function() {
		var parentCode = $(this).val();
		getAreas($("#cityId"), parentCode, "");
		$("#areaId").empty();
	});
	$("#cityId").on("change", function() {
		var parentCode = $(this).val();
		getAreas($("#areaId"), parentCode, "");
	});
	var $msg= require('/js/common/alerts.js');
	var $generate = $("#generate");
	
	var getCheckedBox=function(){
		var $tbody = $("#table-tbody");
		var $checkedBox = $tbody.find("input[type='checkbox']:checked");
		var dataIds =[];
		$checkedBox.each(function(){ 
			var val=$(this).val();
			if(val!=''&&val!=null){
				dataIds.push(val); 
			}
		});
		return dataIds;
	};
	
	$generate.on("click", function() {
		var $this=$(this);
		var dataIds =getCheckedBox();
		if(dataIds.length==0 ){
			$msg.alert($this,"请选择要生成规则库的数据");
			return;
		}
		var id = $this.attr("id");
		var d = dialog({
			id:id,
			title : '消息',
			content : '是否要生成规则库？',
			okValue : '确 定',
			ok : function() {
				var that = this;
				that.title('提交中..');
				$.httpUtil.curl({
					url : url.api +"/" +dataIds+"/package/priceRule.json",
					type : "post",
					loading : true,
					dataType : "json",
					data : null,
					success : function(data) {
						search(false);
						message($this,"处理完成，请在数据包列表查看任务执行情况");
					},
				});
				return true;
			},
			cancelValue : '取消',
			cancel : function() {
			}
		}).showModal();
	});
	//新增
	var $add = $("#add");
	$add.on("click", function() {
		var id = $(this).attr("id");
		var model = dialog({
			id : id,
			fixed: true,
			title : "新增",
			cancelValue : '取消',
			bodyClass : "ui-dialog-body-min",
			height : "580",
			width : "700",
			url : url.api  +"/add.jhtml",
			onclose: function () {
				search(false);
			},
		});
		model.showModal();
	});
	$("#refresh,#seach-btn").on("click", function() {
		$("#page").val("1");
		search(true);
	});
	$("#paginationSize").on("change",function(){
		$("#size").val($(this).val());
		$("#page").val(1);
		search(true);
	});
	setTimeout(function() {
		search(false);
	}, 10);
	var $tbody = $("#table-tbody");
    $("#btSelectAll").on("change",function() {
        var $checkBox = $tbody.find("input[name='id']:not(:disabled)");
        if ($(this).attr("checked")) {
            $checkBox.attr("checked",'true');// 全选
        } else {
            $checkBox.removeAttr("checked");// 反选
        }
    });
	$tbody.on("click", ".showcity",function() {
		var citynames = $(this).data("citynames");
		var d = dialog({
		    content: '包含地市：'+citynames,
		    align:'right top',
		    quickClose: true// 点击空白处快速关闭
		});
		d.show(this);
		setTimeout(function() {
			d.close().remove();
		}, 2000);
	});
	// 修改事件
	$tbody.on("click", ".operate .btn-edit",function() {
				var id = $(this).attr("data");	
				$.httpUtil.curl({
					url : url.api  +"/"+ id+ ".jhtml",
					type : "get",
					loading : false,
					success : function(data) {
						dialog({
							id : id,
							fixed: true,
							title : "修改",
							cancelValue : '取消',
							bodyClass : "ui-dialog-body-min",
							height : "380",
							width : "750",
							content:data,
							onclose: function () {
								search(false);
							},
						}).showModal();
					}
				});
	});
	//生成规则库
	$tbody.on("click", ".operate .btn-generate",function() {
		var $this=$(this);
		var id = $this.attr("id");
		var dataId = $this.attr("data");	
		var d = dialog({
			id:id,
			title : '消息',
			content : '是否要生成规则库？',
			okValue : '确 定',
			ok : function() {
				var that = this;
				that.title('提交中..');
				$.httpUtil.curl({
					url : url.api +"/" +dataId+"/package/priceRule.json",
					type : "post",
					loading : true,
					dataType : "json",
					data : null,
					success : function(data) {
						search(false);
						$msg.alert($this,"处理完成，请在数据包列表查看任务执行情况");
					},
				});
				return true;
			},
			cancelValue : '取消',
			cancel : function() {
			}
		}).showModal();
});
	$tbody.on("click", ".operate .btn-pageage",function() {
		var $this=$(this);
		var id = $this.attr("id");
		var dataId = $this.attr("data");
		var $wap=$("#dialog-template");
		$wap.find("#data-title").html($this.attr("data-title"));
		var d = dialog({
			id:id,
			title : '生成期刊数据包',
			content : $wap,
			okValue : '确 定',
			ok : function() {
				var that = this;
				that.title('提交中..');
				var periodicalCode=$wap.find("#periodicalCode").val();
				$.httpUtil.curl({
					url : url.api +"/" +dataId+"/package/price.json",
					type : "post",
					loading : true,
					dataType : "json",
					data : {periodicalCode:periodicalCode},
					success : function(data) {
						search(false);
						$msg.alert($this,"处理完成，请在数据包列表查看任务执行情况");
					},
				});
				return true;
			},
			cancelValue : '取消',
			cancel : function() {
			}
		}).showModal();
});
	// 删除事件
	$tbody.on("click", ".operate .btn-del", function() {
		var id = $(this).attr("id");
		var $this=$(this);
		var dataId = $this.attr("data");
		var d = dialog({
			id : id,
			title : '消息',
			content : '是否要删除该记录？',
			okValue : '确 定',
			ok : function() {
				var that = this;
				that.title('提交中..');
				$.httpUtil.curl({
					url : url.api+"/" +dataId + "/disabled.json",
					type : "post",
					loading : true,
					dataType : "json",
					data : null,
					success : function(data) {
						message($this,"更新成功");
						$("#page").val(1);
						search(false);
					},
				});
				return true;
			},
			cancelValue : '取消',
			cancel : function() {
			}
		});
		d.showModal();
	});
	var search = function(loading) {
		$("#btSelectAll").removeAttr("checked");
		var param = $("#search_form").serialize();
		$.httpUtil.curl({
					url : url.api + "s.json",
					type : "post",
					dataType : "json",
					loading : loading,
					data : param,
					success : function(data) {
						if (data.success) {
							var html = template('tr-template-js', data.result);
							$("#paginationTotal").html(data.result.totalElements);
							$tbody.empty();
							$tbody.append(html);
							// 全选事件
							$("#btSelectAll").on("change",function() {
								var $checkBox = $tbody.find("input[type='checkbox']:not(:disabled)");
								if ($(this).attr("checked")) {
									$checkBox.attr("checked",'true');// 全选					
							    } else {
									$checkBox.removeAttr("checked");// 反选
								}
							});

							// 加载分页组件
							laypage({
								cont : 'table-pagination', // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div	// id="page1"></div>
								pages : data.result.totalPages, // 通过后台拿到的总页数
								curr : Number(data.result.number) + 1, // 初始化当前页
								skip : true,
								skin : '#AF0000',
								jump : function(obj, first) { // 触发分页后的回调
									if (!first) {
										var curr = obj.curr;
										$("#page").val(obj.curr);
										search(false);
									}
								}
							});
							laypage({
								cont : 'table-pagination2', // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div	// id="page1"></div>
								pages : data.result.totalPages, // 通过后台拿到的总页数
								curr : Number(data.result.number) + 1, // 初始化当前页
								skip : true,
								skin : '#AF0000',
								jump : function(obj, first) { // 触发分页后的回调
									if (!first) {
										var curr = obj.curr;
										$("#page").val(obj.curr);
										search(false);
									}
								}
							});
						}
					}
				});
	}
});