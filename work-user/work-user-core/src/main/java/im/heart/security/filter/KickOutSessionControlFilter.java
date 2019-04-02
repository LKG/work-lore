package im.heart.security.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import im.heart.security.cache.ShiroCacheConfig;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.vo.FrameUserVO;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.session.mgt.WebSessionKey;
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
	public static final String DEFAULT_KICK_OUT_PARAM = "kickOut";
    private String redirectUrl = DEFAULT_REDIRECT_URL;
    private String kickOutParam = DEFAULT_KICK_OUT_PARAM;
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
	@Autowired()
	private SessionManager sessionManager;
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
		Deque<Serializable> deque = this.cache.get(username,Deque.class);
		if (deque == null) {
			deque = Lists.newLinkedList();
		}
		if (!deque.contains(sessionId) && session.getAttribute(kickOutParam) == null) {
			deque.push(sessionId);
		}
		this.cache.put(username, deque);
		while (deque.size() > maxSession) {
			// 如果踢出后者
			Serializable kickOutSessionId = null;
			if (kickOutAfter) {
				kickOutSessionId = deque.removeFirst();
			} else {
				// 否则踢出前者
				kickOutSessionId = deque.removeLast();
			}
			System.out.println("kickOutSession="+kickOutSessionId);
			/// 设置会话的kickout属性表示踢出了
			//根据sessionId 获取session
			DefaultSessionKey sessionKey=new DefaultSessionKey(kickOutSessionId);
			Session kickOutSession=this.sessionManager.getSession(sessionKey);
			if (kickOutSession != null) {
				kickOutSession.setAttribute(kickOutParam, true);
			}
		}
		System.out.println("session.getAttribute(kickOutParam)"+session.getAttribute(kickOutParam));
		Boolean kickOut=(Boolean)session.getAttribute(kickOutParam);
		if (kickOut != null&&Boolean.TRUE.equals(kickOut)) {
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
