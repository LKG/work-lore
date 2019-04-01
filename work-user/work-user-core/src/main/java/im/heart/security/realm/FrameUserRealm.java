package im.heart.security.realm;

import im.heart.core.enums.Status;
import im.heart.core.plugins.captcha.ImageCaptchaExService;
import im.heart.core.utils.StringUtilsEx;
import im.heart.security.AccountToken;
import im.heart.security.FrameAuthenticationInfo;
import im.heart.security.FrameAuthorizationInfo;
import im.heart.security.cache.ShiroCacheConfig;
import im.heart.security.exception.IncorrectCaptchaException;
import im.heart.security.utils.ShiroLoginHelper;
import im.heart.usercore.entity.FrameUser;
import im.heart.usercore.service.FrameRoleService;
import im.heart.usercore.service.FrameUserService;
import im.heart.usercore.vo.FrameUserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;

/**
 * 
 * @author gg
 * @desc 自定义Realm
 */
public class FrameUserRealm extends AuthorizingRealm {

	protected static final Logger logger = LoggerFactory.getLogger(FrameUserRealm.class);
	private String name = ShiroCacheConfig.USER_REALM.keyPrefix;
    private static final String AUTHORIZATION_CACHE_SUFFIX = "_authorizationCache:";
   
	@Override
	public String getAuthorizationCacheName() {
		String authorizationCacheName = super.getAuthorizationCacheName();
		if(!StringUtilsEx.endsWith(authorizationCacheName, AUTHORIZATION_CACHE_SUFFIX)){
			 return this.name + AUTHORIZATION_CACHE_SUFFIX;
		}
		return super.getAuthorizationCacheName();
	}

	public FrameUserRealm(){
		super();
		setName(this.name);
	}

	@Override
	public void setName(String name) {
	   super.setName(name);
	   setAuthenticationCacheName(this.name + AUTHORIZATION_CACHE_SUFFIX);
	}

	protected static final  int MAX_FAIL=3;
	/**
	 * 是否使用验证码
	 */
	protected boolean useCaptcha = false;

	public boolean isUseCaptcha() {
		return useCaptcha;
	}

	public void setUseCaptcha(boolean useCaptcha) {
		this.useCaptcha = useCaptcha;
	}

	@Autowired
	private FrameUserService frameUserService;
	
	@Autowired
	private FrameRoleService frameRoleService;

	@Autowired
	private ImageCaptchaExService imageCaptchaService;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		logger.debug("开始进行授权校验。。。。。。");
		Collection<?> collection = principals.fromRealm(getName());
		if (collection == null || collection.isEmpty()) {
			return null;
		}
		Object primaryPrincipal = principals.getPrimaryPrincipal();
		FrameAuthorizationInfo authorizationInfo = new FrameAuthorizationInfo();
		if(primaryPrincipal instanceof FrameUser){
			FrameUser user = (FrameUser) primaryPrincipal;
			Set<String> roleCodes=this.frameUserService.findRoleCodesByUserId(user.getUserId());
			authorizationInfo.setRoles(roleCodes);
			Set<String> permissions = this.frameRoleService.findRolePermissionsByCode(roleCodes);
			authorizationInfo.setStringPermissions(permissions);
		}
		return authorizationInfo;
	}
	/**
	 *  @Desc：验证码校验
	 * @param accountToken
	 */
	protected void checkValidateCode(AccountToken accountToken){
		Integer loginTimes= ShiroLoginHelper.getLoginTimes();
		//登录次数大于三次需要进行验证码校验，强制开启
		if (loginTimes>=MAX_FAIL||useCaptcha) {
			String sessionId = (String) SecurityUtils.getSubject().getSession().getId();
			String validateCode=accountToken.getValidateCode();
			logger.info("开启验证码校验："+sessionId+"验证码校验。。。。validateCode。。"+validateCode);
			if(StringUtilsEx.isBlank(validateCode)){
				throw new IncorrectCaptchaException();
			}	
			if(!this.imageCaptchaService.validateResponseForID(sessionId, validateCode).booleanValue()){
				throw new IncorrectCaptchaException();
			};
		}
	}

	/**
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		AccountToken accountToken = (AccountToken) authcToken;
		String userName=accountToken.getUsername();
		char[] password=accountToken.getPassword();
		if (StringUtilsEx.isBlank(userName)) {
			throw new UnknownAccountException();
		}
		if (password==null) {
			throw new IncorrectCredentialsException();
		}
		logger.debug("开始进行登录校验。。。。。。");
		this.checkValidateCode(accountToken);
		Object principal = authcToken.getPrincipal();
		if (principal == null) {
			throw new UnknownAccountException();
		}
//		// 查询用户信息
		FrameUser frameUser = this.frameUserService.findFrameUser(userName);
		this.checkUserStatus(frameUser);
		FrameUserVO vo=new FrameUserVO(frameUser,false);
		// 设置认证对象为FrameUser 
		//记录登录日志---
		return new FrameAuthenticationInfo(vo,
				frameUser.getPassWord(), ByteSource.Util.bytes(frameUser.getCredentialsSalt()), getName(),frameUser.getUserId());
	}

	
	private void checkUserStatus(FrameUser frameUser){
		if (frameUser == null) {
			throw new UnknownAccountException();
		}
		Status userStatus = frameUser.getStatus();
		if (userStatus==null|| Status.pending==userStatus) {
			//账号未激活
			throw new LockedAccountException();
		}else 	if (Status.disabled==userStatus) {
			//账号被禁用
			throw new DisabledAccountException();
		}
		if (Status.enabled!=userStatus) {
			//账号状态异常
			throw new LockedAccountException();
		}
	}
	

	@Override
	public Object getAuthorizationCacheKey(PrincipalCollection principals) {
		Object primaryPrincipal = principals.getPrimaryPrincipal();
		if(primaryPrincipal instanceof FrameUserVO){
			FrameUserVO user = (FrameUserVO) primaryPrincipal;
			return user.getUserId();
		}
		return super.getAuthorizationCacheKey(principals);
	}
}
