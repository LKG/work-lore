package im.heart.core.utils;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author gg
 * @desc OkHttpClient 工具类
 */
public class OkHttpClientUtils {
	protected static final Logger logger = LoggerFactory.getLogger(OkHttpClientUtils.class);

	private final static long TIME_OUT=3000;
	private final static OkHttpClient mOkHttpClient = new OkHttpClient();
	public static class UnSafeTrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}
	public static KeyManager[] prepareKeyManager(KeyStore clientKeyStore, String password) {
		try {
			KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(clientKeyStore, password.toCharArray());
			return keyManagerFactory.getKeyManagers();
		} catch (KeyStoreException e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		} catch (UnrecoverableKeyException e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
		return null;
	}
	public static String fetchEntityString(String url) throws IOException {
		return fetchEntityString(url, null);
	}
	public static String fetchEntityString(String url, Map<String, Object> params) throws IOException {
		return fetchResponse(url, params).body().string();
	}

	public static String fetchEntityString(String url, Map<String, Object> params,Map<String, String> headers) throws IOException {
		return fetchResponse(url,params,headers).body().string();
	}
	public static Response fetchResponse(String url) throws IOException {
		return fetchResponse(url,null,null);
	}
	public static Response fetchResponse(String url, Map<String, Object> params) throws IOException {
		return fetchResponse(url,params,null);
	}
	public static Response fetchResponse(String url, Map<String, Object> params,Map<String, String> headers) throws IOException {
		OkHttpClient copy = mOkHttpClient.newBuilder().readTimeout(TIME_OUT, TimeUnit.MILLISECONDS).build();
		Builder builder = new Builder().url(url);
		Request request = null;
		if(headers!=null&& !headers.isEmpty()){
			Iterator<Map.Entry<String, String>> it=headers.entrySet().iterator();
			while (it.hasNext()){
				Map.Entry<String, String> entry = it.next();
				builder.addHeader(entry.getKey(),entry.getValue());
			}
		}
		if (params == null) {
			request = builder.build();
		} else {
			request = builder.post(builderFormBody(params)).build();
		}

		Response response = copy.newCall(request).execute();
		if (!response.isSuccessful()) {
			throw new IOException("Unexpected code " + response);
		}
		return response;
	}
	public static String encodingUrl(String url, Map<String, Object> params, String encoding) throws IOException {
		String httpParam = "";
		if (params != null) {
			for (String key : params.keySet()) {
				if (StringUtils.isBlank(httpParam)) {
					httpParam = "?";
				} else {
					httpParam = httpParam + "&";
				}
				httpParam = httpParam + key + "=" + URLEncoder.encode((String) params.get(key), encoding);
			}
		}
		return url+httpParam;
	}

	public static FormBody builderFormBody(Map<String, Object> params) throws IOException {
		FormBody.Builder formBodyBuilder = new FormBody.Builder();
		if (params != null && !params.isEmpty()) {
			Iterator<Map.Entry<String, Object>> it=params.entrySet().iterator();
			while (it.hasNext()){
				Map.Entry<String, Object> entry = it.next();
				Object value = entry.getValue();
				if (value != null) {
					String key = entry.getKey();
					formBodyBuilder.add(key, value.toString());
				}
			}
		}
		return formBodyBuilder.build();
	}

	public static byte[] fetchEntity(String url, Map<String, Object> params) throws IOException {
		return fetchResponse(url, params).body().bytes();
	}
}
