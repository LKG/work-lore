define(function (require, exports, moudles) {
    var $ = require('jquery');
    var jQuery = require('jquery');
    var dialog = require('artDialog');
    var $baseRoot=$("#baseRoot");
    var baseRoot=$baseRoot.attr("href");
    var $httpUtil = require('/js/common/httpUtil.js');
    require('/js/common/validate');
    var $msg= require('/js/common/alerts.js');
    var url = {
        createApi : baseRoot + "/order/subscribe.json",
    };
    //注册按钮
    var getFormData=function(obj){
        var data = {};
        var $form=$(obj);
        var dataArray=$form.serializeArray();
        $.each(dataArray, function() {
            if (data[this.name]) {
                if (!data[this.name].push) {
                    data[this.name] = [data[this.name]];
                }
                data[this.name].push(this.value || '');
            } else {
                data[this.name] = this.value || '';
            }
        });
        return data;
    };
    var $mainForm=$("#J_OrderForm");
    var post_flag = false;
    $("#J_OrderSubmit").on("click",function(){
        if(!$mainForm.valid()){
            return;
        }
        var $btn=$(this);
        var dataHtml=$(this).html();
        var action=url.createApi;
        if(action.indexOf("?") == -1){
            action = action + "?rid="+Math.random();
        }else{
            action = action + "&rid="+Math.random();
        }
        var prodIds=[];
        $("#J_OrderItemForm input[name=prodId]").each(function(){
            var value = $(this).val(); //这里的value就是每一个input的value值~
            prodIds.push(value);
        });
        var items = getFormData($("#J_OrderItemForm"));
        var sendData={
            couponId:0,
            invoiceFlag:0,
            items:items,
            prodIds:prodIds,
        };
        if(post_flag) return; //如果正在提交则直接返回，停止执行
        $btn.html($btn.attr("data-loading-text")).attr("disabled","disabled");
        $.httpUtil.curl({
            url : action,
            type : "post",
            contentType: 'application/json',
            loading : true,
            dataType : "json",
            data : JSON.stringify(sendData) ,
            success :function(data) {
                if(data.success){
                 //   $msg.alert($btn,"<i class='fa  fa-check-circle text-success'></i>订单提交成功");
                    post_flag=true;
                    return window.location.href = "/order/confirmOrderInfo?orderId=" + data.result.orderId;
                }else{
                    $msg.alert($btn,"<i class='fa fa-exclamation-triangle text-danger'></i>订单提交异常");
                }
                $btn.html(dataHtml).removeAttr("disabled");
            },error:function () {
                $msg.alert($btn,"<i class='fa  fa-check-circle text-success'></i>订单提交异常");
                post_flag=true;
            }
        });
    });
});