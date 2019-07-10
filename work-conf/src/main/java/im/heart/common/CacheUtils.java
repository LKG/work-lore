package im.heart.common;

import im.heart.common.context.ContextManager;
import im.heart.core.CommonConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
/**
 * @author gg
 * @desc : CacheUtils
 */
public  class CacheUtils {
	protected static final Logger logger = LoggerFactory.getLogger(CacheUtils.class);
	
	public static final String CACHE_MANAGER_BEAN_NAME = CommonConst.CACHE_MANAGER_NAME;

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
	 * 检测输入字符串是否和缓存中的数据一致
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
	 * 根据缓存名称获取缓存数据信息
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
	 * 设置缓存
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void generateCache(String cacheName,String key,Object value){
		logger.debug("cacheName:[{}] key:[{} ,value:[{}]",cacheName,key,value);
		Cache cache = getCacheByName(cacheName);
		cache.put(key, value);
	}
	/**
	 * 根据缓存名称和key 清空对应数据
	 * @param cacheName
	 * @param key
	 */
	public static void evictCache(String cacheName,String key){
		logger.debug("cacheName:[{}] key:[{}]",cacheName,key);
		Cache cache = getCacheByName(cacheName);
		cache.evict(key);
	}
}
