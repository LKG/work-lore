package im.heart.security.session;

import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.vo.FrameUserVO;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 
 * @author gg
 * @desc sessionDAO
 */
@Component
public class ShiroSessionDAO extends EnterpriseCacheSessionDAO {
	protected static final Logger logger = LoggerFactory.getLogger(ShiroSessionDAO.class);
    
	@Override
	protected Session doReadSession(Serializable sessionId) {
		logger.debug("doReadSession--"+sessionId);
		return null;
	}
	@Override
	protected void doUpdate(Session session) {
		if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {  
	        return; //如果会话过期/停止 没必要再更新了  
	    }
		if (session instanceof OnlineSession) {   
			OnlineSession ss = (OnlineSession) session;  
            if(ss.getUserId()==null||BigInteger.ZERO.equals(ss.getUserId())){
                FrameUserVO user = SecurityUtilsHelper.getCurrentUser();
                if(user!=null){
        			logger.debug("doUpdate--getUserId:"+user.getUserId());
                    ss.setUserId(user.getUserId());
                    ss.setUsername(user.getUserName());
                    ss.setStatus(OnlineSession.OnlineStatus.on_line);
                }
            }
            if (!ss.isAttributeChanged()) {    
                return;
            }
		}
	}
	@Override
	protected void doDelete(Session session) {
		// does nothing - parent class removes from cache.
		logger.debug("doDelete--"+session.getId());
	//	OnlineSession onlineSession = (OnlineSession) session;
       //设置用户离线
	}
}
