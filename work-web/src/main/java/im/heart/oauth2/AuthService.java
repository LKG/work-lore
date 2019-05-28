package im.heart.oauth2;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

public interface  AuthService {

    default String getAccessToken(String code){
        return null;
    };
    default  String getOpenId(String accessToken){
        return null;
    };
    default  String refreshToken(String code){
        return null;
    };

    default String getAuthorizationUrl(String secretState) throws UnsupportedEncodingException{
        return "";
    }
    default  JSONObject getUserInfo(String accessToken, String openId){
        return null;
    };
}
