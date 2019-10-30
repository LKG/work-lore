package im.heart.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import im.heart.core.web.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Slf4j
public class PayOrderController extends AbstractController {
    /**
     * 查询支付订单接口:
     * 1)先验证接口参数以及签名信息
     * 2)根据参数查询订单
     * 3)返回订单数据
     * @param params
     * @return
     */
    @RequestMapping(value = "/pay/query_order")
    public ModelAndView queryPayOrder(@RequestParam String params) {
        logger.info("###### 开始接收商户查询支付订单请求 ######");
        String logPrefix = "【商户支付订单查询】";
//        logger.info("{}/pay/query_order, host:{}, service_id:{}, params:{}", logPrefix, instance.getHost(), instance.getServiceId(), params);
        try {
            JSONObject po = JSONObject.parseObject(params);
            JSONObject payContext = new JSONObject();
            // 验证参数有效性
            String errorMessage = validateParams(po, payContext);
            if (!"success".equalsIgnoreCase(errorMessage)) {
                logger.warn(errorMessage);
                return null;
            }
            logger.debug("请求参数及签名校验通过");
            // 商户ID
            String mchId = po.getString("mchId");
            // 商户订单号
            String mchOrderNo = po.getString("mchOrderNo");
            // 支付订单号
            String payOrderId = po.getString("payOrderId");
            // 是否执行回调
            String executeNotify = po.getString("executeNotify");
            JSONObject payOrder;
//            String retStr = payOrderServiceClient.queryPayOrder(getJsonParam(new String[]{"mchId", "payOrderId", "mchOrderNo", "executeNotify"}, new Object[]{mchId, payOrderId, mchOrderNo, executeNotify}));
//            JSONObject retObj = JSON.parseObject(retStr);
//            logger.info("{}查询支付订单,结果:{}", logPrefix, retObj);
//            if(!"0000".equals(retObj.getString("code"))) {
//                return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, retObj.getString("msg"), null, null));
//            }
//            payOrder = retObj.getJSONObject("result");
//            if (payOrder == null) {
//                return XXPayUtil.makeRetFail(XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_FAIL, "支付订单不存在", null, null));
//            }
//            Map<String, Object> map = XXPayUtil.makeRetMap(PayConstant.RETURN_VALUE_SUCCESS, "", PayConstant.RETURN_VALUE_SUCCESS, null);
//            map.put("result", payOrder);
//            logger.info("商户查询订单成功,payOrder={}", payOrder);
            logger.info("###### 商户查询订单处理完成 ######");
            return null;
        }catch (Exception ex) {
            logger.error("queryPayOrder:"+ex.getStackTrace()[0].getMethodName(), ex);
            return null;
        }
    }

    /**
     * 验证创建订单请求参数,参数通过返回JSONObject对象,否则返回错误文本信息
     * @param params
     * @return
     */
    private String validateParams(JSONObject params, JSONObject payContext) {
        // 验证请求参数,参数有问题返回错误提示
        String errorMessage;
        // 支付参数
        // 商户ID
        String mchId = params.getString("mchId");
        // 商户订单号
        String mchOrderNo = params.getString("mchOrderNo");
        // 支付订单号
        String payOrderId = params.getString("payOrderId");
    // 签名
        String sign = params.getString("sign");

        // 验证请求参数有效性（必选项）
        if(StringUtils.isBlank(mchId)) {
            errorMessage = "request params[mchId] error.";
            return errorMessage;
        }
        if(StringUtils.isBlank(mchOrderNo) && StringUtils.isBlank(payOrderId)) {
            errorMessage = "request params[mchOrderNo or payOrderId] error.";
            return errorMessage;
        }
        // 签名信息
        if (StringUtils.isEmpty(sign)) {
            errorMessage = "request params[sign] error.";
            return errorMessage;
        }
        // 查询商户信息
        JSONObject mchInfo;
        String retStr ="";
        JSONObject retObj = JSON.parseObject(retStr);
        String code=retObj.getString("code");
        if("0000".equals(code)) {
            mchInfo = retObj.getJSONObject("result");
            if (mchInfo == null) {
                errorMessage = "Can't found mchInfo[mchId="+mchId+"] record in db.";
                return errorMessage;
            }
            if(mchInfo.getByte("state") != 1) {
                errorMessage = "mchInfo not available [mchId="+mchId+"] record in db.";
                return errorMessage;
            }
        }else {
            errorMessage = "Can't found mchInfo[mchId="+mchId+"] record in db.";
            logger.info("查询商户没有正常返回数据,code={},msg={}", code, retObj.getString("msg"));
            return errorMessage;
        }
        String reqKey = mchInfo.getString("reqKey");
        if (StringUtils.isBlank(reqKey)) {
            errorMessage = "reqKey is null[mchId="+mchId+"] record in db.";
            return errorMessage;
        }
        payContext.put("resKey", mchInfo.getString("resKey"));
        // 验证签名数据
        boolean verifyFlag = false;
        if(!verifyFlag) {
            errorMessage = "Verify XX pay sign failed.";
            return errorMessage;
        }
        return "success";
    }

}
