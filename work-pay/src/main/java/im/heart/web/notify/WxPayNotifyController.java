package im.heart.web.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * 接收处理微信通知
 */
@Controller
public class WxPayNotifyController extends BasePayController {
	protected static final Logger logger = LoggerFactory.getLogger(WxPayNotifyController.class);
}
