package im.heart.core.utils;

import im.heart.core.CommonConst;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.StringTokenizer;

/**
 *
 * @author gg
 * @desc常用工具操作类
 */
public class BaseUtils {
	
	protected static final Logger logger = LoggerFactory.getLogger(BaseUtils.class);

	static final String HTTP_PREFIX = "http";
	static final String HTTPS_PREFIX = "https";
	static final String UNKNOWN ="unknown";
	static final String XML_HTTP_REQUEST ="XMLHttpRequest";
	static final String SEPARATOR_DOT  =".";
	static final String SEPARATOR_COMMA  =",";
	static final String SEPARATOR_ASTERISK  ="*";
	static final String LOCAL_HOST_4  ="127.0.0.1";
	static final String LOCAL_HOST_6  ="0:0:0:0:0:0:0:1";
	static final int IPADDRESS_LENGTH = 15;
	static final int HTTP_PORT = 80;
	static final int HTTPS_PORT = 443;
	static final long C1 = 1000L;
	/**
	 * 
	 * 获取客户IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = overshot(request);
		return ip;
	}
	public static String extractBackURL(HttpServletRequest request) {
		String url = request.getParameter(CommonConst.RequestResult.BACK_URL);
		if (StringUtils.isEmpty(url)) {
			url = request.getHeader(HttpHeaders.REFERER);
		}
		boolean isHttp=!StringUtils.isEmpty(url) && (url.startsWith("http://") || url.startsWith("https://"));
		if (isHttp) {
			return url;
		}
		if (!StringUtils.isEmpty(url) && url.startsWith(request.getContextPath())) {
			url = getBasePath(request) + url;
		}
		return url;
	}


	/**
	 * 获取当前请求根路径
	 * @param req
	 * @return
	 */
	public static String getBasePath(HttpServletRequest req) {
		StringBuffer baseUrl = new StringBuffer();
		String scheme = req.getScheme();
		int port = req.getServerPort();
		baseUrl.append(scheme).append("://").append(req.getServerName());
		//非80 和 443 拼接端口号
		boolean isPort=(HTTP_PREFIX.equals(scheme) && port != HTTP_PORT)||(HTTPS_PREFIX.equals(scheme) && port != HTTPS_PORT);
		if(isPort){
			baseUrl.append(':').append(port);
		}
		return baseUrl.toString();
	}

