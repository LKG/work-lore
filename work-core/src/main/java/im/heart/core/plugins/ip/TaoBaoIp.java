package im.heart.core.plugins.ip;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import im.heart.core.utils.OkHttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 
 * @author gg
 * @desc : iP 解析接口 实现类，通过调用淘宝iP 解析服务实现
 */
@Component
public class TaoBaoIp implements IpParse {
	private static String API_URL = "https://ip.taobao.com/service/getIpInfo.php";
	protected static final Logger logger = LoggerFactory.getLogger(TaoBaoIp.class);
	private static String CODE = "code";
	private static String DATA = "data";
	private static String SUCCESS_CODE = "0";
	public static IpInfo getTaoBaoIp(String ip) throws Exception {
		if (StringUtils.isNotBlank(ip)) {
			Map<String, Object> params = Maps.newHashMap();
			params.put("ip", ip);
			String result = OkHttpClientUtils.fetchEntityString(API_URL,params);
			JSONObject json = JSON.parseObject(result);
			if (SUCCESS_CODE.equals(json.getString(CODE))) {
				return JSON.parseObject(json.getString(DATA), IpInfo.class);
			}
		}
		return null;
	}
	@Override
	public String getIpInfo(String ip) throws IpParseException {
		try {
			return JSON.toJSONString(getIp(ip));
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
		return null;
	}
	@Override
	public IpInfo getIp(String ip) throws IpParseException {
		try {
			return getTaoBaoIp(ip);
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
		return null;
	}
}