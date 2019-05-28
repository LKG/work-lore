package im.heart.oauth2;

import com.alibaba.fastjson.JSONObject;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

@Service(QQAuthServiceImpl.BEAN_NAME)
public class QQAuthServiceImpl  implements AuthService {
    public static final String BEAN_NAME = "qqAuthService";

        private  static final String CALLBACK_URL = "https://www.gongwk.com/";
    /**
     *   // QQ 互联应用管理中心的 APP ID
     */
    private  static final String API_KEY  = "1108201287";
    /**
     *    // QQ 互联应用管理中心的 APP Key
     */
    private  static final String API_SECRET = "0mGm81G7HlUjTjDt";
    OAuth20Service service;
    @PostConstruct
    void init(){
       service = new ServiceBuilder(API_KEY)
                .apiSecret(API_SECRET)
                .callback(CALLBACK_URL)
//                .httpClientConfig(OkHttpHttpClientConfig.defaultConfig())
                .build(QQAuthApi.instance());
    }

    @Override
    public String getAccessToken(String code) {
        return null;
    }

    @Override
    public String getOpenId(String accessToken) {
        return null;
    }

    @Override
    public String refreshToken(String code) {
        return null;
    }

    @Override
    public String getAuthorizationUrl(String secretState) throws UnsupportedEncodingException {
        return service.getAuthorizationUrl(secretState);
    }

    @Override
    public JSONObject getUserInfo(String accessToken, String openId) {
        return null;
    }
}
