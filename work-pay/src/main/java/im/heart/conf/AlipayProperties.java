package im.heart.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(AlipayProperties.PROPERTIES_PREFIX)
@ConditionalOnProperty(prefix=AlipayProperties.PROPERTIES_PREFIX,value = "enabled", havingValue = "true", matchIfMissing = true)
public class AlipayProperties {
    public static final String PROPERTIES_PREFIX = "pay.alipay";

    /**
     * 请求网关地址
     */
    @Value("${pay.alipay.URL}")
    public String URL;
    /**
     * 商户appid
     */
    @Value("${pay.alipay.appId}")
    public String appId;

    /**
     * 支付宝公钥
     */
    @Value("${pay.alipay.publicKey}")
    public String publicKey;
    /**
     * 私钥格式的
     */
    @Value("${pay.alipay.privateKey}")
    public String privateKey;

    /**
     * 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    @Value("${pay.alipay.notifyUrl}")
    public String notifyUrl;

    /**
     * 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
     */
    @Value("${pay.alipay.returnUrl}")
    public String returnUrl;

    /**
     * 编码
     */
    @Value("${pay.alipay.charset}")
    public String charset;
    /**
     * 返回格式
     */
    @Value("${pay.alipay.format}")
    public String format;


    /**
     *  日志记录目录
     */
    @Value("${pay.alipay.logPath}")
    public String logPath;

    /**
     * 签名方式RSA2
     */
    @Value("${pay.alipay.signType}")
    public String signType;
    /** 最大查询次数 */
    private static int maxQueryRetry = 5;
    /** 查询间隔（毫秒） */
    private static long queryDuration = 5000;
    /** 最大撤销次数 */
    private static int maxCancelRetry = 3;
    /** 撤销间隔（毫秒） */
    private static long cancelDuration = 3000;
}
