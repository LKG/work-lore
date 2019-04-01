package im.heart.oauth2.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * 公众平台id
     */
    @Value("${wechat.mp.appId}")
    private String mpAppId;
    /**
     * 公众平台密钥
     */
    @Value("${wechat.mp.appSecret}")
    private String mpAppSecret;

    /**
     * 开放平台id
     */
    @Value("${wechat.open.appSecret}")
    private String openAppId;
    /**
     * 开放平台密钥
     */
    @Value("${wechat.open.appSecret}")
    private String openAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;

    /**
     * 微信模板id
     */
    private Map<String,String> templateId;

}
