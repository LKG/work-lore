define(function (require, exports, moudles) {
require('jquery')
	require('art-dialog');
	exports.alert=function(obj,content,loading,isClose){
		var id = $(obj).attr("id");
		$(obj).attr("disabled", "disabled");
		var d = dialog({
			id : id,
			title : '消息',
			content : content?content:'GG正在努力研发中。。。',
			onclose: function () {
				$(obj).removeAttr("disabled");
			},
			cancelValue : '取消',
		});
		if(!loading){
			d.show();
		}else{
			d.showModal();
		}
		if(!isClose){
			setTimeout(function() {
				d.close().remove();
			}, 1000);
		}
	}
});