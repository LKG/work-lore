package im.heart.core.web.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author gg
 * @desc 对springWebUtils 进行扩展
 */
public class WebUtilsEx extends WebUtils {
    protected static final Logger logger = LoggerFactory.getLogger(WebUtilsEx.class);
    private static final String METHOD_GET = "GET";
    /**
     * @param request
     * @return
     * 获取请求头信息返回Json格式数据
     */
    public static String getHeadersJson(HttpServletRequest request) {
        Map<String, List<String>> headers = getHeaderMaps(request);
        return JSON.toJSONString(headers);
    }

    /**
     * @param request
     * @return
     * 获取请求头信息
     */
    public static Map<String, List<String>> getHeaderMaps(
            HttpServletRequest request) {
        Validate.notNull(request, "Request must not be null");
        Map<String, List<String>> headers = Maps.newHashMap();
        Enumeration<?> namesEnumeration = request.getHeaderNames();
        while (namesEnumeration.hasMoreElements()) {
            String name = (String) namesEnumeration.nextElement();
            Enumeration<?> valueEnumeration = request.getHeaders(name);
            List<String> values = Lists.newArrayList();
            while (valueEnumeration.hasMoreElements()) {
                values.add((String) valueEnumeration.nextElement());
            }
            headers.put(name, values);
        }
        return headers;
    }

    /**
     * @param request
     * @param prefix
     * @return
     * 获取请求参数信息 扩展getParametersStartingWit 增加参数编码转换
     */
    public static Map<String, Object> getParametersStartingWithEx(
            HttpServletRequest request, String prefix) {

        Validate.notNull(request, "Request must not be null");
        Enumeration<?> paramNames = request.getParameterNames();
        boolean isGet = false;
        if (METHOD_GET.equalsIgnoreCase(request.getMethod())) {
            isGet = true;
        }
        Map<String, Object> params = new TreeMap<String, Object>();
        if (prefix == null) {
            prefix = "";
        }
        while ((paramNames != null) && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if ((values == null) || (values.length == 0)) {
                    // Do nothing, no values found at all.
                } else if (values.length > 1) {
                    if (isGet) {
                        for (int i = 0; i < values.length; i++) {
                            values[i] = decodeStr(values[i]);
                        }
                    }
                    params.put(unprefixed, values);
                } else {
                    if (isGet) {
                        values[0] = decodeStr(values[0]);
                    }
                    params.put(unprefixed, values[0]);
                }
            }
        }
        return params;
    }

    private static String decodeStr(String value) {
        try {
            value = new String(value.getBytes("ISO-8859-1"), "utf-8");
            value = URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        }
        return value;
    }

    /**
     * @param request
     * @return
     * 获取请求参数
     */
    public static Map<String, Object> getParameters(HttpServletRequest request) {
        return getParameters(request, false);
    }

    public static Map<String, Object> getParameters(HttpServletRequest request, boolean decode) {
        if (decode) {
            return getParametersStartingWithEx(request, null);
        }
        return getParametersStartingWith(request, null);
    }

    ;

    /**
     * @param request
     * @return
     * 获取请求参数JSON格式数据
     */
    public static String getParametersJson(HttpServletRequest request) {
        return getParametersJsonStartingWith(request, null);
    }

    /**
     * @param request
     * @param prefix
     * @return
     * 获取请求参数JSON格式数据
     */
    public static String getParametersJsonStartingWith(
            HttpServletRequest request, String prefix) {
        Map<String, Object> params = getParametersStartingWithEx(request,
                prefix);
        return JSON.toJSONString(params);
    }

}