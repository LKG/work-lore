package im.heart.oauth2;

import com.alibaba.fastjson.JSONObject;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

@Service(DingTalkAuthServiceImpl.BEAN_NAME)
public class DingTalkAuthServiceImpl implements AuthService {
    public static final String BEAN_NAME = "dingTalkAuthService";
    /**
     *获取用户基础信息的接口URL
     */
    public static final String URL_GET_USERINFO_BY_CODE = "https://oapi.dingtalk.com/sns/getuserinfo_bycode";

    private  static final String CALLBACK_URL = "https://www.gongwk.com/";
    /**
     *   // QQ 互联应用管理中心的 APP ID
     */
    private  static final String API_KEY  = "dingoaqzoeij0scglcaclb";
    /**
     *    // QQ 互联应用管理中心的 APP Key
     */
    private  static final String API_SECRET = "vgufnnRTDY-jnwQTl348uy4MJHdyqu0ZbBkmKkqyge9sd8OYeVyu2mz0xQM1wo04";
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
