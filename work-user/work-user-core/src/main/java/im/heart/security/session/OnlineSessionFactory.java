package im.heart.security.session;

import im.heart.core.utils.BaseUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public class OnlineSessionFactory implements SessionFactory {
	protected static final Logger logger = LoggerFactory.getLogger(OnlineSessionFactory.class);

	@Override
	public Session createSession(SessionContext initData) {
		logger.debug("createSession..................");
		OnlineSession session = new OnlineSession();
		if (initData != null && initData instanceof WebSessionContext) {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if (request != null) {
                session.setHost(BaseUtils.getIpAddr(request));
                session.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
                session.setSystemHost(BaseUtils.getServerIp() + ":" + request.getLocalPort());
            }
        }
		return session;
	}
}
