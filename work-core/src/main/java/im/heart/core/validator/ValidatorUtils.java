package im.heart.core.validator;

import im.heart.core.utils.StringUtilsEx;
import org.apache.commons.lang3.RandomUtils;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author gg
 * @desc 验证工具类
 */

@Slf4j
public class ValidatorUtils implements Regular {
	/**
	 * 
	 *  判断是不是一个合法的电子邮件地址
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (StringUtilsEx.isBlank(email)){
			return false;
		}
		return EMAIL_ER.matcher(email).matches();
	}
	public static boolean isNumber(String str) {
		if (StringUtilsEx.isBlank(str)){
			return false;
		}
		return NUMBER_ER.matcher(str).matches();
	}
	/**
	 * 
	 * 判断是否包含中文
	 * @param str
	 * @return
	 */
	public static boolean isContainsChinese(String str) {
		if (StringUtilsEx.isBlank(str)){
			return false;
		}
		return CHINES_ER.matcher(str).find();
	}
	/**
	 * 
	 * 判断是否为日期格式
	 * @param str
	 * @return
	 */
	public static boolean isDateTime(String str) {
		if (StringUtilsEx.isBlank(str)){
			return false;
		}
		return DATETIME_ER.matcher(str).matches();
	}
	
	/**
	 * 
	 * 判断是否为电话号码
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if (StringUtilsEx.isBlank(phone)){
			return false;
		}
		return PHONE_ER.matcher(phone).matches();
	}
	/**
	 * 判断是否为IP地址
	 * @param ip
	 * @return
	 */
	 public static Boolean isIpAddress(String ip){
		 if (StringUtilsEx.isBlank(ip)){
				return false;
		 }
          return IP_ER.matcher(ip).matches();
	 }
	 /**
	  * 
	  * 判断是否为url
	  * @param url
	  * @return
	  */
	 public static Boolean isUrl(String url){
		 if (StringUtilsEx.isBlank(url)){
				return false;
		 }
          return URL_ER.matcher(url).matches();
	 }
	 
	 /**
	  * 
	  * 判断是否为身份证号码
	  * @param idCard
	  * @return
	  */
	 public static Boolean isIdCard(String idCard){
		 if (StringUtilsEx.isBlank(idCard)){
				return false;
		 }
          return ID_CARD_ER.matcher(idCard).matches();
	 }
}
