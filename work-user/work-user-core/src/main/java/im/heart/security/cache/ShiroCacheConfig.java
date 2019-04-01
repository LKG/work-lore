package im.heart.security.cache;
/**
 *
 * @author gg
 * @desc  ShiroCacheConfig
 */
public enum ShiroCacheConfig {

	PASSWORD_RETRY("shiro_password_retry:", 30 * 60 * 1),
	SESSION_ACTIVE("shiro_session_active:", 60 * 60 * 1),
	SESSION_KICKOUT("shiro_session_kickout:", 0),
	USER_REALM("shiro_user_realm", 0);
	ShiroCacheConfig(String keyPrefix, long expiredTime) {
		this.keyPrefix = keyPrefix;
		this.expiredTime = expiredTime;
	}
	public  String keyPrefix;

	public long expiredTime;

	public long getExpiredTimeByKey(String keyPrefix){
		ShiroCacheConfig[] values = ShiroCacheConfig.values();
		for(ShiroCacheConfig value:values ){
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
