package im.heart.conf;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import im.heart.security.credentials.RetryLimitCredentialsMatcher;
import im.heart.security.filter.FrameAuthenticationFilter;
import im.heart.security.filter.FrameLogoutFilter;
import im.heart.security.filter.KickOutSessionControlFilter;
import im.heart.security.filter.ShiroFilterFactory;
import im.heart.security.realm.FrameUserRealm;
import im.heart.security.session.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authz.SslFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.Filter;
import java.util.Collection;
import java.util.Map;

@Configuration
@PropertySource(value = "classpath:/application-shiro.yml")
public class ShiroConfig {
	protected static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

	private final static Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
	@Value("${shiro.login.url}")
	private String loginUrl = "/login.jhtml";

	@Value("${shiro.login.success.url}")
	private String successUrl = "/";
	@Value("${shiro.sessionManager.cookie.name}")
	private String sessionIdName = "jsid";

	@Value("${shiro.unauthorizedUrl}")
	private String unauthorizedUrl = "/unauthorized.jhtml";

	@Value("${shiro.logout.success.url}")
	private String logoutSuccessUrl = "/login.jhtml?logout=1";
	/**
	 * 网络请求的权限过滤, 拦截外部请求
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(WebSecurityManager securityManager) {
		ShiroFilterFactory shiroFilterFactoryBean = new ShiroFilterFactory();
		shiroFilterFactoryBean.setLoginUrl(loginUrl);
		shiroFilterFactoryBean.setSuccessUrl(successUrl);
		shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		/*<!-- 添加自定义过滤链 -->*/
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		filters.put("authc",new FrameAuthenticationFilter());
		/*<!-- 用户注销控制过滤链 -->*/
		filters.put("logout",new FrameLogoutFilter());
		/*<!-- 添加ssl过滤链 -->*/
		filters.put("ssl",new SslFilter());
		/*<!-- 控制并发登录人数 -->*/
		filters.put("kickout",kickoutSessionControlFilter());
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("**.ico", "anon");
		filterChainDefinitionMap.put("/oauth2/**", "anon");
		filterChainDefinitionMap.put("/3rd/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/imgs/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/app/js/**", "anon");
		filterChainDefinitionMap.put("/app/css/**", "anon");
		filterChainDefinitionMap.put("/app/imgs/**", "anon");
		filterChainDefinitionMap.put("/modules/**", "anon");
		filterChainDefinitionMap.put("/login-in**", "anon");
		filterChainDefinitionMap.put("/validate/**", "anon");
		filterChainDefinitionMap.put("/regist**", "anon");
		filterChainDefinitionMap.put("/regist/**", "anon");
		filterChainDefinitionMap.put("/findPwd/**", "anon");
		filterChainDefinitionMap.put("/api/**", "anon");
		filterChainDefinitionMap.put("/index/**", "anon");
		filterChainDefinitionMap.put("/docs**", "anon");
		filterChainDefinitionMap.put("/doc/**", "anon");
		filterChainDefinitionMap.put("/article**", "anon");
		filterChainDefinitionMap.put("/article/**", "anon");
		filterChainDefinitionMap.put("/logout*", "logout");
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/uploads/**", "anon");
		filterChainDefinitionMap.put("/admin/druid/**", "perms[druid:monitor]");
		filterChainDefinitionMap.put("/admin/monitor/**", "perms[monitor:monitor]");
		filterChainDefinitionMap.put("/authenticated", "authc");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean(name = "kickout")
	public KickOutSessionControlFilter kickoutSessionControlFilter() {
		KickOutSessionControlFilter kickoutSessionControlFilter = new KickOutSessionControlFilter();
		return kickoutSessionControlFilter;
	}

	@Bean(name = "credentialsMatcher")
	public RetryLimitCredentialsMatcher credentialsMatcher() {
		RetryLimitCredentialsMatcher retryLimitCredentialsMatcher=new RetryLimitCredentialsMatcher();
		return retryLimitCredentialsMatcher;
	}
	@Bean(name="frameUserRealm")
	public FrameUserRealm frameUserRealm() {
		FrameUserRealm frameUserRealm=	new FrameUserRealm();
		frameUserRealm.setCredentialsMatcher(credentialsMatcher());
		return frameUserRealm;
	}

	/**
	 * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
	 * @return
	 */
	@Bean(name = "securityManager")
	public WebSecurityManager defaultWebSecurityManager() {
		ShiroWebSecurityManager wsm = new ShiroWebSecurityManager();
		wsm.setRealm(frameUserRealm());
		wsm.setSessionManager(sessionManager());
		wsm.setRememberMeManager(rememberMeManager());
		SecurityUtils.setSecurityManager(wsm);
		return wsm;
	}
	@Bean(name = "sessionIdCookie")
	public Cookie sessionIdCookie() {
        Cookie cookie = new SimpleCookie(sessionIdName);
        cookie.setHttpOnly(true);
		return cookie;
	}
	@Bean(name = "rememberMeManager")
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
		//rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		rememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
		return rememberMeManager;
	}
	@Bean(name = "onlineSessionFactory")
	public SessionFactory onlineSessionFactory() {
		SessionFactory sessionFactory = new OnlineSessionFactory();
		return sessionFactory;
	}
	@Bean
	public SessionManager sessionManager() {
		ShiroSessionManager sessionManager = new ShiroSessionManager();
		sessionManager.setGlobalSessionTimeout(ShiroSessionManager.DEFAULT_GLOBAL_SESSION_TIMEOUT);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionFactory(onlineSessionFactory());
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionDAO(sessionDAO());
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(sessionIdCookie());
		Collection<SessionListener> listeners=Lists.newArrayList();
		listeners.add(new ShiroSessionListener());
		sessionManager.setSessionListeners(listeners);
		return sessionManager;
	}
	/**
	 * 自定义sessionListener
	 * @return
	 */
	@Bean(name="sessionListener")
	public SessionListener shiroSessionListener(){
		ShiroSessionListener shiroSessionListener=new ShiroSessionListener();
		return shiroSessionListener;
	}
	/**
	 * 自定义sessionDAO
	 * @return
	 */
	@Bean(name = "sessionDAO")
	public CachingSessionDAO sessionDAO() {
		ShiroSessionDAO sessionDAO=new ShiroSessionDAO();
		return sessionDAO;
	}

	/**
     * 在方法中 注入 securityManager,进行代理控制
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean() {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(new Object[]{defaultWebSecurityManager()});
        return bean;
    }

	/**
	 * 	LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
	 * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
	 * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
	 * 协助shiro初始化, 负责调用shiro的init与destory
	 */
	@Bean
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	/**
	 *
    * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
	 * @return
	 */
	@Bean()
	@ConditionalOnMissingBean
   public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
       DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
       daap.setProxyTargetClass(true);
       return daap;
   }
   @Bean()
   public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
	 AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
	 return sourceAdvisor;
   }

}
