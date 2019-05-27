package im.heart.oauth2;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;

public class QQAuthApi extends DefaultApi20 {
    private final static String AUTHORIZATION_URL = "https://graph.qq.com/oauth2.0/authorize";
    private final static String ACCESSTOKEN_ENDPOINT = "https://graph.qq.com/oauth2.0/authorize";
    protected QQAuthApi() {
    }

    private static class InstanceHolder {
        private static final QQAuthApi INSTANCE = new QQAuthApi();
    }
    public static QQAuthApi instance() {
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
