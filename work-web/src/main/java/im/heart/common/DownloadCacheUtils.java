package im.heart.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  class DownloadCacheUtils {
	protected static final Logger logger = LoggerFactory.getLogger(DownloadCacheUtils.class);

	public enum CacheConfig {
		DOWNLOAD_CACHE("download_total_cache", 30 * 60 * 1),;
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
}
