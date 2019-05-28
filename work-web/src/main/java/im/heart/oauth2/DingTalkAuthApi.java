package im.heart.oauth2;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;

public class DingTalkAuthApi extends DefaultApi20 {

    private final static String AUTHORIZATION_URL = "https://oapi.dingtalk.com/gettoken";
    private final static String ACCESSTOKEN_ENDPOINT = "https://oapi.dingtalk.com/connect/qrconnect";
    protected DingTalkAuthApi() {
    }

    private static class InstanceHolder {
        private static final DingTalkAuthApi INSTANCE = new DingTalkAuthApi();
    }
    public static DingTalkAuthApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESSTOKEN_ENDPOINT;
    }

    @Override
    public String getAuthorizationBaseUrl() {
        return AUTHORIZATION_URL;
    }
    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OAuth2AccessTokenExtractor.instance();
    }
}
