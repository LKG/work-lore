package im.heart.conf;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import im.heart.core.plugins.sms.SmsSendService;
import im.heart.core.web.ResponseError;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

@Profile("prod")
@Component
@EnableConfigurationProperties(QcloudSmsProperties.class)
public class QcloudSmsSendServiceImpl implements SmsSendService {
    @Resource
    private QcloudSmsProperties properties;

    /**
     * @param model
     * @param templateId
     * @param mobileTo
     * @return
     * 根据手机短信模板返送短信
     */
    @Override
    public ResponseError sendSms(Map<String, Object> model, String templateId, String[] mobileTo) {
        SmsSingleSender sender = new SmsSingleSender(this.properties.getAppId(), this.properties.getAppKey());

        return null;
    }

    /**
     * @param model
     * @param templateId
     * @param mobileTo
     * @param nationCode
     * @param sign
     * @param extend
     * @param ext
     * @return
     * 根据手机短信模板返送短信
     */
    @Override
    public ResponseError sendSms(Map<String, Object> model, String templateId, String[] mobileTo, String nationCode, String sign, String extend, String ext) {
        return sendSms(model,templateId,mobileTo,"","","","");
    }
}
