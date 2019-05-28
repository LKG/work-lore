package im.heart.oauth2.impl;

import im.heart.oauth2.conf.WeChatOpenProperties;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class WeChatOpenServiceImpl extends WxOpenServiceImpl {
    private Logger logger = LoggerFactory.getLogger(WeChatOpenServiceImpl.class);
    @Autowired
    private WeChatOpenProperties weChatOpenProperties;

    private WxOpenMessageRouter wxOpenMessageRouter;
    @PostConstruct
    public void init() {
        WeChatOpenInRedisConfigStorage inRedisConfigStorage = new WeChatOpenInRedisConfigStorage();
        inRedisConfigStorage.setComponentAppId(weChatOpenProperties.getComponentAppId());
        inRedisConfigStorage.setComponentAppSecret(weChatOpenProperties.getComponentSecret());
        inRedisConfigStorage.setComponentToken(weChatOpenProperties.getComponentToken());
        inRedisConfigStorage.setComponentAesKey(weChatOpenProperties.getComponentAesKey());
        setWxOpenConfigStorage(inRedisConfigStorage);
        wxOpenMessageRouter = new WxOpenMessageRouter(this);
        wxOpenMessageRouter.rule().handler((wxMpXmlMessage, map, wxMpService, wxSessionManager) -> {
            logger.info("接收到 {} 公众号请求消息，内容：{}", wxMpService.getWxMpConfigStorage().getAppId(), wxMpXmlMessage);
            return null;
        }).next();
    }
}
