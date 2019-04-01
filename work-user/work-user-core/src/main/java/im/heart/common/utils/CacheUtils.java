package im.heart.common.utils;

import im.heart.common.context.ContextManager;
import im.heart.core.CommonConst;
import im.heart.core.utils.StringUtilsEx;
import im.heart.core.validator.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

public  class CacheUtils {
	protected static final Logger logger = LoggerFactory.getLogger(CacheUtils.class);
	
	public static final String CACHE_MANAGER_BEAN_NAME = CommonConst.CACHE_MANAGER_NAME;

	public enum CacheConfig {
		EMAIL_CODE("emailCode_cache", 30 * 60 * 1),
		MOBILE_CODE("mobileCode_cache", 30 * 60 * 1),
		FIND_PWD("findPwd_cache", 30 * 60 * 1);
		CacheConfig(String keyPrefix, long expiredTime) {
			this.keyPrefix = keyPrefix;
			this.expiredTime = expiredTime;
		}
		public  String keyPrefix;

		public long expiredTime;

		public long getExpiredTimeByKey(String keyPrefix){
			CacheConfig[] values = CacheConfig.values();
			for(CacheConfig value:values ){
				if(value.getKeyPrefix().equals(keyPrefix)){
					return value.getExpiredTime();
				}
			}
			return 0;
		}

		public String getKeyPrefix() {
			return keyPrefix;
		}

		public void setKeyPrefix(String keyPrefix) {
			this.keyPrefix = keyPrefix;
		}

		public long getExpiredTime() {
			return expiredTime;
		}

		public void setExpiredTime(long expiredTime) {
			this.expiredTime = expiredTime;
		}

	}

	/**
	 * 获取缓存信息
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object  getCacheObject(String cacheName,String key){
		Cache cache = getCacheByName(cacheName);
		if(cache!=null){
			ValueWrapper valueWrapper =cache.get(key);
			if(valueWrapper!=null){
				Object cacheCode = valueWrapper.get();
				if (cacheCode != null) {
					return cacheCode;
				}
			}
		}
		return null;
	}
	
	/**
	 * @Desc：检测输入字符串是否和缓存中的数据一致
	 * @param cacheName
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean  checkWrapper(String cacheName,String key,String value){
		Object cacheCode =getCacheObject(cacheName,key);
		if (cacheCode != null && cacheCode.toString().equals(value)) {
			return true;
		}
		return false;
	}
	/**
	 * @Desc：根据缓存名称获取缓存数据信息
	 * @param cacheName
	 * @return
	 */
	public static Cache getCacheByName(String cacheName){
		CacheManager cacheManager = (CacheManager) ContextManager.getBean(CACHE_MANAGER_BEAN_NAME);
		Cache cache = cacheManager.getCache(cacheName);
		return cache;
	}
	/**
	 * 
	 * @Desc：设置缓存
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void generatCache(String cacheName,String key,Object value){
		logger.debug("cacheName:[{}] key:[{} ,value:[{}]",cacheName,key,value);
		Cache cache = getCacheByName(cacheName);
		cache.put(key, value);
	}
	/**
	 * @Desc：根据缓存名称和key 清空对应数据
	 * @param cacheName
	 * @param key
	 */
	public static void evictCache(String cacheName,String key){
		logger.debug("cacheName:[{}] key:[{}]",cacheName,key);
		Cache cache = getCacheByName(cacheName);
		cache.evict(key);
	}
	public static void evictMobileCode(String userPhone){
		evictCache(CacheConfig.MOBILE_CODE.keyPrefix,userPhone);
	}
	public static void evictEmailCode(String emailCode){
		evictCache(CacheConfig.EMAIL_CODE.keyPrefix,emailCode);
	}
	/**
	 * @Desc：验证短信码是否正确
	 * @param userPhone
	 * @param phoneCode
	 * @return
	 */
	public static boolean checkMobileCode(String userPhone,String phoneCode){
		logger.debug("mobile:[{}] mobileCode:[{}]",userPhone,phoneCode);
		if (StringUtilsEx.isNotBlank(phoneCode)&& StringUtilsEx.isNotBlank(userPhone)&& ValidatorUtils.isPhone(userPhone)){
			return checkWrapper(CacheConfig.MOBILE_CODE.keyPrefix,userPhone,phoneCode);
		}
		return false;
	}
	public static void generatEmailCodeCache(String email,Object emailCode){
		generatCache(CacheConfig.EMAIL_CODE.keyPrefix,email, emailCode);
	}
	
	
	public static void generateMobileCache(String phone,Object phoneCode){
		generatCache(CacheConfig.MOBILE_CODE.keyPrefix,phone, phoneCode);
	}
	public static void evictEmailCache(String email){
		evictCache(CacheConfig.EMAIL_CODE.keyPrefix,email);
	}
	
	public static void evictMobileCache(String phone){
		evictCache(CacheConfig.MOBILE_CODE.keyPrefix,phone);
	}
	
	/**
	 * @Desc：校验邮箱
	 * @param userEmail
	 * @param emailcode
	 * @return
	 */
	public static boolean checkEmailCode(String userEmail,String emailcode){
		logger.debug("email:[{}] emailCode:[{}]",userEmail,emailcode);
		if (StringUtilsEx.isNotBlank(emailcode)&& StringUtilsEx.isNotBlank(userEmail)&& ValidatorUtils.isEmail(userEmail)){
			return checkWrapper(CacheConfig.EMAIL_CODE.keyPrefix,userEmail,emailcode);
		}
		return false;
	}
	
}
