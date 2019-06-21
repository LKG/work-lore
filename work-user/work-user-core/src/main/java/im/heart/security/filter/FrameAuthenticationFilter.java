package im.heart.security.filter;

import com.alibaba.fastjson.JSONObject;
import im.heart.common.utils.LogLoginUtils;
import im.heart.core.CommonConst;
import im.heart.core.utils.BaseUtils;
import im.heart.core.web.utils.WebUtilsEx;
import im.heart.security.AccountToken;
import im.heart.security.utils.ShiroLoginHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 
 * @author gg
 * @desc 自定义用户验证过滤链
 */
public class FrameAuthenticationFilter extends FormAuthenticationFilter {
	protected static final Logger logger = LoggerFactory.getLogger(FrameAuthenticationFilter.class);
	private String loginUrl = "/login.jhtml";
	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
    public static final String DEFAULT_USERNAME_PARAM = "userName";
    public static final String DEFAULT_PASSWORD_PARAM = "passWord";
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String usernameParam = DEFAULT_USERNAME_PARAM;
	private String passwordParam = DEFAULT_PASSWORD_PARAM;
	@Override
	public String getLoginUrl() {
		return loginUrl;
	}
	@Override
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getCaptchaParam(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}
	@Override
	protected String getUsername(ServletRequest request) {
	  return WebUtils.getCleanParam(request, getUsernameParam());
	}
	@Override
	protected String getPassword(ServletRequest request) {
	  return WebUtils.getCleanParam(request, getPasswordParam());
	}
	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}
	@Override
	public String getUsernameParam() {
		return usernameParam;
	}
	@Override
	public void setUsernameParam(String usernameParam) {
		this.usernameParam = usernameParam;
	}
	@Override
	public String getPasswordParam() {
		return passwordParam;
	}
	@Override
	public void setPasswordParam(String passwordParam) {
		this.passwordParam = passwordParam;
	}

	@Override
	protected AccountToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = this.getUsername(request);
		String password = this.getPassword(request);
		boolean rememberMe = this.isRememberMe(request);
		String host = BaseUtils.getIpAddr(WebUtils.toHttp(request));
		String remoteHost = request.getRemoteHost();
		logger.info("createToken... username:{},host:{},remoteHost:{}",username,host,remoteHost);
		String captcha = this.getCaptchaParam(request);
		return new AccountToken(username, password, rememberMe, host, captcha);
	}

	@Override
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		AuthenticationToken token = createToken(request, response);
		logger.info("executeLogin .......");
		if (token == null) {
			String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken must be created in order to execute a login attempt.";
			throw new IllegalStateException(msg);
		}
		try {
			Subject subject = getSubject(request, response);
			subject.login(token);
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
            Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		logger.debug(token + "onLoginSuccess ......subject:" + subject);
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		ShiroLoginHelper.setLoginSuccessSession();//清空登录次数限制
		//记录登录成功日志
		LogLoginUtils.loginLog(httpServletRequest);
		String loginSuccessUrl = httpServletRequest.getContextPath()+ this.getSuccessUrl();
		SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
		if (savedRequest != null) {
			String savedRequestUrl = savedRequest.getRequestUrl();
			if (!savedRequestUrl.equals(getLoginUrl())) {
				loginSuccessUrl = savedRequestUrl;
			}
		}
		if (BaseUtils.isAjaxRequest(httpServletRequest)) {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.setCharacterEncoding("UTF-8");
			PrintWriter out = httpServletResponse.getWriter();
			JSONObject jsObj = new JSONObject();
			jsObj.put("successUrl", loginSuccessUrl);
			jsObj.put(CommonConst.RequestResult.SUCCESS, true);
			jsObj.put(CommonConst.RequestResult.HTTP_STATUS, HttpStatus.OK.toString());
			out.println(jsObj);
			out.flush();
			out.close();
			return false;
		}
		return super.onLoginSuccess(token, subject, request, response);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		logger.debug(token + "onLoginFailure ......subject:" + e);
		ShiroLoginHelper.setLoginFailureSession();
		return super.onLoginFailure(token, e, request, response);
	}
	@Override
	protected boolean isLoginRequest(ServletRequest request,
			ServletResponse response) {
		String requestURI = StringUtils.substringBefore(getPathWithinApplication(request), ".");
		String loginUrl = StringUtils.substringBefore(getLoginUrl(), ".");
		return super.pathsMatch(loginUrl, requestURI);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		logger.info("onAccessDenied ......subject:,{}", WebUtilsEx.getParametersJson(WebUtils.toHttp(request)));
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				if (logger.isTraceEnabled()) {
					logger.trace("Login submission detected.  Attempting to execute login.");
				}
				return executeLogin(request, response);
			}
			if (logger.isTraceEnabled()) {
					logger.trace("Login page view.");
			}
			HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
			HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
			if (BaseUtils.isAjaxRequest(httpServletRequest)) {
				logger.info("httpServletResponse:{}", httpServletResponse);
				httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
			// allow them to see the login page ;)
				return true;
		}
		if (logger.isTraceEnabled()) {
			logger.trace("Attempting to access a path which requires authentication.  Forwarding to the "
					+ "Authentication url [" + getLoginUrl() + "]");
		}
		saveRequestAndRedirectToLogin(request, response);
		return false;
	}

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		if (isLoginRequest(request, response)) {
			logger.info("@@@@@@@@@@@@@@@@@isLoginRequest@@@@@@@@@@@@@@@@@@@@@@@@");
		}
		if (SecurityUtils.getSubject().isAuthenticated()) {
			// 已经登录过 继续过滤器链
			return true;
		}
		return super.preHandle(request, response);
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) {
		try {
			logger.info("isAccessAllowed .....");
			// 先判断是否是登录操作
			// 解决不能重复登录问题
			if (isLoginSubmission(request, response)) {
				logger.info("isAccessAllowed ..isLoginSubmission...");
				if (logger.isTraceEnabled()) {
					logger.trace("Login submission detected.  Attempting to execute login.");
				}
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}

}
