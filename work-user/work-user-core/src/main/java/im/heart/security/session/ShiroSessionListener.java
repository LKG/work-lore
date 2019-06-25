package im.heart.security.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ShiroSessionListener implements SessionListener {
	protected static final Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);
	
	private static final AtomicInteger sessionCount = new AtomicInteger(0);
	@Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub
		logger.info("创建会话=={}",sessionCount.get());
		sessionCount.incrementAndGet();
	}

	@Override
	public void onStop(Session session) {
		logger.info("会话onStop=={}");
		sessionCount.decrementAndGet();
	}

	@Override
	public void onExpiration(Session session) {
		logger.info("登录过期==");
		sessionCount.decrementAndGet();
	}

	public int getSessionCount() {
        return sessionCount.get();
    }
}
