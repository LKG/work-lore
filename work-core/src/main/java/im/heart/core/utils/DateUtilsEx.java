package im.heart.core.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author gg
 * @desc扩展日期工具类
 */
public class DateUtilsEx extends DateUtils {
	protected static final Logger logger = LoggerFactory.getLogger(DateUtilsEx.class);
	public final static String DATE_PATTERN = "yyyy-MM-dd";

	public final static String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 * 判断是否为yyyy-MM-dd 日期格式 兼容 yyyy-MM-dd HH:mm:ss
	 * @param str
	 * @return
	 */
	public static boolean isValidDate(String str) {
		return isValidDate(str,DATE_PATTERN);
	}
	/**
	 * 
	 * 判断是否为指定日期格式
	 * @param str
	 * @param dateFormat
	 * @return
	 */
	public static boolean isValidDate(String str,String dateFormat) {
	    try {
		    SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		    format.parse(str);
	    	return true;
		} catch (ParseException  e) {
			logger.debug(e.getStackTrace()[0].getMethodName(), e);
		}
		return false;
	}
	/**
	 * 
	 * 判断是否为yyyy-MM-dd HH:mm:ss 日期格式
	 * @param str
	 * @return
	 */
	public static boolean isValidDatetime(String str) {
	    return isValidDate(str,TIME_PATTERN);
	}
	
	/**
	 * 
	 * 转换日期为XMLGregorianCalendar 格式
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar convertXMLGregorianCalendar(Date date) {
		if (date == null) {
			return null;
		}
		try {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);
			DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
			XMLGregorianCalendar gregorianCalendar = datatypeFactory.newXMLGregorianCalendar(cal);
			return gregorianCalendar;
		} catch (DatatypeConfigurationException e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
		return null;
	}
	/**
	 * 
	 * 转换日期为字符串格式
	 * @param aDate
	 * @param format
	 * @return
	 */
	public static final String dateToString(Date aDate, String format) {
		String returnValue = "";
		if (aDate != null) {
			SimpleDateFormat df = new SimpleDateFormat(format);
			returnValue = df.format(aDate);
		}
		return returnValue;
	}
	/**
	 * 
	 * 转换日期为字符串格式  格式为yyyy-MM-dd
	 * @param aDate
	 * @return
	 */
	public static final String dateToString(Date aDate) {
		return dateToString(aDate, DATE_PATTERN);
	}
	/**
	 * 
	 * 转换日期为字符串格式  格式为yyyy-MM-dd HH:mm:ss
	 * @param aDate
	 * @return
	 */
	public static final String timeToString(Date aDate) {
		return dateToString(aDate, TIME_PATTERN);
	}
	/**
	 * 
	 * 字符串转换为日期类型
	 * @param strDate
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static final Date stringToDate(String strDate, String format)
			throws ParseException {
		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			date = df.parse(strDate);
		} catch (ParseException e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
			throw new ParseException(e.getMessage(), e.getErrorOffset());
		}
		return date;
	}

	/**
	 * 
	 * 字符串转换为日期类型 默认格式为 yyyy-MM-dd
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String strDate) throws ParseException {
		return stringToDate(strDate, DATE_PATTERN);
	}
	public static Date stringToDateTime(String strDate) throws ParseException {
		return stringToDate(strDate, TIME_PATTERN);
	}
}
