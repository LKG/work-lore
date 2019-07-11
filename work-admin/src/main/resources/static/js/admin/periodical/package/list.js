define(function(require, exports, moudles) {
	require('jquery');
	var template = require('arttemplate');
	require('art-dialog');
	var laypage = require('laypage');
	window.dialog = dialog;
	var httpUtil = require('js/common/httpUtil.js');
	var $baseRoot = $('#baseRoot');
	var baseRoot = $baseRoot.attr("href");
	var url = {
		api : baseRoot + "/admin/periodical/package",
	};
	var $msg= require('/js/common/alerts.js');
	var getAreas = require('js/common/areas.js');
	getAreas($("#provinceId"), "0");
	$("#provinceId").on("change", function() {
		var parentCode = $(this).val();
		getAreas($("#cityId"), parentCode, "");
		$("#areaId").empty();
	});
	getAreas($("#cityId"));// 
	$("#cityId").on("change", function() {
		var parentCode = $(this).val();
		getAreas($("#areaId"), parentCode, "");
	});
	// 发布
	var $publish = $("#publish");
	
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
	
	$publish.on("click", function() {
		var $this=$(this);
		var dataIds =getCheckedBox();
		if(dataIds.length==0 ){
			$msg.alert($this,"请选择要更新的数据");
			return;
		}
		var id = $(this).attr("id");
		var d = dialog({
			id:id,
			title : '消息',
			content : '是否要发布该数据包？',
			okValue : '确 定',
			ok : function() {
				var that = this;
				that.title('提交中..');
				$.httpUtil.curl({
					url : url.api +"/" +dataIds+"/publish.json",
					type : "post",
					loading : true,
					dataType : "json",
					data : null,
					success : function(data) {
						search(false);
						$msg.alert($this,"更新成功");
					},
				});
				return true;
			},
			cancelValue : '取消',
			cancel : function() {
			}
		}).showModal();
	});
	var $disabled= $("#disabled");
	$disabled.on("click", function() {
		var $this=$(this);
		var $tbody = $("#table-tbody");
		var dataIds =getCheckedBox();
		if(dataIds.length==0 ){
			$msg.alert($this,"请选择要更新的数据");
			return;
		}
		var id = $(this).attr("id");
		var d = dialog({
			id:id,
			title : '消息',
			content : '是否要下架该数据包？',
			okValue : '确 定',
			ok : function() {
				var that = this;
				that.title('提交中..');
				$.httpUtil.curl({
					url : url.api +"/" +dataIds+"/disabled.json",
					type : "post",
					loading : true,
					dataType : "json",
					data : null,
					success : function(data) {
						search(false);
						$msg.alert($this,"更新成功");
					},
				});
				return true;
			},
			cancelValue : '取消',
			cancel : function() {
			}
		}).showModal();
	});
	var $generate= $("#generate");
	$generate.on("click", function() {
		var $this=$(this);
		var $tbody = $("#table-tbody");
		var dataIds =getCheckedBox();
		if(dataIds.length==0 ){
			$msg.alert($this,"请选择要更新的数据");
			return;
		}
		if(dataIds.length>16 ){
			$msg.alert($this,"最多一次生成15个数据");
			return;
		}
		var id = $(this).attr("id");
		var d = dialog({
			id:id,
			title : '消息',
			content : '是否要重新生成数据包？',
			okValue : '确 定',
			ok : function() {
				var that = this;
				that.title('提交中..');
				$.httpUtil.curl({
					url : url.api +"/" +dataIds+"/generate.json",
					type : "post",
					loading : true,
					dataType : "json",
					data : null,
					success : function(data) {
						search(false);
						$msg.alert($this,"更新成功");
					},
				});
				return true;
			},
			cancelValue : '取消',
			cancel : function() {
			}
		}).showModal();
		
	});
	
	$("#refresh,#seach-btn").on("click", function() {
		$("#page").val(1);
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
							var $tbody = $("#table-tbody");
							$tbody.empty();
							$tbody.append(html);
							// 修改事件
							$tbody.on("click", ".operate .fa-pencil",function() {
										var id = $(this).attr("id");
										var dataId = $(this).attr("data");
										var model = dialog({
											id:id,
											title : "修改",
											cancelValue : '取消',
											bodyClass : "ui-dialog-body-min",
											height : "380",
											width : "750",
											url : url.api  +"/"+ dataId+ ".jhtml"
										}).showModal();
									});
							// 
							$tbody.on("click", ".operate .fa-key", function() {
								var $this=$(this);
								var id = $(this).attr("id");
								var dataId = $this.attr("data");
								var d = dialog({
									id:id,
									title : '消息',
									content : '是否要发布该数据包？',
									okValue : '确 定',
									ok : function() {
										var that = this;
										that.title('提交中..');
										$.httpUtil.curl({
											url : url.api +"/" +dataId+"/publish.json",
											type : "post",
											loading : true,
											dataType : "json",
											data : param,
											success : function(data) {
												$msg.alert($this,"更新成功");
												search(false);
											},
										});
										return true;
									},
									cancelValue : '取消',
									cancel : function() {
									}
								}).showModal();
							});
							$tbody.on("click", ".operate .fa-lock", function() {
								var $this=$(this);
								var id = $(this).attr("id");
								var dataId = $this.attr("data");
								var d = dialog({
									id:id,
									title : '消息',
									content : '是否要下架该数据包？',
									okValue : '确 定',
									ok : function() {
										var that = this;
										that.title('提交中..');
										$.httpUtil.curl({
											url : url.api+"/" +dataId + "/disabled.json",
											type : "post",
											loading : true,
											dataType : "json",
											data : param,
											success :function(data) {
												$msg.alert($this,"更新成功");
												search(false);
											},
										});
										return true;
									},
									cancelValue : '取消',
									cancel : function() {
									}
								}).showModal();
							});
							
							
							$tbody.on("click", ".operate .fa-bell", function() {
								var $this=$(this);
								var id = $(this).attr("id");
								var dataId = $this.attr("data");
								var d = dialog({
									id:id,
									title : '消息',
									content : '是否要重新生成该数据包？',
									okValue : '确 定',
									ok : function() {
										var that = this;
										that.title('提交中..');
										$.httpUtil.curl({
											url : url.api+"/" +dataId + "/generate.json",
											type : "post",
											loading : true,
											dataType : "json",
											data : param,
											success :function(data) {
												$msg.alert($this,"更新成功");
												search(false);
											},
										});
										return true;
									},
									cancelValue : '取消',
									cancel : function() {
									}
								}).showModal();
							});
							// 全选事件
							$("#btSelectAll").on("change",function() {
								var $checkBox = $tbody.find("input[type='checkbox']:not(:disabled)");
								if ($(this).is(':checked')) {
									$checkBox.attr("checked",'true');// 全选
								//	$("#generate,#disabled,#publish").removeAttr("disabled");
							    } else {
							    	//$("#generate,#disabled,#publish").attr("disabled","disabled");
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