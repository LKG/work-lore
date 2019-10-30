package im.heart.alipay.web;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.Product;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import im.heart.alipay.service.AliPayService;
import im.heart.core.web.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Slf4j
public class AliPayController extends AbstractController {
    /**
     * 固定的appId
     */
    final static String appId="20000067";
    /**
     * 阿里协议
     */
    final static String STARTAPP_URL="alipays://platformapi/startapp";
    @Autowired
    AliPayService aliPayService;

    /**
     *
     * @param product
     * @param map
     * @return
     */
    @RequestMapping(value = "/pay/aliPay/pc",method=RequestMethod.POST)
    public String  pcPay(Product product, ModelMap map) {
        logger.info("电脑支付");
        String form  =  this.aliPayService.payByPc(product);
        map.addAttribute("form", form);
        return "alipay/pay";
    }
    @RequestMapping(value="pay/aliPay/mobile",method=RequestMethod.POST)
    public ModelAndView  mobilePay(Product product, ModelMap model) {
        logger.info("手机H5支付");
        String form  =  aliPayService.payByMobile(product);
        model.put("form", form);
        super.success(model);
        return new ModelAndView();
    }
    @RequestMapping(value="pay/aliPay/qr",method=RequestMethod.POST)
    public String  qrPay(String orderId, ModelMap model) {
        logger.info("二维码支付orderId:{}",orderId);
        return "alipay/qcpay";
    }
    @RequestMapping(value="pay/aliPay/app",method=RequestMethod.POST)
    public String  appPay(Product product, ModelMap model) {
        logger.info("app支付服务端");
        String orderString  =  aliPayService.payByApp(product);
        model.put("orderString", orderString);
        super.success(model);
        return "alipay/pay";
    }
}
