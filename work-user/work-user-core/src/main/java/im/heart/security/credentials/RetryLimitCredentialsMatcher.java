package im.heart.security.credentials;

import im.heart.security.cache.ShiroCacheConfig;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 
 * @author gg
 * @desc 密码校验器 ，防暴力破解 用户密码输入错误次数过多，自动锁定账号
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
	protected static final Logger logger = LoggerFactory.getLogger(RetryLimitCredentialsMatcher.class);
	@Value("${shiro.password.hashAlgorithmName}")
	private String hashAlgorithmName="md5";

	@Value("${shiro.password.hashIterations}")
	private int hashIterations=2;
	@Value("${shiro.password.storedCredentialsHexEncoded}")
	private boolean storedCredentialsHexEncoded=true;
	protected static final String CACHE_NAME = ShiroCacheConfig.PASSWORD_RETRY.keyPrefix;

	private Cache passwordRetryCache;

	@Autowired(required = false)
	private CacheManager cacheManager;

	private static int MAX_FAIL_COUNT=5;

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		Serializable username = (Serializable) token.getPrincipal();
		logger.debug(username + ":doCredentialsMatch....."+ this.passwordRetryCache);
		if(this.passwordRetryCache==null){
			this.passwordRetryCache = this.cacheManager.getCache(CACHE_NAME);
		}
		AtomicInteger retryCount = (AtomicInteger)this.passwordRetryCache.get(username,AtomicInteger.class);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
		}
		int count = retryCount.incrementAndGet();
		if (count >= 2) {
			logger.warn(username + "密码错误>=2次");
		}
		if (count >= MAX_FAIL_COUNT) {
			logger.warn(username + "被锁定！");
			throw new ExcessiveAttemptsException();
		}
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			this.passwordRetryCache.evict(username);
		}else{
			this.passwordRetryCache.put(username, retryCount);
		}
		return matches;
	}

	@Override
	public String getHashAlgorithmName() {
		return hashAlgorithmName;
	}

	@Override
	public void setHashAlgorithmName(String hashAlgorithmName) {
		this.hashAlgorithmName = hashAlgorithmName;
	}

	@Override
	public int getHashIterations() {
		return hashIterations;
	}

	@Override
	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	@Override
	public boolean isStoredCredentialsHexEncoded() {
		return storedCredentialsHexEncoded;
	}

	@Override
	public void setStoredCredentialsHexEncoded(boolean storedCredentialsHexEncoded) {
		this.storedCredentialsHexEncoded = storedCredentialsHexEncoded;
	}
}
