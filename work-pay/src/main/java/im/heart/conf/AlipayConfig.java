package im.heart.conf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlipayConfig {

    /**
     * 请求网关地址
     */
    @Value("${alipay.URL}")
    public String URL;
    /**
     * 商户appid
     */
    @Value("${alipay.appId}")
    public String appId;

    /**
     * 支付宝公钥
     */
    @Value("${alipay.publicKey}")
    public String publicKey;
    /**
     * 私钥格式的
     */
    @Value("${alipay.privateKey}")
    public String privateKey;

    /**
     * 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    @Value("${alipay.notify_url}")
    public String notifyUrl;

    /**
     * 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
     */
    @Value("${alipay.return_url}")
    public String returnUrl;

    /**
     * 编码
     */
    @Value("${alipay.charset}")
    public String charset;
    /**
     * 返回格式
     */
    @Value("${alipay.format}")
    public String format;


    /**
     *  日志记录目录
     */
    @Value("${alipay.logPath}")
    public String logPath;

    /**
     * 签名方式RSA2
     */
    @Value("${alipay.signType}")
    public String signType;
}
