package im.heart.security.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import im.heart.security.cache.ShiroCacheConfig;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.vo.FrameUserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.eis.SessionDAO;
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

	@Autowired(required = false)
	private CacheManager cacheManager;


    protected void saveRequest(ServletRequest request) {
        WebUtils.saveRequest(request);
    }
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		Subject subject = getSubject(request, response);
		// 如果没有登录，直接进行之后的流程
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			return true;
		}
		Session session = subject.getSession();
		Serializable sessionId = session.getId();
		FrameUserVO user = SecurityUtilsHelper.getCurrentUser();
		// 如果没有登录，直接进行之后的流程
		if(user==null){
			return true;
		}
		if(this.cache==null){
			this.cache = this.cacheManager.getCache(CACHE_NAME);
		}
		String username=user.getUserName();
		Deque<Serializable> deque = this.cache.get(username,Deque.class);
		if (deque == null) {
			deque = Lists.newLinkedList();
			this.cache.put(username, deque);
		}
		if (!deque.contains(sessionId) && session.getAttribute(kickOutParam) == null) {
			deque.push(sessionId);
			this.cache.put(username, deque);
		}
		while (deque.size() > maxSession) {
			Serializable kickoutSessionId = null;
			// 如果踢出后者
			if (kickOutAfter) {
				kickoutSessionId = deque.removeFirst();
			} else {
				// 否则踢出前者
				kickoutSessionId = deque.removeLast();
			}
			//更新队列
			this.cache.put(username, deque);
			try {
				Session onlineSession = SecurityUtils.getSecurityManager().getSession(new WebSessionKey(kickoutSessionId,request,response));
				/// 设置会话的kickout属性表示踢出了
				if (onlineSession != null) {
					onlineSession.setAttribute(kickOutParam, true);
				}
			} catch (SessionException ise) {
				logger.debug("Encountered session exception during logout.  This can generally safely be ignored."
						+ ise);
			}
		}
		if (session.getAttribute(kickOutParam) != null) {
			try {
				logger.info("检测到用户重复登录，移除用户"+username);
				subject.logout();
			} catch (SessionException ise) {
				logger.debug("Encountered session exception during logout.  This can generally safely be ignored."
						+ ise);
			}
			saveRequest(request);  
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
	public boolean isKickOutAfter() {
		return kickOutAfter;
	}

	public void setKickOutAfter(boolean kickOutAfter) {
		this.kickOutAfter = kickOutAfter;
	}

	public String getKickOutParam() {
		return kickOutParam;
	}

	public void setKickOutParam(String kickOutParam) {
		this.kickOutParam = kickOutParam;
	}

	public int getMaxSession() {
		return maxSession;
	}

	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}
}
