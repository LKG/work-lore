package im.heart.oauth2.web;

import im.heart.core.web.AbstractController;
import im.heart.usercore.service.FrameUserConnectService;
import im.heart.usercore.service.FrameUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DingTalkController   extends AbstractAuthController {
    private Logger logger = LoggerFactory.getLogger(DingTalkController.class);

    /**
     *获取用户基础信息的接口URL
     */
    public static final String URL_GET_USERINFO_BY_CODE = "https://oapi.dingtalk.com/sns/getuserinfo_bycode";


}
