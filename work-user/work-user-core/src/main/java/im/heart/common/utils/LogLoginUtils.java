package im.heart.common.utils;

import im.heart.common.context.ContextManager;
import im.heart.core.utils.BaseUtils;
import im.heart.log.entity.FrameLogLogin;
import im.heart.log.service.FrameLogLoginService;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.vo.FrameUserVO;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public class LogLoginUtils{
	
	public static  void loginLog(HttpServletRequest request){
		FrameLogLoginService logLoginService=(FrameLogLoginService) ContextManager.getBean(FrameLogLoginService.BEAN_NAME);
		FrameLogLogin entity=new FrameLogLogin();
		entity.setSystemHost(BaseUtils.getServerIp() + ":" + request.getLocalPort());
		entity.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		String ip= BaseUtils.getIpAddr(request);
		entity.setUserHost(ip);
		FrameUserVO user = SecurityUtilsHelper.getCurrentUser();
		entity.setUserName(user.getUserName());
		entity.setUserId(user.getUserId());
		logLoginService.log(entity);
	}
	
}

