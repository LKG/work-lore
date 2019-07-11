package im.heart.conf;

import com.alibaba.fastjson.JSON;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import im.heart.core.plugins.sms.SmsSendService;
import im.heart.core.web.ResponseError;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
/**
 *
 * @author: gg
 *  腾讯云发送短信接口
 */
@Profile("test")
@Component
@EnableConfigurationProperties(QcloudSmsProperties.class)
public class QcloudSmsSendServiceImpl implements SmsSendService {
    protected static final Logger logger = LoggerFactory.getLogger(QcloudSmsSendServiceImpl.class);
    @Resource
    private QcloudSmsProperties properties;
    public static void main(String[] args) throws Exception{
        int appId = 1400197686;
        String[] phoneNumbers={"18668169331"};
        String appKey = "f9f4cc6fd9ff57f332760650c5d1c755"; //sdkappid 对应的 appkey，需要业务方高度保密
        String[] params = {"6666","公文库","15"};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
        int templateId = 370072;
        SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
        SmsSingleSenderResult result = ssender.sendWithParam(null, phoneNumbers[0],
                templateId, params, null,null,null);
    }
    /**
     * 根据发送短信，短信内容必须和模板中的一致
     *
     * @param smsContent 短信内容必须和模板中的一致
     * @param mobileTo   不带国家码的手机号
     * @param nationCode 国家码，如 86 为中国
     * @param extend     扩展码，可填空
     * @param ext        服务端原样返回的参数，可填空
     * @return
     */
    @Override
    public ResponseError sendSms(String smsContent, String mobileTo, String nationCode, String extend, String ext) {
        logger.info("模拟发送短信啦.smsContent:{},mobileTo:{}..............................",smsContent, JSON.toJSONString(mobileTo));
        SmsSingleSender sender = new SmsSingleSender(this.properties.getAppId(), this.properties.getAppKey());
        try{
            SmsSingleSenderResult result = sender.send(0, "", mobileTo,
                    smsContent, "", "");
        }catch (HTTPException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        } catch (JSONException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        } catch (IOException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        }

        return null;
    }

    /**
     * 根据发送短信，短信内容必须和模板中的一致
     *
     * @param smsContent
     * @param mobileTo
     * @return
     */
    @Override
    public ResponseError sendSms(String smsContent, String mobileTo) {
        return sendSms(smsContent,mobileTo,"","","");
    }

    /**
     * 根据手机短信模板返送短信
     * @param model
     * @param templateId
     * @param mobileTo
     * @return
     */
    @Override
    public ResponseError sendSms(Map<String, Object> model, String templateId, String[] mobileTo) {
        SmsMultiSender sender = new SmsMultiSender(this.properties.getAppId(), this.properties.getAppKey());
        try{
            SmsMultiSenderResult result =sender.sendWithParam("",mobileTo,Integer.valueOf(templateId),mobileTo,"","","");
        }catch (HTTPException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        } catch (JSONException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        } catch (IOException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        }
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
