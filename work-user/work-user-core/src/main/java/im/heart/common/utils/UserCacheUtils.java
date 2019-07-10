package im.heart.common.utils;

import im.heart.common.CacheUtils;
import im.heart.core.CommonConst;
import im.heart.core.utils.StringUtilsEx;
import im.heart.core.validator.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  class UserCacheUtils {
	protected static final Logger logger = LoggerFactory.getLogger(UserCacheUtils.class);
	public enum CacheConfig {
		EMAIL_CODE("emailCode_cache", 15 * 60 * 1),
		MOBILE_CODE("mobileCode_cache", 15 * 60 * 1),
		FIND_PWD("findPwd_cache", 15 * 60 * 1);
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

	public static void evictMobileCode(String userPhone){
		CacheUtils.evictCache(CacheConfig.MOBILE_CODE.keyPrefix,userPhone);
	}
	public static void evictEmailCode(String emailCode){
		CacheUtils.evictCache(CacheConfig.EMAIL_CODE.keyPrefix,emailCode);
	}
	/**
	 * 验证短信码是否正确
	 * @param userPhone
	 * @param phoneCode
	 * @return
	 */
	public static boolean checkMobileCode(String userPhone,String phoneCode){
		logger.debug("mobile:[{}] mobileCode:[{}]",userPhone,phoneCode);
		if (StringUtilsEx.isNotBlank(phoneCode)&& StringUtilsEx.isNotBlank(userPhone)&& ValidatorUtils.isPhone(userPhone)){
			return CacheUtils.checkWrapper(CacheConfig.MOBILE_CODE.keyPrefix,userPhone,phoneCode);
		}
		return false;
	}
	public static void generateEmailCodeCache(String email,Object emailCode){
		CacheUtils.generateCache(CacheConfig.EMAIL_CODE.keyPrefix,email, emailCode);
	}

	public static void generateMobileCache(String phone,Object phoneCode){
		CacheUtils.generateCache(CacheConfig.MOBILE_CODE.keyPrefix,phone, phoneCode);
	}
	public static void evictEmailCache(String email){
		CacheUtils.evictCache(CacheConfig.EMAIL_CODE.keyPrefix,email);
	}
	
	public static void evictMobileCache(String phone){
		CacheUtils.evictCache(CacheConfig.MOBILE_CODE.keyPrefix,phone);
	}
	
	/**
	 * 校验邮箱
	 * @param userEmail
	 * @param emailCode
	 * @return
	 */
	public static boolean checkEmailCode(String userEmail,String emailCode){
		logger.debug("email:[{}] emailCode:[{}]",userEmail,emailCode);
		if (StringUtilsEx.isNotBlank(emailCode)&& StringUtilsEx.isNotBlank(userEmail)&& ValidatorUtils.isEmail(userEmail)){
			return CacheUtils.checkWrapper(CacheConfig.EMAIL_CODE.keyPrefix,userEmail,emailCode);
		}
		return false;
	}
	
}
