package im.heart.conf;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author gg
 * @desc : 腾讯云短信配置
 */
@Configuration
@ConfigurationProperties(prefix = "sms.qcloud")
@Data
public class QcloudSmsConfig {
    private String appId;
    private String appKey;
}
