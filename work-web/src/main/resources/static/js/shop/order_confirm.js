define(function (require, exports, moudles) {
    require('jquery');
    ;
    require('art-dialog');
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
    var $mainForm=$("#J_PayForm");
    var post_flag = false;

    var validator = $mainForm.validate({
        rules: {
            bankSelect: {
                required: true,
            },
        },
        messages:{
            bankSelect: {
                required: "请选择支付方式",
            },
        }
    });
    $("#pay-btn").on("click",function(){
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
        alert("@@@@@@@@@@2")
    });
});