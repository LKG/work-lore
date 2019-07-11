define(function(require, exports, moudles) {
	var $ = require('jquery');
    var template = require('arttemplate');
	var dialog = require('art-dialog');
	var laypage = require('laypage');
	window.dialog = dialog;
	var httpUtil = require('js/common/httpUtil.js');
	var $baseRoot = $('#baseRoot');
	var baseRoot = $baseRoot.attr("href");
	var url = {
		api : baseRoot + "/admin/periodical",
	};
	var $msg= require('/js/common/alerts.js');
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

	// 删除
	var $remove = $("#remove");
	$remove.on("click", function() {
		var $this=$(this);
		var dataIds =getCheckedBox();
		if(dataIds.length==0 ){
			$msg.alert($this,"请选择要删除记录数");
			return;
		}
		if(dataIds.length>15 ){
			$msg.alert($this,"最多一次选择15个数据");
			return;
		}
		var id = $(this).attr("id");
		var d = dialog({
			id:id,
			title : '消息',
			content : '是否删除选中记录？',
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
                        $("#page").val(1);
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
    var $publish=$("#publish");
    $publish.on("click", function() {
        var $this=$(this);
        var dataIds =getCheckedBox();
        if(dataIds.length==0 ){
			$msg.alert($this,"请选择要发布的数据");
            return;
        }
        if(dataIds.length>15 ){
			$msg.alert($this,"最多一次发布15期");
            return;
        }
        var id = $this.attr("id");
        var d = dialog({
            id:id,
            title : '消息',
            content : '是否要发布该数据？',
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
                    success :function(data) {
						$msg.alert($this,"发布成功");
                        $("#page").val(1);
                        search(false);
                        $this.removeAttr("disabled");
                    },
                });
                return true;
            },
            cancelValue : '取消',
            cancel : function() {
            }
        }).showModal();

    });
    var $parse=$("#parse");
    $parse.on("click", function() {
        var $this=$(this);
        var dataIds =getCheckedBox();
        if(dataIds.length==0 ){
            $msg.alert($this,"请选择要的重新生成的数据");
            return;
        }
        if(dataIds.length>15 ){
            $msg.alert($this,"最多一次重新生成的15期");
            return;
        }
        var id = $this.attr("id");
        var d = dialog({
            id:id,
            title : '消息',
            content : '是否要重新生成选中的数据？',
            okValue : '确 定',
            ok : function() {
                var that = this;
                that.title('提交中..');
                $.httpUtil.curl({
                    url : url.api+"/" +dataIds + "/parse.json",
                    type : "post",
                    loading : true,
                    dataType : "json",
                    data : null,
                    success :function(data) {
                        $msg.alert($this,"任务提交成功");
                        $("#page").val(1);
                        $this.removeAttr("disabled");
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
    var $lock=$("#lock");
    $lock.on("click", function() {
        var $this=$(this);
        var dataIds =getCheckedBox();
        if(dataIds.length==0 ){
			$msg.alert($this,"请选择要的下架的数据");
            return;
        }
        if(dataIds.length>15 ){
			$msg.alert($this,"最多一次下架15期");
            return;
        }
        var id = $this.attr("id");
        var d = dialog({
            id:id,
            title : '消息',
            content : '是否要下架选中的数据？',
            okValue : '确 定',
            ok : function() {
                var that = this;
                that.title('提交中..');
                $.httpUtil.curl({
                    url : url.api+"/" +dataIds + "/pull.json",
                    type : "post",
                    loading : true,
                    dataType : "json",
                    data : null,
                    success :function(data) {
						$msg.alert($this,"更新成功");
                        $("#page").val(1);
                        $this.removeAttr("disabled");
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
	$("#refresh,#seach-btn").on("click", function() {
		$("#page").val(1);
		search(true);
	});
	$("#paginationSize").on("change",function(){
		$("#size").val($(this).val());
		$("#page").val(1);
		search(true);
	});
    var $tbody = $("#table-tbody");
    $tbody.on("click", ".operate .btn-publish", function() {
        var $this=$(this);
        var id = $(this).attr("id");
        var dataId = $this.attr("data");
        var d = dialog({
            id:id,
            title : '消息',
            content : '是否要发布该扫文档？',
            okValue : '确 定',
            ok : function() {
                var that = this;
                that.title('提交中..');
                $.httpUtil.curl({
                    url : url.api +"/" +dataId+"/publish.json",
                    type : "post",
                    loading : true,
                    dataType : "json",
                    data : null,//修改
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
    $tbody.on("click", ".operate .btn-parse", function() {
        var $this=$(this);
        var id = $(this).attr("id");
        var dataId = $this.attr("data");
        var d = dialog({
            id:id,
            title : '消息',
            content : '是否要重新生成？',
            okValue : '确 定',
            ok : function() {
                var that = this;
                that.title('提交中..');
                $.httpUtil.curl({
                    url : url.api+"/" +dataId + "/parse.json",
                    type : "post",
                    loading : true,
                    dataType : "json",
                    data : null,//修改
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
    $tbody.on("click", ".operate .btn-lock", function() {
        var $this=$(this);
        var id = $(this).attr("id");
        var dataId = $this.attr("data");
        var d = dialog({
            id:id,
            title : '消息',
            content : '是否要下架？',
            okValue : '确 定',
            ok : function() {
                var that = this;
                that.title('提交中..');
                $.httpUtil.curl({
                    url : url.api+"/" +dataId + "/pull.json",
                    type : "post",
                    loading : true,
                    dataType : "json",
                    data : null,//修改
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
	// 查看事件
	$tbody.on("click", ".operate .btn-view",function() {
		var $this=$(this);
		var id = $(this).attr("id");
		var dataId = $this.attr("data");
		var content=$("#log-"+dataId).html();
		var d = dialog({
			id : id,
			title : '导入日志',
			content : content,
		}).showModal();
	});
	// 删除事件
	$tbody.on("click", ".operate .btn-remove", function() {
		var $this=$(this);
		var id = $(this).attr("id");
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
					success :function(data) {
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
		}).showModal();
	});
	// 全选事件
	$("#btSelectAll").on("change",function() {

		var $checkBox = $tbody.find("input[type='checkbox']:not(:disabled)");
		if ($(this).is(':checked')) {
			$checkBox.attr("checked",'true');// 全选
			$("#remove").removeAttr("disabled");
		} else {
			$checkBox.removeAttr("checked");// 反选
		}
	});
	var search = function(loading) {
		$("#btSelectAll").removeAttr("checked");
		var param = $("#search_form").serialize();
		$.httpUtil.curl({
					url : url.api + "s.json",
					type : "get",
					dataType : "json",
					loading : loading,
					data : param,
					success : function(data) {
						if (data.success) {
							var html = template('tr-template-js', data.result);
							$("#paginationTotal").html(data.result.totalElements);
							$tbody.empty();
							$tbody.append(html);
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
						}
					}
				});
	};

	var $pagination=$("#table-pagination");
	laypage({
		cont : $pagination, // 容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div	// id="page1"></div>
		pages : $pagination.attr('data-totalPages'), // 通过后台拿到的总页数
		curr : Number($pagination.attr('data-number')) + 1, // 初始化当前页
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
});