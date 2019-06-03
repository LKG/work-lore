define(function (require, exports, moudles) {
	var $ = require('jquery');
	var jQuery = require('jquery');
	var pin = require('pin');
    var template = require('arttemplate');
    var lazyload = require('lazyload');
	var $msg= require('/js/common/alerts.js');
	var $baseRoot=$("#baseRoot");
	var baseRoot=$baseRoot.attr("href");
	$("#xx,#xx a").on("click",function () {
		$("#top-banner").hide();
    });
	//TODO：后期考虑公用全局配置
	 function showNum(f){
		  if(f>=10000){
             return parseInt(f/10000) +"万";
         }else if(f<10000 && f>=1000){
             return parseInt(f/1000) +"千";
         }else{
             return f;
         }
	  }
	 //加载轮播图片
	 function getDocImgList(){
		 var url=baseRoot+"/doc/"+$("#doc-carousel-generic").attr("data")+"/imgs.json";
		 $.ajax({
	         url:url,
	         dataType:"jsonp",
	         jsonp:"jsoncallback",
	         success:function(json){
	       	  var html = template('doc-carousel-template-js', json);
	       	  $("#doc-carousel-generic").empty();
			  $("#doc-carousel-generic").append(html);
				 $("img.lazy").lazyload({
					 // placeholder,值为某一图片路径.此图片用来占据将要加载的图片的位置,待图片加载时,占位图则会隐藏
					 effect: "fadeIn", // 载入使用何种效果
					 // effect(特效),值有show(直接显示),fadeIn(淡入),slideDown(下拉)等,常用fadeIn
					 threshold: 200, // 提前开始加载
					 // threshold,值为数字,代表页面高度.如设置为200,表示滚动条在离目标位置还有200的高度时就开始加载图片,可以做到不让用户察觉
					 // event,值有click(点击),mouseover(鼠标划过),sporty(运动的),foobar(…).可以实现鼠标莫过或点击图片才开始加载,后两个值未测试…
					 failurelimit : 10 // 图片排序混乱时
					 // failurelimit,值为数字.lazyload默认在找到第一张不在可见区域里的图片时则不再继续加载,但当HTML容器混乱的时候可能出现可见区域内图片并没加载出来的情况,failurelimit意在加载N张可见区域外的图片,以避免出现这个问题.
				 });
	         }
	    }); 
	 }
	getDocImgList();

	 $("#btn-praise").on("click" ,function(){
		 var $obj=$(this);
		 $obj.attr("disabled","disabled");
		  var dataUrl=$obj.attr("data-url");
		  $.ajax({
		         url:dataUrl,
		         dataType:"jsonp",
		         jsonp:"jsoncallback",
		         success:function(data){
					 $msg.alert($(this),"点赞成功！");
		        	 $obj.removeAttr("disabled");
		        	// $obj.find(".bs-smll-support-code").html(showNum(data.result));
		         }
		    }); 
	 });
    $("#btn-focus").on("click" ,function(){
        var $obj=$(this);
        $obj.attr("disabled","disabled");
        var dataUrl=$obj.attr("data-url");
        $.ajax({
            url:dataUrl,
            dataType:"jsonp",
            jsonp:"jsoncallback",
            success:function(data){
                if(data.httpstatus==403){
                    window.location.href="/login.jhtml";
                    return;
                }
                if (data.success) {
					$msg.alert($(this),"收藏成功！");
                    $("#collect-txt").text("已收藏");
                }else {
					$msg.alert($(this),"已收藏！");
				}
                $obj.removeAttr("disabled");
               // $obj.find(".bs-smll-support-code").html(showNum(data.result));
            }
        });
    });
    // setTimeout(function() {
    //     $(".pin").pin({
    //         // containerSelector: ".container"
    //     });
	// },100);
});