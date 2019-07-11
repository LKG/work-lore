package im.heart.conf;

import com.alibaba.fastjson.JSON;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.google.common.collect.Maps;
import im.heart.core.plugins.sms.SmsSendException;
import im.heart.core.plugins.sms.SmsSendService;
import im.heart.core.utils.BaseUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author: gg
 *  腾讯云发送短信接口
 */
@Profile("prod")
@Component
@EnableConfigurationProperties(QcloudSmsProperties.class)
public class QcloudSmsSendServiceImpl implements SmsSendService {
    protected static final Logger logger = LoggerFactory.getLogger(QcloudSmsSendServiceImpl.class);
    @Resource
    private QcloudSmsProperties properties;


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
    public Boolean sendSms(String smsContent, String mobileTo, String nationCode, String extend, String ext) {
        logger.debug("发送短信.smsContent:{},mobileTo:{}..............................",smsContent, JSON.toJSONString(mobileTo));
        SmsSingleSender sender = new SmsSingleSender(this.properties.getAppId(), this.properties.getAppKey());
        try{
            SmsSingleSenderResult result = sender.send(0, null, mobileTo,
                    smsContent, "", "");
            if(result.result==0){
                return Boolean.TRUE;
            }
            logger.error("发送短信.result:{}..............................",JSON.toJSONString(result));
            throw new SmsSendException(result.result+"",result.errMsg);
        }catch (HTTPException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        } catch (JSONException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        } catch (IOException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        }

       return Boolean.FALSE;
    }

    /**
     * 根据发送短信，短信内容必须和模板中的一致
     *
     * @param smsContent
     * @param mobileTo
     * @return
     */
    @Override
    public Boolean sendSms(String smsContent, String mobileTo) {
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
    public Boolean sendSms(Map<String, ?> model, String templateId, String[] mobileTo) {
        SmsMultiSender sender = new SmsMultiSender(this.properties.getAppId(), this.properties.getAppKey());
        logger.debug("发送短信.smsContent:{},mobileTo:{}..............................",JSON.toJSONString(model), JSON.toJSONString(mobileTo));
        SmsMultiSenderResult result =null;
        try{
            String[] params=model.values().stream().map(Object::toString).toArray(String[]::new);
            result =sender.sendWithParam(null,mobileTo,Integer.valueOf(templateId),params,"","","");
            if(result.result==0){
                return Boolean.TRUE;
            }
            logger.error("发送短信.result:{}..............................",JSON.toJSONString(result));
            throw new SmsSendException(result.result+"",result.errMsg);
        }catch (HTTPException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        } catch (JSONException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        } catch (IOException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        }
        return Boolean.FALSE;
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
    public Boolean sendSms(Map<String, ?> model, String templateId, String[] mobileTo, String nationCode, String sign, String extend, String ext) {
        return sendSms(model,templateId,mobileTo,null,null,null,null);
    }
}
