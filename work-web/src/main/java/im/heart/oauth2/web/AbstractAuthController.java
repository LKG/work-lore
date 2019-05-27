package im.heart.oauth2.web;

import im.heart.core.web.AbstractController;
import im.heart.usercore.service.FrameUserConnectService;
import im.heart.usercore.service.FrameUserService;
import im.heart.usercore.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractAuthController   extends AbstractController {

    @Autowired
    protected FrameUserService userService;
    @Autowired
    protected FrameUserConnectService userConnectService;
    @Autowired
    protected PasswordService passwordService;
}
