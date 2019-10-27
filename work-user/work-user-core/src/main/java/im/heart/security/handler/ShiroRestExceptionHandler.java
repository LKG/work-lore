package im.heart.security.handler;

import com.google.common.collect.Maps;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.utils.BaseUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author gg
 * @desc 认证异常统一处理类
 */
@ControllerAdvice
public class ShiroRestExceptionHandler{
	protected static final Logger logger = LoggerFactory.getLogger(ShiroRestExceptionHandler.class);

	protected Map<String, Object> error(HttpServletRequest request) {
		Map<String, Object> errorMap = Maps.newHashMap();
		errorMap.put(RequestResult.SUCCESS, false);
		errorMap.put(RequestResult.RESULT, request.getRequestURL());
		return errorMap;
	}
	protected Map<String, Object> error(HttpServletRequest request, Exception ex) {
		Map<String, Object> errorMap = this.error(request);
		errorMap.put(RequestResult.EXCEPTION, ex.getMessage());
		return errorMap;
	}
	protected ModelAndView chooseView(HttpServletRequest request, Map<String, Object> errorMap){
		if(BaseUtils.isAjaxRequest(request)){
			return new ModelAndView(RequestResult.PAGE_ERROR_UNAUTHORIZED_IN, errorMap);
		}
		return new ModelAndView(RequestResult.PAGE_ERROR_UNAUTHORIZED, errorMap);
	}
	@ExceptionHandler(value = AuthorizationException.class)
    public ModelAndView defaultAuthorizationExceptionHandler(HttpServletRequest request, AuthorizationException ex) throws Exception {
		logger.debug(ex.getStackTrace()[0].getMethodName(), ex);
		return this.chooseView(request,this.error(request,ex));
    }
	@ExceptionHandler(UnauthorizedException.class)
	public ModelAndView handleUnauthorizedException(HttpServletRequest request, UnauthorizedException ex) {
		logger.debug(ex.getStackTrace()[0].getMethodName(), ex);
		return this.chooseView(request,this.error(request,ex));
	}
	@ExceptionHandler(UnauthenticatedException.class)
	public ModelAndView handleUnauthorizedException(HttpServletRequest request, UnauthenticatedException ex) {
		logger.debug(ex.getStackTrace()[0].getMethodName(), ex);
		return this.chooseView(request,this.error(request,ex));
	}

}
