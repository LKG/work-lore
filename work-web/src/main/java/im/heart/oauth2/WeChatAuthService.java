package im.heart.oauth2;

import com.alibaba.fastjson.JSONObject;

public interface  WeChatAuthService {
    public JSONObject getUserInfo(String accessToken, String openId);
}
