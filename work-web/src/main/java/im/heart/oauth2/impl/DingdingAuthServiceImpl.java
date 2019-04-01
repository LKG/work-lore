package im.heart.oauth2.impl;

import com.alibaba.fastjson.JSONObject;
import im.heart.core.service.ServiceException;
import im.heart.oauth2.WeChatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@Service
public class DingdingAuthServiceImpl extends DefaultAuthServiceImpl implements WeChatAuthService {
    private Logger logger = LoggerFactory.getLogger(DingdingAuthServiceImpl.class);

    /**
     * 请求此地址即跳转到二维码登录界面
     */
    private static final String AUTHORIZATION_URL =
            "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

    /**
     * 获取用户 openid 和access——token 的 URL
     */
    private static final String ACCESSTOKE_OPENID_URL =
            "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    private static final String REFRESH_TOKEN_URL =
            "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";

    private static final String USER_INFO_URL =
            "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    private static final String APP_ID="xxxxxx";
    private static final String APP_SECRET="xxxxxx";
    private static final String SCOPE = "snsapi_login";
    private String callbackUrl = "https://www.xxx.cn/auth/wechat";
    @Override
    public String getAuthorizationUrl() throws UnsupportedEncodingException {
        callbackUrl = URLEncoder.encode(callbackUrl,"UTF-8");
        String url = String.format(AUTHORIZATION_URL,APP_ID,callbackUrl,SCOPE,System.currentTimeMillis());
        return url;
    }


    @Override
    public String getAccessToken(String code) {
        String url = String.format(ACCESSTOKE_OPENID_URL,APP_ID,APP_SECRET,code);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();

        String resp = getRestTemplate().getForObject(uri, String.class);
        logger.error("getAccessToken resp = "+resp);
        if(resp.contains("openid")){
            JSONObject jsonObject = JSONObject.parseObject(resp);
            String access_token = jsonObject.getString("access_token");
            String openId = jsonObject.getString("openid");;

            JSONObject res = new JSONObject();
            res.put("access_token",access_token);
            res.put("openId",openId);
            res.put("refresh_token",jsonObject.getString("refresh_token"));

            return res.toJSONString();
        }else{
            throw new ServiceException("获取token失败，msg = "+resp);
        }
    }

    //微信接口中，token和openId是一起返回，故此方法不需实现
    @Override
    public String getOpenId(String accessToken) {
        return null;
    }

    @Override
    public JSONObject getUserInfo(String accessToken, String openId){
        String url = String.format(USER_INFO_URL, accessToken, openId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();

        String resp = getRestTemplate().getForObject(uri, String.class);
        logger.error("getUserInfo resp = "+resp);
        if(resp.contains("errcode")){
            throw new ServiceException("获取用户信息错误，msg = "+resp);
        }else{
            JSONObject data =JSONObject.parseObject(resp);

            JSONObject result = new JSONObject();
            result.put("id",data.getString("unionid"));
            result.put("nickName",data.getString("nickname"));
            result.put("avatar",data.getString("headimgurl"));

            return result;
        }
    }

    /**
     * 微信的token只有2小时的有效期，过时需要重新获取，所以官方提供了
     * /根据refresh_token 刷新获取token的方法，本项目仅仅是获取用户信息，并将信息存入库
     * @param refresh_token
     * @return
     */
    @Override
    public String refreshToken(String refresh_token) {

        String url = String.format(REFRESH_TOKEN_URL,APP_ID,refresh_token);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();

        ResponseEntity<JSONObject> resp = getRestTemplate().getForEntity(uri,JSONObject.class);
        JSONObject jsonObject = resp.getBody();

        String access_token = jsonObject.getString("access_token");
        return access_token;
    }
}
