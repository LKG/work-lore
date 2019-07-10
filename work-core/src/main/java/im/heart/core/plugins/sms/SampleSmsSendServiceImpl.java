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
	@Override
	@Async
	public ResponseError sendSms(Map<String, Object> model, String templateId,
								 String[] mobileTo,String nationCode,String sign,String extend, String ext) {
		logger.info("模拟发送短信啦.templateId:{}.model:{},mobileTo:{}..............................",templateId,JSON.toJSONString(model),JSON.toJSONString(mobileTo));
		return null;
	}
	@Override
	@Async
	public ResponseError sendSms(Map<String, Object> model, String templateId,
                                 String[] mobileTo) {
		return sendSms(model,templateId,mobileTo,"","","","");
	}


}
