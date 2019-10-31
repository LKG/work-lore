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
        createApi : baseRoot + "/pay/aliPay/qr.json",
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
        var action=url.createApi;
        if(action.indexOf("?") == -1){
            action = action + "?rid="+Math.random();
        }else{
            action = action + "&rid="+Math.random();
        }
        var items = getFormData($("#J_OrderItemForm"));
        if(post_flag) return; //如果正在提交则直接返回，停止执行
        var $btn=$(this);
        var dataHtml=$(this).html();
        $btn.html($btn.attr("data-loading-text")).attr("disabled","disabled");
        var data={
            productId:productId,
            subject:subject,
            body:body,
            totalFee:totalFee,
            outTradeNo:outTradeNo,
            outTradeNo:outTradeNo,
        };
        $.httpUtil.curl({
            url : action,
            type : "post",
            loading : false,
            dataType : "json",
            data : data,
            success : function(data) {
                var content="删除成功";
                $msg.alert($(this),content);
                $btn.html(dataHtml).removeAttr("disabled");
            },
        });
        $btn.html(dataHtml).removeAttr("disabled");
    });
});