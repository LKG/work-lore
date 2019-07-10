package im.heart.core.plugins.sms;

import com.alibaba.fastjson.JSON;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import im.heart.core.web.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;


/**
 * 
 * 短信发送实现类 demo
 * @作者 LKG
 */
@Profile("!prod")
@Component(value = SmsSendService.BEAN_NAME)
public class SampleSmsSendServiceImpl implements SmsSendService {
	protected static final Logger logger = LoggerFactory.getLogger(SampleSmsSendServiceImpl.class);



	/**
	 * 根据发送短信，短信内容必须和模板中的一致
	 *
	 * @param smsContent
	 * @param mobileTo
	 * @return
	 */
	@Async
	@Override
	public ResponseError sendSms(String smsContent, String mobileTo) {
		return sendSms(smsContent,mobileTo,"","","");
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
	@Async
	@Override
	public ResponseError sendSms(String smsContent, String mobileTo, String nationCode, String extend, String ext) {
		logger.info("模拟发送短信啦.smsContent:{},mobileTo:{}..............................",smsContent,JSON.toJSONString(mobileTo));
		return null;
	}
	@Async
	@Override
	public ResponseError sendSms(Map<String, Object> model, String templateId,
								 String[] mobileTo,String nationCode,String sign,String extend, String ext) {
		logger.info("模拟发送短信啦.templateId:{}.model:{},mobileTo:{}..............................",templateId,JSON.toJSONString(model),JSON.toJSONString(mobileTo));
		return null;
	}

	/**
	 * 根据手机短信模板返送短信
	 *
	 * @param model
	 * @param templateId
	 * @param mobileTo
	 * @return
	 */
	@Async
	@Override
	public ResponseError sendSms(Map<String, Object> model, String templateId, String[] mobileTo) {
		return sendSms( model,templateId,mobileTo, "","","", "");
	}
}
