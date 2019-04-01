package im.heart.core.plugins.ip;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import im.heart.core.utils.OkHttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author gg
 * @desc : iP 解析接口 实现类，通过调用淘宝iP 解析服务实现
 */
@Component
public class TaoBaoIP implements IPParse {
	private static String TAOBAO_IP_URL = "https://ip.taobao.com/service/getIpInfo.php";
	protected static final Logger logger = LoggerFactory.getLogger(TaoBaoIP.class);
	private static String CODE = "code";
	private static String DATA = "data";

	public static IPInfo getTaoBaoIp(String ip) throws Exception {
		if (StringUtils.isNotBlank(ip)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("ip", ip);
			String result = OkHttpClientUtils.fetchEntityString(TAOBAO_IP_URL,params);
			JSONObject json = JSON.parseObject(result);
			if ("0".equals(json.getString(CODE))) {
				return JSON.parseObject(json.getString(DATA), IPInfo.class);
			}
		}
		return null;
	}
	@Override
	public String getIpInfo(String ip) throws IPParseException {
		try {
			return JSON.toJSONString(getIp(ip));
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
		return null;
	}
	@Override
	public IPInfo getIp(String ip) throws IPParseException {
		try {
			return getTaoBaoIp(ip);
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
		return null;
	}
}