define(function (require, exports, moudles) {
	var $ = require('jquery');
	var template = require('template');
	 var $baseRoot=$("#baseRoot");
	 var baseRoot=$baseRoot.attr("href");
	 var url=baseRoot+"/projects.json?size=16";
	 $.ajax({
         url:url,
         dataType:"jsonp",
         jsonp:"jsoncallback",
         success:function(json){
       	  var html = template('msg-template-js', json.result);
       	
		  $("#template-container").html(html);
         }
    });
	 
});