	/**
	 * 获取本机IP地址
	 * @return
	 */
	public static String getServerIp(){
		String serverIp="";
		try {  
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();  
	        InetAddress ip = null;  
	        while (netInterfaces.hasMoreElements()) {  
	            NetworkInterface ni = netInterfaces.nextElement();
	            Enumeration<InetAddress>  addresses = ni.getInetAddresses();
	            while (addresses.hasMoreElements()) {  
	            	ip = addresses.nextElement();
	            	if (ip != null && ip instanceof Inet4Address){
	            		serverIp = ip.getHostAddress();  
	            		return serverIp;
	            	}
	            }
	        }  
	    } catch (SocketException e) {  
	    	logger.error(e.getStackTrace()[0].getMethodName(), e);
	    }
	     return serverIp;  
	}
	/**
	 * 
	 * 设置客户端缓存过期时间 的Header.
	 * @param response
	 * @param expiresSeconds
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		// Http 1.0 header, set a fix expires date.
		response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + (expiresSeconds * C1));
		// Http 1.1 header, set a time after now.
		response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age=" + expiresSeconds);
	}
	/**
	 * 
	 * 设置禁止客户端缓存的Header.
	 * @param response
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader(HttpHeaders.EXPIRES, 0L);
		response.addHeader(HttpHeaders.PRAGMA, "no-cache");
		// Http 1.1 header
		response.setHeader(HttpHeaders.CACHE_CONTROL,"no-store, no-cache, max-age=0, must-revalidate");
		response.addHeader(HttpHeaders.CACHE_CONTROL, "post-check=0, pre-check=0");
	}

	/**
	 * 
	 * 设置LastModified Header.
	 * @param response
	 * @param lastModifiedDate
	 */
	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader(HttpHeaders.LAST_MODIFIED, lastModifiedDate);
	}
	
	/**
	 * 
	 *  设置Etag Header.
	 * @param response
	 * @param etag
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader(HttpHeaders.ETAG, etag);
	}
	/**
	 * 
	 *  根据浏览器If-Modified-Since Header, 计算文件是否已被修改.如果无修改, checkIfModify返回false ,设置304 not modify status. 用来判定重复提交
	 * @param request
	 * @param response
	 * @param lastModified  内容的最后修改时间.
	 * @return
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
			long lastModified) {
		long ifModifiedSince = request.getDateHeader(HttpHeaders.IF_MODIFIED_SINCE);
		if ((ifModifiedSince != -1) && (lastModified < (ifModifiedSince + C1))) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 
	 *  根据浏览器 If-None-Match Header, 计算Etag是否已无效.如果Etag有效, checkIfNoneMatch返回false,
	 *  设置304 not modify status. 用来判定重复提交
	 * @param request
	 * @param response
	 * @param etag  内容的ETag.
	 * @return
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
		String headerValue = request.getHeader(HttpHeaders.IF_NONE_MATCH);
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!SEPARATOR_ASTERISK.equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, SEPARATOR_COMMA);
				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}
			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader(HttpHeaders.ETAG, etag);
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * 设置让浏览器弹出下载对话框的Header,不同浏览器使用不同的编码方式.
	 * @param request
	 * @param response
	 * @param fileName 下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
		try {
			String agent = request.getHeader(HttpHeaders.USER_AGENT);
			String encodedfileName = null;
	        if (null != agent) {  
	        	agent = agent.toLowerCase();
	            if (agent.contains("firefox") || agent.contains("chrome") || agent.contains("safari")) {
	    			encodedfileName = "filename=\"" + new String(fileName.getBytes(), StandardCharsets.ISO_8859_1) + "\"";
	            } else if (agent.contains("msie")) {
	            	encodedfileName = "filename=\"" + URLEncoder.encode(fileName,"UTF-8") + "\"";
	            } else if (agent.contains("opera")) {  
	            	encodedfileName = "filename*=UTF-8\"" + fileName + "\"";
	            } else {
	            	encodedfileName = "filename=\"" + URLEncoder.encode(fileName,"UTF-8") + "\"";
	            }
	        }
	        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; " + encodedfileName);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
	}
	/**
	 * 
	 * 检查是否支持gzip
	 * @param request
	 * @return
	 */
	public static boolean checkAcceptGzip(HttpServletRequest request) {
		// Http1.1 header
		String acceptEncoding = request.getHeader(HttpHeaders.ACCEPT_ENCODING);
		return StringUtils.contains(acceptEncoding, "gzip");
	}
	/**
	 * 
	 * 设置gzip 压缩请求头
	 * @param response
	 */
	public static void setGzipHeader(HttpServletResponse response) {
		response.setHeader(HttpHeaders.CONTENT_ENCODING, "gzip");
		response.setHeader(HttpHeaders.VARY, HttpHeaders.ACCEPT_ENCODING);
	}
	
	/**
	 * 
	 * 判断是否为ajax 请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request){
		String requestType = request.getHeader("X-Requested-With");
		if(XML_HTTP_REQUEST.equalsIgnoreCase(requestType)){
			return true;
		}
		return false;
	}
	public static String getRefererHost(HttpServletRequest request) {
		try {
			String origin = request.getHeader(HttpHeaders.REFERER);
			URL url = new URL(origin);
			if (url.getPort() > 0) {
				return url.getProtocol() + "://" + url.getHost() + ":" + url.getPort();
			}
			return url.getProtocol() + "://" + url.getHost();

		} catch (MalformedURLException e) {
			logger.warn("", e);
		}
		return StringUtils.EMPTY;
	}
	/**
	 * 跨域操作
	 * @param request
	 * @param response
	 */
	public static void crossOrigin(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)) {
				response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, getRefererHost(request));
			}
			if (!response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS)) {
				response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
			}
			if (!response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS)) {
				response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, SEPARATOR_ASTERISK);
			}
		} catch (Exception e) {
			logger.warn("crossOrigin", e);
		}
	}


	/**
	 * 
	 * 转换客户真实IP地址
	 * @param request
	 * @return
	 */
	private static String overshot(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-forwarded-for");
		if (StringUtils.isBlank(ipAddress)|| UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ipAddress)|| UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
        if (StringUtils.isBlank(ipAddress)|| UNKNOWN.equalsIgnoreCase(ipAddress)) {
        	ipAddress = request.getHeader("HTTP_CLIENT_IP");  
        }
        if (StringUtils.isBlank(ipAddress)|| UNKNOWN.equalsIgnoreCase(ipAddress)) {
        	ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }
		/**
		 * 自定义的变量名
		 */
		if (StringUtils.isBlank(ipAddress)|| UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-real-ip");
		}
		if (StringUtils.isBlank(ipAddress)|| UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (LOCAL_HOST_4.equals(ipAddress)||LOCAL_HOST_6.equals(ipAddress)) {
				// 根据网卡取本机配置的IP
				ipAddress = getServerIp();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割 // "***.***.***.***".length()
		if (ipAddress != null && ipAddress.length() > IPADDRESS_LENGTH) {
			if (ipAddress.indexOf(SEPARATOR_COMMA) > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(SEPARATOR_COMMA));
			}
		}
		return ipAddress;
	}	

}