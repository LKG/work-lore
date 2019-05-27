package im.heart.oauth2.web;

import im.heart.core.web.AbstractController;
import im.heart.oauth2.OSChinaAuthService;
import im.heart.usercore.service.FrameUserConnectService;
import im.heart.usercore.service.FrameUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class OSChinaController   extends AbstractAuthController {
    private Logger logger = LoggerFactory.getLogger(OSChinaController.class);

    @Autowired
    private OSChinaAuthService osChinaAuthService;
}
