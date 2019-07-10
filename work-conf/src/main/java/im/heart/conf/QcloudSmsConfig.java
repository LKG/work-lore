package im.heart.conf;


import com.github.qcloudsms.SmsSingleSender;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author gg
 * @desc : 腾讯云短信配置
 */
@Configuration
@ConfigurationProperties(prefix = "sms.qcloud")
public class QcloudSmsConfig  {
    private int appId;
    private String appKey;
}
