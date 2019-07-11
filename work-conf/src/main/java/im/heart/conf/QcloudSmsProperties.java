package im.heart.conf;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author gg
 * @desc : 腾讯云短信配置
 */
@ConfigurationProperties(prefix = "sms.qcloud")
@Data
public class QcloudSmsProperties  {
    private int appId;
    private String appKey;

}
