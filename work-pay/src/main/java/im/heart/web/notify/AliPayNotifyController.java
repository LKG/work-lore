package im.heart.web.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * 接收处理支付宝通知
 */
@Controller
public class AliPayNotifyController extends BasePayController {
    protected static final Logger logger = LoggerFactory.getLogger(AliPayNotifyController.class);
}
