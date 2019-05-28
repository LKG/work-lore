package im.heart.oauth2.impl;

import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import org.apache.commons.lang3.StringUtils;

public class WeChatOpenInRedisConfigStorage extends WxOpenInMemoryConfigStorage {
    private final static String COMPONENT_VERIFY_TICKET_KEY = "wechat_component_verify_ticket:";
    private final static String COMPONENT_ACCESS_TOKEN_KEY = "wechat_component_access_token:";

    private final static String AUTHORIZER_REFRESH_TOKEN_KEY = "wechat_authorizer_refresh_token:";
    private final static String AUTHORIZER_ACCESS_TOKEN_KEY = "wechat_authorizer_access_token:";
    private final static String JSAPI_TICKET_KEY = "wechat_jsapi_ticket:";
    private final static String CARD_API_TICKET_KEY = "wechat_card_api_ticket:";

//    protected final Pool<Jedis> jedisPool;
    /**
     * redis 存储的 key 的前缀，可为空
     */
    private String keyPrefix;
    private String componentVerifyTicketKey;
    private String componentAccessTokenKey;
    private String authorizerRefreshTokenKey;
    private String authorizerAccessTokenKey;
    private String jsapiTicketKey;
    private String cardApiTicket;

//    public WeChatOpenInRedisConfigStorage(Pool<Jedis> jedisPool) {
//        this.jedisPool = jedisPool;
//    }
//
//    public WeChatOpenInRedisConfigStorage(Pool<Jedis> jedisPool, String keyPrefix) {
//        this.jedisPool = jedisPool;
//        this.keyPrefix = keyPrefix;
//    }
//
//    public WxOpenInRedisConfigStorage(JedisPool jedisPool) {
//        this.jedisPool = jedisPool;
//    }

    @Override
    public void setComponentAppId(String componentAppId) {
        super.setComponentAppId(componentAppId);
        String prefix = StringUtils.isBlank(keyPrefix) ? "" :
                (StringUtils.endsWith(keyPrefix, ":") ? keyPrefix : (keyPrefix + ":"));
        componentVerifyTicketKey = prefix + COMPONENT_VERIFY_TICKET_KEY.concat(componentAppId);
        componentAccessTokenKey = prefix + COMPONENT_ACCESS_TOKEN_KEY.concat(componentAppId);
        authorizerRefreshTokenKey = prefix + AUTHORIZER_REFRESH_TOKEN_KEY.concat(componentAppId);
        authorizerAccessTokenKey = prefix + AUTHORIZER_ACCESS_TOKEN_KEY.concat(componentAppId);
        this.jsapiTicketKey = JSAPI_TICKET_KEY.concat(componentAppId);
        this.cardApiTicket = CARD_API_TICKET_KEY.concat(componentAppId);
    }

    @Override
    public String getComponentVerifyTicket() {
        return null;
    }

    @Override
    public void setComponentVerifyTicket(String componentVerifyTicket) {

    }

    @Override
    public String getComponentAccessToken() {
        return null;
    }

    @Override
    public boolean isComponentAccessTokenExpired() {
        return false;
    }

    @Override
    public void expireComponentAccessToken(){

    }

    @Override
    public void updateComponentAccessTokent(String componentAccessToken, int expiresInSeconds) {

    }

    private String getKey(String prefix, String appId) {
        return prefix.endsWith(":") ? prefix.concat(appId) : prefix.concat(":").concat(appId);
    }

    @Override
    public String getAuthorizerRefreshToken(String appId) {
        return null;
    }

    @Override
    public void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken) {

    }

    @Override
    public String getAuthorizerAccessToken(String appId) {
        return null;
    }

    @Override
    public boolean isAuthorizerAccessTokenExpired(String appId) {
        return false;
    }

    @Override
    public void expireAuthorizerAccessToken(String appId) {

    }

    @Override
    public void updateAuthorizerAccessToken(String appId, String authorizerAccessToken, int expiresInSeconds) {

    }

    @Override
    public String getJsapiTicket(String appId) {
        return null;
    }

    @Override
    public boolean isJsapiTicketExpired(String appId) {
        return false;
    }

    @Override
    public void expireJsapiTicket(String appId) {

    }

    @Override
    public void updateJsapiTicket(String appId, String jsapiTicket, int expiresInSeconds) {

    }

    @Override
    public String getCardApiTicket(String appId) {
        return null;
    }

    @Override
    public boolean isCardApiTicketExpired(String appId) {
        return false;
    }

    @Override
    public void expireCardApiTicket(String appId) {

    }

    @Override
    public void updateCardApiTicket(String appId, String cardApiTicket, int expiresInSeconds) {

    }
}
