package im.heart.core.plugins.sms;

import im.heart.core.web.ResponseError;

import java.util.Map;

/**
 * 
 * @author gg
 * @desc 短信发送接口
 */
public interface SmsSendService {
	public static final String BEAN_NAME = "smsSendService";

	/**
	 * 根据发送短信，短信内容必须和模板中的一致
	 * @param smsContent
	 * @param mobileTo
	 * @return
	 */
	public ResponseError sendSms(String smsContent, String mobileTo);
	/**
	 * 根据发送短信，短信内容必须和模板中的一致
	 * @param smsContent 短信内容必须和模板中的一致
	 * @param mobileTo 不带国家码的手机号
	 * @param nationCode 国家码，如 86 为中国
	 * @param extend 扩展码，可填空
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 */
	public ResponseError sendSms(String smsContent, String mobileTo,String nationCode,String extend, String ext);


	/**
	 *
	 * 根据手机短信模板返送短信
	 * @param model
	 * @param templateId
	 * @param mobileTo
	 * @return
	 */
	public ResponseError sendSms(Map<String, Object> model, String templateId, String[] mobileTo);
	/**
	 *  根据手机短信模板发送短信
	 * @param model 数据
	 * @param templateId 模板id
	 * @param mobileTo 不带国家码的手机号
	 * @param nationCode 国家码，如 86 为中国
	 * @param sign 签名
	 * @param extend 扩展码，可填空
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 */
	public ResponseError sendSms(Map<String, Object> model, String templateId,String[] mobileTo,String nationCode,String sign,String extend, String ext);
}
