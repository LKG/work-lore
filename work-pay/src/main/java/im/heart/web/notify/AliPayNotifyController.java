package im.heart.web.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 接收处理支付宝通知
 */
@Controller
public class AliPayNotifyController extends BasePayController {
    protected static final Logger logger = LoggerFactory.getLogger(AliPayNotifyController.class);
    /**
     * 支付宝移动支付后台通知响应
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/notify/pay/aliPayNotifyRes.htm")
    public ModelAndView aliPayNotifyRes(HttpServletRequest request,
                                        ModelMap model) throws ServletException, IOException {
        logger.info("====== 开始接收支付宝支付回调通知 ======");
        String notifyRes = doAliPayRes(request);
        logger.info("响应给支付宝:{}", notifyRes);
        logger.info("====== 完成接收支付宝支付回调通知 ======");
        super.success(model,notifyRes);
        return new ModelAndView();
    }

    public String doAliPayRes(HttpServletRequest request) throws ServletException, IOException {
        String logPrefix = "【支付宝支付回调通知】";
        //获取支付宝POST过来反馈信息
        return   logPrefix;
    }
}
