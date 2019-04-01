package im.heart.oauth2.web;

import im.heart.oauth2.OSChinaAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

public class OSChinaController{
    private Logger logger = LoggerFactory.getLogger(OSChinaController.class);

    @Autowired
    private OSChinaAuthService osChinaAuthService;
}
