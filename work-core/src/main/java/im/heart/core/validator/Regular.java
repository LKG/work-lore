package im.heart.core.validator;

import java.util.regex.Pattern;

/**
 * 相关正则
 * @author gg
 */
public interface Regular {
	/**
	 * 邮箱
	 */
	public final static Pattern EMAIL_ER = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

	/**
	 * 数字
	 */
	public final static Pattern NUMBER_ER = Pattern.compile("^[0-9]*$");

	/**
	 * 从字符串中匹配出省份，地市信息  hanlp 语言包里有相应的过滤地市方法，可考虑使用其方法
	 */
	public final static Pattern ADDRS_ER = Pattern.compile("(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市|上海|北京|天津|重庆)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+镇|.+局|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)");
	/**
	 * url
	 */
	public final static Pattern URL_ER = Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
	/**
	 * 验证手机号
	 * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
	 * 联通号码段:130、131、132、136、185、186、145
	 * 电信号码段:133、153、180、189
	 * 其他号段 17 19
	 */
	public static final Pattern PHONE_ER = Pattern.compile("^0{0,1}(13[0-9]|15[0-9]|14[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$");
	/**
	 * 中文
	 */
	public static final Pattern CHINES_ER = Pattern.compile("[\u4e00-\u9fa5]");
	/**
	 * ip
	 */
	public static final Pattern IP_ER = Pattern.compile("(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))");

	/**
	 * 身份证号码
	 */
	public static final Pattern ID_CARD_ER = Pattern.compile("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$");

	/**
	 * 中国车牌号码
	*/
	public final static Pattern PLATE_NUMB_ER = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");

	/**
	 * 用户名
	 */
	public static final Pattern USERNAME_ER = Pattern.compile("^[\\u4E00-\\u9FA5\\uf900-\\ufa2d_a-zA-Z][\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{1,19}$");


	/**
	 * 日期格式  包含闰年判断
	 */
	public static final Pattern DATETIME_ER = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
	

	/**
	 * script
	 */
	public final Pattern SCRIPT_ER =    Pattern.compile("(.*<script.* src=[\'\"])(.*?)(\\??)(\\d*)([\'\"].*>.*)", Pattern.CASE_INSENSITIVE);
	
	/**
	 * link
	 */
	public final Pattern LINK_ER =    Pattern.compile("(.*<link.* href=[\'\"])(.*?)(\\??)(\\d*)([\'\"].*>.*)", Pattern.CASE_INSENSITIVE);

}