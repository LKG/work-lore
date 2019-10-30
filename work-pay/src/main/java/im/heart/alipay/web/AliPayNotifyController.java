package im.heart.alipay.web;

import im.heart.core.web.AbstractController;
import im.heart.core.web.utils.WebUtilsEx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 接收处理支付宝通知
 */
@Controller
@Slf4j
public class AliPayNotifyController extends AbstractController {
    /**
     * 支付宝移动支付后台通知响应
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/notify/pay/aliPayNotifyRes")
    public ModelAndView aliPayNotifyRes(HttpServletRequest request,
                                        ModelMap model) throws ServletException, IOException {
        logger.info("====== 开始接收支付宝支付回调通知 ======");
        //验证签名 校验签名
        boolean signVerified = false;
        if (signVerified) {
            logger.info("支付宝验证签名成功！");
        }
        String notifyRes = doAliPayRes(request);
        logger.info("响应给支付宝:{}", notifyRes);
        logger.info("====== 完成接收支付宝支付回调通知 ======");
        super.success(model,notifyRes);
        return new ModelAndView();
    }

    public String doAliPayRes(HttpServletRequest request) throws ServletException, IOException {
        String logPrefix = "【支付宝支付回调通知】";
        //获取支付宝POST过来反馈信息
        String body=WebUtilsEx.getRequestBodyForString(request);
        logger.info("{}通知请求数据:reqStr={}", logPrefix,body);
        return   logPrefix;
    }
}
