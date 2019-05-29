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
	public final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	
	public final static Pattern number = Pattern.compile("^[0-9]*$");

	/**
	 * 从字符串中匹配出省份，地市信息  hanlp 语言包里有相应的过滤地市方法，可考虑使用其方法
	 */
	public final static Pattern addrser = Pattern.compile("(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市|上海|北京|天津|重庆)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+镇|.+局|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)");
	/**
	 * url
	 */
	public final static Pattern urler = Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
	/**
	 * 电话号码
	 */
	public static final Pattern phoner = Pattern.compile("^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$");
	/**
	 * 中文
	 */
	public static final Pattern chineser = Pattern.compile("[\u4e00-\u9fa5]");
	/**
	 * ip
	 */
	public static final Pattern iper = Pattern.compile("(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))");

	/**
	 * 身份证号码
	 */
	public static final Pattern idCarder = Pattern.compile("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$");
	
	/**
	 * 用户名
	 */
	public static final Pattern usernameer = Pattern.compile("^[\\u4E00-\\u9FA5\\uf900-\\ufa2d_a-zA-Z][\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{1,19}$");


	/**
	 * 日期格式  包含闰年判断
	 */
	public static final Pattern datetimeer = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
	

	/**
	 * script
	 */
	public final Pattern scripter =    Pattern.compile("(.*<script.* src=[\'\"])(.*?)(\\??)(\\d*)([\'\"].*>.*)", Pattern.CASE_INSENSITIVE);
	
	/**
	 * link
	 */
	public final Pattern linker =    Pattern.compile("(.*<link.* href=[\'\"])(.*?)(\\??)(\\d*)([\'\"].*>.*)", Pattern.CASE_INSENSITIVE);

}