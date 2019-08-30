package im.heart.security.filter;

import com.google.common.collect.Lists;
import im.heart.security.cache.ShiroCacheConfig;
import im.heart.security.session.KickOutSession;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.vo.FrameUserVO;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;

/**
 * 
 * @author gg
 * @desc 控制用户登录个数
 */
public class KickOutSessionControlFilter extends LogoutFilter {

	protected static final Logger logger = LoggerFactory.getLogger(KickOutSessionControlFilter.class);
    public static final String DEFAULT_REDIRECT_URL = "/login.jhtml?logout=2";
    private String redirectUrl = DEFAULT_REDIRECT_URL;
	/**
	 * 踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
	 */
	private boolean kickOutAfter = false;
	/**
	 * // 同一个帐号最大会话数 默认1
	 */
	private int maxSession = 1;
	protected static final String CACHE_NAME = ShiroCacheConfig.SESSION_KICKOUT.keyPrefix;

	private Cache cache;
	@Autowired(required = false)
	private CacheManager cacheManager;
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		Subject subject = getSubject(request, response);
		// 如果没有登录，直接进行之后的流程
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			return true;
		}
		FrameUserVO user = SecurityUtilsHelper.getCurrentUser();
		// 如果没有登录，直接进行之后的流程
		if(user==null){
			return true;
		}
		if(this.cache==null){
			this.cache = this.cacheManager.getCache(CACHE_NAME);
		}
		String username=user.getUserName();
        Session session = subject.getSession();
		Serializable sessionId = session.getId();
		Deque<KickOutSession> deque = this.cache.get(username,Deque.class);
		if (deque == null) {
			deque = Lists.newLinkedList();
		}
		KickOutSession kickOutSession = KickOutSession.builder().id(sessionId).build();
		if (!deque.contains(kickOutSession)) {
			deque.push(kickOutSession);
		}
		this.cache.put(username, deque);
		if(deque.size() > maxSession){
			// 如果踢出后者
			if (kickOutAfter) {
				kickOutSession = deque.removeFirst();
			} else {
				// 否则踢出前者
				kickOutSession = deque.removeLast();
			}
			//取出的是当前对象代表当前对象可以被清除
			if(sessionId.equals(kickOutSession.getId())){
				kickOutSession.setKickOut(true);
				//更新缓存
				this.cache.put(username, deque);
			}
		}
		if(kickOutSession!=null&&Boolean.TRUE.equals(kickOutSession.getKickOut())){
			try {
				logger.info("检测到用户重复登录，移除用户"+username);
				subject.logout();
			} catch (SessionException ise) {
				logger.debug("Encountered session exception during logout.  This can generally safely be ignored."
						+ ise);
			}
			WebUtils.saveRequest(request);
			issueRedirect(request, response, redirectUrl);
			return false;
		}
		return true;
	}
	@Override
	public String getRedirectUrl() {
		return redirectUrl;
	}
	@Override
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
