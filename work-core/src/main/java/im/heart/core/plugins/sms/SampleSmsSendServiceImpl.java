package im.heart.core.plugins.sms;

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
 * @Desc：短信发送实现类 demo
 * @作者 LKG
 */
@Component(value = SmsSendService.BEAN_NAME)
public class SampleSmsSendServiceImpl implements SmsSendService {
	protected static final Logger logger = LoggerFactory.getLogger(SampleSmsSendServiceImpl.class);

	
	@Profile("prod")
	@Override
	@Async
	public ResponseError sendSms(String mobilePhone, String content) {
		logger.info("模拟发送短信啦................................");
		return null;
	}

	@Override
	@Async
	public ResponseError sendSms(Map<String, Object> model, String templatePath,
                                 String[] mobileTo) {
		logger.info("模拟发送短信啦................................");
		return null;
	}


}
