package im.heart.oauth2.web;

import im.heart.oauth2.WeiboAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class WeiboController {
    private Logger logger = LoggerFactory.getLogger(WeiboController.class);

    @Autowired
    private WeiboAuthService weiboAuthService;
}
