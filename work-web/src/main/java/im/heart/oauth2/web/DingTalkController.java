package im.heart.oauth2.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DingTalkController {
    private Logger logger = LoggerFactory.getLogger(DingTalkController.class);

    /**
     *获取用户基础信息的接口URL
     */
    public static final String URL_GET_USERINFO_BY_CODE = "https://oapi.dingtalk.com/sns/getuserinfo_bycode";
}
